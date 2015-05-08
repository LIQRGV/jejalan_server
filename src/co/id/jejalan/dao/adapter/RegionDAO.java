package co.id.jejalan.dao.adapter;

import co.id.jejalan.bean.Region;

public interface RegionDAO extends BaseDao {
	public int insert(Region obj);
	public int update(Region obj);
}
