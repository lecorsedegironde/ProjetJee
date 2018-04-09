package fr.grp404.projetjee.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Checker {

    private final static String DATE_FORMAT = "yyyy-MM-dd";

    public static boolean checkLogin(String login) {
        return login.length() > 5 && login.length() < 30;
    }

    public static boolean checkPwd(String pwd) {
        return pwd.length() > 8;
    }

    public static boolean checkMail(String mail) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(mail);
        return m.matches();
    }

    public static boolean checkBirthDate(String birthDate) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(birthDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
