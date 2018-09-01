package dao;

import entity.User;

import java.util.List;

public class UserInfoDaoImpl extends BaseDao<User> implements UserInfoDao {
    @Override
    public List<User> getAllUser() {
        return executeQuery("select * from t_user");
    }
    @Override
    public List<User> getUser(int userId) {
        return executeQuery("select * from t_user where telNum = ?",new Object[]{userId});
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
        return executeUpdate("UPDATE t_user set userName = ? ,telNum=?,email=?,sex=?,school=?,sign=?,address=? where telNum=?",new Object[]{user.getUserName(),user.getTelNum(),user.getEmail(),user.getSex(),user.getSchool(),user.getSign(), user.getAddress(),telNum});
    }

    @Override
    public boolean getUserName(String username) {
        return false;
    }

    @Override
    public boolean getUserIsLock(String telNum) {
        return false;
    }
}
