package com.snapppay.expensetracker.domain.service;

import com.snapppay.expensetracker.domain.model.User;

public interface UserRetrievalService {

  User findUserById(Long userId);

}
