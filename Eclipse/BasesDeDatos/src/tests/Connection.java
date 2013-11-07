package tests;

import java.sql.*;

public class Connection {

	public static void main(String[] args){
		Statement st;
		ResultSet rs;
		java.sql.Connection con;
		String connectionUrl;
		String query;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connectionUrl = "jdbc:mysql://localhost/practica2?user=root&password=92031416126";
            con = DriverManager.getConnection(connectionUrl);
    		
    		query = "select * from authors";
    		
    		st = con.createStatement();
    		rs = st.executeQuery(query);
            while(rs.next())
            	System.out.println(rs.getString("au_lname"));
            
            connectionUrl = "jdbc:mysql://localhost/test?user=root&password=92031416126";
            
            con = DriverManager.getConnection(connectionUrl);
            
            //query = "create table testTable (nombre varchar(50), cedula int(13));";
            query = "drop table testtable;";
            
            st = con.createStatement();
            int n = st.executeUpdate(query);
            System.out.println("Filas "+n);
		}
		catch(SQLException e){
			System.out.println("SQL Exception");
		}
		catch(Exception e){
			System.out.println("Other Excpetion");
			e.printStackTrace();
		}
		
		//Statement
		//ResultSet
		//st.executeQuery()
		//st.executeUpdate()
		
	}
}
