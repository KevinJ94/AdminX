package com.project.admin.utils;

import com.project.admin.entity.MenuEntity;
import com.project.admin.entity.TreeData;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.ArrayList;
import java.util.HashSet;
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
            findChildren(menuEntities,tree);
        }

        return treeDataList;
    }

    private static void findChildren(Set<MenuEntity> menuEntityHashSet,TreeData treeData){
        for (MenuEntity menu : menuEntityHashSet){
            if (treeData.getId().equals(menu.getPid())){
                TreeData t = new TreeData(menu.getId(),menu.getPid(),menu.getIcon(),menu.getTitle(),menu.getIndex());
                treeData.children.add(t);
                findChildren(menuEntityHashSet,t);
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
