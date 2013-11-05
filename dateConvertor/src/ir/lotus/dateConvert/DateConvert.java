//-------_____________________________----------//
//------|CopyRight By Ali Khandani    |---------//
//------|LOTUS SOFTWARE               |---------//
//------|EMail:alKhadnani@Gmail.com   |---------//
//------|2008-2013                    |---------//
//------|_____________________________|---------//
//----------------------------------------------//
package ir.lotus.dateConvert;

import com.ghasemkiani.util.icu.PersianCalendar;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.ULocale;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateConvert
{

    /**
     *
     * @param dateJalali tarikh jalali ra ba format <b>yyyy/mm/dd</b>
     * @param time ra ba format <b>HH:MM</b>
     * @return date <b>java.sql.Date</b>
     */
    public static Date jalaliToMiladiSql(String dateJalali, String time)
    {
        //Cut From String 
        String syear = dateJalali.substring(0, dateJalali.indexOf("/")).trim();
        String smonth = dateJalali.substring(dateJalali.indexOf("/") + 1, dateJalali.indexOf("/", dateJalali.indexOf("/") + 1)).trim();
        String sday = dateJalali.substring(dateJalali.indexOf("/", dateJalali.indexOf("/") + 1) + 1, dateJalali.length()).trim();
        int hour = 0;
        int min = 0;
        //chek First 0
        if (smonth.startsWith("0") || smonth.startsWith("۰"))
        {
            smonth = smonth.substring(1, 2);
        }
        if (sday.startsWith("0") || sday.startsWith("۰"))
        {
            sday = sday.substring(1, 2);
        }

        int year = Integer.parseInt(syear);
        int month = Integer.parseInt(smonth);
        int day = Integer.parseInt(sday);
        month--;

        if (time != null)
        {
            String sHour = time.substring(0, time.indexOf(":")).trim();
            String sMin = time.substring(time.indexOf(":") + 1, time.length()).trim();
            hour = Integer.parseInt(sHour);
            min = Integer.parseInt(sMin);
        } else
        {
            hour = 0;
            min = 0;
        }



        com.ibm.icu.util.Calendar cal = new com.ghasemkiani.util.icu.PersianCalendar();
        com.ibm.icu.util.Calendar gcal = new com.ibm.icu.util.GregorianCalendar();
        cal.set(com.ibm.icu.util.Calendar.ERA, com.ghasemkiani.util.icu.PersianCalendar.AH);
        cal.set(com.ibm.icu.util.Calendar.YEAR, year);
        cal.set(com.ibm.icu.util.Calendar.MONTH, month);
        cal.set(com.ibm.icu.util.Calendar.DAY_OF_MONTH, day);
        cal.set(com.ibm.icu.util.Calendar.HOUR, hour);
        cal.set(com.ibm.icu.util.Calendar.MINUTE, min);
        //dateStart = new Date(gcal.getTimeInMillis());
        com.ibm.icu.text.DateFormat df = gcal.getDateTimeFormat(3, -1, ULocale.ENGLISH);

        java.util.Date dateUtil = cal.getTime();
        Date datesql = new Date(dateUtil.getTime());

        return datesql;
    }

    public static int getJalaliMonth(String dateJalali)
    {
        String smonth = dateJalali.substring(dateJalali.indexOf("/") + 1, dateJalali.indexOf("/", dateJalali.indexOf("/") + 1)).trim();
        if (smonth.startsWith("0") || smonth.startsWith("۰"))
        {
            smonth = smonth.substring(1, 2);
        }
        try
        {
            int month = Integer.parseInt(smonth);
            return month;
        } catch (NumberFormatException ex)
        {
            return 1;
        }

    }

    public static int getJalaliMonth(String dateJalali, int def)
    {
        String smonth = dateJalali.substring(dateJalali.indexOf("/") + 1, dateJalali.indexOf("/", dateJalali.indexOf("/") + 1)).trim();
        if (smonth.startsWith("0") || smonth.startsWith("۰"))
        {
            smonth = smonth.substring(1, 2);
        }
        try
        {
            int month = Integer.parseInt(smonth);
            return month;
        } catch (NumberFormatException ex)
        {
            return def;
        }

    }

    public static String getFullDateJalali(java.util.Date date)
    {

        PersianCalendar pc = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
        DateFormat df = pc.getDateTimeFormat(4, 4, new ULocale("fa", "IR", ""));
        String result = df.format(date).substring(7);
        return result;
    }

    public static int getJalaliYear(String dateJalali)
    {
        String syear = dateJalali.substring(0, dateJalali.indexOf("/")).trim();
        try
        {
            int year = Integer.parseInt(syear);

            return year;
        } catch (NumberFormatException ex)
        {
            return 2013;
        }
    }

    public static int getJalaliYear(String dateJalali, int def)
    {
        String syear = dateJalali.substring(0, dateJalali.indexOf("/")).trim();
        try
        {
            int year = Integer.parseInt(syear);

            return year;
        } catch (NumberFormatException ex)
        {
            return def;
        }
    }

    public static int getJalaliDay(String dateJalali)
    {
        String sday = dateJalali.substring(dateJalali.indexOf("/", dateJalali.indexOf("/") + 1) + 1, dateJalali.length()).trim();

        if (sday.startsWith("0") || sday.startsWith("۰"))
        {
            sday = sday.substring(1, 2);
        }
        try
        {
            int day = Integer.parseInt(sday);
            return day;
        } catch (NumberFormatException ex)
        {
            return 15;
        }
    }

    public static int getJalaliDay(String dateJalali, int def)
    {
        String sday = dateJalali.substring(dateJalali.indexOf("/", dateJalali.indexOf("/") + 1) + 1, dateJalali.length()).trim();

        if (sday.startsWith("0") || sday.startsWith("۰"))
        {
            sday = sday.substring(1, 2);
        }
        try
        {
            int day = Integer.parseInt(sday);
            return day;
        } catch (NumberFormatException ex)
        {
            return def;
        }
    }

    public static Date jalaliToMiladiSql(String dateJalali)
    {
        //Cut From String 
        String syear = dateJalali.substring(0, dateJalali.indexOf("/")).trim();
        String smonth = dateJalali.substring(dateJalali.indexOf("/") + 1, dateJalali.indexOf("/", dateJalali.indexOf("/") + 1)).trim();
        String sday = dateJalali.substring(dateJalali.indexOf("/", dateJalali.indexOf("/") + 1) + 1, dateJalali.length()).trim();

        //chek First 0
        if (smonth.startsWith("0") || smonth.startsWith("۰"))
        {
            smonth = smonth.substring(1, 2);
        }
        if (sday.startsWith("0") || sday.startsWith("۰"))
        {
            sday = sday.substring(1, 2);
        }

        int year = Integer.parseInt(syear);
        int month = Integer.parseInt(smonth);
        int day = Integer.parseInt(sday);

        month--;

        com.ibm.icu.util.Calendar cal = new com.ghasemkiani.util.icu.PersianCalendar();
        com.ibm.icu.util.Calendar gcal = new com.ibm.icu.util.GregorianCalendar();
        cal.set(com.ibm.icu.util.Calendar.ERA, com.ghasemkiani.util.icu.PersianCalendar.AH);
        cal.set(com.ibm.icu.util.Calendar.YEAR, year);
        cal.set(com.ibm.icu.util.Calendar.MONTH, month);
        cal.set(com.ibm.icu.util.Calendar.DAY_OF_MONTH, day);

        com.ibm.icu.text.DateFormat df = gcal.getDateTimeFormat(3, -1, ULocale.ENGLISH);

        java.util.Date dateUtil = cal.getTime();
        Date datesql = new Date(dateUtil.getTime());

        return datesql;
    }

    public static String miladiToJalali(Date dateStart)
    {
        PersianCalendar pc = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
        DateFormat df = pc.getDateTimeFormat(DateFormat.SHORT, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        String result = df.format(new java.util.Date(dateStart.getTime()));
        return result.substring(0, 10);
    }

    public static String miladiToJalali(java.util.Date dateStart)
    {
        PersianCalendar pc = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
        DateFormat df = pc.getDateTimeFormat(DateFormat.SHORT, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        String result = df.format(dateStart);
        return result.substring(0, 10);
    }

    public static String miladiToJalali(Timestamp date)
    {


        PersianCalendar pc = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
        DateFormat df = pc.getDateTimeFormat(DateFormat.SHORT, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        String result = df.format(new java.util.Date(date.getTime()));
        return result.substring(0, 10);

    }

    public static String miladiToJalali(long date)
    {


        PersianCalendar pc = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
        DateFormat df = pc.getDateTimeFormat(DateFormat.SHORT, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        String result = df.format(new java.util.Date(date));
        return result.substring(0, 10);

    }

    public static java.util.Date dayToMilli(int day)
    {
        Calendar calender1 = Calendar.getInstance();
        calender1.add(Calendar.DATE, day);
        java.util.Date dateNow = calender1.getTime();
        return dateNow;
    }

    public static String dateNow(int dayDif)
    {
        PersianCalendar pc = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
        DateFormat df = pc.getDateTimeFormat(DateFormat.FULL, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        String result = df.format(DateConvert.dayToMilli(dayDif));
        return result.substring(0, result.length() - 8);//+" "+result1;
    }

    /*
     * tarikh ra set mikonim va baed az set kardan tedad rooz dar mah ra
     * namayesh midahad .
     */
    public static int getDayInMonth(Date _date)
    {
        PersianCalendar pc = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
        pc.getDateTimeFormat(DateFormat.SHORT, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        pc.setTime(_date);
        return pc.getActualMaximum(PersianCalendar.DAY_OF_MONTH);
    }
    /*
     * tarikh ra set mikonim va baed az set kardan tedad rooz dar mah ra
     * namayesh midahad .
     */

    public static int getDayInMonth(String _date)
    {
        Date date = jalaliToMiladiSql(_date);
        PersianCalendar pc = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
        pc.getDateTimeFormat(DateFormat.SHORT, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        pc.setTime(date);
        return pc.getActualMaximum(PersianCalendar.DAY_OF_MONTH);
    }

    /*
     * tarikh ra dade va avalin rooz hafte dar mah ra migirim .
     */
    public static int getFirstWeekdayInMonth(String _date)
    {
        Date date = jalaliToMiladiSql(_date);
        PersianCalendar pc = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
        pc.getDateTimeFormat(DateFormat.SHORT, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        pc.setTime(date);
        pc.set(PersianCalendar.DAY_OF_MONTH, 1);
        return pc.get(PersianCalendar.DAY_OF_WEEK);
    }

    /*
     * tarikh ra dade va avalin rooz hafte dar mah ra migirim .
     */
    public static int getFirstWeekdayInMonth(Date _date)
    {
        PersianCalendar pc = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
        pc.getDateTimeFormat(DateFormat.SHORT, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        pc.setTime(_date);
        pc.set(PersianCalendar.DAY_OF_MONTH, 1);
        return pc.get(PersianCalendar.DAY_OF_WEEK);
    }

    /*
     * tarikh ra dade va rooz hafte dar mah ra migirim .
     */
    public static int getWeekDayInMonth(Date _date)
    {
        PersianCalendar pc = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
        pc.getDateTimeFormat(DateFormat.SHORT, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        pc.setTime(_date);
        return pc.get(PersianCalendar.DAY_OF_WEEK);
    }

    /*
     * tarikh ra dade va rooz hafte dar mah ra migirim .
     */
    public static int getWeekDayInMonth(String _date)
    {
        Date date = jalaliToMiladiSql(_date);
        PersianCalendar pc = new PersianCalendar(TimeZone.getTimeZone("Asia/Tehran"));
        pc.getDateTimeFormat(DateFormat.SHORT, DateFormat.DEFAULT, new ULocale("fa", "IR", ""));
        pc.setTime(date);
        return pc.get(PersianCalendar.DAY_OF_WEEK);
    }

    public static String addDay2Date(java.util.Date date,int day)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(date); // Now use today date.
        c.add(Calendar.DATE, 5); // Adding 5 days
        String output = sdf.format(c.getTime());
        return output;
    }
}
