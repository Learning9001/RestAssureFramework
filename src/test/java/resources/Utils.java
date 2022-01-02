package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	
	
	public String getGlobalValue(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream globalPropertyFile = new  FileInputStream("C:\\EclipseProjects\\RestAssured\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(globalPropertyFile);
		return prop.getProperty(key);
		
	}
	
	public RequestSpecification requestSpecification() throws IOException {
		
		if (requestSpec == null) {
			PrintStream log = new PrintStream("log.txt");
			requestSpec = new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseURL"))
				.addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON)
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
			return requestSpec;
		}
			return requestSpec;
	}
	
	public ResponseSpecification responseSpecification() {
		responseSpec = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.build();
		return responseSpec;
	}
	
	public String getJsonPathValue(Response res, String key) {
		
		JsonPath js = new JsonPath(res.asString());
		return js.get(key).toString();
		
	}

}
