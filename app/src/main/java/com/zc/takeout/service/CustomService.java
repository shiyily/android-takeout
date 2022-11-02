package com.zc.takeout.service;

import com.zc.takeout.bean.Custom;

public interface CustomService {
    boolean login(String username, String password);
    void register(Custom custom);
}
