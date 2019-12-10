package com.vbf.instantbuy.redis;

/**
 * Created by BRODY on 2019/12/9.
 */
public class OrderKey extends BasePrefix {
    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
