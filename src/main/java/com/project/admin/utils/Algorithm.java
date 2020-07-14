package com.project.admin.utils;

import com.project.admin.entity.MenuEntity;
import com.project.admin.entity.RoleEntity;
import com.project.admin.model.TreeData;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Algorithm {
    public static String encodePassword(String password, String salt){

        int times = 2;
        String algorithmName = "md5";
        return new SimpleHash(algorithmName, password, salt, times).toString();
    }

    public static String generateSalt(){
      return  new SecureRandomNumberGenerator().nextBytes().toString();
    }


    public static List<TreeData> constructMenuTree(Set<MenuEntity> menuEntities){

        List<TreeData> treeDataList = new ArrayList<>();
        for (MenuEntity menu: menuEntities){
            if (menu.getPid()==null){
                treeDataList.add(new TreeData(menu.getId(),menu.getPid(),menu.getIcon(),menu.getTitle(),menu.getIndex()));
            }
        }

        for (TreeData tree : treeDataList){
            findMenuChildren(menuEntities,tree);
        }

        treeDataList.sort(new Comparator<TreeData>() {
            @Override
            public int compare(TreeData o1, TreeData o2) {
                return Integer.parseInt(o1.index) - Integer.parseInt(o2.index);
            }
        });

        return treeDataList;
    }

    private static void findMenuChildren(Set<MenuEntity> menuEntityHashSet,TreeData treeData){
        for (MenuEntity menu : menuEntityHashSet){
            if (treeData.getId().equals(menu.getPid())){
                TreeData t = new TreeData(menu.getId(),menu.getPid(),menu.getIcon(),menu.getTitle(),menu.getIndex());
                treeData.children.add(t);
                findMenuChildren(menuEntityHashSet,t);
            }

        }
    }

    public static List<TreeData> constructRoleTree(List<RoleEntity> roleEntityList){

        List<TreeData> treeDataList = new ArrayList<>();
        for (RoleEntity role: roleEntityList){
            if (role.getPid()==null){
                treeDataList.add(new TreeData(role.getId(),role.getPid(),role.getName(),role.getDesc()));
            }
        }

        for (TreeData tree : treeDataList){
            findRoleChildren(roleEntityList,tree);
        }

        return treeDataList;
    }

    private static void findRoleChildren(List<RoleEntity> roleEntityList, TreeData treeData){
        for (RoleEntity role : roleEntityList){
            if (treeData.getId().equals(role.getPid())){
                TreeData t = new TreeData(role.getId(),role.getPid(),role.getName(),role.getDesc());
                treeData.children.add(t);
                findRoleChildren(roleEntityList,t);
            }

        }
    }


    public static void printTreeData(TreeData treeData,int num){
        System.out.println(treeData.title);
        if (treeData.children.size() != 0){
            for (int i=0;i<num;i++){
                System.out.print("--");
            }
            for (TreeData node : treeData.children){
                printTreeData(node,num+1);
            }
        }
    }

}
