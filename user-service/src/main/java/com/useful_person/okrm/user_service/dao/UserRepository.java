package com.useful_person.okrm.user_service.dao;

import org.springframework.data.repository.CrudRepository;

import com.useful_person.okrm.user_service.domain.UserInfo;

public interface UserRepository extends CrudRepository<UserInfo, Integer> {

}
