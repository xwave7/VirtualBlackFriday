package com.vbf.instantbuy.redis;

/**
 * Created by BRODY on 2019/12/9.
 */
public class UserKey extends BasePrefix {
    public UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
