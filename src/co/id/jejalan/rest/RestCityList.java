package co.id.jejalan.rest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import co.id.jejalan.bean.CityList;
import co.id.jejalan.dao.extended.CityListDAOImplExt;
import co.id.jejalan.factory.DAOFactory;

@Path("/City")
public class RestCityList {

	@GET
	@Produces("application/json")
	public Response get() {
		Map<String,List<CityList>> responseMap = new HashMap<>();
		List<CityList> cityList = new ArrayList<>();
		CityListDAOImplExt cityListDAO = DAOFactory.getCityListDAO();
		ResultSet resultSet = cityListDAO.get();

		try {
			while (resultSet.next()) {
				int id = resultSet.getInt(CityList.ID);
				String cityName = resultSet.getString(CityList.CITYNAME);
				int region = resultSet.getInt(CityList.REGION);

				CityList city = new CityList();
				city.setId(id);
				city.setCityName(cityName);
				city.setRegion(region);

				cityList.add(city);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		responseMap.put("result", cityList);

		return Response.status(200).entity(responseMap).build();
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getID(@PathParam("id") int id) {
		CityList city = new CityList();
		CityListDAOImplExt cityListDAO = DAOFactory.getCityListDAO();
		ResultSet resultSet = cityListDAO.getByID(id);

		try {
			if (resultSet.next()) {
				String cityName = resultSet.getString(CityList.CITYNAME);
				int region = resultSet.getInt(CityList.REGION);

				city.setId(id);
				city.setCityName(cityName);
				city.setRegion(region);

			} else {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(city).build();
	}

	@DELETE
	@Path("{id}")
	public Response deleteID(@PathParam("id") int id) {
		CityListDAOImplExt cityListDao = DAOFactory.getCityListDAO();
		try {
			int deletedRow = cityListDao.deleteID(id);

			if (deletedRow == 0)
				return Response.status(Response.Status.NOT_FOUND).build();
			else
				return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			return Response.status(501).build();
		}
		// return Response.status(501).build();
	}

	@POST
	public Response insert(CityList obj) {
		CityListDAOImplExt cityListDao = DAOFactory.getCityListDAO();
		try {
			int deletedRow = cityListDao.insert(obj);

			if (deletedRow == 0)
				return Response.status(Response.Status.NOT_FOUND).build();
			else
				return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			return Response.status(501).build();
		}
		// return Response.status(501).build();
	}

	@PUT
	public Response update(CityList obj) {
		CityListDAOImplExt cityListDao = DAOFactory.getCityListDAO();
		try {
			int deletedRow = cityListDao.update(obj);

			if (deletedRow == 0)
				return Response.status(Response.Status.NOT_FOUND).build();
			else
				return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			return Response.status(501).build();
		}
		// return Response.status(501).build();
	}

}
