package com.stte.mapper;


import com.stte.domain.Book;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface BookMapper {

	Book getBookById(int id);

	List<Book> getAllBooks();

	int insertBook(Book book);

	int updateBook(Book book);

	int deleteBookById(int id);

	@Select("select max(id) from tb_book")
	int getMaxId();

	List<Object> selectAllBook(String name);
	
//	List<Object> selectListTest();

	/**
	 *
	 * @return
	 */
	Map<String, Object> selectMapTest();
}
