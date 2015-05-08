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

import co.id.jejalan.bean.Comment;
import co.id.jejalan.dao.extended.CommentDAOImplExt;
import co.id.jejalan.factory.DAOFactory;

@Path("/Comment")
public class RestComment {
	@GET
	@Produces("application/json")
	public Response get() {
		List<Comment> commentList = new ArrayList<>();
		CommentDAOImplExt commentDAO = DAOFactory.getCommentDAO();
		ResultSet resultSet = commentDAO.get();
		
		try {
			while(resultSet.next())
			{
				int id = resultSet.getInt(Comment.ID);
				int postID = resultSet.getInt(Comment.POSTID);
				int userID = resultSet.getInt(Comment.USERID);
				String content = resultSet.getString(Comment.CONTENT);
				int revisionOf = resultSet.getInt(Comment.REVISION_OF);
				boolean removed = resultSet.getBoolean(Comment.REMOVED);
				Timestamp timestampCreated = resultSet.getTimestamp(Comment.DATE_CREATED);
				
				Comment comment = new Comment();
				comment.setId(id);
				comment.setPostID(postID);
				comment.setUserID(userID);
				comment.setContent(content);
				comment.setRevisionOf(revisionOf);
				comment.setRemoved(removed);
				comment.setDateCreated(timestampCreated);
				
				commentList.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(commentList).build();
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getID(@PathParam("id")int id) {
		Comment comment= new Comment();
		CommentDAOImplExt commentDAO = DAOFactory.getCommentDAO();
		ResultSet resultSet = commentDAO.getByID(id);
		
		try {
			if(resultSet.next())
			{
				int postID = resultSet.getInt(Comment.POSTID);
				int userID = resultSet.getInt(Comment.USERID);
				String content = resultSet.getString(Comment.CONTENT);
				int revisionOf = resultSet.getInt(Comment.REVISION_OF);
				boolean removed = resultSet.getBoolean(Comment.REMOVED);
				Timestamp timestampCreated = resultSet.getTimestamp(Comment.DATE_CREATED);

				comment.setId(id);
				comment.setPostID(postID);
				comment.setUserID(userID);
				comment.setContent(content);
				comment.setRevisionOf(revisionOf);
				comment.setRemoved(removed);
				comment.setDateCreated(timestampCreated);
			}
			else
			{
				return Response.status(Response.Status.NO_CONTENT).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(comment).build();
	}
	
	@DELETE
	@Path("{id}") 
	public Response deleteID(@PathParam("id")int id) {
		CommentDAOImplExt commentDao = DAOFactory.getCommentDAO();
		int deletedRow = commentDao.deleteID(id);
		
		if(deletedRow == 0) 
			return Response.status(Response.Status.NOT_FOUND).build();
		else
			return Response.status(Response.Status.ACCEPTED).build();
	}

	@POST
	public Response insert(Comment obj) {
		CommentDAOImplExt commentDao = DAOFactory.getCommentDAO();
		int status = commentDao.insert(obj);
		
		if(status != 0)
			return Response.status(Response.Status.ACCEPTED).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}

	@PUT
	public Response update(Comment obj) {
		CommentDAOImplExt commentDao = DAOFactory.getCommentDAO();
		int status = commentDao.update(obj);

		if(status != 0)
			return Response.status(Response.Status.ACCEPTED).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}
