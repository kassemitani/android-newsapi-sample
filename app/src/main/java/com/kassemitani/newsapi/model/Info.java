package com.kassemitani.newsapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Info {

    private String status;
    private int totalResults;
    @SerializedName("articles")
    private List<Article> articles;

    @Override
    public String toString() {
        return "Info{" +
                "status='" + status + '\'' +
                ", totalResults=" + totalResults +
                ", articles=" + articles +
                '}';
    }

    public Info() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
