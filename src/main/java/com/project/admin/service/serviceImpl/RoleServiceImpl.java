package com.project.admin.service.serviceImpl;

import com.project.admin.dao.RoleDAO;
import com.project.admin.dao.UserRoleDAO;
import com.project.admin.entity.RoleEntity;
import com.project.admin.model.AllocRole;
import com.project.admin.model.TreeData;
import com.project.admin.entity.UserEntity;
import com.project.admin.entity.UserRoleEntity;
import com.project.admin.service.RoleService;
import com.project.admin.service.UserRoleService;
import com.project.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDAO roleDAO;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    UserService userService;

    @Autowired
    UserRoleDAO userRoleDAO;

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
    public List<TreeData> listAll() {
        List<RoleEntity> roleEntityList = roleDAO.findAll();

        return null;
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
    public List<RoleEntity> listRoleByUser(String userName) {
        List<RoleEntity> res = new ArrayList<>();
        UserEntity user = userService.findByName(userName);

        if (user== null){
            return res;
        }

        //List<UserRoleEntity> userRoleEntityList = userRoleService.findUserRoleEntitiesByUid(user.getId());
        for (RoleEntity role : findRolesByUid(user.getId())){
            if (!res.contains(role)){
                res.add(role);
            }
        }
        return res;
    }

    @Override
    public Set<RoleEntity> findRolesByUid(int id) {
        Set<RoleEntity> roleEntityList = new HashSet<>();
        List<UserRoleEntity> userRoleEntityList = userRoleService.findUserRoleEntitiesByUid(id);
        for (UserRoleEntity ur : userRoleEntityList){
            RoleEntity roleEntity = findRoleById(ur.getRid());
            roleEntityList.add(roleEntity);
            findChildrenRoles(roleEntity,roleEntityList);
        }
        return roleEntityList;
    }

    private void findChildrenRoles(RoleEntity roleEntity,Set<RoleEntity> roleEntitySet){
        List<RoleEntity> roleEntities = roleDAO.findRoleEntitiesByPid(roleEntity.getId());
        if(roleEntities.size() != 0){
            roleEntitySet.addAll(roleEntities);
            for (RoleEntity role : roleEntities){
                findChildrenRoles(role,roleEntitySet);
            }
        }
    }


    @Override
    public RoleEntity findByName(String name) {

        return roleDAO.findByName(name);
    }

    @Override
    public int findLevel(RoleEntity roleEntity) {
        if (roleEntity.getPid() == null){
            return 0;
        }
        RoleEntity p = findRoleById(roleEntity.getPid());
        return p.getLevel()+1;
    }

    @Transactional
    @Override
    public void allocRoles(AllocRole allocRole) {
        //int topLevel = findTopLevel(allocRole);
        List<UserRoleEntity> rids = new ArrayList<>();
        for (Integer i : allocRole.getRids()){
           //RoleEntity r = findRoleById(i);
           UserRoleEntity t = new UserRoleEntity();
           t.setRid(i);
           t.setUid(allocRole.getUid());
           rids.add(t);
        }

        userRoleDAO.deleteAllByUid(allocRole.getUid());
        userRoleDAO.saveAll(rids);


    }



}
