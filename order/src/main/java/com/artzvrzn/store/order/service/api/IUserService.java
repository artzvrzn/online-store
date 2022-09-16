package com.artzvrzn.store.order.service.api;

import com.artzvrzn.store.order.domain.UserInfo;

public interface IUserService {

  UserInfo getAuthenticatedUser();
}
