package com.gagful.service;

public interface RedisService {
    String getValue(String key);

    void setIfAbsent(String key, String value);

    void set(String key, String value);

    void delete(String key);
}
