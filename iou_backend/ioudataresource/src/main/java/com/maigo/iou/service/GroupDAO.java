package com.maigo.iou.service;

import java.util.List;

import com.maigo.iou.model.Group;

public interface GroupDAO {

    // data read access
    public List<Group> findGroupAll();

    public Group findGroupByGroupId(int groupId);

    public List<Group> findGroupByUserId(int userId);

    public Group findGroupByGroupIdAndUserId(int groupId, int userId);

    // data write access
    public Group createGroup(Group group);

    public Group updateGroupByGroupId(Group group, int groupId);

    public void deleteGroupByGroupId(int groupId);

}
