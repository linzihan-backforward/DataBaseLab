/**
 * 
 */
package com.hzk.library.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hzk.library.Entity.Fine;
import com.hzk.library.Entity.User;

/**
 * @author linzihan
 *
 */
public interface FineRepository  extends JpaRepository<Fine, Integer>{

	List<Fine> getAllFinesByUser(User user);
	
	//得到某人的所有未处理的罚款。
	@Query(value="select * from fine m  where  m.ispayed=0 and  m.userid=?1 ",nativeQuery=true)
	List<Fine> getNotDealedFineByUserID(String userid);
}
