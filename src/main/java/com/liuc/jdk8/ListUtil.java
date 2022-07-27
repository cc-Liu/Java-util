package com.liuc.jdk8;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ListUtil {

    public static void main(String[] args) {
        test2();
    }

    public static List<User> testOne(){
        List<User> userList = new ArrayList<>();
        userList.add(new User("张三",10,"男"));
        userList.add(new User("王五",20,"女"));
        userList.add(new User("刘",25,"男"));
        return userList;
    }


    public static void test(){
        List<User> userList = testOne();
        System.out.println("List普通方式遍历:");
        for (User user : userList) {
            log.info("List普通方式遍历:{}",user);
        }

        System.out.println("map拉姆达表达式遍历:");
        userList.forEach(u -> {
            u.getAge();
            u = new User();
            System.out.println(u);
        });

    }


    public static void test2() {
        List<User> userList = testOne();
        //提取List中的某一属性
        List<String> nameList = userList.stream().map(User::getName).collect(Collectors.toList());
        //log.info("提取List中的Name:{}", nameList);

        //List过滤
        List<User> list = userList.stream().filter(u -> u.getName().equals("王五")).collect(Collectors.toList());
        log.info("提取List中的Name:{}", list);
    }

}
