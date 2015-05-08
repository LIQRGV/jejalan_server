package co.id.jejalan.dao.base;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import co.id.jejalan.bean.Tag;
import co.id.jejalan.dao.Query;
import co.id.jejalan.dao.Query.QueryKey;
import co.id.jejalan.dao.adapter.TagDAO;

public class TagDAOImpl implements TagDAO {

	@Override
	public ResultSet get() {
		PreparedStatement preparedStatement = Query
				.getPreparedStatement(QueryKey.getAllTag);
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
				.getPreparedStatement(QueryKey.getTagById);
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
	public int insert(Tag obj) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
		// return false;
	}

	@Override
	public int update(Tag obj) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
		// return false;
	}
}
