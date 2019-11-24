import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.AggregationOutput;
import java.util.*;
                	
public class MongoDBDataStoreUtilities
{
static DBCollection myReviews;
static DBCollection myProject;
public static DBCollection getConnection()
{
MongoClient mongo;
mongo = new MongoClient("localhost", 27017);

DB db = mongo.getDB("CustomerReviews");

 myReviews= db.getCollection("myReviews");
 myProject= db.getCollection("productsVisited");
// return myReviews; 
return myProject;
}
//Our db is called productsVisited
//Insert the records into the myProject collection
public static String insertProjectReview(String username, String productname,String fivestar)
{
	try
		{		
			getConnection();
			BasicDBObject doc = new BasicDBObject("title", "myProject").
				append("userName", username).
				append("productName", productname).
				append("userrating",fivestar);
				myProject.insert(doc);
			return "Successfull";
		}
		catch(Exception e)
		{
			System.out.println("not successful"+e);
			return "UnSuccessfull";
		}	
		
}


	




  

	
}	