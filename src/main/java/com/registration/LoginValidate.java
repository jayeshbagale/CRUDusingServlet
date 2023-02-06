package com.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/loginvalidate")
public class LoginValidate extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String Ename=req.getParameter("name");
		String password=req.getParameter("password");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/formdetails","root","207@Pgcks");
			PreparedStatement ps=con.prepareStatement("select * from formdetails.registration where Ename=? and password=?");
			ps.setString(1, Ename);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.isBeforeFirst())
			{
				RequestDispatcher rd=req.getRequestDispatcher("Welcome.html");
				rd.forward(req, resp);
			}
			else
			{
				PrintWriter pw=resp.getWriter();
				pw.write("User Is Not Found \n");
				pw.write("Need to Register");
				RequestDispatcher rd=req.getRequestDispatcher("NewFile.html");
				rd.include(req, resp);
				resp.setContentType("text/html");
			}
		}
		catch(ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
						e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}