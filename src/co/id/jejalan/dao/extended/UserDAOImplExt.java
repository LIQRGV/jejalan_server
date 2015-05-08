package co.id.jejalan.dao.extended;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.id.jejalan.bean.User;
import co.id.jejalan.dao.Query;
import co.id.jejalan.dao.Query.QueryKey;
import co.id.jejalan.dao.base.UserDAOImpl;

public class UserDAOImplExt extends UserDAOImpl {
	public boolean isPhoneUsed(String phoneNum)	{
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.isPhoneUsed);
		ResultSet resultSet = null;
		try {
			preparedStatement.setString(1, phoneNum);
			resultSet = preparedStatement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isEmailUsed(String email)	{
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.isEmailUsed);
		ResultSet resultSet = null;
		try {
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isUsernameUsed(String username){
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.isUsernameUsed);
		ResultSet resultSet = null;
		try {
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean register(User user){
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.postUser);
		boolean result = false;
		try {
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getCompleteName());
			preparedStatement.setInt(4, user.getRegion());
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setString(6, user.getHp());
			preparedStatement.setString(7, user.getProfilePicture());
			result = preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean login(User user){
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.login);
		ResultSet resultSet = null;
		try {
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			resultSet = preparedStatement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
