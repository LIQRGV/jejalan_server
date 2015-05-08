package co.id.jejalan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.naming.NamingContext;

public class Query {
	//prohibit other query class
	private Query() {
	};

	//key of the query
	public enum QueryKey {
		getAllUser, 
		getAllSocmed, 
		getAllRegion, 
		getAllCity, 
		getAllPost,
		getAllComment,
		getAllTag, 
		
		getUserById, 
		getSocmedById, 
		getRegionById, 
		getCityById, 
		getPostById,
		getCommentByIdComment,
		getTagById,

		postUser,
		postPost,
		postComment,
		
		putUser,
		putPost,
		putComment,
		
		deleteUser,
		deletePost,
		deleteComment,
		
		isUsernameUsed,
		isEmailUsed,
		isPhoneUsed,
		
		getCommentByPost,
		getCommentByIdUser,
		
		getTopPost,
		
		login,
		
		getTagByPost,
		getPostByTag,
	}

	//list of queries
	private static String getAllUser = "select * from user";
	private static String getAllSocmed = "select * from socmed";
	private static String getAllRegion = "select * from region";
	private static String getAllCity = "select * from city_list";
	private static String getAllPost = "select * from post where removed = 0";
	private static String getAllComment = "select * from comment where removed = 0";
	private static String getAllTag = "select * from tag";

	private static String getUserById = "select * from user where id=?";
	private static String getSocmedById = "select * from socmed where id=?";
	private static String getARegionById = "select * from region where id=?";
	private static String getCityById = "select * from city_list where id=?";
	private static String getPostById = "select * from post where id=? AND removed = 0";
	private static String getCommentByIdComment = "select * from comment where id=? AND removed = 0";
	private static String getTagById = "select * from tag where id=?";

	private static String postUser = "INSERT INTO user (username, password, complete_name, region, email, HP, profilePicture) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static String postPost = "INSERT INTO post (title, creator, city, content, revision_Of, removed, date_created) VALUES (?, ?, ?, ?, 0, 0, now())";
	private static String postComment = "INSERT INTO comment(post_id, user_id, content, revison_Of, removed, date_created) VALUES (?,?,?,0,0,now())";

	private static String putUser = "UPDATE user SET password = ?, complete_name = ?, region = ?, HP = ?, profilePicture = ? WHERE id = ?";
	private static String putPost = "INSERT INTO post (title, creator, city, content, revision_Of, removed, date_created) VALUES (?, ?, ?, ?, ?, 0, ?)";
	private static String putComment = "INSERT INTO comment(post_id, user_id, content, revison_Of, removed, date_created) VALUES (?,?,?,?,0,?)";

	private static String deleteUser = "DELETE FROM user WHERE id = ?";
	private static String deletePost = "UPDATE post SET removed = 1 WHERE id = ?";
	private static String deleteComment = "UPDATE comment SET removed = 1 WHERE id = ?";
	
	private static String isUsernameUsed = "SELECT * FROM user WHERE 1 username=?";
	private static String isEmailUsed = "SELECT * FROM user WHERE 1 email=?";
	private static String isPhoneUsed = "SELECT * FROM user WHERE 1 HP=?";
	
	private static String getCommentByPost = "SELECT * FROM comment WHERE 1 post_id=?";
	private static String getCommentByIdUser = "SELECT * FROM comment WHERE 1 user_id=?";
	
	private static String getTopPost = "SELECT * FROM post LIMIT 3";
	
	private static String login = "SELECT * FROM user where username=? and password=?";
	
	private static String getTagByPost = "SELECT tag.* FROM tag, (SELECT * FROM posts_tag WHERE post_id=?) as tempTable "+
			"where tempTable.tag_id=tag.id";	
	private static String getPostByTag = "SELECT post.* FROM post, (SELECT * FROM posts_tag WHERE tag_id=?) as tempTable "+
									"where tempTable.post_id=post.id";
	
	private static final Map<QueryKey, PreparedStatement> statement;  
	
	static{
		statement = new HashMap<>();
		Context ctx = null;
		DataSource ds = null;
		try {
			ctx = new InitialContext();
			
            NamingContext dbContext = (NamingContext)ctx.lookup("java:/comp/env/jdbc/mySQL");

            ds = (DataSource) dbContext.lookup("Jejalan");
			Connection conn = ds.getConnection();
			
			QueryKey tempKey[] = {
					QueryKey.getAllUser, QueryKey.getAllSocmed,QueryKey.getAllRegion,QueryKey.getAllCity,QueryKey.getAllPost,QueryKey.getAllComment,QueryKey.getAllTag,
					QueryKey.getUserById,QueryKey.getSocmedById,QueryKey.getRegionById,QueryKey.getCityById,QueryKey.getPostById,QueryKey.getCommentByIdComment,QueryKey.getTagById,
					QueryKey.postUser,QueryKey.postPost,QueryKey.postComment,					
					QueryKey.putUser,QueryKey.putPost,QueryKey.putComment,					
					QueryKey.deleteUser,QueryKey.deletePost,QueryKey.deleteComment,
					QueryKey.isUsernameUsed,QueryKey.isEmailUsed,QueryKey.isPhoneUsed,
					QueryKey.getCommentByPost,QueryKey.getCommentByIdUser,
					QueryKey.getTopPost,
					QueryKey.login,
					QueryKey.getTagByPost,
					QueryKey.getPostByTag,
					};
			
			String tempQuery[] = {
						getAllUser, getAllSocmed,getAllRegion,getAllCity,getAllPost,getAllComment,getAllTag,
						getUserById,getSocmedById,getARegionById,getCityById,getPostById,getCommentByIdComment,getTagById,
						postUser,postPost,postComment,			
						putUser,putPost,putComment,		
						deleteUser,deletePost,deleteComment,
						isUsernameUsed,isEmailUsed,isPhoneUsed,
						getCommentByPost,getCommentByIdUser,
						getTopPost,
						login,
						getTagByPost,
						getPostByTag,
					};
			
			int length = tempKey.length;
			
			for(int i=0;i<length;i++)
			{
				PreparedStatement preparedStatement = conn.prepareStatement(tempQuery[i]);
				statement.put(tempKey[i], preparedStatement);
			}

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static PreparedStatement getPreparedStatement(QueryKey key)
	{
		return statement.get(key);
	}
}
