package com.stte.jdbc;

import com.stte.domain.Book;

import java.sql.*;
import java.util.*;

/**
 * create by BloodFly at 2020/3/1
 */
public class jdbcTest {


    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // 1. 加载驱动driver
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 通过url、用户、密码建立数据库连接
            String url = "jdbc:mysql://127.0.0.1:3306/mysql_test_db?useSSL=false";
            String user = "root";
            String passwd = "123456";
            connection = DriverManager.getConnection(url, user, passwd);
            // 3. 通过连接获得jdbc Statement
            statement = connection.prepareStatement("select * from tb_book where author = ?");
            // 4. 设置查询参数
            ((PreparedStatement) statement).setString(1, "韩立");
            // 5. 执行查询
            resultSet = ((PreparedStatement) statement).executeQuery();
            // 6. 查询结果转换为POJO对象
            List<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublicationdate(resultSet.getDate("publicationdate"));
                book.setPublication(resultSet.getString("publication"));
                book.setPrice(resultSet.getDouble("price"));
                book.setImage(resultSet.getString("image"));
                book.setRemark(resultSet.getString("remark"));
                books.add(book);

            }
            System.out.println(books);


            System.out.println("----------------------------------我是分隔线-----------------------------------");
            // 使用ResuSetMetadata
            List<Map<String, Object>> result = new ArrayList<>();
            statement = connection.prepareStatement("select * from tb_book where author = ? order by id desc");
            ((PreparedStatement) statement).setString(1, "韩立");
            ResultSet rs = ((PreparedStatement) statement).executeQuery();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (rs.next()) {
                Map<String, Object> rowData = new LinkedHashMap<>();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = resultSetMetaData.getColumnName(i + 1);
                    rowData.put(columnName, rs.getString(columnName));
                }
                result.add(rowData);
            }
            System.out.println(result);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 7. 关闭ResultSet、Statement、Connection
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}

