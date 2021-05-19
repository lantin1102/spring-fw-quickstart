package com.lantin.springfw.service;

import org.springframework.stereotype.Service;

/**
 * Created on 2021/05/19/9:48 周三
 *
 * @author Lantin
 */

@Service
public class DemoService {


    public String demoService() {

        return "service处理业务";
    }

    public static void main(String[] args) {
        {
            int i = 1, j = 10;

            do {
                if (i > j) {
                    break;
                }
                j--;
            } while (++i < 5);

            System.out.println("i=" + i + ";j=" + j);

        }
    }
}
