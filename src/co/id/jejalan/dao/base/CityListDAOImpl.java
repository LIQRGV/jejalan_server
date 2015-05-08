package co.id.jejalan.dao.base;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import co.id.jejalan.bean.CityList;
import co.id.jejalan.dao.Query;
import co.id.jejalan.dao.Query.QueryKey;
import co.id.jejalan.dao.adapter.CityListDAO;

public class CityListDAOImpl implements CityListDAO {

	@Override
	public ResultSet get() {
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.getAllCity);
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
				.getPreparedStatement(QueryKey.getCityById);
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
		// TODO Auto-generated method stub
		throw new NotImplementedException();
		// return false;
	}

	@Override
	public int insert(CityList obj) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
		// return false;
	}

	@Override
	public int update(CityList obj) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
		// return false;
	}
}
