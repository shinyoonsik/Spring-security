package com.example.springsecurity.repository;

import com.example.springsecurity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD함수를 JpaRepository가 들고 있다
// @Repository라는 어노테이션이 없어도 UserEntityRepository가 Bean으로 등록된다 (JpaRepository를 상속했으므로)
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username); // Jpa 쿼리 메소드
}
