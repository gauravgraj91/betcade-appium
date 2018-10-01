package com.betcade.appiumtests;

import com.betcade.appiumtests.api.android.Android;
import com.betcade.appiumtests.core.UiObject;
import com.betcade.appiumtests.core.UiSelector;
import com.betcade.appiumtests.core.managers.DriverManager;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.betcade.appiumtests.api.android.Android.adb;

/**
 * Created by Gaurav on 5/18/2017.
 */
public class MiscTests {

    public AndroidDriver driver;
    AbstractTest abstractTest = new AbstractTest();
    //ADB adb = new ADB();

    @Test
    public void canLoginThroughGoogle() throws Exception{
        DriverManager.createDriver();
        driver = Android.driver;
        if (adb.wifiStatus().contains("disabled"));{
            adb.wifiTurnOn1();
        }
        Object [][] objects = abstractTest.dataProviderForCanLoginThroughGoogle();
        abstractTest.canLoginThroughGoogle(objects[0][0].toString(), objects[0][1].toString(), objects[0][2].toString(), objects[0][3].toString(), objects[0][4].toString());
    }

    /*@Test(description = "Can Login through Email")
    public void canLoginByEmailLogin() throws Exception{
        UiObject createAccountButton = new UiSelector().text("CREATE ACCOUNT").makeUiObject();
        createAccountButton.waitToAppear(5).tap();
        UiObject selectEmailLoginMethod = new UiSelector().resourceId("com.betcade.android.market:id/imgEmail").makeUiObject();
        selectEmailLoginMethod.tap();
        UiObject loginByEmail = new UiSelector().resourceId("com.betcade.android.market:id/txtEmail").makeUiObject();
        loginByEmail.typeText("venkatgraj91@gmail.com");
        UiObject send4Pin = new UiSelector().resourceId("com.betcade.android.market:id/viewPIN1").makeUiObject();
        send4Pin.typeText("2486");
    }*/

    /*@Test(description = "Mobile Login Test")
    public void canLoginThroughMobile() throws Exception{
        UiObject createAccountButton = new UiSelector().text("CREATE ACCOUNT").makeUiObject();
        createAccountButton.waitToAppear(5).tap();
        UiObject selectMobileLoginMethod = new UiSelector().resourceId("com.betcade.android.market:id/imgPhone").makeUiObject();
        selectMobileLoginMethod.tap();
        *//*UiObject loginWithPhoneNumber  = new UiSelector().resourceId("com.betcade.android.market:id/txtNumber").makeUiObject();
        loginWithPhoneNumber.typeText("919538292289");*//*
        AndroidElement sendPhoneNumber = (AndroidElement)(driver.findElement(By.id("com.betcade.android.market:id/txtNumber")));
        sendPhoneNumber.click();
        sendPhoneNumber.sendKeys(Keys.BACK_SPACE);
        sendPhoneNumber.sendKeys(Keys.BACK_SPACE);
        sendPhoneNumber.sendKeys("919538292289");
        UiObject tapOnNext = new UiSelector().text("NEXT").makeUiObject();
        tapOnNext.tap();
    }*/

