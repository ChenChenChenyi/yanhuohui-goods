package com.chenyi.yanhuohui.goods.mytest;

import com.chenyi.yanhuohui.goods.goods.GoodsSpu;
import org.junit.Test;

import java.lang.reflect.Field;

public class ReflectionTest {
    @Test
    public void test() {
        System.out.println(123);
    }

    @Test
    public void testField() throws IllegalAccessException {
        GoodsSpu goodsSpu = new GoodsSpu();
        goodsSpu.setSpuName("牙膏");
        Field[] fields = goodsSpu.getClass().getDeclaredFields();
        for(Field field:fields){
            String fieldName = field.getName();
            field.setAccessible(true);
            Object object = field.get(goodsSpu);
            if(object instanceof String){
                String value = (String) object;
                System.out.println("字段：" + fieldName + "的值为：" + value);
            }
        }
    }
}

