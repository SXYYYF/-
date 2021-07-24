package com.hr.util;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author yaoyunfei
 * @data 2021/7/23 17:34
 */
@Component
public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
