package vn.iotstar.services.impl;

import java.util.List;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.dao.impl.UserDao;
import vn.iotstar.entity.User;
import vn.iotstar.services.IUserService;

public class UserService implements IUserService {
    IUserDao dao = new UserDao();

    @Override
    public void insert(User user) { dao.insert(user); }
    @Override
    public void update(User user) { dao.update(user); }
    @Override
    public void delete(int userId) throws Exception { dao.delete(userId); }
    @Override
    public User findById(int userId) { return dao.findById(userId); }
    @Override
    public List<User> findAll() { return dao.findAll(); }
    @Override
    public List<User> findByUsername(String username) { return dao.findByUsername(username); }
    @Override
    public int count() { return dao.count(); }
    @Override
    public List<User> findAll(int page, int pageSize) { return dao.findAll(page, pageSize); }
}
