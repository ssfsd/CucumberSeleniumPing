package com.ping.steps;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.zh_cn.*;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
@Log
public class SeleniumOperation {
    WebDriver driver=null;
    public String getBrowserType(String key){
        Properties properties=new Properties();
        BufferedReader bufferedReader= null;
        try {
            bufferedReader = new BufferedReader(new FileReader(("D:\\bakup\\cucumberdemo\\src\\test\\resources\\config\\config.properties")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
 @Before("@Selenium")
    public void openByUrl() throws InterruptedException {
        String browserType=null;
        if(getBrowserType("browser.type")!=null){
            browserType=getBrowserType("browser.type");
        }
        else{
            log.info("浏览器类型没有配置。");
        }
        if(browserType.equals("chrome")){
            ChromeOptions chromeOptions=new ChromeOptions();
            System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
            driver=new ChromeDriver();
        }
        else if(browserType.equals("ie")){
            System.setProperty("webdriver.ie.driver","src/test/resources/IEDriverServer.exe");
            driver=new InternetExplorerDriver();
        }
        else if(browserType.equals("firefox")){
            System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver.exe");
            driver=new FirefoxDriver();
        }
        driver.manage().window().maximize();
        sleep(10);
    }


    @Given("^open a browser with a url$")
    public void openABrowserWithAUrl() throws InterruptedException {
        String url="";
        driver.get(url);
        driver.manage().window().maximize();
        driver.findElements(By.xpath("//button//span[text()=\"登录\"]"));
    }



    @And("^touch \"([^\"]*)\"$")
    public void touch(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        click(arg0);
    }

    @Then("^input \"([^\"]*)\" by xpath value \"([^\"]*)\"$")
    public void inputByXpathValue(String arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        input(arg1,arg0);
    }
    public Boolean waitElementIsEnableBy(WebDriver driver,By by,long waitTime)
    {
        try{
            WebDriver waitDriver=driver;
            waitDriver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
            (new WebDriverWait(waitDriver,waitTime)).until(ExpectedConditions.elementToBeClickable(by));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public void operationElements(String value,String type,String inputValue) {
        boolean find = false;
        WebElement element = null;
        long startTime = System.currentTimeMillis();
        find = waitElementIsEnableBy(driver, By.xpath(value), 20);
        if (find) {

            element = driver.findElement(By.xpath(value));
            element.click();
            if (type.equals("input")) {
                element.clear();
                element.sendKeys(inputValue);
                log.info("find elements and click element by xpath successfull: " + value);
            } else {
                log.info("find elements and click element by xpath failed: " + value);
            }
        }
    }
    public void click(String value)
    {
        operationElements(value,"","");
    }
    public void input(String value,String inputValue)
    {
        operationElements(value,"input",inputValue);
    }
    public void  browserBack()
    {
        driver.navigate().back();
        sleep(2);
    }
    public void browserForward()
    {
        driver.navigate().forward();
        sleep(2);
    }
    public void sleep(int sleepTime) {
        long sleepT=sleepTime*1000;
        try {
            Thread.sleep(sleepT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void refresh()
    {
        driver.navigate().refresh();
        sleep(2);
    }
@After("@Selenium")
    public void closeBrowser()
    {
        driver.quit();
    }
    public void getText(WebElement element)
    {
        String text=element.getText();
        log.info(element.toString()+"的文字为: "+text);
    }
    public void getAttri(WebElement element,String attriType)
    {
        String attri=element.getAttribute(attriType);
        log.info(element.toString()+"通过"+attriType+"获取"+attri);
    }
    public boolean isDisplay(WebElement element)
    {
        return element.isDisplayed();
    }
    public void mouseRightClick(WebElement element)
    {
        Actions action=new Actions(driver);
        if(!element.equals(""))
        {
            action.contextClick(element).perform();
        }
        else{
            action.contextClick().perform();
        }

    }
    public void mouseRightDoubleClick(WebElement element)
    {
        Actions actions=new Actions(driver);
        actions.doubleClick(element);
    }
    public void dragMouseFromTo(WebElement from,WebElement to)
    {
        Actions actions=new Actions(driver);
        actions.dragAndDrop(from,to).perform();
    }
//    public String getText(WebElement element){
//        return  element.getText();
//
//    }
    public void switchFrame(WebElement element)
    {
        driver.switchTo().frame(element);
    }
    public void switchDefault(WebElement element)
    {
        driver.switchTo().defaultContent();
    }
}
