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

import co.id.jejalan.bean.Region;
import co.id.jejalan.dao.extended.RegionDAOImplExt;
import co.id.jejalan.factory.DAOFactory;

@Path("/Region")
public class RestRegion {

	@GET
	@Produces("application/json")
	public Response get() {
		List<Region> regionList = new ArrayList<>();
		RegionDAOImplExt regionDAO = DAOFactory.getRegionDAO();
		ResultSet resultSet = regionDAO.get();
		
		try {
			while(resultSet.next())
			{
				int id = resultSet.getInt(Region.ID);
				String namaRegion = resultSet.getString(Region.NAMA_REGION);
				
				Region region = new Region();
				region.setId(id);
				region.setNamaRegion(namaRegion);
				
				regionList.add(region);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(regionList).build();
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getID(@PathParam("id")int id) {
		Region region = new Region();
		RegionDAOImplExt regionDAO = DAOFactory.getRegionDAO();
		ResultSet resultSet = regionDAO.getByID(id);
		
		try {
			if(resultSet.next())
			{
				String namaRegion = resultSet.getString(Region.NAMA_REGION);

				region.setId(id);
				region.setNamaRegion(namaRegion);
			}
			else
			{
				return Response.status(Response.Status.NO_CONTENT).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(region).build();
	}
	
	@DELETE
	@Path("{id}") 
	public Response deleteID(@PathParam("id")int id) {
		RegionDAOImplExt regionDao = DAOFactory.getRegionDAO();
		int deletedRow = regionDao.deleteID(id);
		
		if(deletedRow == 0) 
			return Response.status(Response.Status.NOT_FOUND).build();
		else
			return Response.status(Response.Status.ACCEPTED).build();
	}

	@POST
	public Response insert(Region obj) {
		RegionDAOImplExt regionDao = DAOFactory.getRegionDAO();
		int status = regionDao.insert(obj);
		
		if(status != 0)
			return Response.status(Response.Status.ACCEPTED).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}

	@PUT
	public Response update(Region obj) {
		RegionDAOImplExt regionDao = DAOFactory.getRegionDAO();
		int status = regionDao.update(obj);

		if(status != 0)
			return Response.status(Response.Status.ACCEPTED).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}
