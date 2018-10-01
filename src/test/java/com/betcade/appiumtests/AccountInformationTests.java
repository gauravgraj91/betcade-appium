package com.betcade.appiumtests;

import static com.betcade.appiumtests.api.android.Android.adb;
import com.betcade.appiumtests.core.managers.DriverManager;
import io.appium.java_client.android.AndroidElement;
import com.betcade.appiumtests.api.android.Android;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.android.AndroidDriver;
import org.springframework.stereotype.Component;
import com.betcade.appiumtests.core.UiSelector;
import com.betcade.appiumtests.core.UiObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.Assert;


/**
 * Created by Gaurav on 5/5/2017.
 */
@Component
public class AccountInformationTests {

    public AndroidDriver driver;
    AbstractTest abstractTest = new AbstractTest();

    /**
     * Can login through google.
     *
     * @throws Exception the exception
     */
    @BeforeClass()
    public void canLoginThroughGoogle() throws Exception {
        DriverManager.createDriver();
        driver = Android.driver;
        Object[][] objects = abstractTest.dataProviderForCanLoginThroughGoogle();
        abstractTest.canLoginThroughGoogle(objects[0][0].toString(), objects[0][1].toString(), objects[0][2].toString(), objects[0][3].toString(), objects[0][4].toString());
    }

    @DataProvider(name = "updateProfileNameInAccountInformation")
    public Object[][] dataProviderForCanUpdateProfileOnAccountInformation() {
        Object[][] data = new Object[1][4];

        data[0][0] = "com.betcade.android.market:id/imgToolbarMenu";
        data[0][1] = "My Account";
        data[0][2] = "Account Information";
        data[0][3] = "com.betcade.android.market:id/viewKycCheck";

        return data;
    }

