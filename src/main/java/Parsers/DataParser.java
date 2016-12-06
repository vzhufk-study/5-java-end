package Parsers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * (╯°□°）╯︵ ┻━┻
 * Created by Zhufyak V.V.
 * zhufyakvv@gmail.com
 * github.com/zhufyakvv
 * 02.11.2016
 **/
public class DataParser {

    //TODO take parser to other class and package
    public static GregorianCalendar parseToDateFromString(String text, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date result = new Date();
        try {
            result = dateFormat.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(result);
        return cal;
    }

    public static GregorianCalendar parseToDateFromStringShort(String text) throws ParseException {
        return  parseToDateFromString(text, "dd-mm-yyyy");
    }

    public static String parseGregorianToString(GregorianCalendar date, String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date.getTime());
    }

    public static String parseGregorianToString(GregorianCalendar date){
        return parseGregorianToString(date, "dd-mm-yyyy");
    }

    public static String parseGregorianToMySqlDate(GregorianCalendar date){
        return "STR_TO_DATE("+parseGregorianToString(date)+")";
    }


    public static String parseGregorianToMySqlDateString(GregorianCalendar date){
        return parseGregorianToString(date, "yyyymmdd");
    }

}
