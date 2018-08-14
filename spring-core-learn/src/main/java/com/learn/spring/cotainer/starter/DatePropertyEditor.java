package com.learn.spring.cotainer.starter;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * @author :lwy
 * @date 2018/6/18 17:15
 * <p>
 * 时间类型转换
 */
public class DatePropertyEditor extends PropertyEditorSupport {
    private String datePattern;

    @Override
    public void setAsText(String text) {
        DateTimeFormatter dateTimeFormatter=DateTimeFormat.forPattern(getDatePattern()) ;
        Date dateValue=dateTimeFormatter.parseDateTime(text).toDate();
        setValue(dateValue);
    }


    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }
}
