package vn.iotstar.dao;

import java.util.List;
import vn.iotstar.entity.User;

public interface IUserDao {
    void insert(User user);
    void update(User user);
    void delete(int userId) throws Exception;
    User findById(int userId);
    List<User> findAll();
    List<User> findByUsername(String username);
    int count();
    List<User> findAll(int page, int pageSize);
}
