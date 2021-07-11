package com.lantin.spring;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created on 2021/07/11/22:18 周日
 *
 * @author Lantin
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/application*.xml",
        "classpath:spring-mvc.xml"})
public class BaseJunitTest {


}
