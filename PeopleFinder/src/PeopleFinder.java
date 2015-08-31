

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CustomerBonus;
import model.DBUtil;


/**
 * Servlet implementation class PeopleFinder
 */
@WebServlet("/PeopleFinder")
public class PeopleFinder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private String message; 
	 
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

		
		// get parameters from the request
		String Name=request.getParameter("Name");
		
		// store data in User object and save User object in db
		 try{
			  message="";  
			  
	        message+="<div align=\"center\"><table style=\"border:2px solid black\">";
            message+="<th style=\" background-color:gray;border:2px solid black\">Title</th><th style=\" background-color:gray;border:2px solid black\">FirstName</th><th style=\" background-color:gray;border:2px solid black\">LastName</th><th style=\" background-color:gray;border:2px solid black\">Street</th><th style=\" background-color:gray;border:2px solid black\">Zip</th><th style=\" background-color:gray;border:2px solid black\">Email</th><th style=\" background-color:gray;border:2px solid black\">Position</th><th style=\" background-color:gray;border:2px solid black\">Company</th><th style=\" background-color:gray;border:2px solid black\">City</th><th style=\" background-color:gray;border:2px solid black\">State</th>";
	       
            EntityManager em=DBUtil.getEmFactory().createEntityManager();
			String q="select c from CustomerBonus c where c.lname='"+Name+"'";
			TypedQuery<CustomerBonus>bq =em.createQuery(q,CustomerBonus.class);
			List<CustomerBonus> list=bq.getResultList();
			if(!(list==null || list.isEmpty())){
			for(CustomerBonus temp:list){
		        	 message+="<tr ><td style=\" background-color:white;border:2px solid black\">"+ temp.getTitle()+              
		             		   "</td><td style=\" background-color:white;border:2px solid black\">"+temp.getFname()+
		             		   "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getLname()+
		             		    "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getStreet()+
		             		    "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getZip()+
		             		    "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getEmail()+
		             		    "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getPosition()+
		             		    "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getCompanyBonus().getCompanyName()+
		             		    "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getCity().getCityName()+
		             		    "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getState().getStateName()+
		             		  "</td></tr>" ;  
		        }
	        }
	        else{
	        	q="select c from CustomerBonus c where upper(c.lname) like upper('"+Name+"%')  or upper(c.companyBonus.companyName) like upper('%"+Name+"%')";
	        	
				TypedQuery<CustomerBonus>bq1 =em.createQuery(q,CustomerBonus.class);
				List<CustomerBonus> list1=bq1.getResultList();
	        	
				for(CustomerBonus temp:list1){
			        	 message+="<tr ><td style=\" background-color:white;border:2px solid black\">"+temp.getTitle() +              
			             		   "</td><td style=\" background-color:white;border:2px solid black\">"+temp.getFname()+
			             		   "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getLname()+
			             		    "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getStreet()+
			             		    "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getZip()+
			             		    "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getEmail()+
			             		    "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getPosition()+
			             		    "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getCompanyBonus().getCompanyName()+
			             		    "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getCity().getCityName()+
			             		    "</td><td style=\"background-color:white;border:2px solid black\">" +temp.getState().getStateName()+
			             		  "</td></tr>" ;  
			        }
	        	
	        }
		// set User object in request object and set URL
		request.setAttribute("message", message);		
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
		}catch(Exception e){
			System.out.println(e.getMessage());

		}
	
	}

}
