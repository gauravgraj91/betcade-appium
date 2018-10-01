package com.betcade.appiumtests;

import com.betcade.appiumtests.api.android.Android;
import com.betcade.appiumtests.core.UiObject;
import com.betcade.appiumtests.core.UiSelector;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.betcade.appiumtests.api.android.Android.adb;

/**
 * Created by Gaurav on 4/27/2017.
 */
@Component
public class AbstractTest {

    AndroidDriver driver = null;

    //@Test(description = "Preset Desired capabilities to run test cases")
    public void launchApp() throws MalformedURLException {
        File appDir = new File("src");
        File app = new File(appDir, "betcadebuild874prod.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability("BROWSER_NAME", "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "04e7bbc02523da92");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability("platformVersion", "6.0.1");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        Android.driver = driver;
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        //capabilities.setCapability("appPackage", "com.betcade.android.market");
        //capabilities.setCapability("test", "Android Emulator");
    }

    /**
     * Data provider forcan login through google object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "loginByGoogle")
    public Object[][] dataProviderForCanLoginThroughGoogle()
    {
        Object[][] data = new Object[1][5];

        data[0][0] = "CREATE ACCOUNT";
        data[0][1] = "com.betcade.android.market:id/lblGoogle";
        data[0][2] = "com.betcade.android.market:id/lblPositive";
        data[0][3] = "gaurav.raj@innoflexion.com";
        data[0][4] = "com.betcade.android.market:id/viewPIN1";

        return data;
    }

    /**
     * Can login through google.
     *
     * @param createAccountButton_name the create account button name
     * @param loginGoogleButton_id     the login google button id
     * @param agreeTermsButton_id      the agree terms button id
     * @param selectEmailIdButton_name the select email id button name
     * @param enterPINText_id          the enter pin text id
     * @throws Exception the exception
     */
    @Test(description = "Login Through Google", dataProvider = "loginByGoogle")
    public void canLoginThroughGoogle(String createAccountButton_name, String loginGoogleButton_id, String agreeTermsButton_id, String selectEmailIdButton_name, String enterPINText_id) throws Exception
    {
        UiObject createAccountButton = new UiSelector().text(createAccountButton_name).makeUiObject();
        createAccountButton.waitToAppear(30).tap();
        UiObject loginByGoogle =  new UiSelector().resourceId(loginGoogleButton_id).makeUiObject();
        loginByGoogle.waitToAppear(30).tap();
        UiObject agreeTerms = new UiSelector().resourceId(agreeTermsButton_id).makeUiObject();
        agreeTerms.waitToAppear(30).tap();
        UiObject selectEmail = new UiSelector().text(selectEmailIdButton_name).makeUiObject();
        selectEmail.waitToAppear(30).tap();
        UiObject send4Digits = new UiSelector().resourceId("com.betcade.android.market:id/viewPIN1").makeUiObject();
        UiObject newUserSetupPin = new UiSelector().text("Setup Pin").makeUiObject();
        if (send4Digits.waitToAppear(30).exists()){
            send4Digits.waitToAppear(30).typeText("2486");
        } else if (newUserSetupPin.waitToAppear(30).exists()) {
            newUserSetupPin.waitToAppear(30).tap();
            UiObject enterNewPin = new UiSelector().resourceId("com.betcade.android.market:id/rlPIN1").makeUiObject();
            enterNewPin.waitToAppear(30).typeText("2486");
            UiObject enterNewPinTwice = new UiSelector().resourceId("com.betcade.android.market:id/rlConfirmPIN1").makeUiObject();
            enterNewPinTwice.waitToAppear(30).typeText("2486");
            UiObject skipStarterPack = new UiSelector().resourceId("com.betcade.android.market:id/lblSkip").makeUiObject();
            skipStarterPack.waitToAppear(30).tap();
        }
    }

    /**
     * Data provider for can logout of the app object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "logoutOfTheApp")
    public Object[][] dataProviderForCanLogoutOfTheApp()
    {
        Object[][] data = new Object[1][4];

        data[0][0] = "com.betcade.android.market:id/imgToolbarMenu";
        data[0][1] = "My Account";
        data[0][2] = "Log Out";
        data[0][3] = "com.betcade.android.market:id/lblPositive";

        return data;
    }

    /**
     * Can logout of the app.
     *
     * @param hamburgerMenuButton_id the hamburger menu button id
     * @param myAccountButton_name   the my account button name
     * @param logoutButton_name      the logout button name
     * @param confirmLogoutButton_id the confirm logout button id
     * @throws Exception the exception
     */
    @Test(description = "Logout of the App", dependsOnMethods = "canCheckWishListStatus", dataProvider = "logoutOfTheApp")
    public void canLogoutOfTheApp(String hamburgerMenuButton_id, String myAccountButton_name, String logoutButton_name, String confirmLogoutButton_id) throws Exception
    {
        UiObject hamburgerMenu = new UiSelector().resourceId(hamburgerMenuButton_id).makeUiObject();
        hamburgerMenu.waitToAppear(30).tap();
        UiObject myAccount = new UiSelector().text(myAccountButton_name).makeUiObject();
        myAccount.waitToAppear(30).tap();
        UiObject logoutButton = new UiSelector().text(logoutButton_name).makeUiObject();
        logoutButton.waitToAppear(30).tap();
        UiObject confirmLogout = new UiSelector().resourceId(confirmLogoutButton_id).makeUiObject();
        confirmLogout.waitToAppear(30).tap();
        driver.quit();
        adb.killServer();
    }
}