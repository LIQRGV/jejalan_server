package co.id.jejalan.rest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Base64.Decoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import co.id.jejalan.bean.BaseBean;
import co.id.jejalan.bean.Comment;
import co.id.jejalan.bean.Post;
import co.id.jejalan.bean.Tag;
import co.id.jejalan.bean.User;
import co.id.jejalan.dao.extended.CommentDAOImplExt;
import co.id.jejalan.dao.extended.PostDAOImplExt;
import co.id.jejalan.dao.extended.TagDAOImplExt;
import co.id.jejalan.dao.extended.UserDAOImplExt;
import co.id.jejalan.factory.DAOFactory;

@Path("/Util")
public class RestUtil {

	@POST
	@Path("login")
	public Response login(@Context HttpServletRequest request) {

		String authHeaderValue = request.getHeader("Authorization");
		boolean userExist = false;
		//prevent null auth
		if(authHeaderValue != null){
			String arrayAuth[] = authHeaderValue.split(" ");
			// ensure auth have 2 string
			if(arrayAuth.length == 2){
				String authType = arrayAuth[0];
				String authValue = arrayAuth[1];
				// handle the basic auth (note : other auth not implemented yet)
				if(authType.matches("[B|b]asic"))
				{
					Decoder decoder = Base64.getDecoder();
					String usernamePassword = new String(decoder.decode(authValue));
					String arrayUsernamePassword[] = usernamePassword.split(":");
					// check username password pair
					if(arrayUsernamePassword.length == 2)
					{
						String username = arrayUsernamePassword[0];					
						String password = arrayUsernamePassword[1];
				
						User user = new User();
				
						user.setUsername(username);
						user.setPassword(password);
						UserDAOImplExt userDAO = DAOFactory.getUserDAO();
						
						userExist = userDAO.login(user);
					}
				}
			}
		}
		
		if (!userExist)
			return Response.status(Response.Status.NOT_FOUND).build();
		else
			return Response.status(Response.Status.OK).build();
	}

	@GET
	@Path("isUsernameUsed/{username}")
	@Produces("application/json")
	public Response isUsernameUsed(@PathParam("username") String username) {
		UserDAOImplExt userDao = DAOFactory.getUserDAO();
		boolean isUsed = userDao.isUsernameUsed(username);
		Map<String, String> mapResponse = new HashMap<>();

		if (isUsed)
			mapResponse.put("status", "not available");
		else
			mapResponse.put("status", "available");

		return Response.status(Response.Status.OK).entity(mapResponse).build();
	}

	@GET
	@Path("isEmailUsed/{email}")
	@Produces("application/json")
	public Response isEmailUsed(@PathParam("email") String email) {
		UserDAOImplExt userDao = DAOFactory.getUserDAO();
		boolean isUsed = userDao.isEmailUsed(email);
		Map<String, String> mapResponse = new HashMap<>();

		if (isUsed)
			mapResponse.put("status", "not available");
		else
			mapResponse.put("status", "available");

		return Response.status(Response.Status.OK).entity(mapResponse).build();
	}

	@GET
	@Path("isPhoneUsed/{phone}")
	@Produces("application/json")
	public Response isPhoneUsed(@PathParam("phone") String phone) {
		UserDAOImplExt userDao = DAOFactory.getUserDAO();
		boolean isUsed = userDao.isPhoneUsed(phone);
		Map<String, String> mapResponse = new HashMap<>();

		if (isUsed)
			mapResponse.put("status", "not available");
		else
			mapResponse.put("status", "available");

		return Response.status(Response.Status.OK).entity(mapResponse).build();
	}

