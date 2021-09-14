package Pack;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Reg
 */
public class Reg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String dburl="jdbc:mysql://localhost:3306/user";
	private String dbuname="dbuname";
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
			
			String uname=request.getParameter("uname");
			String address=request.getParameter("address");
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			
			PreparedStatement ps=conn.prepareStatement("insert into forgot123 values(?,?,?,?)");
			ps.setString(1,uname);
			ps.setString(2,address);
			ps.setString(3,email);
			ps.setString(4,password);
			
			int i=ps.executeUpdate();
			if(i>0) {
				response.sendRedirect("welcome.jsp");
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
