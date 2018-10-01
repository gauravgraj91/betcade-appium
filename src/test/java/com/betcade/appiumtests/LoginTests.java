package com.betcade.appiumtests;

/**
 * Created by Gaurav on 3/31/2017.
 */
import static com.betcade.appiumtests.api.android.Android.adb;

import com.betcade.appiumtests.api.android.Android;
import com.betcade.appiumtests.core.managers.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import com.betcade.appiumtests.core.UiSelector;
import com.betcade.appiumtests.core.UiObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import java.net.MalformedURLException;
import org.testng.annotations.Test;
import org.testng.Assert;

@Component
public class LoginTests {

    public AndroidDriver driver;
    AbstractTest abstractTest = new AbstractTest();

    @BeforeTest
    public void test() throws MalformedURLException {
        DriverManager.createDriver();
        driver = Android.driver;
    }

    /**
     * Data provider method object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "loginByGoogle")
    public Object[][] dataProviderForCanLoginByGoogle()
    {
        Object[][] data = new Object[1][6];

        data[0][0] = "CREATE ACCOUNT";
        data[0][1] = "com.betcade.android.market:id/lblGoogle";
        data[0][2] = "com.betcade.android.market:id/lblPositive";
        data[0][3] = "gaurav.raj@innoflexion.com";
        data[0][4] = "com.betcade.android.market:id/btnSetupPin";
        data[0][5] = "com.betcade.android.market:id/viewPIN1";

        return data;
    }

    /**
     * Can login by google.
     *
     * @param createAccountButton_name the create account name
     * @param loginGoogleButton_id     the login google button id
     * @param agreeTermsButton_id      the agree terms button id
     * @param selectEmailIdButton_name the select email id button name
     * @param enterPINText_id          the enter pin text id
     * @throws Exception the exception
     */
    @Test(description = "Login by Google", dataProvider = "loginByGoogle")
    public void canLoginByGoogle( String createAccountButton_name, String loginGoogleButton_id, String agreeTermsButton_id, String selectEmailIdButton_name, String newUserSetupPin_id, String enterPINText_id) throws Exception
    {
        UiObject createAccountButton = new UiSelector().text(createAccountButton_name).makeUiObject();
        createAccountButton.waitToAppear(25).tap();
        UiObject loginByGoogle =  new UiSelector().resourceId(loginGoogleButton_id).makeUiObject();
        loginByGoogle.waitToAppear(30).tap();
        UiObject agreeTerms = new UiSelector().resourceId(agreeTermsButton_id).makeUiObject();
        agreeTerms.waitToAppear(30).tap();
        UiObject selectEmail = new UiSelector().text(selectEmailIdButton_name).makeUiObject();
        selectEmail.waitToAppear(30).tap();
        UiObject newUserSetupPin = new UiSelector().resourceId(newUserSetupPin_id).makeUiObject();
        UiObject send4Digits = new UiSelector().resourceId(enterPINText_id).makeUiObject();
        if (send4Digits.waitToAppear(30).exists()){
            send4Digits.waitToAppear(30).typeText("2486");
            Thread.sleep(5000);
        } else if (newUserSetupPin.waitToAppear(30).exists()) {
            newUserSetupPin.tap();
            UiObject enterNewPin = new UiSelector().resourceId("com.betcade.android.market:id/rlPIN1").makeUiObject();
            enterNewPin.waitToAppear(30).typeText("2486");
            UiObject enterNewPinTwice = new UiSelector().resourceId("com.betcade.android.market:id/rlConfirmPIN1").makeUiObject();
            enterNewPinTwice.waitToAppear(30).typeText("2486");
            UiObject skipStarterPack = new UiSelector().resourceId("com.betcade.android.market:id/lblSkip").makeUiObject();
            skipStarterPack.waitToAppear(30).tap();
        }
    }

