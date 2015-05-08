package co.id.jejalan.factory;

import co.id.jejalan.dao.extended.CityListDAOImplExt;
import co.id.jejalan.dao.extended.CommentDAOImplExt;
import co.id.jejalan.dao.extended.PostDAOImplExt;
import co.id.jejalan.dao.extended.RegionDAOImplExt;
import co.id.jejalan.dao.extended.SocmedDAOImplExt;
import co.id.jejalan.dao.extended.TagDAOImplExt;
import co.id.jejalan.dao.extended.UserDAOImplExt;

public class DAOFactory {
	public static CityListDAOImplExt getCityListDAO()
	{
		return new CityListDAOImplExt();
	}
	
	public static CommentDAOImplExt getCommentDAO()
	{
		return new CommentDAOImplExt();
	}
	
	public static PostDAOImplExt getPostDAO()
	{
		return new PostDAOImplExt();
	}
	
	public static RegionDAOImplExt getRegionDAO()
	{
		return new RegionDAOImplExt();
	}
	
	public static SocmedDAOImplExt getSocmedDAO()
	{
		return new SocmedDAOImplExt();
	}
	
	public static TagDAOImplExt getTagDAO()
	{
		return new TagDAOImplExt();
	}
	
	public static UserDAOImplExt getUserDAO()
	{
		return new UserDAOImplExt();
	}
}
