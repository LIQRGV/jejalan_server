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

import co.id.jejalan.bean.Tag;
import co.id.jejalan.dao.extended.TagDAOImplExt;
import co.id.jejalan.factory.DAOFactory;

@Path("/Tag")
public class RestTag {

	@GET
	@Produces("application/json")
	public Response get() {
		Map<String,List<Tag>> responseMap = new HashMap<>();
		List<Tag> tagList = new ArrayList<>();
		TagDAOImplExt tagDAO = DAOFactory.getTagDAO();
		ResultSet resultSet = tagDAO.get();

		try {
			while (resultSet.next()) {
				int id = resultSet.getInt(Tag.ID);
				String title = resultSet.getString(Tag.TITLE);

				Tag tag = new Tag();
				tag.setId(id);
				tag.setTitle(title);

				tagList.add(tag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		responseMap.put("result", tagList);
		
		return Response.status(200).entity(responseMap).build();
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getID(@PathParam("id") int id) {
		Tag tag = new Tag();
		TagDAOImplExt tagDAO = DAOFactory.getTagDAO();
		ResultSet resultSet = tagDAO.getByID(id);

		try {
			if (resultSet.next()) {
				String title = resultSet.getString(Tag.TITLE);

				tag.setId(id);
				tag.setTitle(title);
			} else {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(tag).build();
	}

	@DELETE
	@Path("{id}")
	public Response deleteID(@PathParam("id") int id) {
		TagDAOImplExt tagDao = DAOFactory.getTagDAO();
		try {
			int deletedRow = tagDao.deleteID(id);
			if (deletedRow == 0)
				return Response.status(Response.Status.NOT_FOUND).build();
			else
				return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			return Response.status(501).build();
		}
	}

	@POST
	public Response insert(Tag obj) {
		TagDAOImplExt tagDao = DAOFactory.getTagDAO();
		try {
			int status = tagDao.insert(obj);
			if (status != 0)
				return Response.status(Response.Status.ACCEPTED).build();
			else
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.build();
		} catch (Exception e) {
			return Response.status(501).build();
		}
	}

	@PUT
	public Response update(Tag obj) {
		TagDAOImplExt tagDao = DAOFactory.getTagDAO();
		try {
			int status = tagDao.update(obj);
			if (status != 0)
				return Response.status(Response.Status.ACCEPTED).build();
			else
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.build();
		} catch (Exception e) {
			return Response.status(501).build();
		}
	}
}
