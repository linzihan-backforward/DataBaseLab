/**
 * 
 */
package com.hzk.library.Service;

import com.hzk.library.VO.BookListVO;

/**
 * @author linzihan
 *
 */
public interface BooklistService {
	BookListVO getBooklistbyID(String isbn);
}
