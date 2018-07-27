package com.yujian.petmii.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fr on 2018/7/20.
 */

public class ToolsUtils {

    private ToolsUtils() {}

    public static boolean isValidPhone(String phone) {
        String regex = "^(13\\d|14[5,7]|15[0-3,5-9]|17[0,6-8]|18\\d)\\d{8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
