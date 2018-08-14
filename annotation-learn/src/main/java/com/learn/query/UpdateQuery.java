package com.learn.query;

/**
 * @author :lwy
 * @date 2018/6/15 14:16
 */
public class UpdateQuery {
    private String id;
    private Object object;
    private String indexName;
    private String type;

    public UpdateQuery() {
    }

    private UpdateQuery(Builder builder) {
        setObject(builder.object);
        setIndexName(builder.indexName);
        setType(builder.type);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * {@code UpdateQuery} builder static inner class.
     */
    public static final class Builder {

        private String id;
        private Object object;
        private String indexName;
        private String type;

        private Builder() {
        }

        /**
         * Sets the {@code object} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code object} to set
         * @return a reference to this Builder
         */
        public Builder withObject(Object val) {
            object = val;
            return this;
        }

        /**
         * Sets the {@code indexName} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code indexName} to set
         * @return a reference to this Builder
         */
        public Builder withIndexName(String val) {
            indexName = val;
            return this;
        }

        /**
         * Sets the {@code type} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code type} to set
         * @return a reference to this Builder
         */
        public Builder withType(String val) {
            type = val;
            return this;
        }

        public Builder withId(String val) {
            id = val;
            return this;
        }

        /**
         * Returns a {@code UpdateQuery} built from the parameters previously set.
         *
         * @return a {@code UpdateQuery} built with parameters of this {@code UpdateQuery.Builder}
         */
        public UpdateQuery build() {
            UpdateQuery updateQuery = new UpdateQuery();
            updateQuery.setIndexName(indexName);
            updateQuery.setType(type);
            updateQuery.setObject(object);
            updateQuery.setId(id);
            return updateQuery;
        }
    }
}
