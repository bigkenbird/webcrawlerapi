package com.ken.webcrawlerapi.util;

public class UrlUtil {

    private static final String PROFILE_URL_BY_USERNAME_TEMPLATE = "https://www.instagram.com/%s/";

    public static String getProfileUrlByUserName(String userName) {
        return String.format(PROFILE_URL_BY_USERNAME_TEMPLATE, userName);
    }
}
