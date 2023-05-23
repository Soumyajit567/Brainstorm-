package com.example.brainstorm.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    This interface is for database access.
 */
@Repository
public interface UserRepository
        extends JpaRepository<Users, Long> {

    /**
     * Search for a user by username.
     *
     * @param username
     * @return a Users object whose username is found within the database
     */
    @Query("SELECT u FROM Users u WHERE u.username = ?1")
    public Users findByUsername(String username);

    /**
     * Search for a user by email.
     *
     * @param email
     * @return a Users object whose email is found within the database
     */
    @Query("SELECT u FROM Users u WHERE u.email = ?1")
    public Users findByEmail(String email);

    /**
     * Returns all students registered.
     *
     * @return a list os Users objects who are students
     */
    @Query("SELECT u FROM Users u WHERE u.role = 0")
    public List<Users> findAllStudents();

    /**
     * Returns all instructors registered.
     *
     * @return a list os Users objects who are instructors
     */
    @Query("SELECT u FROM Users u WHERE u.role = 1")
    public List<Users> findAllInstructors();

    /**
     * Returns all admins registered.
     *
     * @return a list os Users objects who are admins
     */
    @Query("SELECT u FROM Users u WHERE u.role = 2")
    public List<Users> findAllAdmins();

    /**
     * Search for a user by reset token.
     *
     * @param token
     * @return a Users object whose reset token is found within the database
     */
//    public Users findByResetToken(String token);
}
