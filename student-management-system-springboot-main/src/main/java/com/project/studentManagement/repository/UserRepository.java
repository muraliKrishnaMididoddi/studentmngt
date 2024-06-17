package com.project.studentManagement.repository;

import com.project.studentManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{

	public User findByUserName(String name);
	
	public User findByUserNameAndUserPassword(String username,String password);
}
