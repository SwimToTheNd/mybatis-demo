package com.stte.plusgenerator;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stte.generator.entity.TbBook;
import com.stte.generator.mapper.TbBookMapper;
import com.stte.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


/**
 * create by BloodFly at 2020/3/7
 */
public class GeneratedMapperTest {

    public static void main(String[] args) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        TbBookMapper mapper = sqlSession.getMapper(TbBookMapper.class);
        Wrapper tbBookQueryWrapper = new QueryWrapper<TbBook>();
        List list = mapper.selectList(tbBookQueryWrapper);
        list.forEach(System.out::println);

    }
}