    /**
     * Data provider for can logout of the google login object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "logoutOfTheGoogleLogin")
    public Object[][] dataProviderForCanLogoutOfTheGoogleLogin()
    {
        Object[][] data = new Object[1][4];

        data[0][0] = "com.betcade.android.market:id/imgToolbarMenu";
        data[0][1] = "My Account";
        data[0][2] = "Log Out";
        data[0][3] = "com.betcade.android.market:id/lblPositive";

        return data;
    }

    /**
     * Can logout of the google login.
     *
     * @param hamburgerMenuButton_id the hamburger menu button id
     * @param myAccountButton_id     the my account button id
     * @param logoutButton_name      the logout button name
     * @param confirmLogoutButton_id the confirm logout button id
     * @throws Exception the exception
     */
    @Test(description = "logout of the Google Login", dependsOnMethods = "canLoginByGoogle", dataProvider = "logoutOfTheGoogleLogin")
    public void canLogoutOfTheGoogleLogin(String hamburgerMenuButton_id, String myAccountButton_id, String logoutButton_name, String confirmLogoutButton_id) throws Exception
    {
        UiObject hamburgerMenu = new UiSelector().resourceId(hamburgerMenuButton_id).makeUiObject();
        hamburgerMenu.waitToAppear(30).tap();
        UiObject myAccount = new UiSelector().text(myAccountButton_id).makeUiObject();
        myAccount.waitToAppear(30).tap();
        UiObject logoutButton = new UiSelector().text(logoutButton_name).makeUiObject();
        logoutButton.waitToAppear(30).tap();
        UiObject confirmLogout = new UiSelector().resourceId(confirmLogoutButton_id).makeUiObject();
        confirmLogout.waitToAppear(30).tap();
    }

    /**
     * Data provider for can use forgot pin by google object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "useForgotPINByGoogle")
    public Object[][] dataProviderForCanUseForgotPINByGoogle()
    {
        Object[][] data = new Object[1][7];

        data[0][0] = "com.betcade.android.market:id/lblGoogle";
        data[0][1] = "com.betcade.android.market:id/lblPositive";
        data[0][2] = "gaurav.raj@innoflexion.com";
        data[0][3] = "com.betcade.android.market:id/lblForgotPassword";
        data[0][4] = "com.betcade.android.market:id/rlPIN1";
        data[0][5] = "com.betcade.android.market:id/rlConfirmPIN1";
        data[0][6] = "Your Betcade PIN has been changed successfully.";

        return data;
    }

    /**
     * Can use forgot pin by google.
     *
     * @param loginGoogleButton_id       the login google button id
     * @param agreeTermsButton_id        the agree terms button id
     * @param selectEmailIdButton_name   the select email id button name
     * @param forgotPINButton_id         the forgot pin button id
     * @param send4digits_id             the send 4 digits id
     * @param confirmSend4Digits_id      the confirm send 4 digits id
     * @param verifyPINChangeScreen_name the verify pin change screen name
     * @throws Exception the exception
     */
    @Test(description = "Can use forgot pin by login from google", dependsOnMethods = "canLogoutOfTheGoogleLogin", dataProvider = "useForgotPINByGoogle")
    public void canUseForgotPINByGoogle(String loginGoogleButton_id, String agreeTermsButton_id, String selectEmailIdButton_name, String forgotPINButton_id, String send4digits_id, String confirmSend4Digits_id, String verifyPINChangeScreen_name) throws Exception
    {
        UiObject loginByGoogle =  new UiSelector().resourceId(loginGoogleButton_id).makeUiObject();
        loginByGoogle.waitToAppear(30).tap();
        UiObject agreeTerms = new UiSelector().resourceId(agreeTermsButton_id).makeUiObject();
        agreeTerms.waitToAppear(30).tap();
        UiObject selectEmail = new UiSelector().text(selectEmailIdButton_name).makeUiObject();
        selectEmail.waitToAppear(30).tap();
        UiObject selectForgotPIN = new UiSelector().resourceId(forgotPINButton_id).makeUiObject();
        selectForgotPIN.waitToAppear(30).tap();
        selectEmail.waitToAppear(30).tap();
        UiObject send4Digits = new UiSelector().resourceId(send4digits_id).makeUiObject();
        send4Digits.waitToAppear(30).typeText("2486");
        UiObject confirmSend4Digits = new UiSelector().resourceId(confirmSend4Digits_id).makeUiObject();
        confirmSend4Digits.waitToAppear(30).typeText("2486");
        UiObject verifyPINChangeScreen = new UiSelector().text(verifyPINChangeScreen_name).makeUiObject();
        Assert.assertEquals(verifyPINChangeScreen.waitToAppear(30).getText(),"Your Betcade PIN has been changed successfully.");
    }

