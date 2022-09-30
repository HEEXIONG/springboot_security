package com.springboot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.security.model.User;

// JpaRepository 를 상속하면 자동 컴포넌트 스캔됨.
// CRUD 함수를 JpaRepository가 들고 있음.
// @Repository라는 어노테이션이 없어도 IoC 되요. 이유는 JpaRepository를 상속했기 때문에...
public interface UserRepository extends JpaRepository<User, Integer>{

	// findBy규칙 -> Username 문법
	// select * from user where username = 1?
	public User findByUsername(String username); // findByUsername : Jpa Query methods
	
}