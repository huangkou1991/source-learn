package com.learn.dozerspringboot.es.test;

import com.learn.dozerspringboot.es.config.Config;
import com.learn.dozerspringboot.es.domain.Article;
import com.learn.dozerspringboot.es.domain.Author;
import com.learn.dozerspringboot.es.service.ArticleService;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.regexpQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author :lwy
 * @date 2018/6/2 9:18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class EsTest {


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ArticleService articleService;

    private final Author johnSmith = new Author("John Smith");
    private final Author johnDoe = new Author("John Doe");

    //@Before
    public void before() {
        elasticsearchTemplate.deleteIndex(Article.class);
        elasticsearchTemplate.createIndex(Article.class);
        // don't call putMapping() to test the default mappings

        Article article = new Article("Spring Data Elasticsearch");
        article.setAuthors(asList(johnSmith, johnDoe));
        article.setTags("elasticsearch", "spring data");
        articleService.save(article);

        article = new Article("Search engines");
        article.setAuthors(asList(johnDoe));
        article.setTags("search engines", "tutorial");
        articleService.save(article);

        article = new Article("Second Article About Elasticsearch");
        article.setAuthors(asList(johnSmith));
        article.setTags("elasticsearch", "spring data");
        articleService.save(article);

        article = new Article("Elasticsearch Tutorial");
        article.setAuthors(asList(johnDoe));
        article.setTags("elasticsearch");
        articleService.save(article);
    }


    @Test
    public void givenArticleService_whenSaveArticle_thenIdIsAssigned() {
        final List<Author> authors = asList(new Author("John Smith"), johnDoe);

        Article article = new Article("Making Search Elastic");
        article.setAuthors(authors);

        article = articleService.save(article);
        assertNotNull(article.getId());
    }

    @Test
    public void givenPersistedArticles_whenSearchByAuthorsName_thenRightFound() {

        final Page<Article> articleByAuthorName =
                articleService.findByAuthorName(johnDoe.getName(), new PageRequest(0, 10));
        //assertEquals(2L, articleByAuthorName.getTotalElements());

        Iterator<Article> it = articleByAuthorName.iterator();
        while (it.hasNext()) {
            System.err.println(it.next());
        }
    }


    @Test
    public void givenPersistedArticles() {

        final Page<Article> articleByAuthorName =
                articleService.findByAuthorsNameUsingCustomQuery(johnDoe.getName(), new PageRequest(0, 10));
        Iterator<Article> it = articleByAuthorName.iterator();
        while (it.hasNext()) {
            System.err.println(it.next());
        }
    }

    @Test
    public void givenFullTitle_whenRunMatchQuery_thenDocIsFound() {
        final SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("title", "Search engines")
                        .operator(MatchQueryBuilder.Operator.AND)).build();
        final List<Article> articles = elasticsearchTemplate.queryForList(searchQuery, Article.class);
        assertEquals(1, articles.size());
    }

    //自定义查询
    @Test
    public void testQuery() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(regexpQuery("title", ".*data.*"))
                .build();
        List<Article> articles = elasticsearchTemplate.queryForList(searchQuery, Article.class);
        articles.stream().forEach(str -> System.err.println(str));
    }

    //更新文档
    @Test
    public void testUpdate(){
        SearchQuery searchQuery=new NativeSearchQueryBuilder()
                .withQuery(matchQuery("tags","elasticsearch"))
                .build();
        List<Article> articles = elasticsearchTemplate.queryForList(searchQuery, Article.class);
        for(Article article:articles){
            article.setTitle("Getting started with Search Engines");
            articleService.save(article);
        }
    }



}
