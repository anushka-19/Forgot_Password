package Pack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Forgot
 */
public class Forgot extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String dburl="jdbc:mysql://localhost:3306/user";
	private String dbuname="dbname";
	private String dbpassword="dbpassword";
	private String dbdriver="com.mysql.jdbc.Driver";
	
	public void loadDriver(String dbDriver) 
	{
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		loadDriver(dbdriver);
		Connection con=null;
		try {
			con=DriverManager.getConnection(dburl, dbuname, dbpassword);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		try {
			Connection conn=getConnection();
			
			String email=request.getParameter("email");
			String pass=request.getParameter("pass");
			
			PreparedStatement ps=conn.prepareStatement("update forgot123 set password=? where email=?");
			ps.setString(2,email);
			ps.setString(1, pass);
			
			int i=ps.executeUpdate();
			
			if(i>0) {
				response.sendRedirect("Update.jsp");
			}
			else {
				response.sendRedirect("error.jsp");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
