package com.alibaba.dubbo.demo.provider;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Daxin on 2017/10/22.
 */
public class FileTest {
    private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(2, 0.75f);

    public static void main(String[] args) throws InterruptedException {
        File file = new File("C:\\Users\\zhenghangjin/.dubbo/dubbo-registry-demo-consumer-106.13.35.40:2181.cache");
        System.out.println(file.exists());
    }
}