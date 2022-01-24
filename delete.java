package temp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Delete")
public class delete extends HttpServlet {

	Connection con = null;
	Statement stmt = null;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException 
	{
		
		res.setContentType("text/html");
		
		PrintWriter out= res.getWriter();
		
		//collecting values from html value
		
		String Cname= req.getParameter("name");
		String Cemail= req.getParameter("mail");
		String Cpassword= req.getParameter("passwd");
		
		
		String driverUrl = "jdbc:mysql://localhost:3310/wad_jdbc";
		
		try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				con= DriverManager.getConnection(driverUrl,"root","02022020");
				String SelectQuery = "select name , Mail , password from  registration where name = '"+ Cname +"' and Mail = '"+Cemail+"' and password = '"+Cpassword+"'; ";
				
				Statement  st = con.createStatement(); //statement created
				
				ResultSet rs =st.executeQuery(SelectQuery);
				//out.println(rs.next());
				if(rs.next()==true)
				{
					
					if((rs.getString(1).equals(Cname)) && (rs.getString(2)).equals(Cemail) && (rs.getString(3)).equals(Cpassword))
					{
						String DeletedataQuery = "Delete from registration where name = '"+Cname+"' and Mail = '"+Cemail+"' and password = '"+Cpassword+"'; ";
						PreparedStatement DeleteData = con.prepareStatement(DeletedataQuery);
						DeleteData.executeUpdate();
						out.println("\n======================================");
						out.println("Successfully Data Deleted : !!");			
						out.println("======================================");
					}
					
				}
				else if(rs.next()==false)
				{
					
					out.println("\nEnter Valid Data !!");
					RequestDispatcher rd = req.getRequestDispatcher("Delete.html");
					rd.include(req,res);
				}
				
				con.close();
				
			}
		
		catch(Exception e) 
			{
				out.println(e);
			}

	}
	
	public void destroy(){}
	
}
	

