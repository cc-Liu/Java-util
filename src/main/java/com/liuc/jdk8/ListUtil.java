package com.liuc.jdk8;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ListUtil {

    public static void main(String[] args) {
//        test2();
    }

    public static List<User> testOne(){
        List<User> userList = new ArrayList<>();
        userList.add(new User("张三",10,"男","178"));
        userList.add(new User("王五",20,"女","168"));
        userList.add(new User("刘",25,"男","173"));
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
        /*
         * 提取List中的某一属性
         */
        List<String> nameList = userList.stream().map(User::getName).collect(Collectors.toList());

        /*
         * List过滤
         */
        List<User> list = userList.stream().filter(u -> u.getName().equals("王五")).collect(Collectors.toList());

        //findAny()方法返回从任何元素流而使用
        //findFirst()方法返回在第一个元素流
        //orElse 查询不到返回指定值
        User user3 = userList.stream().filter(u -> u.getSex().equals("男")).findAny().orElse(null);
        User user2 = userList.stream().filter(u -> u.getSex().equals("男")).findFirst().orElse(new User());

    }

    public static void test3() {
        List<User> userList = testOne();

        /*
         * 汇总操作
         */
        Integer reduce = userList.stream().map(User::getAge).reduce(0, Integer::sum);
        log.info(":{}", reduce);
    }

}
