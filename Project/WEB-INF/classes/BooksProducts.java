import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.io.Reader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.List;

@WebServlet("/BooksProducts")

public class BooksProducts extends HttpServlet {

	// Display all the products for books caterogry

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		Utilities utility = new Utilities(request, pw);
		// Code to parse data from JSON file which will be written by our python script

		if (!utility.isLoggedin()) {
			HttpSession session = request.getSession(true);
			session.setAttribute("login_msg", "Please Login");
			response.sendRedirect("Login");
			return;
		}
		JSONParser parser = new JSONParser();
		String TOMCAT_HOME = System.getProperty("catalina.home");

		// Run the script to get/generate the recommendations dynamically 
		Runtime.getRuntime().exec("wscript "+TOMCAT_HOME + "\\webapps\\Project\\getrecommendation.vbs");
		Reader reader = new FileReader(TOMCAT_HOME + "\\webapps\\Project\\Data\\books_proc.json");
		ArrayList<String> product = new ArrayList<String>();
		ArrayList<Long> fivestar = new ArrayList<Long>();
		ArrayList<Long> onestar = new ArrayList<Long>();
		ArrayList<String> img_url = new ArrayList<String>();
		ArrayList<String> title = new ArrayList<String>();
		try {
			//parsing JSON array and retrieving the fields
			Object jsonObj = parser.parse(reader);
			JSONArray arr = (JSONArray) jsonObj;
			arr.forEach(item -> {
				System.out.println(item.toString());
				System.out.println(item.toString());
				
			});

			for (Object o : arr) {
				JSONObject ca = (JSONObject) o;
				String product_str = (String) ca.get("product");
				Long fivestar_str = (Long) ca.get("fiveStar");
				Long onestar_str = (Long) ca.get("oneStar");
				String img_url_str = (String) ca.get("img_url");
				String title_str = (String) ca.get("title");

				product.add(product_str);
				fivestar.add(fivestar_str);
				onestar.add(onestar_str);
				img_url.add(img_url_str);
				title.add(title_str);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println(title);

		utility.printHtml("Header.html");
		pw.print("<script src='./carouselbooks.js'></script>");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div class='col-sm-8 text-left'>");

		JParser jp = new JParser();
		HashMap<String, String> prodRecmMap = new HashMap<String, String>();
		prodRecmMap = jp.readOutputFile();

		if (prodRecmMap.containsKey(utility.username())) {
			pw.print("<h1>Our Recommendation for you,"+utility.username().toString()+"</h1>");
			String products = prodRecmMap.get(utility.username());
			products = products.replace("[", "");
			products = products.replace("]", "");
			products = products.replace("\"", " ");
			ArrayList<String> productsList = new ArrayList<String>(Arrays.asList(products.split(",")));

			for (String recP : productsList) {
				//convert array for getting productname and printing
				TopProduct tp = jp.getProducts().get(recP.replace("'", "").trim());
		

				pw.println("<div class='container2'>");
				pw.print("<div>");
				pw.print("<img src='" + tp.getImgurl() + "' class='iconDetails'>");
				pw.print("</div>");
				pw.print("<div style='margin-left:60px;'>");
				pw.print("<form action='VisitLink'>");
				pw.print("<b><h4>" + tp.getTitle() + "</h4></b>");
				pw.print("<div style='font-size:.6em'><h6>Total Reviews :" + (Integer.parseInt(tp.getFstar()) + Integer.parseInt(tp.getOstar())) + ""
						+ "</h6></div>");
				pw.print("<input type='hidden' name='title' value='" + tp.getTitle() + "'>");
				pw.print("<input type='hidden' name='img_url' value='" + tp.getImgurl() + "'>");
				pw.print("<input type='hidden' name='link' value='" + tp.getpUrl() + "'>");
				pw.print("<input type='hidden' name='fivestar' value='" + tp.getFstar() + "'>");
				pw.print("<div style='font-size:.6em'><h6>5 star Reviews :" + tp.getFstar() + "</h6></div>");
				pw.print("<div style='font-size:.6em'><h6>1 star Reviews :" + tp.getOstar() + "</h6></div>");
				pw.print("<input type='submit' name='Visit' value='Visit' style='margin-left:40%'/>");
				pw.print("</div>");
				pw.print("</form>");
				pw.println("</div>");
				pw.print("<br/>");
				pw.print("<hr/>");
			}

		}
				//print all the top products 
		pw.print("<h1>All Top Book Products</h1>");

		for (int i = 0; i < product.size(); i++) {
			pw.println("<div class='container2'>");
			pw.print("<div>");
			pw.print("<img src='" + img_url.get(i) + "' class='iconDetails'>");
			pw.print("</div>");
			pw.print("<div style='margin-left:60px;'>");
			pw.print("<form action='VisitLink'>");
			pw.print("<b><h4>" + title.get(i) + "</h4></b>");
			pw.print("<div style='font-size:.6em'><h6>Total Reviews :" + (fivestar.get(i) + onestar.get(i)) + ""
					+ "</h6></div>");
			pw.print("<input type='hidden' name='title' value='" + title.get(i) + "'>");
			pw.print("<input type='hidden' name='img_url' value='" + img_url.get(i) + "'>");
			pw.print("<input type='hidden' name='link' value='" + product.get(i) + "'>");
			pw.print("<input type='hidden' name='fivestar' value='" + fivestar.get(i) + "'>");
			pw.print("<div style='font-size:.6em'><h6>5 star Reviews :" + fivestar.get(i) + "</h6></div>");
			pw.print("<div style='font-size:.6em'><h6>1 star Reviews :" + onestar.get(i) + "</h6></div>");
			pw.print("<input type='submit' name='Visit' value='Visit' style='margin-left:40%'/>");
			pw.print("</div>");
			pw.print("</form>");
			pw.println("</div>");
			pw.print("<br/>");
			pw.print("<hr/>");
		}
		pw.print("</div>");

		utility.printHtml("RightNavigationBar.html");
		utility.printHtml("Footer.html");

	}
}
