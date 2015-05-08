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

import co.id.jejalan.bean.User;
import co.id.jejalan.dao.extended.UserDAOImplExt;
import co.id.jejalan.factory.DAOFactory;

@Path("/User")
public class RestUser {
	@GET
	@Produces("application/json")
	public Response get() {
		List<User> userList = new ArrayList<>();
		UserDAOImplExt userDAO = DAOFactory.getUserDAO();
		ResultSet resultSet = userDAO.get();
		
		try {
			while(resultSet.next())
			{
				int id = resultSet.getInt(User.ID);
				String username = resultSet.getString(User.USERNAME);
				String password = resultSet.getString(User.PASSWORD);
				String completeName = resultSet.getString(User.COMPLETE_NAME);
				int region = resultSet.getInt(User.REGION);
				String email = resultSet.getString(User.EMAIL);
				String hp = resultSet.getString(User.HP);
				String profilePicture = resultSet.getString(User.PROFILE_PICTURE);
				
				String maskPassword = "******";
				password = maskPassword;
				
				User user = new User();
				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				user.setCompleteName(completeName);
				user.setRegion(region);
				user.setEmail(email);
				user.setHp(hp);
				user.setProfilePicture(profilePicture);
				
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(userList).build();
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getID(@PathParam("id")int id) {
		User user= new User();
		UserDAOImplExt userDAO = DAOFactory.getUserDAO();
		ResultSet resultSet = userDAO.getByID(id);
		
		try {
			if(resultSet.next())
			{
				String username = resultSet.getString(User.USERNAME);
				String password = resultSet.getString(User.PASSWORD);
				String completeName = resultSet.getString(User.COMPLETE_NAME);
				int region = resultSet.getInt(User.REGION);
				String email = resultSet.getString(User.EMAIL);
				String hp = resultSet.getString(User.HP);
				String profilePicture = resultSet.getString(User.PROFILE_PICTURE);
				
				String maskPassword = "******";
				password = maskPassword;

				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				user.setCompleteName(completeName);
				user.setRegion(region);
				user.setEmail(email);
				user.setHp(hp);
				user.setProfilePicture(profilePicture);
			}
			else
			{
				return Response.status(Response.Status.NO_CONTENT).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(user).build();
	}
	
	@DELETE
	@Path("{id}") 
	public Response deleteID(@PathParam("id")int id) {
		UserDAOImplExt userDao = DAOFactory.getUserDAO();
		int deletedRow = userDao.deleteID(id);
		
		if(deletedRow == 0) 
			return Response.status(Response.Status.NOT_FOUND).build();
		else
			return Response.status(Response.Status.ACCEPTED).build();
	}

	@POST
	public Response insert(User obj) {
		UserDAOImplExt userDao = DAOFactory.getUserDAO();
		int status = userDao.insert(obj);
		
		if(status != 0)
			return Response.status(Response.Status.ACCEPTED).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}

	@PUT
	public Response update(User obj) {
		UserDAOImplExt userDao = DAOFactory.getUserDAO();
		int status = userDao.update(obj);

		if(status != 0)
			return Response.status(Response.Status.ACCEPTED).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}