    /*@DataProvider(name = "addAnAppToWishList")
    public Object[][] dataProviderForCanAddAppsToWishList()
    {
        Object[][] data = new Object[1][4];
        data[0][0] = "com.betcade.android.market:id/imgToolbarMenu";
        data[0][1] = "Store Home";
        data[0][2] = "bwin Sports";
        data[0][3] = "com.betcade.android.market:id/lblSaveLater";
        //data[0][4] = "com.betcade.android.market:id/lblSaveLater";

        return data;
    }

    @Test(description = "Add apps to Wishlist method 1", dataProvider = "addAnAppToWishList")
    public void canAddAppsToWishList(String hamburgerMenuButton_Id,String selectStoreHome_name, String addBetFredSportsBook_name, String selectAddToWishList_id) throws Exception
    {
        *//*UiObject hamburgerMenu = new UiSelector().resourceId(hamburgerMenuButton_Id).makeUiObject();
        hamburgerMenu.tap();*//*
        *//*UiObject selectStoreHome = new UiSelector().text(selectStoreHome_name).makeUiObject();
        selectStoreHome.tap();*//*
        UiObject selectBetFredSportsBook = new UiSelector().text(addBetFredSportsBook_name).makeUiObject();
        selectBetFredSportsBook.tap();
        AndroidElement swipeUpAppDetails = (AndroidElement) (driver.findElement(By.id("com.betcade.android.market:id/imgBonus")));
        swipeUpAppDetails.swipe(SwipeElementDirection.UP, 3000);
        AndroidElement swipeUpNextAppDetails = (AndroidElement) (driver.findElement(By.id("com.betcade.android.market:id/llEase")));
        swipeUpNextAppDetails.swipe(SwipeElementDirection.UP, 3000);
        AndroidElement swipeUpNextOneAppDetails = (AndroidElement) (driver.findElement(By.id("com.betcade.android.market:id/llGamePlay")));
        swipeUpNextOneAppDetails.swipe(SwipeElementDirection.UP, 5000);
        AndroidElement swipeUpNextTwoAppDetails = (AndroidElement) (driver.findElement(By.name("APP INFORMATION")));
        swipeUpNextTwoAppDetails.swipe(SwipeElementDirection.UP, 5000);
        UiObject selectAddToWishListFromThreeDots = new UiSelector().resourceId(selectAddToWishList_id).makeUiObject();
        selectAddToWishListFromThreeDots.tap();
    }

    @DataProvider(name = "checkWishListStatus")
    public Object[][] dataProviderForCanCheckWishListStatus()
    {
        Object[][] data = new Object[1][4];

        data[0][0] = "com.betcade.android.market:id/imgToolMenu";
        data[0][1] = "My Apps";
        data[0][2] = "WISH LIST";
        data[0][3] = "bwin Sports";

        return data;
    }

    @Test(description = "Navigate to My Apps to check wishlist status", dependsOnMethods = {"canAddAppsToWishList"}, dataProvider = "checkWishListStatus")
    public void canCheckWishListStatus(String hamburgerMenuButton_Id, String myAppsSubMenu, String wishListTab, String betFredSportsBook) throws Exception
    {
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
        }
        UiObject hamburgerMenu = new UiSelector().resourceId(hamburgerMenuButton_Id).makeUiObject();
        hamburgerMenu.tap();
        UiObject selectMyApps = new UiSelector().text(myAppsSubMenu).makeUiObject();
        selectMyApps.tap();
        UiObject selectWishList = new UiSelector().text(wishListTab).makeUiObject();
        selectWishList.tap();
        UiObject checkWilliamHillSports = new UiSelector().text(betFredSportsBook).makeUiObject();
        Assert.assertEquals(checkWilliamHillSports.getText(), "bwin Sports");
    }*/

    /*@DataProvider(name = "shareAnApp")
    public Object[][] dataProviderForCanShareAnApp()
    {
        Object[][] data = new Object[1][5];
        data[0][0] = "com.betcade.android.market:id/imgToolbarMenu";
        data[0][1] = "Store Home";
        data[0][2] = "Betfred Sportsbook";
        data[0][3] = "com.betcade.android.market:id/imgOption";
        data[0][4] = "com.betcade.android.market:id/lblShare";

        return data;
    }

    @Test(description = "Can share an app", dataProvider = "shareAnApp")
    public void canShareAnApp(String addBetFredSportsBook_name, String selectThreeDots_id, String selectShareAnApp_id) throws Exception
    {
        UiObject selectBetFredSportsBook = new UiSelector().text(addBetFredSportsBook_name).makeUiObject();
        selectBetFredSportsBook.tap();
        UiObject selectThreeDots = new UiSelector().resourceId(selectThreeDots_id).makeUiObject();
        selectThreeDots.waitToAppear(5).tap();
        UiObject selectShareAnAppFromThreeDots = new UiSelector().resourceId(selectShareAnApp_id).makeUiObject();
        selectShareAnAppFromThreeDots.waitToAppear(5).tap();
        *//*UiObject shareDetails = new UiSelector().className("android.widget.TextView").makeUiObject();
        Assert.assertEquals(shareDetails.getText(), "Whatsapp");*//*
        UiObject shareAppName = new UiSelector().resourceId("android:id/text1").makeUiObject();
        if (shareAppName.exists()) {
            Assert.assertEquals(shareAppName.getText(), "Whatsapp");
        }else if (shareAppName.exists()){
            Assert.assertEquals(shareAppName.getText(), "Messages");
        }else if (shareAppName.exists()){
            Assert.assertEquals(shareAppName.getText(), "GMail");
        }else if (shareAppName.exists()){
            Assert.assertEquals(shareAppName.getText(), "FaceBook");
        }
        AndroidElement swipeDown = (AndroidElement)(driver.findElement(By.id("android:id/icon")));
        swipeDown.swipe(SwipeElementDirection.UP, 4000);
        adb.navigateBack();
        adb.navigateBack();
    }*/

