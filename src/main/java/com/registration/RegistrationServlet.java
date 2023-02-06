package com.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String id=req.getParameter("id");
		String Ename=req.getParameter("name");
		String phoneNo=req.getParameter("phoneNo");
		String yop=req.getParameter("yop");
		String degree=req.getParameter("degree");
		String stream=req.getParameter("stream");
		String password=req.getParameter("password");
		int id1=Integer.parseInt(id);
		long phoneNo1=Long.parseLong(phoneNo);
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/formdetails","root","207@Pgcks");
			PreparedStatement ps=con.prepareStatement("insert into formdetails.registration(id,Ename,phoneNo,yop,degree,stream,password) values(?,?,?,?,?,?,?)");
			ps.setInt(1, id1);
			ps.setString(2, Ename);
			ps.setLong(3, phoneNo1);
			ps.setString(4, yop);
			ps.setString(5, degree);
			ps.setString(6, stream);
			ps.setString(7, password);
			ps.execute();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}