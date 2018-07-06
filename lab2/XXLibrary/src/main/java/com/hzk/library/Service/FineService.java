/**
 * 
 */
package com.hzk.library.Service;

import java.util.List;

import com.hzk.library.Entity.Fine;
import com.hzk.library.VO.FineVO;

/**
 * @author linzihan
 *
 */
public interface FineService {
    /**
     * 增加一个罚款单，含一条主表和多条从表
     */
	Fine append(Fine fine);
	
	//根据用户ID得到该用户的所有未处理罚款。
	List<FineVO> getFineByUserid(String userid);
	
	//处理完成某个罚款单：更新ispayed=1;payeddate=now(); 将罚款明细记录的书做还书操作。
	boolean dealFine(Fine fine);
	
}
