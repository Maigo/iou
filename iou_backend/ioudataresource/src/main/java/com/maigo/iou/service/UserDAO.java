package com.maigo.iou.service;

import java.util.List;

import com.maigo.iou.model.User;

public interface UserDAO {

    // data read access
    public List<User> findUserAll();

    public User findUserByUserId(int userId);

    public List<User> findUserByGroupId(int groupId);

    public User findUserByUserIdAndGroupId(int userId, int groupId);

    public List<User> findUserByEventId(int eventId);

    public User findUserByUserIdAndEventId(int userId, int eventId);

    // data write access
    public User createUser(User user);

    public User updateUserByUserId(User user, int userId);

    public void deleteUserByUserId(int userId);

}
