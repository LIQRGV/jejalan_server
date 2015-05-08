package co.id.jejalan.dao.adapter;

import java.sql.ResultSet;

public interface BaseDao {
	public ResultSet get();
	public ResultSet getByID(int id);
	public int deleteID(int id);
}
