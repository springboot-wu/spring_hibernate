package com.test.controller;

import com.test.service.TestService;
import com.test.utils.PagingQueryResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

     Logger log = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private TestService testService;

    @RequestMapping("/getList")
    @ResponseBody
    public PagingQueryResult  getList(){
        log.info("in  testcontroller 层=========");
        PagingQueryResult pagingQueryResult = testService.testList();
        System.out.println(pagingQueryResult);
//        EmpEntity empEntity = empEntities.get(0);
        return  pagingQueryResult;

    }

    public static void main(String[] args) {

    }
}
