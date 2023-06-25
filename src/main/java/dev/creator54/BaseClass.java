package dev.creator54;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BaseClass {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static String binary = null, datePosted = null, location = null, jobsDataFileName = null, role=null, track=null;
    protected static String[] roles = null, tracks = null;
    protected static Boolean headless = null,dataAppend= null;
    protected static Integer pageCount = null;
    protected static Set<String> expLevels = null;

    protected static void setup() throws MalformedURLException {
        WebDriverManager.chromedriver ().setup ();
        ChromeOptions options = new ChromeOptions ();
        driver = new RemoteWebDriver (new URL ("http://localhost:4444/wd/hub"),options);
        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public static boolean goTo(String url) {
        try {
            if (!driver.getCurrentUrl().equals(url)) {
                driver.get(url);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace ();
            return false;
        }
    }

    protected static void refresh(){
        driver.navigate().refresh();
    }

    protected static void waitUntilPageLoaded(){
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    protected static String currentURL(){
        return driver.getCurrentUrl();
    }
    protected static WebElement findElement (By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }
    protected static WebElement findElement(WebElement element, By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.findElement(locator);
    }
    protected static String stringFromElement(By locator){
        WebElement ele;
        try {
            ele = findElement (locator);
            return ele.getText();
        }catch (Exception e){
            return "NOT FOUND";
        }
    }
    protected static String stringFromElement(WebElement element, By locator){
        WebElement ele;
        try {
            ele = findElement (element,locator);
            return ele.getText();
        }catch (Exception e){
            return "NOT FOUND";
        }
    }
    protected static List<WebElement> findElements(By locator){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        return driver.findElements(locator);
    }

    protected static void sendKeys(By locator, String value){
        findElement(locator).sendKeys(value);
    }

    protected static void click(By locator){
        findElement(locator).click();
    }

    protected static void click(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    protected static void close (){
        driver.close ();
        driver.quit ();
    }
}
