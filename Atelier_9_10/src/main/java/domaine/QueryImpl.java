package domaine;

class QueryImpl implements Query {
    private String url;
    private QueryMethod method;

    public QueryImpl(String url, QueryMethod method) {
        this.url = url;
        this.method = method;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setMethod(String method) throws IllegalArgumentException {
        this.method = QueryMethod.valueOf(method);
    }

    @Override
    public void setMethod(QueryMethod method) {
        this.method = method;
    }

}
