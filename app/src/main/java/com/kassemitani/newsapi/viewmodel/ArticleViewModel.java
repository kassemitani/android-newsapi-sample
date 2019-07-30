package com.kassemitani.newsapi.viewmodel;

import com.kassemitani.newsapi.model.Article;

public class ArticleViewModel {
    private String title;
    private String author;
    private String source;
    private String publishedAt;
    private String description;
    private String image;

    public ArticleViewModel(Article article) {
        this.title = article.getTitle();
        this.author = article.getAuthor();
        this.source = article.getSource().getName();
        this.publishedAt = article.getPublishedAt();
        this.description = article.getDescription();
        this.image = article.getUrlToImage();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getSource() {
        return source;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}
