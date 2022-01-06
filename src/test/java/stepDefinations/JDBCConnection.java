package stepDefinations;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.cucumber.java.en.Given;
import pojo.CustomerInfo;

public class JDBCConnection {

	@Given("Access Data from database")
	public void access_data_from_database() throws ClassNotFoundException, SQLException, StreamWriteException, DatabindException, IOException {
		
//		try {
//		MysqlDataSource dataSource = new MysqlDataSource();
//		dataSource.setUser("root");
//		dataSource.setPassword("root");
//		dataSource.setServerName("//localhost");
//		dataSource.setPort(3036);
//		dataSource.setDatabaseName("business");
//		
		
//		Connection connect = dataSource.getConnection("root", "root");
//		}catch (Exception e) {
//            System.err.println("Cannot connect to database server");
//            System.err.println(e.getMessage());
//            e.printStackTrace();
//		}
//		
		
		ArrayList<CustomerInfo> customers = new ArrayList<CustomerInfo>();
		
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection con = null; 
	    
	   
	    try {
	    	con = DriverManager.getConnection("jdbc:mysql://localhost/business", "root", "root");
	    } catch (SQLException e) {
	    	throw new IllegalStateException("Cannot connect the database!", e);
	    }
	    
	    
	    Statement st = con.createStatement();
	    
	    ResultSet  result = st.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia' ;");
	
	    
	    
	    while (result.next()) {
	    	CustomerInfo customer = new CustomerInfo();
	    	customer.setCourseName(result.getString(1));
	    	customer.setPurchaseDate(result.getString(2));
	    	customer.setAmount(result.getInt(3));
	    	customer.setLocation(result.getString(4));
	    	
	    	customers.add(customer);
	    	

	    }
	    JSONObject jo = new JSONObject();
	
	    
	    JSONArray jsonArray = new JSONArray();
	    
	    
	    for (int i = 0; i < customers.size(); i++) {
	    	ObjectMapper ob = new ObjectMapper();
	    	Gson g = new Gson();
	    	String jsonString = g.toJson(customers.get(i));
	    	jsonArray.add(jsonString);
		   // ob.writeValue(new File("Record"+i+".json"), customers.get(i));
	    }
	    jo.put("data", jsonArray);
	    
	    System.out.println("---"+jo.toJSONString());
	    
	    con.close();
	}

}
