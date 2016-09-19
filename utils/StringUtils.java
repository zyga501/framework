package framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class StringUtils {
    public static String convertNullableString(Object object) {
        if (object == null) {
            return new String();
        }

        return object.toString();
    }

    public static String convertNullableString(Object object, String defaultValue) {
        if (object == null) {
            return defaultValue;
        }

        return object.toString();
    }

    public static String generateRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String generateDate(String pattern, String timeZone) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return df.format(new Date(Long.valueOf(System.currentTimeMillis()).longValue()));
    }
}
