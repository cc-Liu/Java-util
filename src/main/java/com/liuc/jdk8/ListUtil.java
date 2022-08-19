package com.liuc.jdk8;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class ListUtil {

    public static void main(String[] args) {
        test3();
    }

    public static List<User> testOne(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(1,"张三",10,"男","178,190"));
        userList.add(new User(2,"王五",20,"女","168"));
        userList.add(new User(3,"刘",25,"男","173"));
        return userList;
    }


    /**
     * @Author liuc
     * @Description 遍历方式
     * @Date 2022/8/19 14:24
     **/
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

    /**
     * @Author liuc
     * @Description 提取属性/转换属性
     * @Date 2022/8/19 14:24
     * @Param []
     * @return void
     **/
    public static void test2() {
        List<User> userList = testOne();
        /*
         * 提取List中的某一属性
         */
        List<String> nameList = userList.stream().map(User::getName).collect(Collectors.toList());

        /*
         * 提取List中的某一属性并且转换数据类型
         */
        //List<Integer> heightList = userList.stream().map(User::getHeight).map(Integer::valueOf).collect(Collectors.toList());

        //1:先转成一个String
        String collect = userList.stream().map(User::getHeight).collect(Collectors.joining(","));
        //2:再转成List<Integer>
        List<Integer> heightList = Arrays.asList(collect.split(",")).stream().map(Integer::valueOf).collect(Collectors.toList());
        log.info(":{}", heightList);

        /*
         * List过滤
         */
        List<User> list = userList.stream().filter(u -> u.getName().equals("王五")).collect(Collectors.toList());

        //findAny()方法返回从任何元素流而使用
        //findFirst()方法返回在第一个元素流
        //orElse 查询不到返回指定值
        User user3 = userList.stream().filter(u -> u.getSex().equals("男")).findAny().orElse(null);
        User user2 = userList.stream().filter(u -> u.getSex().equals("男")).findFirst().orElse(new User());

        /*
         * anyMatch：判断的条件里，任意一个元素成功，返回true
         * allMatch：判断条件里的元素，所有的都是，返回true
         * noneMatch：与allMatch相反，判断条件里的元素，所有的都不是，返回true
         */
        boolean isMatch = userList.stream().anyMatch(u -> u.getSex() == "男");
        boolean isMatch2 = userList.stream().noneMatch(u -> u.getSex().isEmpty());

        /*
         * 集合排序
         * Comparator.comparing(类::属性一).reversed();
         * Comparator.comparing(类::属性一,Comparator.reverseOrder());
         * 两种排序是完全不一样的,一定要区分开来 1 是得到排序结果后再排序,2是直接进行排序,很多人会混淆导致理解出错,2更好理解,建议使用2
         * https://www.cnblogs.com/zhangliang88/p/14341348.html
         *
         */
        List<User> collect1 = userList.stream().sorted(Comparator.comparing(User::getUserId,Comparator.naturalOrder())).collect(Collectors.toList());
        log.info("collect1:{}", collect1);

    }

    /**
     * @Author liuc
     * @Description 汇总操作
     * @Date 2022/8/19 10:23
     **/
    public static void test3() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1,"张三",10,"男","178"));
        userList.add(new User(2,"张三",10,"男","178"));
        userList.add(new User(3,"张三2",10,"男","180"));
        userList.add(new User(4,"张三3",10,"男","175"));

        /*
         * 汇总操作
         * 需要先判断是否为null
         * https://blog.csdn.net/Hatsune_Miku_/article/details/73414406
         */
        Integer reduce = userList.stream().map(User::getAge).reduce(0, Integer::sum);
        int sum = userList.stream().mapToInt(t -> Objects.isNull(t.getUserId()) ? 0 : t.getUserId()).sum();
//        log.info("sum:{}", sum);

        //按姓名和年龄(相加)分组
        Map<String, Integer> collect = userList.stream().collect(Collectors.groupingBy(User::getHeight,Collectors.summingInt(User::getAge)));

        log.info("collect:{}", collect);
    }

}
