package com.trendyol.tydeliveryandcampaign.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


@UtilityClass
public class DateUtil {

    @SneakyThrows
    public Date integerToDate(Integer date){
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
        Date formattedDate = originalFormat.parse(date.toString());
        return formattedDate;
    }

    @SneakyThrows
    public Integer dateToInteger(LocalDate localDate){
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(originalFormat.format(date));
    }


    public boolean checkDateRange(Integer startDate, Integer endDate){
        Date currentDate = new Date();

        if(currentDate.after(DateUtil.integerToDate(startDate)) && currentDate.before(DateUtil.integerToDate(endDate)))
            return true;

        return false;
    }
}
