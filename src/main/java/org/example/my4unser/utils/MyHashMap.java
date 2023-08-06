package org.example.my4unser.utils;

import java.util.HashMap;

public class MyHashMap<K, V> extends HashMap<K, V> {
    public transient String testcmd;

    public V put(K key, V value) {
        super.put(key, value);
        System.out.println("testcmd:"+testcmd);
        System.out.println(111111111);
        try {
//                Runtime.getRuntime().exec("calc");
        } catch (Exception e) {
        }
        System.out.println(22222222);
        return null;
    }
}