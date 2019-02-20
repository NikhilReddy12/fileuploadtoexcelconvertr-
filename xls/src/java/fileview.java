/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.annotation.WebServlet; 
import javax.servlet.ServletException;
   
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  



@WebServlet("/fileview")
public class fileview extends HttpServlet {
//private static final long serialVersionUID = 1L;
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
         PrintWriter pw = response.getWriter();
        try{ 
				  

		     Class.forName("com.mysql.jdbc.Driver");                          

			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");   
				
			 	Statement stmt=con.createStatement();  
			 	
			 	
			 	ResultSet rs=stmt.executeQuery("select * from student");  
			 	
			 	
			     pw.println("<h1>Students List</h1>");       
			        
				 
				 pw.print("<html>");                               
				  
				 pw.print("<body>");            
				 
				 pw.print("<form>");                 
				 
			     pw.print("<table border='1' width='100%'");                   
				  
   pw.print("<tr><th>ID</th><th>Name</th><th>Password</th><th>Email</th></tr>");  
				  
				 
				 while(rs.next())                   
				 {     
					 
					
					//System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));  
		   
 pw.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr>");  
	                 
						
		 }           
				 
				                  
	   			 pw.print("</table>");                           
				 
				 pw.print("</form>");            
				 pw.print("</body>");   
				 pw.print("</html>");              
				 
				 
				
				con.close();  
				
				rs.close();
				
				
				pw.close();
				 
				
				
				
				
			}
			
			catch(Exception e)
			{
				
				
				System.out.println(e);    
			}
			
			
			
			
			
			
			
			
			
	}
 // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}


