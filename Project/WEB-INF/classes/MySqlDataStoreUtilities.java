import java.sql.*;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
                	
public class MySqlDataStoreUtilities extends HttpServlet
{
static Connection conn = null;
static HashMap <String,Integer> arr;
static HttpServletRequest request;

public static void getConnection()
{

	try
	{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project","root","root");							
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
}

//Connect to the database via Connection
public static Connection getConnection2()
{

	try
	{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project","root","root");							
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	return conn;
}

//Insert the user in database

public static void insertUser(String username,String password,String repassword,String usertype)
{
	try
	{	

		getConnection();
		String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype) "
		+ "VALUES (?,?,?,?);";	
				
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
		pst.setString(1,username);
		pst.setString(2,password);
		pst.setString(3,repassword);
		pst.setString(4,usertype);
		pst.execute();
	}
	catch(Exception e)
	{
	
	}
	
	
}

//Select the user for login check


public static HashMap<String,User> selectUser()
{	
	HashMap<String,User> hm=new HashMap<String,User>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  Registration";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("usertype"));
				hm.put(rs.getString("username"), user);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}
























	
}	