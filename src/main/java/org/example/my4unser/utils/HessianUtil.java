package org.example.my4unser.utils;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class HessianUtil {
    public static byte[] hessianSer(Object argobj) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(os);
        //Serialized class javax.naming.ldap.Rdn$RdnEntry must implement java.io.Serializable
// 这样就不用都要求实现java.io.Serializable
        output.getSerializerFactory().setAllowNonSerializable(true);
        output.writeObject(argobj);
        output.getBytesOutputStream().flush();
        output.completeMessage();
        output.close();
        byte[] bytes = os.toByteArray();
        return bytes;
    }
    public static Object hessianUnser(byte[] bytes) throws Exception {
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        Hessian2Input input = new Hessian2Input(is);
        Object obj = input.readObject();
//        System.out.println(obj);
        return obj;
    }
}
