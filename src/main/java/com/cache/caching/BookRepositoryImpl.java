package com.cache.caching;

import com.mongodb.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.MongoCollectionUtils;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
public class BookRepositoryImpl implements BookRepository{

    @Override
    @Cacheable("articles")
    public List<Article> getArticles() throws UnknownHostException {
        /*Consumer<Documet> printConsumer = new Consumer<Article>() {
            @Override
            public void accept(final Article document) {
                System.out.println(document.toJson());
            }
        };*/
        simulateSlowService();
        MongoClient mongoClient
                = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        DB db = mongoClient.getDB("Mart");
        DBCollection collection =  db.getCollection("articles");
        DBCursor cursor = collection.find();
        List<Article> list= new ArrayList<>();
        while (cursor.hasNext()) {
           Article article = new Article();
           DBObject dbObject = cursor.next();
           article.setId((Double) dbObject.get("_id"));
           article.setSubject((String) dbObject.get("subject"));
           list.add(article);
        }
        return list;
    }
    // Don't do this at home
    private void simulateSlowService() {
        try {
            long time = 2000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
