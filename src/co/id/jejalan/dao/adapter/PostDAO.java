package co.id.jejalan.dao.adapter;

import co.id.jejalan.bean.Post;

public interface PostDAO extends BaseDao {
	public int insert(Post obj);
	public int update(Post obj);
}
