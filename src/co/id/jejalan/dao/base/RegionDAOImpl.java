package co.id.jejalan.dao.base;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.id.jejalan.bean.Region;
import co.id.jejalan.dao.Query;
import co.id.jejalan.dao.Query.QueryKey;
import co.id.jejalan.dao.adapter.RegionDAO;

public class RegionDAOImpl implements RegionDAO {

	@Override
	public ResultSet get() {
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.getAllRegion);
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
				.getPreparedStatement(QueryKey.getRegionById);
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
	public int deleteID(int id) throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
		// return false;
	}

	@Override
	public int insert(Region obj) throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
		// return false;
	}

	@Override
	public int update(Region obj) throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
		// return false;
	}
}
