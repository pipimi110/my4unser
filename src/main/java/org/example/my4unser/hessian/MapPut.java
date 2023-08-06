package org.example.my4unser.hessian;

import org.example.my4unser.utils.HessianUtil;
import org.example.my4unser.utils.MyHashMap;

import java.util.HashMap;

public class MapPut {
    public static void main(String[] args) throws Exception {
        MyHashMap attachments1 = new MyHashMap();
        attachments1.testcmd = "qwe";
        attachments1.put("1","2");
        Object obj = HessianUtil.hessianUnser(HessianUtil.hessianSer(attachments1));
        System.out.println(obj);
    }
}
