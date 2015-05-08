package co.id.jejalan.dao.adapter;

import co.id.jejalan.bean.Socmed;

public interface SocmedDAO extends BaseDao {
	public int insert(Socmed obj);
	public int update(Socmed obj);
}
