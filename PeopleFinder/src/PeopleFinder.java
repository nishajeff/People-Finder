

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PeopleFinder
 */
@WebServlet("/PeopleFinder")
public class PeopleFinder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private String message; 
	 Connection conn=null;
	 ResultSet res=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PeopleFinder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
String url2="";
		
		// get parameters from the request
		String Name=request.getParameter("Name");
		
		// store data in User object and save User object in db
		 try{
			  message="";  
			  String url= "jdbc:oracle:thin:testuser/password@localhost"; 
			  Class.forName("oracle.jdbc.driver.OracleDriver");
	        //properties for creating connection to Oracle database
	        Properties props = new Properties();
	        props.setProperty("user", "testdb");
	        props.setProperty("password", "password");
	        conn = DriverManager.getConnection(url,props);
	        //creating connection to Oracle database using JDBC              
	        Statement s=conn.createStatement();
	        message+="<div align=\"center\"><table style=\"border:2px solid black\">";
            message+="<th style=\" background-color:gray;border:2px solid black\">Title</th><th style=\" background-color:gray;border:2px solid black\">FirstName</th><th style=\" background-color:gray;border:2px solid black\">LastName</th><th style=\" background-color:gray;border:2px solid black\">Street</th><th style=\" background-color:gray;border:2px solid black\">Zip</th><th style=\" background-color:gray;border:2px solid black\">Email</th><th style=\" background-color:gray;border:2px solid black\">Position</th><th style=\" background-color:gray;border:2px solid black\">Company</th><th style=\" background-color:gray;border:2px solid black\">City</th><th style=\" background-color:gray;border:2px solid black\">State</th>";
	        String query1="select c.fullname,c.title,c.fname,c.lname,c.street,c.zip,c.email,c.position,t.company_name,b.city_name,s.state_name from customer_bonus c , company_bonus t, cities b, states s where c.company_id=t.company_id and c.city_id=b.city_id and c.state_id= s.state_id and UPPER(c.lname)= UPPER('"+Name+"')";
	        //System.out.println(query1);
	        res=s.executeQuery(query1);
	        if(res.next()){
		        
		        res=s.executeQuery(query1);
	            while (res.next()){
		        	 message+="<tr ><td style=\" background-color:white;border:2px solid black\">"+ res.getString("title")+              
		             		   "</td><td style=\" background-color:white;border:2px solid black\">"+res.getString("fname")+
		             		   "</td><td style=\"background-color:white;border:2px solid black\">" +res.getString("lname")+
		             		    "</td><td style=\"background-color:white;border:2px solid black\">" +res.getString("street")+
		             		    "</td><td style=\"background-color:white;border:2px solid black\">" +res.getInt("zip")+
		             		    "</td><td style=\"background-color:white;border:2px solid black\">" +res.getString("email")+
		             		    "</td><td style=\"background-color:white;border:2px solid black\">" +res.getString("position")+
		             		    "</td><td style=\"background-color:white;border:2px solid black\">" +res.getString("company_name")+
		             		    "</td><td style=\"background-color:white;border:2px solid black\">" +res.getString("city_name")+
		             		    "</td><td style=\"background-color:white;border:2px solid black\">" +res.getString("state_name")+
		             		  "</td></tr>" ;  
		        }
	        }
	        else{
	        	
		        	String query2="select cp.fullname,cp.title,cp.fname,cp.lname,cp.street,cp.zip,cp.email,cp.position,cp.company_name,cp.city_name,cp.state_name "+
		        			"from (select c.fullname,c.title,c.fname,c.lname,c.street,c.zip,c.email,c.position,t.company_name,b.city_name,s.state_name "+
		        					"from customer_bonus c , company_bonus t, cities b, states s "+
		        					"where c.company_id=t.company_id and c.city_id=b.city_id and c.state_id= s.state_id) cp "+
		        					"where upper(cp.lname) like upper('"+Name+"%')  or upper(cp.company_name) like upper('%"+Name+"%')";
		        	res=s.executeQuery(query2);
		        	 while (res.next()){
			        	 message+="<tr ><td style=\" background-color:white;border:2px solid black\">"+ res.getString("title")+              
			             		   "</td><td style=\" background-color:white;border:2px solid black\">"+res.getString("fname")+
			             		   "</td><td style=\"background-color:white;border:2px solid black\">" +res.getString("lname")+
			             		    "</td><td style=\"background-color:white;border:2px solid black\">" +res.getString("street")+
			             		    "</td><td style=\"background-color:white;border:2px solid black\">" +res.getInt("zip")+
			             		    "</td><td style=\"background-color:white;border:2px solid black\">" +res.getString("email")+
			             		    "</td><td style=\"background-color:white;border:2px solid black\">" +res.getString("position")+
			             		    "</td><td style=\"background-color:white;border:2px solid black\">" +res.getString("company_name")+
			             		    "</td><td style=\"background-color:white;border:2px solid black\">" +res.getString("city_name")+
			             		    "</td><td style=\"background-color:white;border:2px solid black\">" +res.getString("state_name")+
			             		  "</td></tr>" ;  
			        }
	        	
	        }
		// set User object in request object and set URL
		request.setAttribute("message", message);
		url2="/output.jsp";
		getServletContext().getRequestDispatcher(url2).forward(request, response);
		}catch(Exception e){
			System.out.println(e.getMessage());

		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}

}
