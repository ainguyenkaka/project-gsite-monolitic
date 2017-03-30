package app.discovery.com.idiscovery;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainValidation {

    private static final String DATE_PATTERN = "^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$";
    private static final String TIME_PATTERN = "^\\d{1,2}:\\d{1,2}$";

    public static boolean checkEmpty(String text) {
        if(text == null)
            return true;
        return text.isEmpty();
    }

    public static boolean validateDate(String text) {
        if(text == null)
            return false;
        Pattern p = Pattern.compile(DATE_PATTERN);
        Matcher m = p.matcher(text);
        return m.matches();
    }

    public static boolean validateTime(String text) {
        if(text == null)
            return false;
        Pattern p = Pattern.compile(TIME_PATTERN);
        Matcher m = p.matcher(text);
        return m.matches();
    }
}
