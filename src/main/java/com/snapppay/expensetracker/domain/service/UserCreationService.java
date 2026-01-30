package com.snapppay.expensetracker.domain.service;

import com.snapppay.expensetracker.domain.model.User;

public interface UserCreationService {

  User create(String mobile, String password);

}
