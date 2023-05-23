package com.example.brainstorm.courses;

import com.example.brainstorm.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    //@Query("SELECT u FROM Users u WHERE u.username = ?1")
//    @Query("SELECT e.users FROM Enrollment e WHERE e.course = ?1")
//    public List<Users> findEnrolledStudents(Long courseID);
}
