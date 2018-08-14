package com.learn.query;

/**
 * @author :lwy
 * @date 2018/6/15 15:55
 */
public class DeleteQuery {

    private String id;
    private Object object;
    private String indexName;
    private String type;

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

    public static final class Builder {

        private String id;
        private Object object;
        private String indexName;
        private String type;

        public Builder() {
        }

        public Builder withObject(Object val) {
            object = val;
            return this;
        }

        public Builder withIndexName(String val) {
            indexName = val;
            return this;
        }

        public Builder withType(String val) {
            type = val;
            return this;
        }

        public Builder withId(String val) {
            id = val;
            return this;
        }

        public DeleteQuery build() {
            DeleteQuery queryIndex = new DeleteQuery();
            queryIndex.setIndexName(indexName);
            queryIndex.setObject(object);
            queryIndex.setType(type);
            queryIndex.setId(id);
            return queryIndex;
        }
    }
}
