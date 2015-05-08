package co.id.jejalan.dao.base;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.id.jejalan.bean.Post;
import co.id.jejalan.dao.Query;
import co.id.jejalan.dao.Query.QueryKey;
import co.id.jejalan.dao.adapter.PostDAO;

public class PostDAOImpl implements PostDAO {

	@Override
	public ResultSet get() {
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.getAllPost);
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
				.getPreparedStatement(QueryKey.getPostById);
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
				.getPreparedStatement(QueryKey.deletePost);
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
	public int insert(Post obj) {
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.postPost);
		int result = -1;
		try {
			preparedStatement.setString(1, obj.getTitle());
			preparedStatement.setInt(2, obj.getCreator());
			preparedStatement.setInt(3, obj.getCity());
			preparedStatement.setString(4, obj.getContent());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}

	@Override
	public int update(Post obj) {
		PreparedStatement preparedStatementPut = Query
				.getPreparedStatement(QueryKey.putPost);
		PreparedStatement preparedStatementDelete = Query
				.getPreparedStatement(QueryKey.deletePost);
		int resultPut,resultDelete;
		resultPut = resultDelete = -1;
		try {
			preparedStatementPut.setString(1, obj.getTitle());
			preparedStatementPut.setInt(2, obj.getCreator());
			preparedStatementPut.setInt(3, obj.getCity());
			preparedStatementPut.setString(4, obj.getContent());
			preparedStatementPut.setInt(5, obj.getId());
			preparedStatementPut.setTimestamp(6, obj.getDateCreated());
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