    /**
     * Can logout of the google login.
     *
     * @param hamburgerMenuButton_id the hamburger menu button id
     * @param myAccountButton_id     the my account button id
     * @param logoutButton_name      the logout button name
     * @param confirmLogoutButton_id the confirm logout button id
     * @throws Exception the exception
     */
    @Test(description = "logout of the app after pin change", dependsOnMethods = "canUseForgotPINByGoogle", dataProvider = "logoutOfTheGoogleLogin")
    public void canLogoutOfTheGoogleLoginAfterPinChange(String hamburgerMenuButton_id, String myAccountButton_id, String logoutButton_name, String confirmLogoutButton_id) throws Exception
    {
        UiObject hamburgerMenu = new UiSelector().resourceId(hamburgerMenuButton_id).makeUiObject();
        hamburgerMenu.waitToAppear(30).tap();
        UiObject myAccount = new UiSelector().text(myAccountButton_id).makeUiObject();
        myAccount.waitToAppear(30).tap();
        UiObject logoutButton = new UiSelector().text(logoutButton_name).makeUiObject();
        logoutButton.waitToAppear(30).tap();
        UiObject confirmLogout = new UiSelector().resourceId(confirmLogoutButton_id).makeUiObject();
        confirmLogout.waitToAppear(30).tap();
    }

