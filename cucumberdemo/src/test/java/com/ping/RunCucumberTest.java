package com.ping;

import com.ping.LiveDemoTest;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"html:target/cucumber-reports/html",
        "json:target/cucumber-reports/cucumber.json",
        "junit:target/cucumber-reports/cucumber.xml",
        "pretty"},features="src/test/resources/com/ping/features",glue = {"com.ping.steps"},tags = {"@Selenium"})
public class RunCucumberTest {
    @AfterClass
    public static void  setReposts() throws IOException {
        LiveDemoTest liveDemoTest=new LiveDemoTest();
        liveDemoTest.generateDemoReport();
    }
}