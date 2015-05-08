package co.id.jejalan.dao.extended;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.id.jejalan.dao.Query;
import co.id.jejalan.dao.Query.QueryKey;
import co.id.jejalan.dao.base.PostDAOImpl;

public class PostDAOImplExt extends PostDAOImpl {
	public ResultSet getByTagID(int id) {
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.getPostByTag);
		ResultSet resultSet = null;
		try {
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return resultSet;
	}
	
	public ResultSet getTopPost() {
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.getTopPost);
		ResultSet resultSet = null;
		try {
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return resultSet;
	}
}
