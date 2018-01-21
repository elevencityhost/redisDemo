package com.example;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 十一城城主
 * @data 2018/1/21 23:03
 */
@Logger
public class JedisDemoFirst {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(JedisDemoFirst.class);

    public static void main(String[] args) {
        logger.info("测试一下记录日志的注解!!!");
    }
}
