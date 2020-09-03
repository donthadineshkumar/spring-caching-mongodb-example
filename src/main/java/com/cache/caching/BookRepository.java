package com.cache.caching;


import java.net.UnknownHostException;
import java.util.List;

public interface BookRepository {
    List<Article> getArticles() throws UnknownHostException;
}
