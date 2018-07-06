/**
 * 
 */
package com.hzk.library.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hzk.library.Entity.User;

/**
 * @author linzihan
 *
 */
@org.springframework.stereotype.Repository
public interface UserRepository extends JpaRepository<User,String>{
	
	//根据username得到userid
	User getByName(String name);
}
