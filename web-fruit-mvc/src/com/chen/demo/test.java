package com.chen.demo;

import org.junit.Test;

/**
 * ClassName: test
 * Package: com.chen.demo
 * Description:
 *
 * @Author: Night
 * @Create: 2023/9/29 - 16:48
 * @Version: 1.0
 */
public class test {
    @Test
    public void test(){
       String nullString = null;
        System.out.println(nullString == null);
    }

    @Test
    public void test2(){
        String var1 = "";
        System.out.println((var1 == null) || ("".equals(var1)));
    }
    
    @Test
    public void test3(){
        
    }
}
