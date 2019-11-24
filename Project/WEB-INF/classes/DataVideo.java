import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import com.mongodb.AggregationOutput;


@WebServlet("/DataVideo")
public class DataVideo extends HttpServlet {

    static DBCollection myReviews;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        displayPage(request, response, pw);
    }

    protected void displayPage(HttpServletRequest request, HttpServletResponse response, PrintWriter pw)
            throws ServletException, IOException {

        //printing visualization chart for Video category
        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        pw.print("<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
        pw.print("<script src='./barchartforvideo.js'></script>");
		utility.printHtml("LeftNavigationBar.html");
        pw.print("<div class='col-sm-8 text-left'>");
        pw.print("<h1>Visualization for Video</h1>");
        pw.print("<div id='chart_div'></div>");
        pw.print("</div>");
		utility.printHtml("RightNavigationBarVideo.html");
        utility.printHtml("Footer.html");

    }


    

    

}
