package com.ly.shopping.service;

import com.ly.shopping.pojo.Admin;

public interface AdminService {

    Admin get(String name, String password);
}
