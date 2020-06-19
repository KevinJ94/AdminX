package com.project.admin.service;

import com.project.admin.entity.RoleEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface RoleService {

    public void addRole(RoleEntity roleEntity);

    public void  updateRole(RoleEntity roleEntity);

    public void deleteRoleById(int id);

    public List<RoleEntity> findAll();

    public RoleEntity findRoleById(int id);

    public Set<String> listRoleNames(String userName);

    public Set<RoleEntity> findRolesByUid(int id);

    RoleEntity findByName(String name);

}
