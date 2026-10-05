package lk.bookbarlibrarymember.user.dao;

import lk.bookbarlibrarymember.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserDao extends JpaRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.employee_id.designation_id.name='Librarian'")
    User findByDesignation(String librarian);
}
