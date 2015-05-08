package co.id.jejalan.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;





//import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.naming.NamingContext;
import org.json.JSONException;
import org.json.JSONObject;

import co.id.jejalan.bean.BaseBean;
 
//@Path("/ftocservice")
public class ConvertService implements Test{
 
	  //@GET
	  //@Produces("application/json")
	  public Response convertFtoC() throws JSONException {
 
		JSONObject jsonObject = new JSONObject();
		Double fahrenheit = 98.24;
		Double celsius;
		celsius = (fahrenheit - 32)*5/9; 
		jsonObject.put("F Value", fahrenheit); 
		jsonObject.put("C Value", celsius);
 
		//String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(jsonObject.toString()).build();
	  }
 
	  //@Path("{f}")
	  //@GET
	  //@Produces("application/json")
	  public Response convertFtoCfromInput(/*@PathParam("f") */float f) throws JSONException {
 
		JSONObject jsonObject = new JSONObject();
		float celsius;
		celsius =  (f - 32)*5/9; 
		jsonObject.put("F Value", f); 
		jsonObject.put("C Value", celsius);
 
		//String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(jsonObject.toString()).build();
	  }
	  
	  //@Path("header")
	  @POST
	  public Response test(BaseBean entity) throws JSONException 
	  {
		  
		  System.out.println(entity);
		  //BaseBean entity = new BaseBean();
		  //entity.setId(1);
		  return Response.status(200).entity(entity).type(MediaType.APPLICATION_JSON).build();
	  }
	  
	  /*@Path("ctxtest")
	  @GET
	  public Response ctxTest() throws JSONException
	  {
		  Context ctx = null;
		  try {
				ctx = new InitialContext();
				DataSource ds;// = new DataSource();
				
	            NamingContext dbContext = (NamingContext)ctx.lookup("java:/comp/env/jdbc/mySQL");

				//dbContext.bind("TestDB", ds);
	            ds = (DataSource) dbContext.lookup("Jejalan");
				Connection conn = ds.getConnection();
				Statement stat = conn.createStatement();
			} catch (NamingException | SQLException e) {
				e.printStackTrace();
			}		  
		  return null;
	  }
	  */
	  
	  //@Path("dbl/{param1}/{param1Val}/{param2}/{param2Val}")
	  @POST
	  public Response dbl(@PathParam("param1") String param1,@PathParam("param2") String param2,
			  @PathParam("param1Val") String param1Val,@PathParam("param2Val") String param2Val, BaseBean bean) throws JSONException
			  
	  {
		  Map<String,String> map = new HashMap<>();
		  map.put("test", "testa");
		  
		  List<String> list = new LinkedList<>();
		  list.add("nil1");
		  list.add("nil2");
		  //return Response.status(200).entity(bean.toString()).build();
		  return Response.status(200).entity(map).build();
	  }
	  
	  //@Path("uth")
	  @GET
	  public Response uth(@Context HttpServletRequest req ) throws JSONException
			  
	  {
		  
		  //return Response.status(200).entity(bean.toString()).build();
		  String res = req.getHeader("Authorization");
		  String aa = res.split(" ")[1];
		  Decoder decoder = Base64.getDecoder();
		  String rr = new String(decoder.decode(aa));
		  return Response.status(200).entity(rr).build();
	  }
}
