package com.liuc.jdk8;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

    public static void main(String[] args) {
        test();
    }

    public static void test(){
        User user = new User();
        user.setName("张三");
        user.setAge(10);
        user.setSex("男");

        User user2 = new User();
        user2.setName("李四");
        user2.setAge(20);
        user2.setSex("男");

        User user3 = new User();
        user3.setName("王五");
        user3.setAge(20);
        user3.setSex("男");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);
        userList.add(user3);

        userList.forEach(u -> {
            u.getAge();
            u = new User();
            System.out.println(u);
        });

    }

}
