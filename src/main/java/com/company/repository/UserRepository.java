package com.company.repository;

import com.company.enums.Roles;
import com.company.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Transactional(readOnly = true)
    @Query("select user from UserEntity user where user.username = ?1")
    Optional<UserEntity> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.state = false, u.username = ?1 where u.id = ?2")
    void deleteUserById(String username, int id);


    @Transactional(readOnly = true)
    @Query("SELECT u FROM UserEntity u WHERE u.id = ?1 AND u.state = true ")
    UserEntity findUserById(int id);

    @Transactional
    @Modifying
    @Query("update UserEntity set firstName = ?1, lastName = ?2, username = ?3, password = ?4, roles = ?5 where id = ?6")
    void updateUser(String firstName, String lastName, String username, String password, Roles roles, int id);

    @Transactional(readOnly = true)
    @Query("select user from UserEntity user where user.state = true and user.username ilike %:username% and user.firstName ilike %:firstName% and user.lastName ilike %:lastName% order by user.username")
    List<UserEntity> findUserList(@Param("username") String username, @Param("firstName") String firstName, @Param("lastName") String lastName);
}
