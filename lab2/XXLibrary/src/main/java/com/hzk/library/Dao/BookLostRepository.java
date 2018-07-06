/**
 * 
 */
package com.hzk.library.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hzk.library.Entity.BookLost;
import com.hzk.library.Entity.User;

/**
 * @author linzihan
 *
 */
public interface BookLostRepository extends JpaRepository<BookLost, Integer> {

	List<BookLost> getByUser(User user);
}
