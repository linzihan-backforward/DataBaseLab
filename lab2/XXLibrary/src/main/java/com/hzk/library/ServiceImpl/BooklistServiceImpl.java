/**
 * 
 */
package com.hzk.library.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzk.library.Dao.BooklistRepository;
import com.hzk.library.Entity.BookList;
import com.hzk.library.Service.BooklistService;
import com.hzk.library.VO.BookListVO;

/**
 * @author linzihan
 *
 */
@Service
public class BooklistServiceImpl implements BooklistService {

	@Autowired
	BooklistRepository BooklistRepository;
	
	/* (non-Javadoc)
	 * @see com.hzk.library.Service.BooklistService#getBooklistbyID(java.lang.String)
	 */
	@Override
	public BookListVO getBooklistbyID(String isbn) {
		Optional<BookList> booklist = BooklistRepository.findById(isbn);
		BookListVO res = null;
		if(booklist.isPresent()){
			res = new BookListVO();
			res.setIsbn(booklist.get().getIsbn());
			res.setTitle(booklist.get().getTitle());
			res.setCategory(booklist.get().getCategory());
			res.setAuthor(booklist.get().getAuthor());
			res.setPress(booklist.get().getPress());
			res.setPublishDay(booklist.get().getPublishDay());
			res.setPages(booklist.get().getPages());
			res.setPrice(booklist.get().getPrice());
			res.setIntro(booklist.get().getIntro());
			res.setBookcnt(booklist.get().getBooks().size());
		}
		return res;
	}

}
