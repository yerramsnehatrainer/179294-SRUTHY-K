package BANKPROJECT;

import java.sql.*;
import java.util.Scanner;

public class HDFC extends newbank {

	public static void main(String args[]) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
		Statement stat = con.createStatement();

		HDFC obj = new HDFC();
		obj.DBCreation(con, stat);
		obj.create(con, stat);
		obj.display(con, stat);
	}
}

class newbank {
	void DBCreation(Connection con, Statement stat) throws SQLException
	{
		String sql = "create database Newbank1";
		stat.executeUpdate(sql);
		System.out.println("Database created successfully...");
	}
	void create(Connection con, Statement stat) throws SQLException {
		final String CREATE_TABLE = "CREATE TABLE customer_details (" + "ID INT auto_increment primary key,"
				+ "NAME VARCHAR(45) NOT NULL," + "AGE INT NOT NULL)";
		String sql = "insert into customer_details " + " ( NAME, AGE)" + " values (?, ?)";
		stat.execute("use Newbank1");
		PreparedStatement prep1 = con.prepareStatement(sql);
		stat.executeUpdate(CREATE_TABLE);

		try (Scanner input = new Scanner(System.in)) {
			int i;
			System.out.println("***************CUSTOMER DETAILS**************** ");
			System.out.println(" ");
			System.out.println("WELCOME TO HDFC CUSTOMER SERVICES............. ");
			System.out.println("Enter the no of customers that you have to Insert.......... ");
			int N = input.nextInt();
			for (i = 1; i <= N; i++) {

				System.out.println("ENTER NAME: ");
				String Name = input.next();

				System.out.print("Enter AGE:  ");
				int AGE = input.nextInt();

				prep1.setString(1, Name);//setting up the values
				prep1.setInt(2, AGE);
				prep1.addBatch();//// add the columns that have been entered
				prep1.executeUpdate();
				System.out.println("_______________________________");

			}
			System.out.println("NEW RECORDS INSERTED SUCCESSFULLY...............");
			System.out.println(" ");
			input.close();
		}
	}
	void display(Connection con, Statement stat) throws SQLException {
		ResultSet rs = stat.executeQuery("select * from customer_details");
		System.out.println("*****************CUSTOMER DETAILS******************");
		System.out.println("CUSTOMER_ID       NAME          AGE");
		System.out.println("_____________________________________");

		while (rs.next())
			System.out.println(rs.getInt(1) + "                "+rs.getString(2) +"            "+ rs.getInt(3));
		con.close();
		System.out.println("_____________________________________");

		System.out.println("THANK YOU FOR USING HDFC SERVICES.........HAVE A NICE DAY........");

	}
}
	
	


