package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Inventory {
	
	//Database Connection
		private Connection connect()
			{
				Connection con = null;
			try
			
			{
			
			Class.forName("com.mysql.jdbc.Driver");

			//Create Database connection
	 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "yasith@1234");
			
			}
				catch (Exception e)
				{
					e.printStackTrace();
				}
	 return con;
	 }
		
	
		
	//Insert app Details
		public String insertInventory(String name, String size, String view, String des,String type,String down)
	
			{
				String output = "";
				try
			{
				
					Connection con = connect();
					
						if (con == null)
							{
							return "Error while connecting to the database for inserting."; 
							}
						
					//Create a prepared statement
					String query = " insert into admintb(`id`,`appName`,`appSize`,`reviews`,`appDes`,`appType`,`downloads`)"
							       + " values (?, ?, ?, ?, ? ,?,?)";
	 
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
						// binding values
						preparedStmt.setInt(1, 0);
						preparedStmt.setString(2, name);
						preparedStmt.setString(3, size);
						preparedStmt.setString(4, view);
						preparedStmt.setString(5, des);
						preparedStmt.setString(6, type);
						preparedStmt.setString(7, down);
						
	
						//execute the statement
						preparedStmt.execute();
						con.close();
						output = "Inserted successfully";
			}
						catch (Exception e)
				
							{
								output = "Error while inserting the Details.";
								System.err.println(e.getMessage());
							}
				
				return output;
			}
	
		
		
	//Retrieve data from Database
		public String readadmintb()
				
			{
				String output = "";
				try{
					Connection con = connect();
					if (con == null)
						
					{
						return "Error while connecting to the database for reading."; 
					}
					
	 // Prepare the html table to be displayed
			
						output = "<table border='1'><tr><th>App Name</th><th>App Size</th><th>Reviews</th>" +
								"<th>App Description</th>" +
								"<th>App Type</th>" +
								"<th>Downloads</th>" +
								"<th>Update</th><th>Delete</th></tr>";
						

								String query = "select * from admintb";
								Statement stmt = con.createStatement();
								ResultSet rs = stmt.executeQuery(query);
	
	// iterate through the rows in the result set
			while (rs.next())
				{
							String id = Integer.toString(rs.getInt("id"));
							String appName = rs.getString("appName");
							String appSize = rs.getString("appSize");
							String reviews = rs.getString("reviews");
							String appDes = rs.getString("appDes");
							String appType = rs.getString("appType");
							String downloads = rs.getString("downloads");
	 
	 // Add into the html table
	 
							output += "<tr><td>" + appName + "</td>";
							output += "<td>" + appSize + "</td>";
							output += "<td>" + reviews + "</td>";
							output += "<td>" + appDes + "</td>";
							output += "<td>" + appType + "</td>";
							output += "<td>" + downloads + "</td>";
	 // Insert buttons
							
		
							/* output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
								    		+ "<td><form method='post' action='inventory.jsp'> <input name='btnDelete' type='submit' value='Delete' class='btn btn-danger'>"
								    		+ "<input name='hidInventoryIDDelete' type='hidden' value=''" + id
								    		+ "\">" + "</form></td></tr>"; */
							 
							 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						    		 + "<td><form method='post' action='inventory.jsp'>"+ "<input name='btnDelete' type='submit' value='Delete' class='btn btn-danger'>"
						    		 + "<input name='hidInventoryIDDelete' type='hidden' data-inventoryid='" + id 
						    		 + "'>" + "</form></td></tr>";
						    
				}
			
	 con.close();
	 
	 // Complete the html table
	 output += "</table>";
				}
				
				catch (Exception e)
				
				{
					output = "Error while reading the items.";
					System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
			public String updateInventory(String ID, String name, String size, String view, String des,String type,String down)
	
			{
				String output = "";
				try
			{
					Connection con = connect();
					if (con == null)
			{
					return "Error while connecting to the database for updating."; 
		 
			}
	
					
			// create a prepared statement		
			String query = "UPDATE admintb SET appName=?,appSize=?,reviews=?,appDes=?,appType=?,downloads=? WHERE id=?";
	 
			PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	
				preparedStmt.setString(1, name);
				preparedStmt.setString(2, size);
				preparedStmt.setString(3, view);
				preparedStmt.setString(4, des);
				preparedStmt.setString(5, type);
				preparedStmt.setString(6, down);
				preparedStmt.setInt(7, Integer.parseInt(ID));
	 
	 // execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
			}
				catch (Exception e)
					{
						output = "Error while updating the inventory.";
						System.err.println(e.getMessage());
					}
					return output;
	 }
	
	
	//Delete
	 
			public String deleteInventory(String id)
				{
					String output = "";
					try
						{
				
							Connection con = connect();
							if (con == null)
						{
								return "Error while connecting to the database for deleting."; 
						}
	 // create a prepared statement
				
							String query = "delete from admintb where id=?";
							PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
							preparedStmt.setInt(1, Integer.parseInt(id));
	 // execute the statement
							preparedStmt.execute();
							con.close();
							output = "Deleted successfully";
							}
							catch (Exception e)
									{
										output = "Error while deleting the item.";
										System.err.println(e.getMessage());
									}
						return output;
	 }
	 


}
