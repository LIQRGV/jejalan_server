package co.id.jejalan.rest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import co.id.jejalan.bean.Socmed;
import co.id.jejalan.dao.extended.SocmedDAOImplExt;
import co.id.jejalan.factory.DAOFactory;

@Path("/Socmed")
public class RestSocmed {

	@GET
	@Produces("application/json")
	public Response get() {
		List<Socmed> socmedList = new ArrayList<>();
		SocmedDAOImplExt socmedDAO = DAOFactory.getSocmedDAO();
		ResultSet resultSet = socmedDAO.get();
		
		try {
			while(resultSet.next())
			{
				int id = resultSet.getInt(Socmed.ID);
				String namaSocmed = resultSet.getString(Socmed.NAMA_SOCMED);
				
				Socmed socmed = new Socmed();
				socmed.setId(id);
				socmed.setNamaSocmed(namaSocmed);
				
				socmedList.add(socmed);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(socmedList).build();
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getID(@PathParam("id")int id) {
		Socmed socmed = new Socmed();
		SocmedDAOImplExt socmedDAO = DAOFactory.getSocmedDAO();
		ResultSet resultSet = socmedDAO.getByID(id);
		
		try {
			if(resultSet.next())
			{
				String namaSocmed = resultSet.getString(Socmed.NAMA_SOCMED);

				socmed.setId(id);
				socmed.setNamaSocmed(namaSocmed);
			}
			else
			{
				return Response.status(Response.Status.NO_CONTENT).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(socmed).build();
	}
	
	@DELETE
	@Path("{id}") 
	public Response deleteID(@PathParam("id")int id) {
		SocmedDAOImplExt socmedDao = DAOFactory.getSocmedDAO();
		int deletedRow = socmedDao.deleteID(id);
		
		if(deletedRow == 0) 
			return Response.status(Response.Status.NOT_FOUND).build();
		else
			return Response.status(Response.Status.ACCEPTED).build();
	}

	@POST
	public Response insert(Socmed obj) {
		SocmedDAOImplExt socmedDao = DAOFactory.getSocmedDAO();
		int status = socmedDao.insert(obj);
		
		if(status != 0)
			return Response.status(Response.Status.ACCEPTED).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}

	@PUT
	public Response update(Socmed obj) {
		SocmedDAOImplExt socmedDao = DAOFactory.getSocmedDAO();
		int status = socmedDao.update(obj);

		if(status != 0)
			return Response.status(Response.Status.ACCEPTED).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}
