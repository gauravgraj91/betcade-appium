package com.betcade.appiumtests.core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import java.awt.*;
import static com.betcade.appiumtests.api.android.Android.driver;

/**
 * Created by Gaurav on 5/12/2017.
 */
public class UiObject {

    private String locator;

    UiObject(String locator){
        this.locator = locator;
        //MyLogger.log.debug("Created new UiObject: "+this.locator);
    }

    private boolean isXpath(){
        return !locator.contains("UiSelector");
    }

    public boolean exists(){
        try{
            WebElement element;
            if(isXpath()) element = driver.findElementByXPath(locator);
            else element = driver.findElementByAndroidUIAutomator(locator);
            return element.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isChecked(){
        WebElement element;
        if(isXpath()) element = driver.findElementByXPath(locator);
        else element = driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("checked").equals("true");
    }

    public boolean isCheckable(){
        WebElement element;
        if(isXpath()) element = driver.findElementByXPath(locator);
        else element = driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("checkable").equals("true");
    }

    public boolean isClickable(){
        WebElement element;
        if(isXpath()) element = driver.findElementByXPath(locator);
        else element = driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("clickable").equals("true");
    }

    public boolean isEnabled(){
        WebElement element;
        if(isXpath()) element = driver.findElementByXPath(locator);
        else element = driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("enabled").equals("true");
    }

    public boolean isFocusable(){
        WebElement element;
        if(isXpath()) element = driver.findElementByXPath(locator);
        else element = driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("focusable").equals("true");
    }

    public boolean isFocused(){
        WebElement element;
        if(isXpath()) element = driver.findElementByXPath(locator);
        else element = driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("focused").equals("true");
    }

    public boolean isScrollable(){
        WebElement element;
        if(isXpath()) element = driver.findElementByXPath(locator);
        else element = driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("scrollable").equals("true");
    }

    public boolean isLongClickable(){
        WebElement element;
        if(isXpath()) element = driver.findElementByXPath(locator);
        else element = driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("longClickable").equals("true");
    }

    public boolean isSelected(){
        WebElement element;
        if(isXpath()) element = driver.findElementByXPath(locator);
        else element = driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("selected").equals("true");
    }

    public Point getLocation(){
        WebElement element;
        if(isXpath()) element = driver.findElementByXPath(locator);
        else element = driver.findElementByAndroidUIAutomator(locator);
        return element.getLocation();
    }

    public String getText(){
        WebElement element;
        if(isXpath()) element = driver.findElementByXPath(locator);
        else element = driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("name");
    }

    public String getResourceId(){
        WebElement element;
        if(isXpath()) element = driver.findElementByXPath(locator);
        else element = driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("resourceId");
    }

    public String getClassName(){
        WebElement element;
        if(isXpath()) element = driver.findElementByXPath(locator);
        else element = driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("className");
    }

    public String getContentDesc(){
        WebElement element;
        if(isXpath()) element = driver.findElementByXPath(locator);
        else element = driver.findElementByAndroidUIAutomator(locator);
        return element.getAttribute("contentDesc");
    }

    public UiObject clearText(){
        if(isXpath()) driver.findElementByXPath(locator).clear();
        else  driver.findElementByAndroidUIAutomator(locator).clear();
        return this;
    }

    public UiObject typeText(String value){
        if(isXpath()) driver.findElementByXPath(locator).sendKeys(value);
        else  driver.findElementByAndroidUIAutomator(locator).sendKeys(value);
        return this;
    }

    public UiObject tap(){
        if(isXpath()) driver.findElementByXPath(locator).click();
        else  driver.findElementByAndroidUIAutomator(locator).click();
        return this;
    }

    public UiObject waitToAppear(int seconds){
        Timer timer = new Timer();
        timer.start();
        while(!timer.expired(seconds)) if(exists()) break;
        if(timer.expired(seconds) && !exists()) throw new AssertionError("Element "+locator+" failed to appear within "+seconds+" seconds");
        return this;
    }

    public UiObject waitToDisappear(int seconds){
        Timer timer = new Timer();
        timer.start();
        while(!timer.expired(seconds)) if(!exists()) break;
        if(timer.expired(seconds) && exists()) throw new AssertionError("Element "+locator+" failed to disappear within "+seconds+" seconds");
        return this;
    }

    public static void scrollVerticalBottomToTop(){
        Dimension size;
        size = driver.manage().window().getSize();
        int starty = (int) (size.height * 0.80);
        int endy = (int) (size.height * 0.20);
        int startx = size.width / 2;
        driver.swipe(startx, starty, startx, endy, 3000);
    }

    public static void scrollVerticalTopToBottom(){
        Dimension size;
        size = driver.manage().window().getSize();
        int starty = (int) (size.height * 0.80);
        int endy = (int) (size.height * 0.20);
        int startx = size.width / 2;
        driver.swipe(startx, endy, startx, starty, 3000);
    }

    public static void scrollHorizontalRightToLeft(){
        Dimension size;
        size = driver.manage().window().getSize();
        int startx = (int) (size.width * 0.70);
        int endx = (int) (size.width * 0.30);
        int starty = size.height / 2;
        driver.swipe(startx, starty, endx, starty, 3000);
    }

    public static void scrollHorizontalLeftToRight(){
        Dimension size;
        size = driver.manage().window().getSize();
        int startx = (int) (size.width * 0.70);
        int endx = (int) (size.width * 0.30);
        int starty = size.height / 2;
        driver.swipe(startx, starty, endx, starty, 3000);
    }
}