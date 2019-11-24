import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MusicTop")

public class MusicTop extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Parsing and printing Visualization for Music Category
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		pw.print("<script src='./carouselbooks.js'></script>");
		utility.printHtml("LeftNavigationBar.html");
        pw.print("<div class='col-sm-8 text-left'>");
        pw.print("<h1>All Top Music Products</h1>");
        pw.print("</div>");
		utility.printHtml("RightNavigationBar.html");
        utility.printHtml("Footer.html");
        
		
	}
}
