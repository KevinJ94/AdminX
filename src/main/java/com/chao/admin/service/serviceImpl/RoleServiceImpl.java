package com.chao.admin.service.serviceImpl;

import com.chao.admin.dao.RoleDAO;
import com.chao.admin.entity.RoleEntity;
import com.chao.admin.entity.UserEntity;
import com.chao.admin.entity.UserRoleEntity;
import com.chao.admin.service.RoleService;
import com.chao.admin.service.UserRoleService;
import com.chao.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDAO roleDAO;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    UserService userService;

    @Override
    public void addRole(RoleEntity roleEntity) {
        roleDAO.save(roleEntity);
    }

    @Override
    public void updateRole(RoleEntity roleEntity) {
        roleDAO.save(roleEntity);
    }

    @Override
    public void deleteRoleById(int id) {
        roleDAO.deleteById(id);
    }

    @Override
    public List<RoleEntity> findAll() {
        return roleDAO.findAll();
    }

    @Override
    public RoleEntity findRoleById(int id) {
        return roleDAO.findById(id).get();
    }

    @Override
    public Set<String> listRoleNames(String userName) {
        HashSet<String> res = new HashSet<>();
        UserEntity user = userService.findByName(userName);

        if (user== null){
            return res;
        }

        //List<UserRoleEntity> userRoleEntityList = userRoleService.findUserRoleEntitiesByUid(user.getId());
        for (RoleEntity role : findRolesByUid(user.getId())){
            res.add(role.getName());
        }
        return res;
    }

    @Override
    public List<RoleEntity> findRolesByUid(int id) {
        List<RoleEntity> roleEntityList = new LinkedList<>();
        List<UserRoleEntity> userRoleEntityList = userRoleService.findUserRoleEntitiesByUid(id);
        for (UserRoleEntity ur : userRoleEntityList){
            roleEntityList.add(findRoleById(ur.getRid()));
        }
        return roleEntityList;
    }

    @Override
    public RoleEntity findByName(String name) {

        return roleDAO.findByName(name);
    }
}
