package com.mrigor.testTasks.department.service;


import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;

/**
 * Created by Игорь on 11.12.2016.
 */
public class TestUtil {

    public static ResultActions printContent(ResultActions action) throws UnsupportedEncodingException {
        System.out.println(getContent(action));
        return action;
    }

    public static String getContent(ResultActions action) throws UnsupportedEncodingException {
       //ToDo
        // action.andReturn().getResponse().getContentAsString();
        return null;
    }
}
