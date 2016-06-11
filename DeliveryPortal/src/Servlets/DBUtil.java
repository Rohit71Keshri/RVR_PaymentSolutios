package Servlets;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;

public class DBUtil {
 
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/shadowfax";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "rohit@mysql1990";
	   
	   HashMap<String,String> getOrderDetailsMap = new HashMap<String,String>();
	
	public HashMap<String,String> getOrderDetails(String orderId){
		
		Connection conn = null;
		Statement stmt = null;
		
		
		try{
			//STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      
		    //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = (Statement) conn.createStatement();
		      
		      String sql = "SELECT ORDER_ID, MERCHANT_ID, AMOUNT FROM Order_table where ORDER_ID = "+orderId;
		      ResultSet rs = (ResultSet) stmt.executeQuery(sql);
		      
		    //STEP 5: Extract data from result set
		      while(rs.next()){
		          //Retrieve by column name
		          String ORDERID  = rs.getString("ORDER_ID");
		          String MERCHANTID = rs.getString("MERCHANT_ID");
		          String AMOUNT = rs.getString("AMOUNT");
		         
		          getOrderDetailsMap.put("ORDER_ID", ORDERID);
		          getOrderDetailsMap.put("MERCHANT_ID", MERCHANTID);
		          getOrderDetailsMap.put("AMOUNT", AMOUNT);

		          //Display values
		          System.out.print("ORDER_ID: " + ORDERID);
		          System.out.print(", MERCHANT_ID: " + MERCHANTID);
		          System.out.println(", AMOUNT: " + AMOUNT);
		          //System.out.println(", Last: " + last);
		          
		          
		       }
		       rs.close();
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("Goodbye!");
			   return getOrderDetailsMap;
	}
	
public Boolean submitOrderAmount(String merchantId,String amount){
		
		Connection conn = null;
		Statement stmt = null;
		Boolean status = false;
		
		
		try{
			//STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      
		    //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = (Statement) conn.createStatement();
		      
		      String sql1 = "select MERCHANT_ID,MERCHANT_NAME,BALANCE from Merchant_details where MERCHANT_ID = "+merchantId;
		      ResultSet rs1 = (ResultSet) stmt.executeQuery(sql1);
		      
		      
		      Integer intBanlance=0;
		      Integer intAmount;
		      String MERCHANTID=null;
		      String MERCHANTNAME=null;
		      String BALANCE=null;
		    //STEP 5: Extract data from result set
		      while(rs1.next()){
		          //Retrieve by column name
		           MERCHANTID  = rs1.getString("MERCHANT_ID");
		           MERCHANTNAME = rs1.getString("MERCHANT_NAME");
		           BALANCE = rs1.getString("BALANCE");
		          
		          intBanlance = Integer.parseInt(BALANCE);
		          intAmount = Integer.parseInt(amount);
		          
		          intBanlance = intBanlance + intAmount;
		          
		       }
		       rs1.close();
		       
		       String strBalance = intBanlance.toString();
		       
		       String sql2 = "Insert into Merchant_details values('"+MERCHANTID+"','"+MERCHANTNAME+"','"+strBalance+"', now())";
		       
		      // String sql2 = "UPDATE Merchant_details set BALANCE ="+ intBanlance+", LAST_UPDATE_DATE = now() where MERCHANT_ID = "+merchantId;
			     int rs2 =  stmt.executeUpdate(sql2);
			      if(rs2 > 0){
			    	  System.out.println("insert  succesfull");
			    	  status=true;
			      }
			      
		       
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("Goodbye!");
			   return status;
	}
	
}