    /*@DataProvider(name = "navigateToUpdateProfile")
    public Object[][] dataProviderForCanUpdateProfileDetailsOfAUser()
    {
        Object[][] data = new Object[1][3];

        data[0][0] = "com.betcade.android.market:id/imgToolbarMenu";
        data[0][1] = "com.betcade.android.market:id/imgUser";
        data[0][2] = "com.betcade.android.market:id/viewKycCheck";

        return data;
    }

    @Test(description = "Update Profile details of a user", dataProvider = "navigateToUpdateProfile")
    public void canUpdateProfileDetailsOfAUser(String hamburgerMenu_id, String goToAccountInformationTab_id, String updateProfileButton_id) throws Exception
    {
        UiObject hamburgerMenu = new UiSelector().resourceId(hamburgerMenu_id).makeUiObject();
        hamburgerMenu.waitToAppear(30).tap();
        UiObject goToProfileNames = new UiSelector().resourceId(goToAccountInformationTab_id).makeUiObject();
        goToProfileNames.waitToAppear(30).tap();
        UiObject updateProfileButton = new UiSelector().resourceId(updateProfileButton_id).makeUiObject();
        updateProfileButton.waitToAppear(30).tap();
    }

    @Test(description = "Can Update Address Name, House, Street and Apartment Details", dependsOnMethods = "canUpdateProfileDetailsOfAUser")
    public void canUpdateBillingAddressOfAUser() throws Exception {
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
        }
        UiObject updateFirstName = new UiSelector().resourceId("com.betcade.android.market:id/txtFirstName").makeUiObject();
        //updateFirstName.waitToAppear(30).tap().clearText().waitToAppear(30).typeText("Slith");
        updateFirstName.waitToAppear(30).clearText().waitToAppear(30).typeText("Slith");
        UiObject updateLastName = new UiSelector().resourceId("com.betcade.android.market:id/txtLastName").makeUiObject();
        updateLastName.waitToAppear(30).clearText().waitToAppear(30).typeText("Ice");
        *//*UiObject updateHouseStreet = new UiSelector().resourceId("com.betcade.android.market:id/txtHouseStreet").makeUiObject();
        updateHouseStreet.waitToAppear(30).clearText().waitToAppear(30).typeText("61 Wellfield Road");*//*
        //UiObject updateFlatAndApartmentNo = new UiSelector().resourceId("com.betcade.android.market:id/txtFlat").makeUiObject();
        //updateFlatAndApartmentNo.waitToAppear(30).typeText("3 Edgar Buildings");
        *//*updateLastName.waitToAppear(30).clearText();
        updateLastName.waitToAppear(30).typeText("ice");*//*
        *//*try {
            driver.hideKeyboard();
        } catch (Exception e) {
        }*//*
    }

    @Test(description = "Can update city and other details of the user", dependsOnMethods = "canUpdateBillingAddressOfAUser")
    public void canUpdateBillingAddressOfAUser2() throws Exception
    {
        try {driver.hideKeyboard();} catch (Exception e) {}
        UiObject updateCity = new UiSelector().resourceId("com.betcade.android.market:id/txtCity").makeUiObject();
        updateCity.waitToAppear(30).tap().clearText().waitToAppear(30).typeText("Edgar");
        UiObject updatePostalCode = new UiSelector().resourceId("com.betcade.android.market:id/txtPostalCode").makeUiObject();
        updatePostalCode.waitToAppear(30).tap().clearText().waitToAppear(30).typeText("BA1 2FJ");
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
        }
        UiObject updateCountry = new UiSelector().resourceId("com.betcade.android.market:id/txtCountry").makeUiObject();
        updateCountry.waitToAppear(30).tap();
        UiObject selectCountry = new UiSelector().text("United Kingdom").makeUiObject();
        selectCountry.waitToAppear(30).tap();
        UiObject tapOkButton = new UiSelector().resourceId("com.betcade.android.market:id/lblOk").makeUiObject();
        tapOkButton.waitToAppear(30).tap();
        UiObject.scrollVerticalBottomToTop();
        Thread.sleep(2000);
    }

    @Test(description = "Can update phone, email and DOB", dependsOnMethods = "canUpdateBillingAddressOfAUser2")
    public void canUpdateBillingAddressOfAUser3() throws Exception
    {
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
        }
        UiObject updatePhone = new UiSelector().resourceId("com.betcade.android.market:id/txtMobileNumber").makeUiObject();
        updatePhone.waitToAppear(30).clearText().waitToAppear(30).typeText("+919999999999");
        UiObject updateEmail = new UiSelector().resourceId("com.betcade.android.market:id/txtEmailAddress").makeUiObject();
        updateEmail.waitToAppear(30).clearText().waitToAppear(30).typeText("slithice@sample.com");
        UiObject updateDOB = new UiSelector().resourceId("com.betcade.android.market:id/txtDOB").makeUiObject();
        updateDOB.waitToAppear(30).tap();
        UiObject tapAddAfterUpdate = new UiSelector().resourceId("com.betcade.android.market:id/lblAdd").makeUiObject();
        tapAddAfterUpdate.waitToAppear(30).tap();
        UiObject tapUpdateButton = new UiSelector().resourceId("com.betcade.android.market:id/btnUpdate").makeUiObject();
        tapUpdateButton.tap();
    }*/

    @AfterClass
    public void canLogoutOfGoogleLogin() throws Exception
    {
        Object [][] objects = abstractTest.dataProviderForCanLogoutOfTheApp();
        abstractTest.canLogoutOfTheApp(objects[0][0].toString(), objects[0][1].toString(), objects[0][2].toString(), objects[0][3].toString());
        driver = abstractTest.driver;
    }
}
