package com.tag.configurations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class LoggerConfig{
    public static Logger getLogger(Class classname) {
        return LoggerFactory.getLogger(classname);
    }
}