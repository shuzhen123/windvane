package dianfan.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDate {

	/*private static String FORMATSTR = "HH:mm";
    public static void main(String[] args) {
        System.out.println(StringToDate("22:22",FORMATSTR));
    }*/
     
    /**      
     * 字符串转换到时间格式
     * @param dateStr 需要转换的字符串
     * @param formatStr 需要格式的目标字符串
     * @return Date 返回转换后的时间
     * @throws ParseException 转换异常
     */
    public static Date StringToDate(String dateStr,String formatStr){
        DateFormat sdf=new SimpleDateFormat(formatStr);
        Date date=null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
