package co.id.jejalan.dao.base;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.id.jejalan.bean.User;
import co.id.jejalan.dao.Query;
import co.id.jejalan.dao.Query.QueryKey;
import co.id.jejalan.dao.adapter.UserDAO;

public class UserDAOImpl implements UserDAO {

	@Override
	public ResultSet get() {
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.getAllUser);
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
				.getPreparedStatement(QueryKey.getUserById);
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
				.getPreparedStatement(QueryKey.deleteUser);
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
	public int insert(User obj) {
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.postUser);
		int result = -1;
		try {
			preparedStatement.setString(1, obj.getUsername());
			preparedStatement.setString(2, obj.getPassword());
			preparedStatement.setString(3, obj.getCompleteName());
			preparedStatement.setInt(4, obj.getRegion());
			preparedStatement.setString(5, obj.getEmail());
			preparedStatement.setString(6, obj.getHp());
			preparedStatement.setString(7, obj.getProfilePicture());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}

	@Override
	public int update(User obj) {
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.putUser);
		int result = -1;
		try {
			preparedStatement.setString(1, obj.getPassword());
			preparedStatement.setString(2, obj.getCompleteName());
			preparedStatement.setInt(3, obj.getRegion());
			preparedStatement.setString(4, obj.getHp());
			preparedStatement.setString(5, obj.getProfilePicture());
			preparedStatement.setInt(6, obj.getId());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}
}