	@GET
	@Path("getTopPost")
	@Produces("application/json")
	public Response getTopPost() {
		Map<String, List<? extends BaseBean>> resultMap = new HashMap<>();
		List<Post> postList = new ArrayList<>();
		List<User> userInfoList = new ArrayList<>();
		Set<Integer> tempCreatorSet = new TreeSet<>();
		PostDAOImplExt postDao = DAOFactory.getPostDAO();
		UserDAOImplExt userDao = DAOFactory.getUserDAO();
		ResultSet resultSet = postDao.getTopPost();

		try {
			while (resultSet.next()) {
				int id = resultSet.getInt(Post.ID);
				String title = resultSet.getString(Post.TITLE);
				int creator = resultSet.getInt(Post.CREATOR);
				int city = resultSet.getInt(Post.CITY);
				String content = resultSet.getString(Post.CONTENT);
				int hit = resultSet.getInt(Post.HIT);
				int revisionOf = resultSet.getInt(Post.REVISION_OF);
				Timestamp dateCreated = resultSet
						.getTimestamp(Post.DATE_CREATED);
				// Date dateCreated = resultSet
				// .getDate(Post.DATE_CREATED);
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

				tempCreatorSet.add(creator);

				postList.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			for (int id : tempCreatorSet) {
				ResultSet resultSetUser = userDao.getByID(id);
				if (resultSetUser.next()) {
					User user = new User();
					String username = resultSetUser.getString(User.USERNAME);
					String password = resultSetUser.getString(User.PASSWORD);
					String completeName = resultSetUser
							.getString(User.COMPLETE_NAME);
					int region = resultSetUser.getInt(User.REGION);
					String email = resultSetUser.getString(User.EMAIL);
					String hp = resultSetUser.getString(User.HP);
					String profilePicture = resultSetUser
							.getString(User.PROFILE_PICTURE);

					String mask = "******";
					username = mask;
					password = mask;

					user.setId(id);
					user.setUsername(username);
					user.setPassword(password);
					user.setCompleteName(completeName);
					user.setRegion(region);
					user.setEmail(email);
					user.setHp(hp);
					user.setProfilePicture(profilePicture);
					
					userInfoList.add(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		resultMap.put("result", postList);
		resultMap.put("info", userInfoList);

		return Response.status(200).entity(resultMap).build();
	}

	@GET
	@Path("getPostByTag/{id}")
	@Produces("application/json")
	public Response getPostByTag(@PathParam("id") int idTag) {
		List<Post> postList = new ArrayList<>();
		Map<Object, Object> mapResponse = new HashMap<>();
		PostDAOImplExt postDao = DAOFactory.getPostDAO();
		ResultSet resultSet = postDao.getByTagID(idTag);

		mapResponse.put("tagID", idTag);

		try {
			while (resultSet.next()) {
				int id = resultSet.getInt(Post.ID);
				String title = resultSet.getString(Post.TITLE);
				int creator = resultSet.getInt(Post.CREATOR);
				int city = resultSet.getInt(Post.CITY);
				String content = resultSet.getString(Post.CONTENT);
				int hit = resultSet.getInt(Post.HIT);
				int revisionOf = resultSet.getInt(Post.REVISION_OF);
				Timestamp timestampCreated = resultSet
						.getTimestamp(Post.DATE_CREATED);
				Timestamp dateCreated = new Timestamp(
						timestampCreated.getTime());
				// Date dateCreated = resultSet
				// .getDate(Post.DATE_CREATED);
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
		mapResponse.put("postList", postList);
		return Response.status(200).entity(mapResponse).build();
	}

	@GET
	@Path("getCommentByPost/{id}")
	@Produces("application/json")
	public Response getCommentByPost(@PathParam("id") int idPost) {
		List<Comment> commentList = new ArrayList<>();
		Map<Object, Object> mapResponse = new HashMap<>();
		CommentDAOImplExt commentDao = DAOFactory.getCommentDAO();
		ResultSet resultSet = commentDao.getByPostID(idPost);

		mapResponse.put("postID", idPost);

		try {
			while (resultSet.next()) {
				int id = resultSet.getInt(Comment.ID);
				int postID = resultSet.getInt(Comment.POSTID);
				int userID = resultSet.getInt(Comment.USERID);
				String content = resultSet.getString(Comment.CONTENT);
				int revisionOf = resultSet.getInt(Comment.REVISION_OF);
				boolean removed = resultSet.getBoolean(Comment.REMOVED);
				Timestamp timestampCreated = resultSet
						.getTimestamp(Comment.DATE_CREATED);

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

		mapResponse.put("commentList", commentList);
		return Response.status(200).entity(mapResponse).build();
	}

	@GET
	@Path("getCommentByUser/{id}")
	@Produces("application/json")
	public Response getCommentByUser(@PathParam("id") int idUser) {
		List<Comment> commentList = new ArrayList<>();
		Map<Object, Object> mapResponse = new HashMap<>();
		CommentDAOImplExt commentDao = DAOFactory.getCommentDAO();
		ResultSet resultSet = commentDao.getByUserID(idUser);

		mapResponse.put("userID", idUser);

		try {
			while (resultSet.next()) {
				int id = resultSet.getInt(Comment.ID);
				int postID = resultSet.getInt(Comment.POSTID);
				int userID = resultSet.getInt(Comment.USERID);
				String content = resultSet.getString(Comment.CONTENT);
				int revisionOf = resultSet.getInt(Comment.REVISION_OF);
				boolean removed = resultSet.getBoolean(Comment.REMOVED);
				Timestamp timestampCreated = resultSet
						.getTimestamp(Comment.DATE_CREATED);

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

		mapResponse.put("commentList", commentList);
		return Response.status(200).entity(mapResponse).build();
	}

	@GET
	@Path("getTagByPost/{id}")
	@Produces("application/json")
	public Response getTagByPost(@PathParam("id") int idPost) {
		List<Tag> tagList = new ArrayList<>();
		Map<Object, Object> mapResponse = new HashMap<>();
		TagDAOImplExt tagDao = DAOFactory.getTagDAO();
		ResultSet resultSet = tagDao.getByPostID(idPost);

		mapResponse.put("postID", idPost);

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

		mapResponse.put("commentList", tagList);
		return Response.status(200).entity(mapResponse).build();
	}
}
