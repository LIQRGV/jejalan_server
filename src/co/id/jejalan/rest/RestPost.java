package co.id.jejalan.rest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

import co.id.jejalan.bean.Post;
import co.id.jejalan.dao.extended.PostDAOImplExt;
import co.id.jejalan.factory.DAOFactory;

@Path("/Post")
public class RestPost {

	@GET
	@Produces("application/json")
	public Response get() {
		List<Post> postList = new ArrayList<>();
		PostDAOImplExt postDAO = DAOFactory.getPostDAO();
		ResultSet resultSet = postDAO.get();
		
		try {
			while(resultSet.next())
			{
				int id = resultSet.getInt(Post.ID);
				String title = resultSet.getString(Post.TITLE);
				int creator = resultSet.getInt(Post.CREATOR);
				int city = resultSet.getInt(Post.CITY);
				String content = resultSet.getString(Post.CONTENT);
				int hit = resultSet.getInt(Post.HIT);
				int revisionOf = resultSet.getInt(Post.REVISION_OF);
				Timestamp timestampCreated = resultSet.getTimestamp(Post.DATE_CREATED); 
				Timestamp dateCreated = new Timestamp(timestampCreated.getTime());
				boolean removed = resultSet.getBoolean(Post.REMOVED);
				
				Post post = new Post();
				post.setId(id);
				post.setTitle(title);
				post.setCreator(creator);
				post.setCity(city);
				post.setContent(content);
				post.setHit(hit);
				post.setRevisionOf(revisionOf);
				post.setDateCreated(dateCreated);
				post.setRemoved(removed);
				
				postList.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(postList).build();
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getID(@PathParam("id")int id) {
		Post post = new Post();
		PostDAOImplExt postDAO = DAOFactory.getPostDAO();
		ResultSet resultSet = postDAO.getByID(id);
		
		try {
			if(resultSet.next())
			{
				String title = resultSet.getString(Post.TITLE);
				int creator = resultSet.getInt(Post.CREATOR);
				int city = resultSet.getInt(Post.CITY);
				String content = resultSet.getString(Post.CONTENT);
				int hit = resultSet.getInt(Post.HIT);
				int revisionOf = resultSet.getInt(Post.REVISION_OF);
				Timestamp dateCreated = resultSet.getTimestamp(Post.DATE_CREATED);
				boolean removed = resultSet.getBoolean(Post.REMOVED);

				post.setId(id);
				post.setTitle(title);
				post.setCreator(creator);
				post.setCity(city);
				post.setContent(content);
				post.setHit(hit);
				post.setRevisionOf(revisionOf);
				post.setDateCreated(dateCreated);
				post.setRemoved(removed);

			}
			else
			{
				return Response.status(Response.Status.NO_CONTENT).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(post).build();
	}
	
	@DELETE
	@Path("{id}") 
	public Response deleteID(@PathParam("id")int id) {
		PostDAOImplExt postDao = DAOFactory.getPostDAO();
		int deletedRow = postDao.deleteID(id);
		
		if(deletedRow == 0) 
			return Response.status(Response.Status.NOT_FOUND).build();
		else
			return Response.status(Response.Status.ACCEPTED).build();
	}

	@POST
	public Response insert(Post obj) {
		PostDAOImplExt postDao = DAOFactory.getPostDAO();
		int status = postDao.insert(obj);
		
		if(status != 0)
			return Response.status(Response.Status.ACCEPTED).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}

	@PUT
	public Response update(Post obj) {
		PostDAOImplExt postDao = DAOFactory.getPostDAO();
		int status = postDao.update(obj);

		if(status != 0)
			return Response.status(Response.Status.ACCEPTED).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}
