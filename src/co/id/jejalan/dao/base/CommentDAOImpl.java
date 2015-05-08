package co.id.jejalan.dao.base;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.id.jejalan.bean.Comment;
import co.id.jejalan.dao.Query;
import co.id.jejalan.dao.Query.QueryKey;
import co.id.jejalan.dao.adapter.CommentDAO;

public class CommentDAOImpl implements CommentDAO {

	@Override
	public ResultSet get() {
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.getAllComment);
		ResultSet resultSet = null;
		try {
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	@Override
	public ResultSet getByID(int id) {
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.getCommentByIdComment);
		ResultSet resultSet = null;
		try {
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	@Override
	public int deleteID(int id) {
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.deleteComment);
		int result = -1;
		try {
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}

	@Override
	public int insert(Comment obj) {
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.postComment);
		int result = -1;
		try {
			preparedStatement.setInt(1, obj.getPostID());
			preparedStatement.setInt(2, obj.getUserID());
			preparedStatement.setString(3, obj.getContent());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}

	@Override
	public int update(Comment obj) {
		PreparedStatement preparedStatementPut = Query
				.getPreparedStatement(QueryKey.putComment);
		PreparedStatement preparedStatementDelete = Query
				.getPreparedStatement(QueryKey.deleteComment);
		int resultPut,resultDelete;
		resultPut = resultDelete = -1;
		try {
			preparedStatementPut.setInt(1,obj.getPostID());
			preparedStatementPut.setInt(2,obj.getUserID());
			preparedStatementPut.setString(3,obj.getContent());
			preparedStatementPut.setInt(4,obj.getId());
			preparedStatementPut.setTimestamp(5, obj.getDateCreated());
			resultPut = preparedStatementPut.executeUpdate();
			
			preparedStatementDelete.setInt(1, obj.getId());
			resultDelete = preparedStatementDelete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return resultPut | resultDelete;
		}
		return resultPut | resultDelete;
	}

}
