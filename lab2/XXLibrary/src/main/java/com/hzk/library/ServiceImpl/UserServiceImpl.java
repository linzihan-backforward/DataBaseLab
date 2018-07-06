/**
 * 
 */
package com.hzk.library.ServiceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzk.library.Dao.UserRepository;
import com.hzk.library.Entity.User;
import com.hzk.library.Entity.UserState;
import com.hzk.library.Service.UserService;

/**
 * @author linzihan
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	/* 根据读者编号得到某读者
	 * @see com.hzk.library.Service.IUserService#getUserById(java.lang.String)
	 */
	@Override
	public Optional<User> getUserById(String id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	/* 得到所有的读者
	 * @see com.hzk.library.Service.UserService#getAllUsers()
	 */
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	//删除特定用户
	@Override
	@Transactional
	public void deleteUserByid(String id) {
		userRepository.deleteById(id);
	}

	/* 某读者的借书周期设定，计量单位：天
	 * @see com.hzk.library.Service.UserService#getBorrowPeriodOfUser(java.lang.String)
	 */
	@Override
	public int getBorrowPeriodOfUser(String id) {
		
		return userRepository.findById(id).get().getRole().getCheckoutPeriod() ;
	}

	/* (non-Javadoc)
	 * @see com.hzk.library.Service.UserService#isDisabled(java.lang.String)
	 */
	@Override
	public boolean isDisabled(String id) {
		return getUserById(id).get().getState()==UserState.暂停借阅;
	}

	/* 读者有超期？
	 * @see com.hzk.library.Service.UserService#IsReaderOverTime(java.lang.String)
	 */
	@Override
	public boolean IsReaderOverTime(String id) {
		
		UserState userState=getUserById(id).get().getState();
		return userState==UserState.有逾期;
	}

	/* 有罚款未交？
	 * @see com.hzk.library.Service.UserService#IsReaderfined(java.lang.String)
	 */
	@Override
	public boolean IsReaderfined(String id) {
		UserState userState=getUserById(id).get().getState();
		return userState==UserState.有罚款未交;
	}

	/* (non-Javadoc)
	 * @see com.hzk.library.Service.UserService#getidByName(java.lang.String)
	 */
	@Override
	public String getidByName(String name) {
		
		return userRepository.getByName(name).getId();
	}
   
	 
}
