package com.se196668.mvc.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface CategoryService {

    HashMap<Integer, String> cateList();
}
