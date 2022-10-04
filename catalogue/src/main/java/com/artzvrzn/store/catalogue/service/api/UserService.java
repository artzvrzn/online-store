package com.artzvrzn.store.catalogue.service.api;

import com.artzvrzn.store.catalogue.domain.UserInfo;

public interface UserService {

  UserInfo getAuthenticatedUser();
}
