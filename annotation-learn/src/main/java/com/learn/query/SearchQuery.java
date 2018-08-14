package com.learn.query;

import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.sort.SortBuilder;

import java.util.List;

/**
 * @author :lwy
 * @date 2018/6/15 16:12
 */
public class SearchQuery {

    private String indexName;
    private String indexType;
    private SearchType searchType = SearchType.DFS_QUERY_THEN_FETCH;
    private QueryBuilder query;
    private QueryBuilder filter;
    private List<AbstractAggregationBuilder> aggs;
    private List<SortBuilder> sorts;

    private float minScore;
    private List<String> sourceIncludes;
    private List<String> sourceExcludes;


    public SearchQuery() {
    }

    private SearchQuery(Builder builder) {
        setIndexName(builder.indexName);
        setIndexType(builder.indexType);
        setSearchType(builder.searchType);
        setQuery(builder.query);
        setFilter(builder.filter);
        setAggs(builder.aggs);
        setSorts(builder.sorts);
        setMinScore(builder.minScore);
        setSourceIncludes(builder.sourceIncludes);
        setSourceExcludes(builder.sourceExcludes);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public SearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }

    public QueryBuilder getQuery() {
        return query;
    }

    public void setQuery(QueryBuilder query) {
        this.query = query;
    }

    public QueryBuilder getFilter() {
        return filter;
    }

    public void setFilter(QueryBuilder filter) {
        this.filter = filter;
    }

    public List<AbstractAggregationBuilder> getAggs() {
        return aggs;
    }

    public void setAggs(List<AbstractAggregationBuilder> aggs) {
        this.aggs = aggs;
    }

    public List<SortBuilder> getSorts() {
        return sorts;
    }

    public void setSorts(List<SortBuilder> sorts) {
        this.sorts = sorts;
    }

    public float getMinScore() {
        return minScore;
    }

    public void setMinScore(float minScore) {
        this.minScore = minScore;
    }

    public List<String> getSourceIncludes() {
        return sourceIncludes;
    }

    public void setSourceIncludes(List<String> sourceIncludes) {
        this.sourceIncludes = sourceIncludes;
    }

    public List<String> getSourceExcludes() {
        return sourceExcludes;
    }

    public void setSourceExcludes(List<String> sourceExcludes) {
        this.sourceExcludes = sourceExcludes;
    }


    public static final class Builder {
        private String indexName;
        private String indexType;
        private SearchType searchType;
        private QueryBuilder query;
        private QueryBuilder filter;
        private List<AbstractAggregationBuilder> aggs;
        private List<SortBuilder> sorts;
        private float minScore;
        private List<String> sourceIncludes;
        private List<String> sourceExcludes;

        private Builder() {
        }

        public Builder withIndexName(String indexName) {
            this.indexName = indexName;
            return this;
        }

        public Builder withIndexType(String indexType) {
            this.indexType = indexType;
            return this;
        }

        public Builder withSearchType(SearchType searchType) {
            this.searchType = searchType;
            return this;
        }

        public Builder withQuery(QueryBuilder query) {
            this.query = query;
            return this;
        }

        public Builder withFilter(QueryBuilder filter) {
            this.filter = filter;
            return this;
        }

        public Builder withAggs(List<AbstractAggregationBuilder> aggs) {
            this.aggs = aggs;
            return this;
        }

        public Builder withSorts(List<SortBuilder> sorts) {
            this.sorts = sorts;
            return this;
        }

        public Builder withMinScore(float minScore) {
            this.minScore = minScore;
            return this;
        }

        public Builder withSourceIncludes(List<String> sourceIncludes) {
            this.sourceIncludes = sourceIncludes;
            return this;
        }

        public Builder withSourceExcludes(List<String> sourceExcludes) {
            this.sourceExcludes = sourceExcludes;
            return this;
        }

        public SearchQuery build() {
            return new SearchQuery(this);
        }
    }
}
