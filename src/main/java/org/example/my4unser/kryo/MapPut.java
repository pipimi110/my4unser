package org.example.my4unser.kryo;

import org.example.my4unser.utils.MyHashMap;
import org.example.my4unser.utils.KryoUtil;

import java.util.HashMap;

public class MapPut {
    public static void main(String[] args) throws Exception {
        MyHashMap attachments1 = new MyHashMap();
        attachments1.testcmd = "qwe";
        attachments1.put("1","2");
        KryoUtil.kryoUnser(KryoUtil.kryoSer(attachments1));
    }
}
