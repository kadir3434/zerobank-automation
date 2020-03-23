package com.zerobank.utilities;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class BrowserUtils {

    //It will be used to pause our test execution
    //just provide number of seconds as a parameter
    public static void wait(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





    //    PLEASE INSERT THIS METHOD INTO BROWSER UTILS
    /*
     * takes screenshot
     * whenever you call this method
     * it takes screenshot and returns location of the screenshot
     * @param name of test or whatever your like
     * take a name of a test and returns a path to screenshot takes
     */
    public static String getScreenshot(String name) {
        // name the screenshot with the current date time to avoid duplicate name
//        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));​
        SimpleDateFormat df = new SimpleDateFormat("-yyyy-MM-dd-HH-mm");
        String date = df.format(new Date());
        // TakesScreenshot ---> interface from selenium which takes screenshots
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        //where screenshot will be stored
        //System.getProperty("user.dir") returns path to the project as a string
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * Wait 15 seconds with polling interval of 200 milliseconds then click
     *
     * @param webElement of element
     */
    public static void clickWithWait(WebElement webElement) {
        Wait wait = new FluentWait<>(Driver.getDriver())
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(800))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotVisibleException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(WebDriverException.class);
        WebElement element = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> webElement);
        try {
            element.click();
        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * waits for backgrounds processes on the browser to complete
     * waits for loading the page
     * @param timeOutInSeconds
     */
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    /**
     * Wait for proper page title
     *
     * @param pageTitle
     */
    public static void waitForPageTitle(String pageTitle) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        wait.until(ExpectedConditions.titleIs(pageTitle));

    }


    //Verifies the title of the page with the given string to this method
    public static boolean verifyPageTitle(String pageTitle){
        boolean verify = false;
        if (pageTitle.equals(Driver.getDriver().getTitle())){
            verify = true;
        }
        return verify;
    }

    /*
     * switches to new window by the exact title
     * returns to original window if windows with given title not found
     */
    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getDriver().switchTo().window(origin);
    }

    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    /**
     * return a list of string from a list of elements ignores any element with no
     * text
     *
     * @param list
     * @return
     */
    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }

    public static List<String> getElementsText(By locator) {

        List<WebElement> elems = Driver.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();

        for (WebElement el : elems) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }

    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement fluentWait(final WebElement webElement, int timeinsec) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver())
                .withTimeout(Duration.ofSeconds(timeinsec))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });
        return element;
    }

    /**
     * Verifies whether the element matching the provided locator is displayed on page
     * fails if the element matching the provided locator is not found or not displayed
     * @param by
     */
    public static void verifyElementDisplayed(By by) {
        try {
            assertTrue(Driver.getDriver().findElement(by).isDisplayed(), "Element not visible: "+by);
        } catch (NoSuchElementException e) {
            fail("Element not found: " + by);

        }
    }

    /**
     * Verifies whether the element is displayed on page
     * fails if the element is not found or not displayed
     * @param element
     */
    public static void verifyElementDisplayed(WebElement element) {
        try {
            assertTrue(element.isDisplayed(), "Element not visible: "+element);
        } catch (NoSuchElementException e) {
            fail("Element not found: " + element);

        }
    }

    /**
     * Waits for element to be not stale
     * @param element
     */
    public void waitForStaleElement(WebElement element) {
        int y = 0;
        while (y <= 15) {
            if(y==1)
                try {
                    element.isDisplayed();
                    break;
                } catch (StaleElementReferenceException st) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (WebDriverException we) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    /**
     * Selects a random value from a dropdown list and returns the selected Web Element
     * @param select
     * @return
     */
    public static WebElement selectRandomTextFromDropdown(Select select) {
        Random random = new Random();
        List<WebElement> weblist = select.getOptions();
        int optionIndex = 1 + random.nextInt(weblist.size() - 1);
        select.selectByIndex(optionIndex);
        return select.getFirstSelectedOption();
    }

    //Select Dropdown by value
    public static void selectFromDropDown(WebElement element, String value){
        Select select = new Select(element);
        select.selectByValue(value);
    }

    //Select Dropdown by index
    public static void selectFromDropdown(WebElement element, int index){
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    //Select Dropdown by visible text
    public static void selectFromDropdown(WebElement element, String visibleText){
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    public static String getTextFromSelectedDropDown(WebElement element){
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }


    /**
     * Clicks on an element using JavaScript
     * @param element
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }


    /**
     * Scrolls down to an element using JavaScript
     * @param element
     */
    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Performs double click action on an element
     * @param element
     */
    public void doubleClick(WebElement element) {
        new Actions(Driver.getDriver()).doubleClick(element).build().perform();
    }

    /**
     * Changes the HTML attribute of a Web Element to the given value using JavaScript
     * @param element
     * @param attributeName
     * @param attributeValue
     */
    public void setAttribute(WebElement element, String attributeName, String attributeValue) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attributeName, attributeValue);
    }

    /**
     *
     * @param element
     * @param check
     */
    public static void selectCheckBox(WebElement element, boolean check){
        if(check){
            if(!element.isSelected()){
                element.click();
            }
        } else {
            if(element.isSelected()){
                element.click();
            }
        }
    }

    public static String[] fromListToString(List<WebElement> x){
        String[] trans = new String[x.size()];
        for (WebElement xx : x) {
            int count = 0;
            trans[count] = xx.getText();
            count++;
        }
        Arrays.sort(trans);

        return trans;
    }

    public static void grabHold(WebDriver driver, String parentHandle){
        /* /NOTE: Be sure to set -> String parentHandle=driver.getWindowHandle(); prior to the action preceding method deployment */
        Set<String> windows= driver.getWindowHandles();
        for(String window: windows){
            if(window!=parentHandle)
                driver.switchTo().window(window);
        }
    }

    public static void waitUntilTitleEquals(int timeout, String x) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        wait.until(ExpectedConditions.titleContains(x));
    }


    public static String getElementText(WebElement element){
        waitForVisibility(element,20);
        return element.getText();
    }

    public static void verifyElementText(WebElement element, String expected){
        Assert.assertEquals(getElementText(element), expected);
    }

//    // Calculate the age
//    public static int calculateAge(String birth){
//        int acadYear;
//        int month = Integer.parseInt(birth.substring(0,2));
//        int day = Integer.parseInt(birth.substring(3,5));
//        int year = Integer.parseInt(birth.substring(6,10));
//
//        if (Student_Pg.sAcademicYear.substring(0,4).equals("2019")){
//            acadYear =2019;
//        }else {
//            acadYear = 2020;
//        }
//        LocalDate academicDate = LocalDate.of(acadYear, 9, 1);   //Academic date
//        LocalDate birthday = LocalDate.of(year, month, day);  //Birth date
//
//        Period period = Period.between(birthday, academicDate);
//
////Now access the values as below
////        System.out.println(period.getDays());
////        System.out.println(period.getMonths());
////        System.out.println(period.getYears());
//        return period.getYears();
//    }



    /**
     * This method will convert list of WebElements into list of string
     *
     * @param listOfWebElements
     * @return list of strings
     */
    public static List<String> getListOfString(List<WebElement> listOfWebElements) {
        List<String> listOfStrings = new ArrayList<>();
        for (WebElement element : listOfWebElements) {
            String value = element.getText().trim();
            //if there is no text
            //do not add this blank text into list
            if (value.length() > 0) {
                listOfStrings.add(value);
            }
        }
        return listOfStrings;
    }
}