    @Test(description = "can update profile name", dataProvider = "updateProfileNameInAccountInformation")
    public void canUpdateProfileOnAccountInformation(String hamburgerMenuButton_id, String myAccountButton_name, String accountInformationButton_name, String updateProfileButton_id) throws Exception
    {
        UiObject hamburgerMenuButton = new UiSelector().resourceId(hamburgerMenuButton_id).makeUiObject();
        hamburgerMenuButton.waitToAppear(30).tap();
        UiObject myAccountButton = new UiSelector().text(myAccountButton_name).makeUiObject();
        myAccountButton.waitToAppear(30).tap();
        UiObject accountInformationButton = new UiSelector().text(accountInformationButton_name).makeUiObject();
        accountInformationButton.waitToAppear(30).tap();
        UiObject updateProfileButton = new UiSelector().resourceId(updateProfileButton_id).makeUiObject();
        updateProfileButton.waitToAppear(30).tap();
        UiObject updateFirstName = new UiSelector().resourceId("com.betcade.android.market:id/txtFirstName").makeUiObject();
        updateFirstName.waitToAppear(30).tap();
        updateFirstName.waitToAppear(30).clearText();
        updateFirstName.typeText("Slith");
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
        }
        AndroidElement scrollToUpdate = (AndroidElement)(driver.findElement(By.id("com.betcade.android.market:id/ipHouseStreet")));
        scrollToUpdate.swipe(SwipeElementDirection.UP, 3000);
        AndroidElement scrollToUpdate2 = (AndroidElement)(driver.findElement(By.id("com.betcade.android.market:id/ipCountry")));
        scrollToUpdate2.swipe(SwipeElementDirection.UP, 3000);
        UiObject updateButton = new UiSelector().text("Update").makeUiObject();
        updateButton.waitToAppear(30).tap();
    }

    /**
     * Data provider for change pin feature object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "changePinFeature")
    public Object[][] dataProviderForChangePINFeature() {
        Object[][] data = new Object[1][5];

        data[0][0] = "com.betcade.android.market:id/viewChangePin";
        data[0][1] = "com.betcade.android.market:id/rlPIN1";
        data[0][2] = "com.betcade.android.market:id/rlConfirmPIN11";
        data[0][3] = "com.betcade.android.market:id/rlConfirmPIN12";
        data[0][4] = "Your Betcade PIN has been changed successfully.";

        return data;
    }

    /**
     * Can change pin feature.
     *
     * @param changePINButton_name       the change pin button name
     * @param enterExistingPIN_id        the enter existing pin id
     * @param enterNewPINFirstTime_id    the enter new pin first time id
     * @param enterNewPinSecondTime_id   the enter new pin second time id
     * @param verifyPINChangeScreen_name the verify pin change screen name
     * @throws Exception the exception
     */
    @Test(description = "Can Change PIN feature Test", dependsOnMethods = "canUpdateProfileOnAccountInformation", dataProvider = "changePinFeature")
    public void canChangePINFeature(String changePINButton_name, String enterExistingPIN_id, String enterNewPINFirstTime_id, String enterNewPinSecondTime_id, String verifyPINChangeScreen_name) throws Exception {
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
        }
        UiObject selectChangePIN = new UiSelector().resourceId(changePINButton_name).makeUiObject();
        selectChangePIN.waitToAppear(30).waitToAppear(30).tap();
        UiObject enterExisitngPin = new UiSelector().resourceId(enterExistingPIN_id).makeUiObject();
        enterExisitngPin.waitToAppear(30).typeText("2486");
        UiObject enterNewPIN = new UiSelector().resourceId(enterNewPINFirstTime_id).makeUiObject();
        enterNewPIN.waitToAppear(30).typeText("2486");
        UiObject enterNewPINSecondTime = new UiSelector().resourceId(enterNewPinSecondTime_id).makeUiObject();
        enterNewPINSecondTime.waitToAppear(30).typeText("2486");
        UiObject verifyPINChangeScreen = new UiSelector().text(verifyPINChangeScreen_name).makeUiObject();
        Assert.assertEquals(verifyPINChangeScreen.waitToAppear(30).getText(), "Your Betcade PIN has been changed successfully.");
        //driver.navigate().back();
    }

    /**
     * Data provider for can delete betcade account object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "deleteBetcadeAccount")
    public Object[][] dataProviderForCanDeleteBetcadeAccount(){

        Object[][] data = new Object[1][4];
        data[0][0] = "Account Information";
        data[0][1] = "com.betcade.android.market:id/viewDelete";
        data[0][2] = "com.betcade.android.market:id/btnDelete";
        data[0][3] = "com.betcade.android.market:id/lblPositive";
        //data[0][4] = "com.betcade.android.market:id/imgToolbarMenu";
        //data[0][5] = "Store Home";

    return data;
    }

    /**
     * Can delete betcade account.
     *
     * @param selectAccountInfoButton_id   the select account info button id
     * @param selectDeleteAccountButton_id the select delete account button id
     * @param infoOnDeleteAccountButton_id the info on delete account button id
     * @param finalConfirmDeleteButton_id  the final confirm delete button id
     * @throws Exception the exception
     */
    @Test(description = "delete betcade account", dependsOnMethods = "canChangePINFeature", dataProvider = "deleteBetcadeAccount")
    public void canDeleteBetcadeAccount(String selectAccountInfoButton_id, String selectDeleteAccountButton_id, String infoOnDeleteAccountButton_id, String finalConfirmDeleteButton_id) throws Exception
    {
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
        }
        UiObject selectAccountInfoButton = new UiSelector().text(selectAccountInfoButton_id).makeUiObject();
        selectAccountInfoButton.waitToAppear(30).tap();
        UiObject selectDeleteBetcadeAccount = new UiSelector().resourceId(selectDeleteAccountButton_id).makeUiObject();
        selectDeleteBetcadeAccount.waitToAppear(30).tap();
        UiObject confirmDeleteButton = new UiSelector().resourceId(infoOnDeleteAccountButton_id).makeUiObject();
        confirmDeleteButton.waitToAppear(30).tap();
        UiObject dialogBoxConfirmDelete = new UiSelector().resourceId(finalConfirmDeleteButton_id).makeUiObject();
        dialogBoxConfirmDelete.waitToAppear(30).tap();
    }

    /**
     * Data provider for can navigate to login methods from facebook object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "loginByFacebookForAddingLoginMethods")
    public Object[][] dataProviderForCanNavigateToLoginMethodsFromFacebook()
    {
        Object[][] data = new Object[1][7];

        data[0][0] = "com.betcade.android.market:id/lblFb";
        data[0][1] = "com.betcade.android.market:id/lblPositive";
        data[0][2] = "com.betcade.android.market:id/viewPIN1";
        data[0][3] = "com.betcade.android.market:id/imgToolbarMenu";
        data[0][4] = "My Account";
        data[0][5] = "Account Information";
        data[0][6] = "com.betcade.android.market:id/viewLogin";

        return data;
    }

    /**
     * Can navigate to login methods from facebook.
     *
     * @param selectFacebookLoginButton_id  the select facebook login button id
     * @param agreeTermsButton_id           the agree terms button id
     * @param send4Digits_id                the send 4 digits id
     * @param selectHamburgerMenuButton_id  the select hamburger menu button id
     * @param myAccountButton_name          the my account button name
     * @param accountInformationButton_name the account information button name
     * @param loginMethodsButton_id         the login methods button id
     * @throws Exception the exception
     */
    @Test(description = "use facebook login method and add google email", dependsOnMethods = "canDeleteBetcadeAccount", dataProvider = "loginByFacebookForAddingLoginMethods")
    public void canNavigateToLoginMethodsFromFacebook(String selectFacebookLoginButton_id, String agreeTermsButton_id, String send4Digits_id, String selectHamburgerMenuButton_id, String myAccountButton_name, String accountInformationButton_name, String loginMethodsButton_id) throws Exception
    {
        UiObject selectFacebookLogin = new UiSelector().resourceId(selectFacebookLoginButton_id).makeUiObject();
        selectFacebookLogin.waitToAppear(30).tap();
        UiObject agreeTerms = new UiSelector().resourceId(agreeTermsButton_id).makeUiObject();
        agreeTerms.waitToAppear(30).tap();
        UiObject send4Digits = new UiSelector().resourceId(send4Digits_id).makeUiObject();
        send4Digits.waitToAppear(30).typeText("2486");
        UiObject selectHamburgerMenu = new UiSelector().resourceId(selectHamburgerMenuButton_id).makeUiObject();
        selectHamburgerMenu.waitToAppear(30).tap();
        UiObject myAccountButton = new UiSelector().text(myAccountButton_name).makeUiObject();
        myAccountButton.waitToAppear(30).tap();
        UiObject accountInformationButton = new UiSelector().text(accountInformationButton_name).makeUiObject();
        accountInformationButton.waitToAppear(30).tap();
        UiObject loginMethodsButton = new UiSelector().resourceId(loginMethodsButton_id).makeUiObject();
        loginMethodsButton.waitToAppear(30).tap();
    }

    /**
     * Data provider for can add google account using login methods object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "addLoginMethodsByGoogle")
    public Object[][] dataProviderForCanAddGoogleAccountUsingLoginMethods()
    {
        Object[][] data = new Object[1][6];

        data[0][0] = "GOOGLE";
        data[0][1] = "com.betcade.android.market:id/rlPIN1";
        data[0][2] = "gaurav.raj@innoflexion.com";
        data[0][3] = "com.betcade.android.market:id/imgAddRemove";
        data[0][4] = "com.betcade.android.market:id/lblPositive";
        data[0][5] = "com.betcade.android.market:id/lblToolbarTitle";

        return data;
    }

    /**
     * Can add google account using login methods.
     *
     * @param selectLoginByGoogleMethod_name the select login by google method name
     * @param enter4Digits_id                the enter 4 digits id
     * @param selectGoogleMail_name          the select google mail name
     * @param removeAddedAccount_id          the remove added account id
     * @param removeAccountDialogBoxYes_id   the remove account dialog box yes id
     * @param goToHamburgerMenu_id           the go to hamburger menu id
     * @throws Exception the exception
     */
    @Test(description = "can add login methods", dependsOnMethods = "canNavigateToLoginMethodsFromFacebook", dataProvider = "addLoginMethodsByGoogle")
    public void canAddGoogleAccountUsingLoginMethods(String selectLoginByGoogleMethod_name, String enter4Digits_id, String selectGoogleMail_name, String removeAddedAccount_id, String removeAccountDialogBoxYes_id, String goToHamburgerMenu_id) throws Exception
    {
        UiObject selectLoginByGoogleMethod = new UiSelector().text(selectLoginByGoogleMethod_name).makeUiObject();
        selectLoginByGoogleMethod.waitToAppear(30).tap();
        UiObject enter4Digits = new UiSelector().resourceId(enter4Digits_id).makeUiObject();
        enter4Digits.waitToAppear(30).typeText("2486");
        UiObject selectGoogleMail = new UiSelector().text(selectGoogleMail_name).makeUiObject();
        selectGoogleMail.waitToAppear(30).tap();
        UiObject removeAddedAccount = new UiSelector().resourceId(removeAddedAccount_id).makeUiObject();
        UiObject removeAddedAccount2 = new UiSelector().clickable(true).makeUiObject();
        if (removeAddedAccount.waitToAppear(30).isClickable() && removeAddedAccount2.waitToAppear(30).isClickable()){
            removeAddedAccount.waitToAppear(30).tap();
            UiObject removeAccountDialogBox = new UiSelector().resourceId(removeAccountDialogBoxYes_id).makeUiObject();
            removeAccountDialogBox.waitToAppear(30).tap();
        }
        UiObject goToHamburgerMenu = new UiSelector().resourceId(goToHamburgerMenu_id).makeUiObject();
        goToHamburgerMenu.waitToAppear(30).tap();
        goToHamburgerMenu.waitToAppear(30).tap();
    }

    /**
     * Data provider for can navigate to profile name activity object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "navigateToProfileName")
    public Object[][] dataProviderForCanNavigateToProfileNameActivity()
    {
        Object[][] data = new Object[1][3];

        data[0][0] = "com.betcade.android.market:id/imgToolbarMenu";
        data[0][1] = "com.betcade.android.market:id/imgUser";
        data[0][2] = "com.betcade.android.market:id/lblUserName";

        return data;
    }

    /**
     * Can navigate to profile name.
     *
     * @param hamburgerMenu_id             the hamburger menu id
     * @param goToAccountInformationTab_id the go to account information tab id
     * @param profileNamelabel_id          the profile namelabel id
     * @throws Exception the exception
     */
    @Test(description = "Navigate To Profile Name Update Screen", dependsOnMethods = "canAddGoogleAccountUsingLoginMethods", dataProvider = "navigateToProfileName")
    public void canNavigateToProfileName(String hamburgerMenu_id, String goToAccountInformationTab_id, String profileNamelabel_id ) throws Exception
    {
        UiObject hamburgerMenu = new UiSelector().resourceId(hamburgerMenu_id).makeUiObject();
        hamburgerMenu.waitToAppear(30).tap();
        UiObject goToProfileNames = new UiSelector().resourceId(goToAccountInformationTab_id).makeUiObject();
        goToProfileNames.waitToAppear(30).tap();
        UiObject tapOnProfileName = new UiSelector().resourceId(profileNamelabel_id).makeUiObject();
        tapOnProfileName.waitToAppear(30).tap();
    }

    /**
     * Data provider for can update profile name in the app object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "updateProfileNameInTheApp")
    public Object[][] dataProviderForCanUpdateProfileNameInTheApp()
    {
        Object[][] data = new Object[1][5];

        data[0][0] = "com.betcade.android.market:id/imgToolbarMenu";
        data[0][1] = "com.betcade.android.market:id/txtFirstName";
        data[0][2] = "com.betcade.android.market:id/txtLastName";
        data[0][3] = "com.betcade.android.market:id/imgSaveName";
        data[0][4] = "Store Home";

        return data;
    }

    /**
     * Can update profile name in the app.
     *
     * @param hamburgerMenu_id     the hamburger menu id
     * @param firstNameField_id    the first name field id
     * @param lastNameField_id     the last name field id
     * @param saveChangesButton_id the save changes button id
     * @param storeHomeButton_name the store home button name
     * @throws Exception the exception
     */
    @Test(description = "can update profile name", dependsOnMethods = "canAddGoogleAccountUsingLoginMethods", dataProvider = "updateProfileNameInTheApp")
    public void canUpdateProfileNameInTheApp(String hamburgerMenu_id, String firstNameField_id, String lastNameField_id, String saveChangesButton_id, String storeHomeButton_name) throws Exception
    {
        UiObject hamburgerMenu = new UiSelector().resourceId(hamburgerMenu_id).makeUiObject();
        hamburgerMenu.waitToAppear(30).tap();
        UiObject firstNameField = new UiSelector().resourceId(firstNameField_id).makeUiObject();
        firstNameField.waitToAppear(30).clearText();
        firstNameField.typeText("Slith");
        UiObject lastNameField = new UiSelector().resourceId(lastNameField_id).makeUiObject();
        lastNameField.waitToAppear(30).tap();
        lastNameField.waitToAppear(30).clearText();
        lastNameField.typeText("ice");
        UiObject saveTheChanges = new UiSelector().resourceId(saveChangesButton_id).makeUiObject();
        saveTheChanges.waitToAppear(30).tap();
        adb.navigateBack();
        hamburgerMenu.waitToAppear(30).tap();
        UiObject storeHome = new UiSelector().text(storeHomeButton_name).makeUiObject();
        storeHome.waitToAppear(30).tap();
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