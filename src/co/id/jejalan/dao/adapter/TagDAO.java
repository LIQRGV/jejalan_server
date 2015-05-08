package co.id.jejalan.dao.adapter;

import co.id.jejalan.bean.Tag;

public interface TagDAO extends BaseDao {
	public int insert(Tag obj);
	public int update(Tag obj);
}
