package com.lantin.spring;


import com.lantin.spring.config.SpringConfig;
import com.lantin.spring.config.service.AccountService;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created on 2021/07/17/10:54 周六
 *
 * @author Lantin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class BaseSpringAnnoJunitTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("com.lantin.spring.config");
        // JdbcTemplate jdbcTemplate = ac.getBean("jdbcTemplate", JdbcTemplate.class);
        // jdbcTemplate.update("insert into account (username, money) values (?,?)", "hehes12", 100);
        AccountService accountService = (AccountService) ac.getBean("accountServiceImpl");
        accountService.saveAccount();
    }

}