/**
 * 
 */
package com.hzk.library.Service;

import java.util.List;
import java.util.Optional;

import com.hzk.library.Entity.User;

/**
 * @author linzihan
 *
 */
public interface UserService {
	
	Optional<User> getUserById(String id);
	
    List<User> getAllUsers() ;
	
	void deleteUserByid(String id);
	
	//被人为暂停了借书资格了吗？
	boolean isDisabled(String id);
	
	//某读者的借书周期设定，计量单位：天
	int getBorrowPeriodOfUser(String id);
	
    //读者有超期？
	boolean IsReaderOverTime(String id);
	
	//读者有罚款未交？
	boolean IsReaderfined(String id);
	
	//根据username 得到userid;
	String getidByName(String name);
}

