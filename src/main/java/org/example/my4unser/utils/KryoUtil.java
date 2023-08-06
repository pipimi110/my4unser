package org.example.my4unser.utils;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import org.objenesis.strategy.SingleInstantiatorStrategy;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;

public class KryoUtil {
    public static com.esotericsoftware.kryo.Kryo kryo = new com.esotericsoftware.kryo.Kryo();
    static {
        //Class is not registered: java.util.HashMap
        kryo.setRegistrationRequired(false);
        //Class cannot be created (missing no-arg constructor): com.rometools.rome.feed.impl.EqualsBean
        //DefaultInstantiatorStrategy.newInstantiatorOf
//        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
        kryo.setInstantiatorStrategy(new DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));//可行
//        kryo.setInstantiatorStrategy(new SingleInstantiatorStrategy());

    }
    public static byte[] kryoSer(Object argobj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Output output = new Output(bos);
        kryo.writeClassAndObject(output, argobj);
        output.flush();
        output.close();
        return bos.toByteArray();
    }
    public static Object kryoUnser(byte[] bytes) {
        ByteArrayInputStream bas = new ByteArrayInputStream(bytes);
        Input input = new Input(bas);
        Object obj = kryo.readClassAndObject(input);
        return obj;
    }
}
