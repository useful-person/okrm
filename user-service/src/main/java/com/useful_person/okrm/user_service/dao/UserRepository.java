package com.useful_person.okrm.user_service.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.useful_person.okrm.user_service.domain.UserInfo;

public interface UserRepository extends CrudRepository<UserInfo, UUID> {

}
