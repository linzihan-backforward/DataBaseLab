/**
 * 
 */
package com.hzk.library.Dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hzk.library.Entity.BookList;

/**
 * @author linzihan
 *
 */

public interface BooklistRepository extends PagingAndSortingRepository<BookList, String>{

}
