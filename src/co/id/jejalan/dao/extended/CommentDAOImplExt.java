package co.id.jejalan.dao.extended;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.id.jejalan.dao.Query;
import co.id.jejalan.dao.Query.QueryKey;
import co.id.jejalan.dao.base.CommentDAOImpl;

public class CommentDAOImplExt extends CommentDAOImpl {
	public ResultSet getByPostID(int id) {
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.getCommentByPost);
		ResultSet resultSet = null;
		try {
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public ResultSet getByUserID(int id) {
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.getCommentByIdUser);
		ResultSet resultSet = null;
		try {
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
}
