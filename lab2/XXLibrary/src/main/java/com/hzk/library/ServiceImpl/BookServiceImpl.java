/**
 * 
 */
package com.hzk.library.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.aspectj.weaver.NewParentTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hzk.library.Dao.BookRepository;
import com.hzk.library.Entity.Book;
import com.hzk.library.Entity.BookCategory;
import com.hzk.library.Service.BookService;
import com.hzk.library.VO.BookVO;

/**
 * @author linzihan
 *
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Override
	public Optional<BookVO> getBookbyID(int id) {

		Optional<Book> book=bookRepository.findById(id);
		Optional<BookVO> bv=Optional.empty();
		if(book.isPresent()) bv=Optional.of(BookToBookVO(book.get()));
		
		return bv;
	}

	/* (non-Javadoc)
	 * 1、根据图书分类列出库存图书
	 * @see com.hzk.library.Service.BookService#GetBooksByCategery(com.hzk.library.Entity.BookCategory)
	 */
	@Override
	public Page<BookVO> GetBooksByCategery(BookCategory category,Pageable pageable) {
		List<BookVO> BL=new ArrayList<>();
		Page<Book> books = bookRepository.getByBookList_category(category,pageable);
		for (Book book : books) {
			BL.add(BookToBookVO(book));
		}
		
		Page<BookVO> pbv =  new PageImpl<BookVO>(BL,pageable, books.getTotalElements());
		return pbv;
	}

	/* (non-Javadoc)
	 * 2、根据作者列出库存图书
	 * @see com.hzk.library.Service.BookService#GetBooksByAuthor(java.lang.String)
	 */
	@Override
	public List<BookVO> GetBooksByAuthor(String author) {
		List<BookVO> BL=new ArrayList<>();
		List<Book> books = bookRepository.getByBookList_author(author);
		for (Book book : books) {
			BL.add(BookToBookVO(book));
		}
		return BL;
	}

	/* (non-Javadoc)
	 * 3、根据出版社列出库存图书
	 * @see com.hzk.library.Service.BookService#GetBooksByPress(java.lang.String)
	 */
	@Override
	public List<BookVO> GetBooksByPress(String press) {
		List<BookVO> BL=new ArrayList<>();
		List<Book> books =bookRepository.getByBookList_press(press);
		for (Book book : books) {
			BL.add(BookToBookVO(book));
		}
		return BL;
	}
	
	/**
	 * 将Book改写成面向终端用户的BookVO(VO也可以叫DTO)
	 * @param book
	 * @return
	 */
    private BookVO BookToBookVO(Book book){
    	BookVO bookVO = new BookVO();
    	
    	bookVO.setID(book.getID());
    	bookVO.setTitle(book.getTitle());
    	bookVO.setIsbn(book.getBookList().getIsbn());
    	bookVO.setAuthor(book.getBookList().getAuthor());
    	bookVO.setCategory(book.getBookList().getCategory().getTitle());
    	bookVO.setPages(book.getBookList().getPages());
    	bookVO.setPrice(book.getBookList().getPrice());
    	bookVO.setPress(book.getBookList().getPress());
    	bookVO.setPublishDay(book.getBookList().getPublishDay());
    	bookVO.setState(book.getState().getTitle());
    	
    	return bookVO;
    	
    }

	/* (non-Javadoc)
	 * 列出所有的库存图书
	 * @see com.hzk.library.Service.BookService#getAllBooks()
	 */
	@Override
	public Page<BookVO> getAllBooks(Pageable pageable) {
		
		List<BookVO> BL=new ArrayList<>();
		Page<Book> books =bookRepository.findAll(pageable);
		for (Book book : books.getContent()) {
			BL.add(BookToBookVO(book));
		}
		Page<BookVO> pbv =  new PageImpl<BookVO>(BL,pageable, books.getTotalElements());
		
		return pbv;
	}

	//根据指定的参数，保存(Create)图书记录
	/* (non-Javadoc)
	 * @see com.hzk.library.Service.BookService#SaveAll()
	 */
	@Override
	@Transactional
	public List<Book> saveAll(List<Book> books) {
		
		return bookRepository.saveAll(books);
	}

	
	/* (non-Javadoc)
	 * 增加一条记录
	 * @see com.hzk.library.Service.BookService#addOne(com.hzk.library.Entity.Book)
	 */
	@Override
	@Transactional
	public Book addOne(Book book) {
		
		return bookRepository.save(book);
	}
}
