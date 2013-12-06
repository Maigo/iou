package com.maigo.iou.service;

import java.util.List;

import com.maigo.iou.model.Role;

public interface RoleDAO {

    // data read access
    public List<Role> findRoleAll();

    public Role findRoleByRoleId(int roleId);

    public List<Role> findRoleByAccountId(int accountId);

    public Role findRoleByRoleIdAndAccountId(int roleId, int accountId);

    // data write access
    public Role createRole(Role role);

    public Role updateRoleByRoleId(Role role, int roleId);

    public void deleteRoleByRoleId(int roleId);

}
