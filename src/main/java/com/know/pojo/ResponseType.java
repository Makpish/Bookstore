package com.know.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseType {

    @JsonProperty
    public long count;

    @JsonProperty
    public long pageNumber;

    @JsonProperty
    public long pageSize;

    @JsonProperty
    public long totalPages;

    @JsonProperty
    public Object data;

    @JsonProperty
    public Object error;

    public ResponseType(long count, long pageNumber, long pageSize, long totalPages, Object data, Object error) {
        this.count = count;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.data = data;
        this.error = error;
    }

    public ResponseType() {
    }

    public void setValue(Object data, long count, long offset, long limit) {
        this.setCount(count);
        this.setData(data);
        this.setPageSize((limit > 0) ? limit : this.getCount());
        this.setTotalPages((limit > 0) ? (int) Math.ceil(this.getCount() * 1.0 / limit) : 1);
        this.setPageNumber((long) Math.ceil(offset * 1.0 / this.getPageSize()));
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
