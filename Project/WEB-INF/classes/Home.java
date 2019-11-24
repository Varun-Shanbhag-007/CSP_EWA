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

@WebServlet("/Home")

public class Home extends HttpServlet {

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
        // This runs the python file and get's the recommendation for top recommendation
        Runtime.getRuntime().exec("wscript " + TOMCAT_HOME + "\\webapps\\Project\\getrecommendation.vbs");
        //Read recommendations for video category from video json
        Reader readerv = new FileReader(TOMCAT_HOME + "\\webapps\\Project\\Data\\video_trends.json");
        TrendProduct tpv = new TrendProduct();
         //Read recommendations for books category from book json
        Reader readerb = new FileReader(TOMCAT_HOME + "\\webapps\\Project\\Data\\books_trends.json");
        TrendProduct tpb = new TrendProduct();
            //Read recommendations for books category from book json
        Reader readers = new FileReader(TOMCAT_HOME + "\\webapps\\Project\\Data\\sports_trends.json");
        TrendProduct tps = new TrendProduct();
           //Read recommendations for musci category from music json
        Reader readerm = new FileReader(TOMCAT_HOME + "\\webapps\\Project\\Data\\music_trends.json");
        TrendProduct tpm = new TrendProduct();


        //Reading and parsing the json file to print it to HTML format

        try {

            Object jsonObj = parser.parse(readerv);
            JSONArray arr = (JSONArray) jsonObj;
            arr.forEach(item -> {
                System.out.println(item.toString());
                System.out.println(item.toString());
            });

            for (Object o : arr) {
                JSONObject ca = (JSONObject) o;
                String product_str = (String) ca.get("product");
                String img_url_str = (String) ca.get("img_url");
                String trend_str = (String) ca.get("trend");

                tpv.setTrend(trend_str);
                tpv.setImgurl(img_url_str);
                tpv.setpUrl(product_str);

                break;
            }

            Object jsonObjs = parser.parse(readers);
            JSONArray arrs = (JSONArray) jsonObjs;
            arrs.forEach(item -> {
                System.out.println(item.toString());
                System.out.println(item.toString());
            });

            for (Object o : arrs) {
                JSONObject ca = (JSONObject) o;
                String product_str = (String) ca.get("product");
                String img_url_str = (String) ca.get("img_url");
                String trend_str = (String) ca.get("trend");

                tps.setTrend(trend_str);
                tps.setImgurl(img_url_str);
                tps.setpUrl(product_str);

                break;
            }

            Object jsonObjb = parser.parse(readerb);
            JSONArray arrb = (JSONArray) jsonObjb;
            arrb.forEach(item -> {
                System.out.println(item.toString());
                System.out.println(item.toString());
            });

            for (Object o : arrb) {
                JSONObject ca = (JSONObject) o;
                String product_str = (String) ca.get("product");
                String img_url_str = (String) ca.get("img_url");
                String trend_str = (String) ca.get("trend");

                tpb.setTrend(trend_str);
                tpb.setImgurl(img_url_str);
                tpb.setpUrl(product_str);

                break;
            }

            Object jsonObjm = parser.parse(readerm);
            JSONArray arrm = (JSONArray) jsonObjm;
            arrm.forEach(item -> {
                System.out.println(item.toString());
                System.out.println(item.toString());
            });

            for (Object o : arrm) {
                JSONObject ca = (JSONObject) o;
                String product_str = (String) ca.get("product");
                String img_url_str = (String) ca.get("img_url");
                String trend_str = (String) ca.get("trend");

                tpm.setTrend(trend_str);
                tpm.setImgurl(img_url_str);
                tpm.setpUrl(product_str);

                break;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        utility.printHtml("Header.html");
        pw.print("<script src='./carouselbooks.js'></script>");
        utility.printHtml("LeftNavigationBar.html");
        JParser jp = new JParser();
        HashMap<String, String> prodRecmMap = new HashMap<String, String>();
        prodRecmMap = jp.readOutputFile();

        StringBuilder sb = new StringBuilder();

        pw.print("<div class='col-sm-8 text-left'>");
        pw.print("<h1>Welcome to Ignite</h1>");
        pw.print("<p>Our website will show you the highest rated products on amazon</p>");
        pw.print("<hr>");
        pw.print("<h3>Check out our top trending products</h3>");
        pw.print("<p>");

        pw.println("<div class='well col-sm-3'>");
        pw.print("<p><h4>" + tpv.getTrend() + "</h4>");
        pw.print("<a href='" + tpv.getpUrl() + "'>" + "<img src='" + tpv.getImgurl()
                + "' class='img-responsive' alt='ad1'></a>");
        pw.print("</div>");

        pw.println("<div class='well col-sm-3'>");
        pw.print("<p><h4>" + tps.getTrend() + "</h4>");
        pw.print("<a href='" + tps.getpUrl() + "'>" + "<img src='" + tps.getImgurl()
                + "' class='img-responsive' alt='ad1'></a>");
        pw.print("</div>");

        pw.println("<div class='well col-sm-3'>");
        pw.print("<p><h4>" + tpb.getTrend() + "</h4>");
        pw.print("<a href='" + tpb.getpUrl() + "'>" + "<img src='" + tpb.getImgurl()
                + "' class='img-responsive' alt='ad1'></a>");
        pw.print("</div>");

        pw.println("<div class='well col-sm-3'>");
        pw.print("<p><h4>" + tpm.getTrend() + "</h4>");
        pw.print("<a href='" + tpm.getpUrl() + "'>" + "<img src='" + tpm.getImgurl()
                + "' class='img-responsive' alt='ad1'></a>");
        pw.print("</div>");

        pw.print("</p></div>");

        utility.printHtml("RightNavigationBarVideo.html");
        utility.printHtml("Footer.html");

    }
}
