package com.kassemitani.newsapi.data;

public class Constants {

    private static final String BASE_URL = "https://newsapi.org/";
    private static final String API_KEY = "f116d93c777c4dfe843a62934df394c9";
    private static final String KEYWORD = "android";
    private static final String DATE_FROM = "2019-07-19";
    private static final String DATE_TO = "2019-07-31";
    private static final String SORT_BY = "popularity";

    public static String getBaseUrl() { return BASE_URL; }

    public static String getApiKey() {
        return API_KEY;
    }

    public static String getKEYWORD() {
        return KEYWORD;
    }

    public static String getDateFrom() {
        return DATE_FROM;
    }

    public static String getDateTo() {
        return DATE_TO;
    }

    public static String getSortBy() {
        return SORT_BY;
    }
}
