package temp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Display")
public class display extends HttpServlet {
	Connection con =null;
	Statement stmt = null;
	
	public void doGet(HttpServletRequest req , HttpServletResponse res  )
	throws ServletException, IOException
	{
		String query = "select * from Registration";
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String Url = "jdbc:mysql://localhost:3310/wad_jdbc";
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(Url,"root","02022020");
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmt = rs.getMetaData();
			
			out.println("<div><u><b>STUDENT PROFILE</b></u></div><br><br>");
			out.println("<table border= \"1px\" style= \"width:600px; line-height:40px;\" >");
			
			out.println("<tr align= \"center\" ><th>"+rsmt.getColumnName(1)+"</th>"); 
			out.println("<th>"+rsmt.getColumnName(2)+"</th>"); 
			out.println("<th>"+rsmt.getColumnName(3)+"</th>"); 
			out.println("<th>"+rsmt.getColumnName(4)+"</th>");
			out.println("<th>"+rsmt.getColumnName(5)+"</th>"); 
			out.println("<th>"+rsmt.getColumnName(6)+"</th>");
			out.println("<th>"+rsmt.getColumnName(7)+"</th></tr>");

//
			
			while (rs.next()) 
			{
				out.println("<tr align= \"center\" ><td>"+rs.getString("enroll")+"</td>"); 
				out.println("<td>"+rs.getString("name")+"</td>"); 
				out.println("<td>"+rs.getString("password")+"</td>"); 
				out.println("<td>"+rs.getString("gender")+"</td>"); 
				out.println("<td>"+rs.getString("phone")+"</td>");
				out.println("<td>"+rs.getString("Mail")+"</td>");
				out.println("<td>"+rs.getString("address")+"</td></tr>"); 
				
            }	  
			out.println("</table>");
			 stmt.close();
             rs.close();
		}
		catch(Exception e)
        {
            System.out.println(e);
        }
		
	}
	
}