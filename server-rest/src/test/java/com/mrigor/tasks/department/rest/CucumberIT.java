package com.mrigor.tasks.department.rest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Игорь on 19.06.2017.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        format = "pretty",
        tags = {"~@Ignore"},
        features = "src/test/resources/features"  //refer to Feature file
)
public class CucumberIT {  }
