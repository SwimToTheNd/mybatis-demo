package com.stte.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.stte.domain.Book;
import com.stte.mapper.BookMapper;
import com.stte.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test_01_Mapper {

    private static final Logger logger = LoggerFactory.getLogger(Test_01_Mapper.class);

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession(false);
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        int id = bookMapper.getMaxId();
        logger.info("tb_book max id is: {}", id);
        Book book = bookMapper.getBookById(id);

        if (book != null) {
            Map<String, Book> map = new HashMap<String, Book>();
            map.put("book", book);
            logger.info("{}", book);
            logger.info("{}", JSON.toJSONString(map, SerializerFeature.WriteDateUseDateFormat));
            // JSON.DEFFAULT_DATE_FORMAT = "yyyy/mm/dd";
            logger.info("{}", JSON.toJSONString(book, SerializerFeature.WriteDateUseDateFormat));
            logger.info("{}", book.toString());
            // 获得所有的Book
            logger.info("=============================================");
            List<Book> lBooks = bookMapper.getAllBooks();
            logger.info("{}", JSON.toJSONString(lBooks, SerializerFeature.WriteDateUseDateFormat));
        } else {
            // 添加Bokk
            Book book2 = new Book();
            book2.setId(5);
            book2.setAuthor("韩立");
            book2.setName("凡人修仙传");
            book2.setPublication("东方文化出版社");
            book2.setPublicationdate(new Date(2009, 10, 15));
            book2.setPrice(112.1);
            book2.setImage("xiuxian.jpg");
            book2.setRemark("小说文档");
            int result = bookMapper.insertBook(book2);
            logger.info(" result: {}" + result);
//		sqlSession.commit();
            logger.info(" ===============================================");
//		List<Object> record = bookMapper.selectAllBook("疯狂Ajax讲义");
            Map<String, Object> testMap = bookMapper.selectMapTest();
            logger.info("{}", testMap);
        }
    }

}
