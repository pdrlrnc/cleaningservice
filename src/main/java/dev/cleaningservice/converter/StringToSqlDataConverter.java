package dev.cleaningservice.converter;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.core.convert.converter.Converter;
import org.thymeleaf.util.StringUtils;

@Component
public class StringToSqlDataConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source){
        if(StringUtils.isEmpty(source))
            return null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            java.util.Date parsedDate = dateFormat.parse(source);
            return new Date(parsedDate.getTime());
        } catch (ParseException parseException){
            throw new IllegalArgumentException("Could not parse date: " + source );
        }
    }
}
