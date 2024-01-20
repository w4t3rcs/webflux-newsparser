package com.w4t3rcs.newsparser.model.common.transformer;

public interface Parser<K, V> {
    K parse(V v);
}
