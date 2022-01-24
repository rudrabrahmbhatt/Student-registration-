package temp;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/Update")
public class Update extends HttpServlet {
 public void doPost(HttpServletRequest request,HttpServletResponse response)
 throws ServletException,IOException
 {
  response.setContentType("text/html");
  PrintWriter out=response.getWriter();
  
  String name1=request.getParameter("name");
  out.println("Name.-"+name1+"<br>");
  
  
  String passwd=request.getParameter("O_passwd");
  out.println("Old Password-"+passwd+"<br>");
  
  String new_passwd=request.getParameter("N_passwd");
  out.println("New Password-"+new_passwd+"<br>");
  
 
 try
 {
  String Updateq="update registration set new_password=? where name=? AND password=? ";
  Scanner sc=new Scanner(System.in);
  Class.forName("com.mysql.jdbc.Driver");
  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3310/wad_jdbc","root","02022020");
  PreparedStatement UpdateData=con.prepareStatement(Updateq);
 //
  UpdateData.setString(1,new_passwd);
  UpdateData.setString(2,name1);
  UpdateData.setString(3,passwd);

  UpdateData.executeUpdate();
   
  
 }
 catch(Exception e)
 {
  System.out.println(e);
 }
 }

}