    /**
     * Data provider for can login by facebook object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "loginByFacebook")
    public Object[][] dataProviderForCanLoginByFacebook()
    {
        Object[][] data = new Object[1][4];

        data[0][0] = "com.betcade.android.market:id/lblFb";
        data[0][1] = "com.betcade.android.market:id/lblPositive";
        data[0][2] = "com.betcade.android.market:id/viewPIN1";
        data[0][3] = "Setup Pin";
        return data;
    }

    /**
     * Can login by facebook.
     *
     * @param loginFacebookButton_id the login facebook button id
     * @param agreeTermsButton_id    the agree terms button id
     * @param newUserSetupPin_text     the new user setup pin id
     * @param sendPINTextBox_id      the send pin text box id
     * @throws Exception the exception
     */
    @Test(description = "Login By Facebook For pin change", dependsOnMethods = "canLogoutOfTheGoogleLoginAfterPinChange", dataProvider = "loginByFacebook")
    public void canLoginByFacebookForPinChange(String loginFacebookButton_id, String agreeTermsButton_id, String sendPINTextBox_id, String newUserSetupPin_text) throws Exception
    {
        UiObject loginByFacebook = new UiSelector().resourceId(loginFacebookButton_id).makeUiObject();
        loginByFacebook.waitToAppear(30).tap();
        UiObject agreeTerms = new UiSelector().resourceId(agreeTermsButton_id).makeUiObject();
        agreeTerms.waitToAppear(30).tap();
        UiObject send4Digits = new UiSelector().resourceId(sendPINTextBox_id).makeUiObject();
        UiObject newUserSetupPin = new UiSelector().text(newUserSetupPin_text).makeUiObject();
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

    @Test(description = "logout of the app after pin change", dependsOnMethods = "canLoginByFacebookForPinChange", dataProvider = "logoutOfTheGoogleLogin")
    public void canLogoutOfTheFacebookLoginAfterPinChange(String hamburgerMenuButton_id, String myAccountButton_id, String logoutButton_name, String confirmLogoutButton_id) throws Exception
    {
        UiObject hamburgerMenu = new UiSelector().resourceId(hamburgerMenuButton_id).makeUiObject();
        hamburgerMenu.waitToAppear(30).tap();
        UiObject myAccount = new UiSelector().text(myAccountButton_id).makeUiObject();
        myAccount.waitToAppear(30).tap();
        UiObject logoutButton = new UiSelector().text(logoutButton_name).makeUiObject();
        logoutButton.waitToAppear(30).tap();
        UiObject confirmLogout = new UiSelector().resourceId(confirmLogoutButton_id).makeUiObject();
        confirmLogout.waitToAppear(30).tap();
    }

    /**
     * Data provider for can use forgot pin by facebook login object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "forgotPINByFacebookLogin")
    public Object[][] dataProviderForCanUseForgotPINByFacebookLogin()
    {
        Object[][] data = new Object[1][6];

        data[0][0] = "com.betcade.android.market:id/lblFb";
        data[0][1] = "com.betcade.android.market:id/lblPositive";
        data[0][2] = "com.betcade.android.market:id/lblForgotPassword";
        data[0][3] = "com.betcade.android.market:id/rlPIN1";
        data[0][4] = "com.betcade.android.market:id/rlConfirmPIN1";
        data[0][5] = "Your Betcade PIN has been changed successfully.";

        return data;
    }

    /**
     * Can use forgot pin by facebook login.
     *
     * @param loginFacebookButton_id     the login facebook button id
     * @param agreeTermsButton_id        the agree terms button id
     * @param forgotPINButton_id         the forgot pin button id
     * @param send4digits_id             the send 4 digits id
     * @param confirmSend4Digits_id      the confirm send 4 digits id
     * @param verifyPINChangeScreen_name the verify pin change screen name
     * @throws Exception the exception
     */
    @Test(description = "Can use forgot PIN feature through facebook login", dependsOnMethods = "canLogoutOfTheFacebookLoginAfterPinChange", dataProvider = "forgotPINByFacebookLogin")
    public void canUseForgotPINByFacebookLoginPostPinChange(String loginFacebookButton_id, String agreeTermsButton_id, String forgotPINButton_id, String send4digits_id, String confirmSend4Digits_id, String verifyPINChangeScreen_name) throws Exception
    {
        UiObject loginByFacebook = new UiSelector().resourceId(loginFacebookButton_id).makeUiObject();
        loginByFacebook.waitToAppear(30).tap();
        UiObject agreeTerms = new UiSelector().resourceId(agreeTermsButton_id).makeUiObject();
        agreeTerms.waitToAppear(30).tap();
        UiObject selectForgotPIN = new UiSelector().resourceId(forgotPINButton_id).makeUiObject();
        selectForgotPIN.waitToAppear(30).tap();
        UiObject send4Digits = new UiSelector().resourceId(send4digits_id).makeUiObject();
        send4Digits.waitToAppear(30).typeText("2486");
        UiObject confirmSend4Digits = new UiSelector().resourceId(confirmSend4Digits_id).makeUiObject();
        confirmSend4Digits.waitToAppear(30).typeText("2486");
        UiObject verifyPINChangeScreen = new UiSelector().text(verifyPINChangeScreen_name).makeUiObject();
        Assert.assertEquals(verifyPINChangeScreen.waitToAppear(30).getText(),"Your Betcade PIN has been changed successfully.");
    }

    /**
     * Can logout of the app after pin change google.
     *
     * @param hamburgerMenuButton_id the hamburger menu button id
     * @param myAccountButton_id     the my account button id
     * @param logoutButton_name      the logout button name
     * @param confirmLogoutButton_id the confirm logout button id
     * @throws Exception the exception
     */
    @Test(description = "logout of the app after pin change from google login", dependsOnMethods = "canUseForgotPINByFacebookLoginPostPinChange", dataProvider = "logoutOfTheGoogleLogin")
    public void canLogoutOfTheAppAfterPinChangeGoogle(String hamburgerMenuButton_id, String myAccountButton_id, String logoutButton_name, String confirmLogoutButton_id) throws Exception
    {
        UiObject hamburgerMenu = new UiSelector().resourceId(hamburgerMenuButton_id).makeUiObject();
        hamburgerMenu.waitToAppear(30).tap();
        UiObject myAccount = new UiSelector().text(myAccountButton_id).makeUiObject();
        myAccount.waitToAppear(30).tap();
        UiObject logoutButton = new UiSelector().text(logoutButton_name).makeUiObject();
        logoutButton.waitToAppear(30).tap();
        UiObject confirmLogout = new UiSelector().resourceId(confirmLogoutButton_id).makeUiObject();
        confirmLogout.waitToAppear(30).tap();
    }

    /**
     * Data provider for can login by google to update profile image object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "loginByGoogleToUpdateProfileImage")
    public Object[][] dataProviderForCanLoginByGoogleToUpdateProfileImage()
    {
        Object[][] data = new Object[1][4];

        data[0][0] = "com.betcade.android.market:id/lblGoogle";
        data[0][1] = "com.betcade.android.market:id/lblPositive";
        data[0][2] = "venkatgraj91@gmail.com";
        //data[0][3] = "com.betcade.android.market:id/btnSetupPin";
        data[0][3] = "com.betcade.android.market:id/viewPIN1";

        return data;
    }

    /**
     * Can login by google to update profile image.
     *
     * @param loginGoogleButton_id     the login google button id
     * @param agreeTermsButton_id      the agree terms button id
     * @param selectEmailIdButton_name the select email id button name
     * @param enterPinField_id         the enter pin field id
     * @throws Exception the exception
     */
    @Test(description = "Login by Google to update profile image", dependsOnMethods = "canLogoutOfTheAppAfterPinChangeGoogle", dataProvider = "loginByGoogleToUpdateProfileImage")
    public void canLoginByGoogleToUpdateProfileImage( String loginGoogleButton_id, String agreeTermsButton_id, String selectEmailIdButton_name, String enterPinField_id) throws Exception
    {
        UiObject loginByGoogle =  new UiSelector().resourceId(loginGoogleButton_id).makeUiObject();
        loginByGoogle.waitToAppear(30).tap();
        UiObject agreeTerms = new UiSelector().resourceId(agreeTermsButton_id).makeUiObject();
        agreeTerms.waitToAppear(30).tap();
        UiObject selectEmail = new UiSelector().text(selectEmailIdButton_name).makeUiObject();
        selectEmail.waitToAppear(30).tap();
        UiObject send4Digits = new UiSelector().resourceId(enterPinField_id).makeUiObject();
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
     * Data provider for can update profile image from google object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "updateProfileImageFromGoogle")
    public Object[][] dataProviderForCanUpdateProfileImageFromGoogle()
    {
        Object[][] data = new Object[1][5];

        data[0][0] = "com.betcade.android.market:id/imgToolbarMenu";
        data[0][1] = "com.betcade.android.market:id/lblName";
        data[0][2] = "com.betcade.android.market:id/imgUser";
        data[0][3] = "com.betcade.android.market:id/lblGooglePic";
        data[0][4] = "com.betcade.android.market:id/lblRemove";

        return data;
    }

    /**
     * Can update profile image from google.
     *
     * @param hamburgerMenuButton_id the hamburger menu button id
     * @param profileButton_id       the profile button id
     * @param profileMenuOptions_id  the profile menu options id
     * @param setPhotoFromGoogle_id  the set photo from google id
     * @param removeAppliedPhoto_id  the remove applied photo id
     * @throws Exception the exception
     */
    @Test(description = "can update profile image by login through google", dependsOnMethods = "canLoginByGoogleToUpdateProfileImage", dataProvider = "updateProfileImageFromGoogle")
    public void canUpdateProfileImageFromGoogle(String hamburgerMenuButton_id, String profileButton_id, String profileMenuOptions_id, String setPhotoFromGoogle_id, String removeAppliedPhoto_id) throws Exception
    {
        UiObject hamburgerMenu =  new UiSelector().resourceId(hamburgerMenuButton_id).makeUiObject();
        hamburgerMenu.waitToAppear(30).tap();
        UiObject navigateToProfile = new UiSelector().resourceId(profileButton_id).makeUiObject();
        navigateToProfile.waitToAppear(30).tap();
        UiObject getProfileImageDropdown = new UiSelector().resourceId(profileMenuOptions_id).makeUiObject();
        getProfileImageDropdown.waitToAppear(30).tap();
        UiObject setGooglePhoto = new UiSelector().resourceId(setPhotoFromGoogle_id).makeUiObject();
        UiObject removeGooglePhoto = new UiSelector().resourceId(removeAppliedPhoto_id).makeUiObject();
        setGooglePhoto.waitToAppear(30).tap();
        getProfileImageDropdown.waitToAppear(30).tap();
        removeGooglePhoto.waitToAppear(30).tap();
        getProfileImageDropdown.waitToAppear(30).tap();
        setGooglePhoto.waitToAppear(30).tap();
        UiObject navigateBack = new UiSelector().resourceId("com.betcade.android.market:id/imgToolbarMenu").makeUiObject();
        navigateBack.waitToAppear(30).tap();
        UiObject logoutButton = new UiSelector().text("Log Out").makeUiObject();
        logoutButton.waitToAppear(30).tap();
        UiObject confirmLogout = new UiSelector().resourceId("com.betcade.android.market:id/lblPositive").makeUiObject();
        confirmLogout.waitToAppear(30).tap();
    }

    /**
     * Data provider for can login by facebook to update profile image object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "loginByFacebookToUpdateProfileImage")
    public Object[][] dataProviderForCanLoginByFacebookToUpdateProfileImage()
    {
        Object[][] data = new Object[1][4];

        data[0][0] = "com.betcade.android.market:id/lblFb";
        data[0][1] = "com.betcade.android.market:id/lblPositive";
        data[0][2] = "com.betcade.android.market:id/viewPIN1";
        data[0][3] = "Setup Pin";

        return data;
    }

    /**
     * Can login by facebook to update profile image.
     *
     * @param loginFacebookButton_id the login facebook button id
     * @param agreeTermsButton_id    the agree terms button id
     * @param sendPINTextBox_id      the send pin text box id
     * @param newUserSetupPin_text   the new user setup pin text
     * @throws Exception the exception
     */
    @Test(description = "Login By Facebook To update profile image", dependsOnMethods = "canUpdateProfileImageFromGoogle", dataProvider = "loginByFacebookToUpdateProfileImage")
    public void canLoginByFacebookToUpdateProfileImage(String loginFacebookButton_id, String agreeTermsButton_id, String sendPINTextBox_id, String newUserSetupPin_text) throws Exception
    {
        UiObject loginByFacebook = new UiSelector().resourceId(loginFacebookButton_id).makeUiObject();
        loginByFacebook.waitToAppear(30).tap();
        UiObject agreeTerms = new UiSelector().resourceId(agreeTermsButton_id).makeUiObject();
        agreeTerms.waitToAppear(30).tap();
        UiObject send4Digits = new UiSelector().resourceId(sendPINTextBox_id).makeUiObject();
        UiObject newUserSetupPin = new UiSelector().text(newUserSetupPin_text).makeUiObject();
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

    @DataProvider(name = "")
    public Object[][] dataProviderForCanNavigateToProfileImageSettings()
    {
        Object[][] data = new Object[1][7];

        data[0][0] = "com.betcade.android.market:id/imgToolbarMenu";
        data[0][1] = "com.betcade.android.market:id/lblName";
        data[0][2] = "com.betcade.android.market:id/imgUser";
        data[0][3] = "com.betcade.android.market:id/lblFacebookPic";
        data[0][4] = "com.betcade.android.market:id/lblRemove";
        //data[0][5] = "com.betcade.android.market:id/imgToolbarMenu";
        data[0][5] = "Log Out";
        data[0][6] = "com.betcade.android.market:id/lblPositive";

        return data;
    }

    /**
     * Can navigate to profile image settings.
     *
     * @throws Exception the exception
     */
    @Test(description = "can navigate to profile to make changes to profile", dependsOnMethods = "canLoginByFacebookToUpdateProfileImage", dataProvider = "navigateToProfileImageSettings")
    public void canNavigateToProfileImageSettings(String hamburgerMenuButton_id, String navigateToProfile_id, String profileImageDropDownMenu_id,
    String setFBProfilePic_id, String removeProfilePic_id, String logoutButton_name, String confirmYesButton_id) throws Exception
    {
        UiObject hamburgerMenu =  new UiSelector().resourceId(hamburgerMenuButton_id).makeUiObject();
        hamburgerMenu.waitToAppear(30).tap();
        UiObject navigateToProfile = new UiSelector().resourceId(navigateToProfile_id).makeUiObject();
        navigateToProfile.waitToAppear(30).tap();
        UiObject getProfileImageDropdown = new UiSelector().resourceId(profileImageDropDownMenu_id).makeUiObject();
        getProfileImageDropdown.waitToAppear(30).tap();
        UiObject setFBPhoto = new UiSelector().resourceId(setFBProfilePic_id).makeUiObject();
        UiObject removeGooglePhoto = new UiSelector().resourceId(removeProfilePic_id).makeUiObject();
        setFBPhoto.waitToAppear(30).tap();
        getProfileImageDropdown.waitToAppear(30).tap();
        removeGooglePhoto.waitToAppear(30).tap();
        getProfileImageDropdown.waitToAppear(30).tap();
        setFBPhoto.waitToAppear(30).tap();
        hamburgerMenu.waitToAppear(30).tap();
        UiObject logoutButton = new UiSelector().text(logoutButton_name).makeUiObject();
        logoutButton.waitToAppear(30).tap();
        UiObject confirmLogout = new UiSelector().resourceId(confirmYesButton_id).makeUiObject();
        confirmLogout.waitToAppear(30).tap();
    }

    @Test(description = "Login By Facebook To update profile image", dependsOnMethods = "canNavigateToProfileImageSettings", dataProvider = "loginByFacebookToUpdateProfileImage")
    public void canLoginByFacebookToValidateNewPhotoButton(String loginFacebookButton_id, String agreeTermsButton_id, String sendPINTextBox_id, String newUserSetupPin_text) throws Exception
    {
        UiObject loginByFacebook = new UiSelector().resourceId(loginFacebookButton_id).makeUiObject();
        loginByFacebook.waitToAppear(30).tap();
        UiObject agreeTerms = new UiSelector().resourceId(agreeTermsButton_id).makeUiObject();
        agreeTerms.waitToAppear(30).tap();
        UiObject send4Digits = new UiSelector().resourceId(sendPINTextBox_id).makeUiObject();
        UiObject newUserSetupPin = new UiSelector().text(newUserSetupPin_text).makeUiObject();
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

    @DataProvider(name = "navigateToProfileImageNewPhoto")
    public Object[][] dataProviderForCanNavigateToProfileImageNewPhotoSettings()
    {
        Object[][] data = new Object[1][5];

        data[0][0] = "com.betcade.android.market:id/imgToolbarMenu";
        data[0][1] = "com.betcade.android.market:id/lblName";
        data[0][2] = "com.betcade.android.market:id/lblToolbarTitle";
        data[0][3] = "com.betcade.android.market:id/imgUser";
        data[0][4] = "com.betcade.android.market:id/lblCamera";

        return data;
    }

    @Test(description = "can navigate to Account information to check if take new photo button is working.", dependsOnMethods = "canNavigateToProfileImageSettings", dataProvider = "navigateToProfileImageNewPhoto")
    public void canNavigateToProfileImageNewPhotoSettings(String hamburgerMenu_id, String navigateToProfile_id, String validateAccountInformationAct_id,
    String profileImageOptions_id, String takeNewPhotoButton_id) throws Exception
    {
        UiObject hamburgerMenu = new UiSelector().resourceId(hamburgerMenu_id).makeUiObject();
        hamburgerMenu.waitToAppear(30).tap();
        UiObject navigateToProfile = new UiSelector().resourceId(navigateToProfile_id).makeUiObject();
        navigateToProfile.waitToAppear(30).tap();
        UiObject checkAccountInformationAct = new UiSelector().resourceId(validateAccountInformationAct_id).makeUiObject();
        Assert.assertEquals(checkAccountInformationAct.waitToAppear(30).getText(), "Account Information");
        UiObject getProfileImageDropdown = new UiSelector().resourceId(profileImageOptions_id).makeUiObject();
        getProfileImageDropdown.waitToAppear(30).tap();
        UiObject takeNewPhotoButton = new UiSelector().resourceId(takeNewPhotoButton_id).makeUiObject();
        takeNewPhotoButton.waitToAppear(30).tap();
        UiObject navigateBack = new UiSelector().resourceId("com.betcade.android.market:id/imgToolbarMenu").makeUiObject();
        navigateBack.waitToAppear(30).tap();
        UiObject logoutButton = new UiSelector().text("Log Out").makeUiObject();
        logoutButton.waitToAppear(30).tap();
        UiObject confirmLogout = new UiSelector().resourceId("com.betcade.android.market:id/lblPositive").makeUiObject();
        confirmLogout.waitToAppear(30).tap();
    }

    /*@AfterClass
    public void canLogoutOfGoogleLogin() throws Exception{
        Object [][] objects = abstractTest.dataProviderForCanLogoutOfTheApp();
        abstractTest.canLogoutOfTheApp(objects[0][0].toString(), objects[0][1].toString(), objects[0][2].toString(), objects[0][3].toString());
        driver = abstractTest.driver;
    }*/
}
