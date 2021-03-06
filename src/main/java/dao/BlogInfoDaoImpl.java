package dao;

import entity.Blog;

import java.util.List;

public class BlogInfoDaoImpl extends BaseDao<Blog> implements BlogInfoDao{
    @Override
    public List<Blog> getAllBlog() {
        return executeQuery("select * from t_blog");
    }

    @Override
    public int deleteBlog(int blogId) {
        return executeUpdate("delete from t_blog where blogId = ?",new Object[]{blogId});
    }

    @Override
    public List<Blog> getAllBlog(int userId) {
        return executeQuery("select * from t_blog where userId = ?",new Object[]{userId});
    }

    @Override
    public List<Blog> getMyAllBlog(int userId, int page) {
        return executeQuery("select * from t_blog where userId = ? limit ?,5",new Object[]{userId,page});
    }

    public  int getOneBlog(int userId){
        return  executeUpdate("select blogId from t_blog where userid=?",new Object[]{userId});
    }

    @Override
    public int insertBlog(Blog blog) {
        return executeUpdate("insert into t_blog(context,userId,blogPic,blogVideo) value(?,?,?,?)",new Object[]{blog.getContext(),blog.getUserId(),blog.getBlogPic(),blog.getBlogVideo()});
    }

    @Override
    public List<Blog> getFollowBlogById(int userId) {
        return executeQuery("select * from t_blog where  userId in (select fansId from t_fansuser where userId=?) ",new Object[]{userId}
);
    }

    @Override
    public List<Blog> getBlogByKey(String key) {
        return executeQuery("select * from t_blog where context like ?",new Object[]{"%" + key + "%"});
    }

    @Override
    public List<Blog> getBlogByKey(int userId, String key) {
        return executeQuery("select * from t_blog where userId = ? and context like ? order by sendDate desc",new Object[]{userId,"%" + key + "%"});
    }

    @Override
    public List<Blog> getTransCount(int blogId) {
        return executeQuery("select tsNum from t_blog where blogId = ?",new Object[]{blogId});
    }

    @Override
    public List<Blog> searchDayBlog() {
        return executeQuery("select * from t_blog where sendDate >= DATE(now()) ORDER BY sendDate");
    }
    @Override
    public int getCountBlog(){
        return getRecordCount("select count(*) from t_blog");
    }

    @Override
    public int getCountBlog(int userId) {
        return getRecordCount("select count(*) from t_blog where userId = ?",new Object[]{userId});
    }

    @Override
    public List<Blog> getAllBlog(int pageNo,int pageSize){
        return executeQuery("select * from t_blog limit ?,?",new Object[]{(pageNo-1)*pageSize,pageSize});
    }
    @Override
    public List<Blog> getBlogByUserId(int userId,int pageNo,int pageSize){
        return executeQuery("select * from t_blog where userId = ? limit ? , ?",new Object[]{userId,(pageNo-1)*pageSize,pageSize});
    }
    @Override
    public int getCountBlog(String column,Object condition){
        return getRecordCount("select count(*) from t_blog where "+column+"= ?",new Object[]{condition});
    }
}
