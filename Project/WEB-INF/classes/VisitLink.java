import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.io.Reader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.servlet.http.HttpSession;

import java.util.List;

@WebServlet("/VisitLink")

public class VisitLink extends HttpServlet  {

	//Stores the data into the database as soon as user hits the visit buttion to improve recommedation for him.

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
        HttpSession session = request.getSession(true);
		utility.printHtml("Header.html");
		pw.print("<script src='./carouselbooks.js'></script>");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div class='col-sm-8 text-left'>");
        pw.print("<h1>Just a Second</h1>");
        pw.print("</div>");
		utility.printHtml("RightNavigationBarVideo.html");
        utility.printHtml("Footer.html");

        String product_link = request.getParameter("link");
        String title = request.getParameter("title");
        String img_url = request.getParameter("img_url");
        String username = session.getAttribute("username").toString();
        String fivestar = request.getParameter("fivestar").toString();
        System.out.println(product_link+""+title+""+img_url+""+username+""+fivestar);
        response.sendRedirect(product_link);

        MongoDBDataStoreUtilities.insertProjectReview(username, title,fivestar);

	}
}


