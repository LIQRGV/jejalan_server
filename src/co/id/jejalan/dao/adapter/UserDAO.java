package co.id.jejalan.dao.adapter;

import co.id.jejalan.bean.User;

public interface UserDAO extends BaseDao {
	public int insert(User obj);
	public int update(User obj);
}
