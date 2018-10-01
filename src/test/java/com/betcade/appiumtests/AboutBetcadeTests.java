package com.betcade.appiumtests;

import com.betcade.appiumtests.api.android.Android;
import com.betcade.appiumtests.core.UiObject;
import com.betcade.appiumtests.core.UiSelector;
import com.betcade.appiumtests.core.managers.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.betcade.appiumtests.api.android.Android.adb;

/**
 * Created by Gaurav on 5/2/2017.
 */

@Component
public class AboutBetcadeTests {

    public AndroidDriver driver;
    AbstractTest abstractTest = new AbstractTest();

    /**
     * Can login through google.
     *
     * @throws Exception the exception
     */
    @BeforeClass()
    public void canLoginThroughGoogle() throws Exception
    {
        DriverManager.createDriver();
        driver = Android.driver;
        Object [][] objects = abstractTest.dataProviderForCanLoginThroughGoogle();
        abstractTest.canLoginThroughGoogle(objects[0][0].toString(), objects[0][1].toString(), objects[0][2].toString(), objects[0][3].toString(), objects[0][4].toString());
    }

    /**
     * Data provider for about betcade object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "checkAboutBetcade")
    public Object[][] dataProviderForAboutBetcade()
    {
        Object[][] data = new Object[1][7];

        data[0][0] = "com.betcade.android.market:id/imgToolbarMenu";
        data[0][1] = "About Betcade";
        data[0][2] = "com.betcade.android.market:id/lblTerms";
        data[0][3] = "android:id/button_once";
        data[0][4] = "https://betcade.com/terms-of-service/";
        data[0][5] = "com.betcade.android.market:id/lblPolicy";
        data[0][6] = "https://betcade.com/privacy-policy/";

        return data;
    }

    /**
     * Can check about betcade.
     *
     * @param hamburgerMenuButton_id  the hamburger menu button id
     * @param aboutBetcadeButton_name the about betcade button name
     * @param termsAndServiceLink_id  the terms and service link id
     * @param tapJustOnceButton_id    the tap just once button id
     * @param termsAndServiceURL_name the terms and service url name
     * @param policyLink_id           the policy link id
     * @param privacyPolicyURL_name   the privacy policy url name
     * @throws Exception the exception
     */
    @Test(description = "Navigate to about betcade tab and check for links", dataProvider = "checkAboutBetcade")
    public void canCheckAboutBetcade(String hamburgerMenuButton_id, String aboutBetcadeButton_name, String termsAndServiceLink_id, String tapJustOnceButton_id, String termsAndServiceURL_name, String policyLink_id, String privacyPolicyURL_name ) throws Exception
    {
        UiObject tapOnHamburgerMenu = new UiSelector().resourceId(hamburgerMenuButton_id).makeUiObject();
        tapOnHamburgerMenu.waitToAppear(30).tap();
        UiObject tapOnAboutBetcade = new UiSelector().text(aboutBetcadeButton_name).makeUiObject();
        tapOnAboutBetcade.waitToAppear(30).tap();
        UiObject tapOnTermsAndService = new UiSelector().resourceId(termsAndServiceLink_id).makeUiObject();
        tapOnTermsAndService.waitToAppear(30).tap();
        UiObject tapJustOnce = new UiSelector().resourceId(tapJustOnceButton_id).makeUiObject();
        if (tapJustOnce != null) {
            tapJustOnce.waitToAppear(30).tap();
        }
        UiObject termsAndServiceURL =  new UiSelector().text(termsAndServiceURL_name).makeUiObject();
        Assert.assertEquals(termsAndServiceURL.waitToAppear(30).getText(), "https://betcade.com/terms-of-service/");
        adb.navigateBack();
        UiObject tapOnPrivacyPolicy = new UiSelector().resourceId(policyLink_id).makeUiObject();
        tapOnPrivacyPolicy.waitToAppear(30).tap();
        if (tapJustOnce != null) {
            tapJustOnce.waitToAppear(30).tap();
        }
        UiObject privacyPolicyURL = new UiSelector().text(privacyPolicyURL_name).makeUiObject();
        Assert.assertEquals(privacyPolicyURL.waitToAppear(30).getText(), "https://betcade.com/privacy-policy/");
        adb.navigateBack();
        adb.navigateBack();
    }

    /**
     * Can logout of google login.
     *
     * @throws Exception the exception
     */
    @AfterClass
    public void canLogoutOfGoogleLogin() throws Exception{
        Object [] [] objects = abstractTest.dataProviderForCanLogoutOfTheApp();
        abstractTest.canLogoutOfTheApp(objects[0][0].toString(), objects[0][1].toString(), objects[0][2].toString(), objects[0][3].toString());
        driver = abstractTest.driver;
    }
}