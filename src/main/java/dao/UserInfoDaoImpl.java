package dao;

import entity.User;

import java.sql.Timestamp;
import java.util.List;

public class UserInfoDaoImpl extends BaseDao<User> implements UserInfoDao {
    @Override
    public List<User> getAllUser() {
        return executeQuery("select * from t_user");
    }
    @Override
    public List<User> getUser(int userId) {
        return executeQuery("select * from t_user where userId = ?",new Object[]{userId});
    }
    @Override
    public List<User> getUser(String telNum) {
        return executeQuery("select * from t_user where telNum = ?",new Object[]{telNum});
    }

    @Override
    public List<User> getUser(String telNum, String password) {
        return executeQuery("select * from t_user where telNum = ? and password = ?",new Object[]{telNum,password});
    }

    @Override
    public int insertUser(User user) {
        return executeUpdate("insert into t_user(userName,telNum,password) values(?,?,?)",new Object[]{user.getUserName(),user.getTelNum(),user.getPassword()});
    }

    @Override
    public int updateUser(User user, String telNum) {
        return executeUpdate("UPDATE t_user set userName = ? ,telNum=?,email=?,sex=?,school=?,sign=?,birthday=?,address=? where telNum=?",new Object[]{user.getUserName(),user.getTelNum(),user.getEmail(),user.getSex(),user.getSchool(),user.getSign(),new Timestamp(user.getBirthday().getTime()), user.getAddress(),telNum});
    }

    @Override
    public boolean getUserName(String username) {
        if (executeQuery("select * from t_user where username = ?", new Object[]{username}) != null)
            return true;
        else
            return false;
    }

    @Override
    public boolean getUserIsLock(String telNum) {
        List<User> userList = executeQuery("select lockDate from t_user where telNum = ?", new Object[]{telNum});
        if (userList.get(0).getLockDate() != null)
            //锁定
            return true;
        else
            return false;
    }
    @Override
    public List<User> getAllUser(int pageNo,int pageSize){
        return executeQuery("select * from t_user limit ?,?",new Object[]{(pageNo-1)*pageSize,pageSize});
    }
    public int getCountUser(){
        return getRecordCount("select count(*) from t_user");
    }
    @Override
    public int delOneUser(int userId){
        return executeUpdate("delete from t_user where userId = ?",new Object[]{userId});
    }

    @Override
    public int UpdateHeadP(String headp,String telNum) {
        return executeUpdate("update t_user set headP=? where telNum = ?",new Object[]{headp,telNum});
    }
    @Override
    public int countToday(){
        return getRecordCount("SELECT count(*) FROM t_user WHERE TO_DAYS(regDate) = TO_DAYS(NOW())");
    }
    @Override
    public int countPreDay(int day){//1为昨天，2为前天
        return getRecordCount("SELECT count(*) FROM t_user WHERE TO_DAYS(now()) - TO_DAYS(regDate) <= ?",new Object[]{day});
    }
    @Override
    public List<User> hotBlogUserNameByP(){
        return executeQuery("select d.userName,c.userId from t_user AS d,\n" +
                "(select a.userId,b.blogId FROM \n" +
                "t_blog AS a,(select blogId from (select blogId,count(*) hot from t_praise GROUP BY blogId ORDER BY hot DESC) hotblog limit 0,5) as b\n" +
                "WHERE a.blogId = b.blogId) as c\n" +
                "WHERE c.userId = d.userId;");
    }

}
