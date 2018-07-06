/**
 * 
 */
package com.hzk.library.Dao;

import org.springframework.data.repository.CrudRepository;

import com.hzk.library.Entity.UserRole;

/**
 * @author linzihan
 *
 */
public interface RoleRepository  extends CrudRepository<UserRole, Integer>{

}
