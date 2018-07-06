/**
 * 
 */
package com.hzk.library;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hzk.library.Dao.FineItemRepository;
import com.hzk.library.Dao.FineRepository;
import com.hzk.library.Entity.Fine;
import com.hzk.library.Entity.FineItem;
import com.hzk.library.Entity.User;
import com.hzk.library.Service.FineService;


/**
 * @author kecheng
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class FineTest {

	@Autowired
	private FineRepository  fineRepository;
	
	@Autowired
	private FineService fineService;
		
	//根据用户得到罚款列表
	@Test
	@Transactional
	public void getFineByUser() {
		User user=new User();
		user.setId("A0101");
		
		List<Fine> bl =fineRepository.getAllFinesByUser(user);
		
		for (Fine fine : bl) {
			System.out.println("罚款数额："+fine.getAmount()+"，已经缴纳？："+fine.isIspayed());
			for (FineItem fineitem : fine.getItems()) {
				System.out.println(fineitem.getBorrowovertimeID()+fineitem.getType().name());
			}
		}
	}
	
	//根据罚款流水号检索数据库得到罚款对象
	@Test
	@Transactional
	public void getfineByid(){
		//罚款流水号
		int id=111;
		Fine fine = fineRepository.getOne(id);
		System.out.println("罚款数额："+fine.getAmount()+"，已经缴纳？："+fine.isIspayed());
		for (FineItem fineitem : fine.getItems()) {
			System.out.println(fineitem.getBorrowovertimeID()+fineitem.getType().name());
		}
	}
	
	//将特定罚款流水号的罚单处理完成
	@Test
	@Transactional
    public void dealFinebyid(){
		//罚款流水号
		int id=111;
		Fine fine = fineRepository.getOne(id);
		
		fineService.dealFine(fine);
		
    }
}
