/**
 * 
 */
package com.hzk.library.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzk.library.Dao.RoleRepository;
import com.hzk.library.Entity.UserRole;
import com.hzk.library.Service.RoleService;

/**
 * @author linzihan
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository ;
	
	/* (non-Javadoc)
	 * @see com.hzk.library.Service.RoleService#getRoleByID(int)
	 */
	@Override
	public Optional<UserRole> getRoleByID(int id) {
		// TODO Auto-generated method stub
		return roleRepository.findById(id);
	}

}
