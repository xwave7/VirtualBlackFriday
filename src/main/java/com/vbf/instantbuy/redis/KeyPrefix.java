package com.vbf.instantbuy.redis;

/**
 * Created by BRODY on 2019/12/4.
 */
public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();

}
