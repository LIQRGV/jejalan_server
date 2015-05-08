package co.id.jejalan.dao.adapter;

import co.id.jejalan.bean.CityList;

public interface CityListDAO extends BaseDao {
	public int insert(CityList obj);
	public int update(CityList obj);
}
