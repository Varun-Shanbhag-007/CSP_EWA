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


@WebServlet("/DataSports")
public class DataSports extends HttpServlet {

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

        Utilities utility = new Utilities(request, pw);
       //Printing Visualization chart for sports
        utility.printHtml("Header.html");
        pw.print("<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
        pw.print("<script src='./barchartforsports.js'></script>");
		utility.printHtml("LeftNavigationBar.html");
        pw.print("<div class='col-sm-8 text-left'>");
        pw.print("<h1>Visualization for Sports</h1>");
        pw.print("<div id='chart_div'></div>");
        pw.print("</div>");
		utility.printHtml("RightNavigationBarSports.html");
        utility.printHtml("Footer.html");
        



    }


    

    

}
