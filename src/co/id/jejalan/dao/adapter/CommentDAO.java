package co.id.jejalan.dao.adapter;

import co.id.jejalan.bean.Comment;

public interface CommentDAO extends BaseDao {
	public int insert(Comment obj);
	public int update(Comment obj);
}
