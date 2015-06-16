package com.twu.biblioteca;

public class SearchAgent<T> {
    private String searchKey;
    private T result;

    public SearchAgent(String searchKey) {
        this.searchKey = searchKey;
    }

    public T result() {
        return result;
    }

    public void add(T book) {
        result = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchAgent<?> that = (SearchAgent<?>) o;

        if (searchKey != null ? !searchKey.equals(that.searchKey) : that.searchKey != null) return false;
        return !(result != null ? !result.equals(that.result) : that.result != null);

    }

    @Override
    public int hashCode() {
        return searchKey == null ? 0 : searchKey.hashCode();
    }
}
