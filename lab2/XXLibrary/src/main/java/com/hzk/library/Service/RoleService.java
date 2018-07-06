/**
 * 
 */
package com.hzk.library.Service;

import java.util.Optional;

import com.hzk.library.Entity.UserRole;

/**
 * @author linzihan
 *
 */
public interface RoleService  {
   public Optional<UserRole> getRoleByID(int id);
}
