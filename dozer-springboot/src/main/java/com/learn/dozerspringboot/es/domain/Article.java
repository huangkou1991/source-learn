package com.learn.dozerspringboot.es.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;

/**
 * @author :lwy
 * @date 2018/6/2 8:18
 */
@Document(indexName = "blog", type = "article")
public class Article {

    @Id
    private String id;

    @MultiField(mainField = @Field(type = FieldType.String),
            otherFields = {@InnerField(index = not_analyzed, suffix = "verbatim",
                    type = FieldType.String)})
    private String title;

    //@Field(type = FieldType.Nested)
    private List<Author> authors;

    //@Field(type = FieldType.String, index = not_analyzed)
    private String[] tags;

    public Article() {
    }

    public Article(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String... tags) {
        this.tags = tags;
    }


    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", tags=" + Arrays.toString(tags) +
                '}';
    }
}
