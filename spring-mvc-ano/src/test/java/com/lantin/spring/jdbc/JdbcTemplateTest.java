package com.lantin.spring.jdbc;

import com.lantin.spring.BaseSpringAnnoJunitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created on 2021/07/17/11:35 周六
 *
 * @author Lantin
 */
public class JdbcTemplateTest extends BaseSpringAnnoJunitTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void test(){
        jdbcTemplate.update("insert into account (username, money) values (?,?)", "hehes12", 100);

    }
}
