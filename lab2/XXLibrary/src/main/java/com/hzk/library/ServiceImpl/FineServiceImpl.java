/**
 * 
 */
package com.hzk.library.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzk.library.Dao.BookBorrowRepository;
import com.hzk.library.Dao.FineRepository;
import com.hzk.library.Entity.Fine;
import com.hzk.library.Entity.FineItem;
import com.hzk.library.Entity.FineType;
import com.hzk.library.Service.BookBorrowService;
import com.hzk.library.Service.FineService;
import com.hzk.library.VO.FineVO;

/**
 * @author linzihan
 *
 */
@Service
public class FineServiceImpl implements FineService {

	@Autowired
	private FineRepository fineRepository;
	
	@Autowired
	private BookBorrowRepository bbs;
	
	@Autowired
	private BookBorrowService bookBorrowService;
	
	
	/* (non-Javadoc)
	 * 
	 * @see com.hzk.library.Service.FineService#append()
	 */
	@Override
	@Transactional
	public Fine append(Fine fine) {
		
		return  fineRepository.saveAndFlush(fine);
	}


	/* (non-Javadoc)
	 * @see com.hzk.library.Service.FineService#dealFine(com.hzk.library.Entity.Fine)
	 */
	@Override
	@Transactional
	public boolean dealFine(Fine fine) {
		
		//1、得到该罚款明细对应的逾期图书的编号：
		List<Integer> bookidList= new ArrayList<>();
		
		for (FineItem  bb : fine.getItems()) {
			
			bookidList.add( bbs.getbookidByID( bb.getBorrowovertimeID() ) );
			
		}
		
		//2、将罚款明细记录的书做还书操作。
		bookBorrowService.WriteReturnBook(bookidList);
		
		//3、更新罚款主表： ispayed=1;payeddate=now(); 
		fine.setIspayed(true);
		fine.setPayeddate(new Date());
		
		fineRepository.save(fine);
		
		return true;
	}


	/* (non-Javadoc)
	 * @see com.hzk.library.Service.FineService#getFineByUserid(java.lang.String)
	 */
	@Override
	public List<FineVO> getFineByUserid(String userid) {
		
		List<Fine> bl =fineRepository.getNotDealedFineByUserID(userid);
		
		List<FineVO> fineVOs= new ArrayList<>();
		for (Fine fine : bl) {
			FineVO fVo= new FineVO();
			fVo.setId(fine.getID());
			fVo.setAmount(fine.getAmount());
			fVo.setFinetype(fine.getFinetype());
			fVo.setIspayed(fine.isIspayed());
			fVo.setUserName(fine.getUser().getName());
			fVo.setPayeddate(fine.getPayeddate());
			
			fineVOs.add(fVo);
		}
		return fineVOs;
	}
	
	

}
