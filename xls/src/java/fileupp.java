/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload; 
import javax.servlet.*;


import org.apache.poi.xssf.usermodel.XSSFRow; 
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.sql.*;


@WebServlet("/fileupp")
public class fileupp extends HttpServlet {

 //  private static final long serialVersionUID = 1L;
	
	
	public static Connection con;   
	
	public static PreparedStatement ps;  
	
	
	public static FileInputStream input;    
	
	
	
	
	public static int id;   
	
	public static String name;    
	 
	public static String password;
	
	public  static String email;
	
	public static String sql;
	
	
	public static int result;  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
      
        if (ServletFileUpload.isMultipartContent(request)) {      

			try { 
                           List<FileItem> multipart = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);     
				  

				 for (FileItem item : multipart) {      

					if (!item.isFormField()) {      

						String nam = new File(item.getName()).getName();        

						item.write(new File("E://nikki" + File.separator + nam));       

					}   
					
					

				}    
				 
				 pw.println("File successfull upload");     

			} catch (Exception Ex) {    

				System.out.println("output" + Ex); 
			}  
			
	 }     
		
	  else{      
	
		    pw.println("Please insert files ");   
		   
		}             
     
		
		readFileReader();        
	
		if(result>0)       
		{  
			
			
		    RequestDispatcher rd=request.getRequestDispatcher("fileview"); 
		    
	        rd.forward(request, response);          
	} 
	
		
		else{      
			
			
			 RequestDispatcher rd=request.getRequestDispatcher("fileup.html"); 
			    
		        rd.include(request, response);    
			
		}        
		
		
	 	
	}

	public void readFileReader() {      
		// TODO Auto-generated method stub 
		
		
		
		 try{              
			

	     	 Class.forName("com.mysql.jdbc.Driver");               

			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");     
			 
			 input=new FileInputStream("E://nikki//dat.xlsx");               
			 
			 System.out.println("hello1");     
			 
			 XSSFWorkbook workbook=new XSSFWorkbook(input);            
		    
			 XSSFSheet sheet=workbook.getSheetAt(0);        
			 
			 XSSFRow row;  
			 
			 System.out.println("hello2");   
			 
			 
			 for(int i=1; i<=sheet.getLastRowNum(); i++)    
			 { 
				 
				  row=sheet.getRow(i);           
				 
				 
				 id=(int)row.getCell(0).getNumericCellValue();                 
				 
				name=row.getCell(1).getStringCellValue();   
				
				password=row.getCell(2).getStringCellValue();     
				
				email=row.getCell(3).getStringCellValue();     
				
				sql="insert into student(id,name,password,email) values(?,?,?,?)";           
				
				  ps=con.prepareStatement(sql);            
				   
				  ps.setInt(1, id);              
				  ps.setString(2, name);      
				  ps.setString(3, password);               
				  ps.setString(4, email);          
		    		  
				  
				 result =ps.executeUpdate();                
				  
		} 
			 
			 System.out.println("Query will be updateded");       
			 
			
		    if(result>0)          
			 { 
				 
			
				 System.out.println("Successfull register"+" "+result);      
				 
			}        
			 
			
			  
			 
			 
			 System.out.println("************************************************");    
		
			 
			 
		
		
			 
			
			 con.close();              
			 
			 input.close();    
			 
			// pw.close();  
			
			 
			  System.out.println("***************************************");   

				System.out.println("Success import excel to mysql table");      
			 
		  }      
		
		
		catch(Exception e)     
		{
			
		      System.out.println("out"+e);                  
			
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


    