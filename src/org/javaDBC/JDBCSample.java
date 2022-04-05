package org.javaDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCSample {
	public static Connection con;
	public static void main(String[] args) throws SQLException {
		try {
			//1.load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.connect with database
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "admin");
			//3.write SQL query
			String sql="select * from employees";
			//4.prepare the statement
			PreparedStatement ps = con.prepareStatement(sql);
			//5.execute the query
			ResultSet rs = ps.executeQuery();
			//6.To iterate the result
			System.out.println("EMPLOYEE_ID\tFIRST_NAME\tLAST_NAME");
			while(rs.next()) {
				//get column data
				String s="";
				int empId = rs.getInt("employee_id");
				String firstName = rs.getString("first_name");
				if(firstName.length()<8) s="\t";
				String lastName = rs.getString("last_name");
				System.out.println(empId+"\t\t"+firstName+"\t"+s+lastName);
			}
			//handling two exceptions
		} catch (ClassNotFoundException | SQLException e){e.printStackTrace();}
		//closing database connection
		finally {con.close();}
	}
}
