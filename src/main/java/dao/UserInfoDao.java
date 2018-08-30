package dao;

import entity.User;

import java.util.List;

public interface UserInfoDao {
    //获取用户列表
    List<User> getAllUser();
    //根据主键查看用户信息
    User getUser(int userId);
    //根据手机号和密码验证用户是否登录成功
    boolean getUser(int telNum,String password);
    //添加用户信息
    int insertUser(User user);
    //更新用户信息
    int updateUser(User user);
    //查看用户名是否存在
    int getUserName(String username);
}
