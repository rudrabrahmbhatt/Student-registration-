package temp;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;//bee jo anu insert krelo tne record bi dekhadu

@WebServlet("/detail_demo")
public class registration extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws 
	ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter out= res.getWriter();
		
		String enn=req.getParameter("en");
		String username=req.getParameter("un");
		String passwd=req.getParameter("passwd");
		String gender=req.getParameter("gender");
		String number=req.getParameter("pn");
		String email= req.getParameter("mail");
		String address = req.getParameter("add");
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3310/wad_jdbc","root","02022020");
			stmt = con.prepareStatement("INSERT INTO Registration (Enroll,Name,password,gender,phone,mail,address) VALUES(?,?,?,?,?,?,?)");
			  
			  stmt.setString(1, enn); 
			  stmt.setString(2, username);
			  stmt.setString(3, passwd); 
			  stmt.setString(4, gender);
			  stmt.setString(6, email);
			  stmt.setString(5, number);
			  stmt.setString(7, address);
			  stmt.executeUpdate();
		out.println("DONE!!\n\n <h2><b>Successfully Registered!!</b></h2> ");
		
			  } catch(Exception e) { out.println("Error : "+e); }
		}
		
		
	}
	
	
	
