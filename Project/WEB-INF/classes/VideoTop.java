import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VideoTop")

public class VideoTop extends HttpServlet {

	/* TV Page Displays all the TVs and their Information in Game Speed */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		Utilities utility = new Utilities(request,pw);

		//Reading JSON and printing visualization 
		
        utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
        pw.print("<div class='col-sm-8 text-left'>");
        pw.print("<h1>Visualization</h1>");
        utility.printHtml("carouselforproject.html");
        pw.print("</div>");
        
		utility.printHtml("RightNavigationBar.html");
        utility.printHtml("Footer.html");
        
		
	}
}

// Passing array from java to javascript
// <%
//     String[] jArray= new String[2];
//     jArray[0]="a";
//     jArray[1]="b";

//     StringBuilder sb = new StringBuilder();
//     for(int i=0;i<jArray.length;i++) 
//         sb.append(jArray[i]+",");
// %>

// <script type="text/javascript">
//     temp="<%=sb.toString()%>";
//     var array = new Array();
//     array = temp.split(',','<%=jArray.length%>');

//     alert("array: "+array);
// </script>

