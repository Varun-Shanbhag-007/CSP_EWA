import java.util.HashMap;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.io.Reader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class JParser{

    

    public HashMap<String,TopProduct> getProducts(){
        HashMap<String,TopProduct> ProductsMap = new HashMap<>();

        JSONParser parser = new JSONParser();
		String TOMCAT_HOME = System.getProperty("catalina.home");
        
        String[] jsons = {"music_proc","video_proc","books_proc","sports_proc"};
        int count = 0;
        
		try{

            while(count !=4){
            Reader reader = new FileReader(TOMCAT_HOME+"\\webapps\\Project\\Data\\"+jsons[count]+".json");
		    Object jsonObj = parser.parse(reader);
		    JSONArray arr = (JSONArray) jsonObj;
			
		

                for (Object o : arr) {
                    JSONObject ca = (JSONObject) o;
                    String product_str = (String) ca.get("product");
                    Long fivestar_str = (Long) ca.get("fiveStar");
                    Long onestar_str = (Long) ca.get("oneStar");
                    String img_url_str = (String) ca.get("img_url");
                    String title_str = (String)ca.get("title");

                    TopProduct tp = new TopProduct();
                    tp.setTitle(title_str);
                    tp.setImgurl(img_url_str);
                    tp.setFstar(fivestar_str.toString());
                    tp.setOstar(onestar_str.toString());
                    tp.setpUrl(product_str);
                    
                    ProductsMap.put(title_str.trim(), tp);
               }

               count ++;
            }
            }catch(Exception e)
            {
                System.out.println(e);
            }

        return ProductsMap;

    }

    public HashMap<String,String> readOutputFile(){

		String TOMCAT_HOME = System.getProperty("catalina.home");
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
		HashMap<String,String> prodRecmMap = new HashMap<String,String>();
		try {

            br = new BufferedReader(new FileReader(new File(TOMCAT_HOME+"\\webapps\\Project\\output.csv")));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] prod_recm = line.split(cvsSplitBy,2);
				prodRecmMap.put(prod_recm[0],prod_recm[1]);
            }
			
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
		
		return prodRecmMap;
	}

}