package com.betcade.appiumtests;

import com.betcade.appiumtests.api.android.Android;
import com.betcade.appiumtests.core.UiObject;
import com.betcade.appiumtests.core.UiSelector;
import com.betcade.appiumtests.core.managers.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.betcade.appiumtests.api.android.Android.adb;

/**
 * Created by Gaurav on 4/26/2017.
 */
@Component
public class MyAppsTests {

    public AndroidDriver driver;
    AbstractTest abstractTest = new AbstractTest();

    /**
     * Can login through google.
     *
     * @throws Exception the exception
     */
    @BeforeClass()
    public void canLoginThroughGoogle() throws Exception{
        DriverManager.createDriver();
        driver = Android.driver;
        Object [][] objects = abstractTest.dataProviderForCanLoginThroughGoogle();
        abstractTest.canLoginThroughGoogle(objects[0][0].toString(), objects[0][1].toString(), objects[0][2].toString(), objects[0][3].toString(), objects[0][4].toString());
    }

    /**
     * Data provider for can select and download an app object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "selectAndDownloadAnApp")
    public Object[][] dataProviderForCanSelectAndDownloadAnApp()
    {
        Object[][] data = new Object[1][4];

        data[0][0] = "William Hill Sportsbook";
        data[0][1] = "com.betcade.android.market:id/lblFabState";
        data[0][2] = "com.android.packageinstaller:id/ok_button";
        data[0][3] = "com.android.packageinstaller:id/done_button";

        return data;
    }

    /**
     * Can select and download an app.
     *
     * @param selectWilliamHillSportsBook_name   the select william hill sports book name
     * @param downloadWilliamHillSportsButton_id       the download william hill sports id
     * @param installWilliamHillSportsBookButton_id the install button william hill sports book
     * @param doneAfterInstalling_id             the done after installing id
     * @throws Exception the exception
     */
    @Test(description = "Can Search an app and download it", dataProvider = "selectAndDownloadAnApp")
    public void canSelectAndDownloadAnApp(String selectWilliamHillSportsBook_name, String downloadWilliamHillSportsButton_id, String installWilliamHillSportsBookButton_id, String doneAfterInstalling_id ) throws Exception{
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
        }
        UiObject selectWilliamHillSports = new UiSelector().text(selectWilliamHillSportsBook_name).makeUiObject();
        selectWilliamHillSports.waitToAppear(30).tap();
        UiObject downloadWilliamHillSports = new UiSelector().resourceId(downloadWilliamHillSportsButton_id).makeUiObject();
        downloadWilliamHillSports.waitToAppear(30).tap();
        Thread.sleep(5000);
        UiObject installWilliamHillSportsBook =  new UiSelector().resourceId(installWilliamHillSportsBookButton_id).makeUiObject();
        installWilliamHillSportsBook.waitToAppear(30).tap();
        UiObject doneInstallWHillSportsBook = new UiSelector().resourceId(doneAfterInstalling_id).makeUiObject();
        doneInstallWHillSportsBook.waitToAppear(30).tap();
    }

    /**
     * Data provider for can check download status object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "checkDownloadStatus")
    public Object[][] dataProviderForCanCheckDownloadStatus()
    {
        Object[][] data = new Object[1][4];

        data[0][0] = "com.betcade.android.market:id/imgToolMenu";
        data[0][1] = "My Apps";
        data[0][2] = "ALL APPS";
        data[0][3] = "William Hill Sportsbook";

        return data;
    }

    /**
     * Can check download status.
     *
     * @param hamburgerMenuButton_Id     the hamburger menu button id
     * @param myAppsSubMenu_name         the my apps sub menu name
     * @param allAppsTab_name            the all apps tab name
     * @param checkWilliamHillSportsBook_name the check william hill sports book
     * @throws Exception the exception
     */
    @Test(description = "Navigate to My Apps", dependsOnMethods = {"canSelectAndDownloadAnApp"}, dataProvider = "checkDownloadStatus")
    public void canCheckDownloadStatus(String hamburgerMenuButton_Id, String myAppsSubMenu_name, String allAppsTab_name, String checkWilliamHillSportsBook_name) throws Exception
    {
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
        }
        UiObject hamburgerMenu =  new UiSelector().resourceId(hamburgerMenuButton_Id).makeUiObject();
        hamburgerMenu.waitToAppear(30).tap();
        UiObject selectMyApps = new UiSelector().text(myAppsSubMenu_name).makeUiObject();
        selectMyApps.waitToAppear(30).tap();
        UiObject checkAllApps = new UiSelector().text(allAppsTab_name).makeUiObject();
        checkAllApps.waitToAppear(30).tap();
        UiObject checkWilliamHillSports = new UiSelector().text(checkWilliamHillSportsBook_name).makeUiObject();
        Assert.assertEquals(checkWilliamHillSports.getText(), "William Hill Sportsbook");
        adb.uninstallApp("com.mobenga.williamhill");
    }

    /**
     * Data provider for can add apps to wish list object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "addAnAppToWishList")
    public Object[][] dataProviderForCanAddAppsToWishList()
    {
        Object[][] data = new Object[1][5];
        data[0][0] = "com.betcade.android.market:id/imgToolbarMenu";
        data[0][1] = "Store Home";
        data[0][2] = "Betfred Sportsbook";
        data[0][3] = "com.betcade.android.market:id/imgOption";
        data[0][4] = "com.betcade.android.market:id/lblSaveLater";

        return data;
    }

    /**
     * Can add apps to wish list.
     *
     * @param hamburgerMenuButton_Id    the hamburger menu button id
     * @param selectStoreHome_name      the select store home name
     * @param addBetFredSportsBook_name the add bet fred sports book name
     * @param selectThreeDots_id        the select three dots id
     * @param selectAddToWishList_id    the select add to wish list id
     * @throws Exception the exception
     */
    @Test(description = "Add apps to Wishlist method 1", dependsOnMethods = {"canCheckDownloadStatus"}, dataProvider = "addAnAppToWishList")
    public void canAddAppsToWishList(String hamburgerMenuButton_Id,String selectStoreHome_name, String addBetFredSportsBook_name, String selectThreeDots_id, String selectAddToWishList_id) throws Exception
    {
        UiObject hamburgerMenu = new UiSelector().resourceId(hamburgerMenuButton_Id).makeUiObject();
        hamburgerMenu.waitToAppear(30).tap();
        UiObject selectStoreHome = new UiSelector().text(selectStoreHome_name).makeUiObject();
        selectStoreHome.waitToAppear(30).tap();
        UiObject selectBetFredSportsBook = new UiSelector().text(addBetFredSportsBook_name).makeUiObject();
        selectBetFredSportsBook.waitToAppear(30).tap();
        UiObject selectThreeDots = new UiSelector().resourceId(selectThreeDots_id).makeUiObject();
        selectThreeDots.waitToAppear(30).tap();
        UiObject selectAddToWishListFromThreeDots = new UiSelector().resourceId(selectAddToWishList_id).makeUiObject();
        selectAddToWishListFromThreeDots.waitToAppear(30).tap();
    }

    /**
     * Data provider for can check wish list status object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "checkWishListStatus")
    public Object[][] dataProviderForCanCheckWishListStatus()
    {
        Object[][] data = new Object[1][4];

        data[0][0] = "com.betcade.android.market:id/imgToolMenu";
        data[0][1] = "My Apps";
        data[0][2] = "WISH LIST";
        data[0][3] = "Betfred Sportsbook";

        return data;
    }

    /**
     * Can check wish list status.
     *
     * @param hamburgerMenuButton_Id the hamburger menu button id
     * @param myAppsSubMenu          the my apps sub menu
     * @param wishListTab            the wish list tab
     * @param betFredSportsBook      the bet fred sports book
     * @throws Exception the exception
     */
    @Test(description = "Navigate to My Apps to check wishlist status", dependsOnMethods = {"canAddAppsToWishList"}, dataProvider = "checkWishListStatus")
    public void canCheckWishListStatus(String hamburgerMenuButton_Id, String myAppsSubMenu, String wishListTab, String betFredSportsBook) throws Exception
    {
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
        }
        UiObject hamburgerMenu = new UiSelector().resourceId(hamburgerMenuButton_Id).makeUiObject();
        hamburgerMenu.waitToAppear(30).tap();
        UiObject selectMyApps = new UiSelector().text(myAppsSubMenu).makeUiObject();
        selectMyApps.waitToAppear(30).tap();
        UiObject selectWishList = new UiSelector().text(wishListTab).makeUiObject();
        selectWishList.waitToAppear(30).tap();
        UiObject checkWilliamHillSports = new UiSelector().text(betFredSportsBook).makeUiObject();
        Assert.assertEquals(checkWilliamHillSports.waitToAppear(30).getText(), "Betfred Sportsbook");
    }

    /**
     * Data provider for can check download all and cancel all object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "canCancelAllAndDownloadAll")
    public Object[][] dataProviderForCanCheckDownloadAllAndCancelAll()
    {
        Object[][] data = new Object[1][4];

        data[0][0] = "com.betcade.android.market:id/lblTitle";
        data[0][1] = "com.betcade.android.market:id/rlUpdateAll";
        data[0][2] = "com.betcade.android.market:id/btnFooter";
        data[0][3] = "com.betcade.android.market:id/lblFabState";

        return data;
    }

    /**
     * Can check download all and cancel all.
     *
     * @param wishListTabTitle_id  the wish list tab title id
     * @param downloadAllButton_id the download all button id
     * @param cancelAllButton_id   the cancel all button id
     * @param cancelButtonApp_id   the cancel button app id
     * @throws Exception the exception
     */
    @Test(description = "Check download all and cancel all", dependsOnMethods = "canCheckWishListStatus", dataProvider = "canCancelAllAndDownloadAll")
    public void canCheckDownloadAllAndCancelAll(String wishListTabTitle_id, String downloadAllButton_id, String cancelAllButton_id, String cancelButtonApp_id) throws Exception
    {
        UiObject checkWishListTab = new UiSelector().resourceId(wishListTabTitle_id).makeUiObject();
        Assert.assertEquals(checkWishListTab.waitToAppear(30).getText(), "Wish List");
        UiObject tapOnDownloadAll = new UiSelector().resourceId(downloadAllButton_id).makeUiObject();
        if (tapOnDownloadAll.waitToAppear(30).isClickable()){
            tapOnDownloadAll.waitToAppear(30).tap();
            UiObject tapOnCancelAll = new UiSelector().resourceId(cancelAllButton_id).makeUiObject();
            tapOnCancelAll.waitToAppear(30).tap();
            tapOnDownloadAll.waitToAppear(30).tap();
            UiObject tapOnCancelFromApp = new UiSelector().resourceId(cancelButtonApp_id).makeUiObject();
            tapOnCancelFromApp.waitToAppear(30).tap();
            Assert.assertEquals(tapOnCancelAll.waitToAppear(30).getText(), "Download All");
        }
    }

    @AfterClass
    public void canLogoutOfGoogleLogin() throws Exception{
        Object [] [] objects = abstractTest.dataProviderForCanLogoutOfTheApp();
        abstractTest.canLogoutOfTheApp(objects[0][0].toString(), objects[0][1].toString(), objects[0][2].toString(), objects[0][3].toString());
        driver = abstractTest.driver;
    }
}
