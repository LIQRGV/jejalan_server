package co.id.jejalan.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;

//import org.codehaus.jettison.json.JSONException;

public interface Test {
	  @GET
	  @Produces("application/json")
	public Response convertFtoC() throws JSONException;
	//@Path("{f}")
	  @GET
	  @Produces("application/json")
	  public Response convertFtoCfromInput(@PathParam("f") float f) throws JSONException;
}
