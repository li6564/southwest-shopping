package com;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author：linan
 * @Date：2023/8/13 18:31
 */
public class Test {
    private static List<Integer> appList = new ArrayList<>();


    public static boolean test(){
        appList.forEach(app->{
            System.out.println(app);
        });
        return true;
    }

    public static void main(String[] args) {
        test();
    }
}
