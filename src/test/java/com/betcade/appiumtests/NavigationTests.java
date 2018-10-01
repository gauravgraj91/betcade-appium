package com.betcade.appiumtests;

/**
 * Created by Gaurav on 3/31/2017.
 */

import com.betcade.appiumtests.api.android.Android;
import com.betcade.appiumtests.core.UiObject;
import com.betcade.appiumtests.core.UiSelector;
import com.betcade.appiumtests.core.managers.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Component
public class NavigationTests {

    public AndroidDriver driver;
    AbstractTest abstractTest = new AbstractTest();

    /**
     * Can login through google.
     *
     * @throws Exception the exception
     */
    @BeforeTest()
    public void canLoginThroughGoogle() throws Exception{
        DriverManager.createDriver();
        driver = Android.driver;
        Object [][] objects = abstractTest.dataProviderForCanLoginThroughGoogle();
        abstractTest.canLoginThroughGoogle(objects[0][0].toString(), objects[0][1].toString(), objects[0][2].toString(), objects[0][3].toString(), objects[0][4].toString());
    }

    /**
     * Data provider for object [ ] [ ]
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "navigateWithinBetcadeApp")
    public Object[][] dataProviderForNavigateInBetcadeApp()
    {
        Object[][] data = new Object[1][6];

        data[0][0] = "com.betcade.android.market:id/imgToolbarMenu";
        data[0][1] = "com.betcade.android.market:id/txtToolbar";
        data[0][2] = "Sportsbetting";
        data[0][3] = "Poker";
        data[0][4] = "Casino";
        data[0][5] = "Bingo";

        return data;
    }

    /**
     * Navigate store home.
     *
     * @param hamburgerMenuButton_Id the hamburger menu button id
     * @param featuredLabel_id       the featured label id
     * @param sportsBettingMenu_name the sports betting menu name
     * @param pokerMenu_name         the poker menu name
     * @param casinoMenu_name        the casino menu name
     * @param bingoMenu_name         the bingo menu name
     * @throws Exception the exception
     */
    @Test(description = "Navigate with app", dataProvider = "navigateWithinBetcadeApp")
    public void navigateInBetcadeApp(String hamburgerMenuButton_Id, String featuredLabel_id, String sportsBettingMenu_name, String pokerMenu_name, String casinoMenu_name, String bingoMenu_name) throws Exception
    {
        UiObject hamburgerMenu = new UiSelector().resourceId(hamburgerMenuButton_Id).makeUiObject();
        hamburgerMenu.waitToAppear(30).tap();
        UiObject featuredSection = new UiSelector().resourceId(featuredLabel_id).makeUiObject();
        UiObject sportsBettingMenu = new UiSelector().text(sportsBettingMenu_name).makeUiObject();
        sportsBettingMenu.waitToAppear(30).tap();
        Assert.assertEquals(featuredSection.getText(), "Sportsbetting");
        hamburgerMenu.waitToAppear(30).tap();
        UiObject pokerMenu = new UiSelector().text(pokerMenu_name).makeUiObject();
        pokerMenu.waitToAppear(30).tap();
        Assert.assertEquals(featuredSection.getText(), "Poker");
        hamburgerMenu.waitToAppear(30).tap();
        UiObject casinoMenu = new UiSelector().text(casinoMenu_name).makeUiObject();
        casinoMenu.waitToAppear(30).tap();
        Assert.assertEquals(featuredSection.getText(), "Casino");
        hamburgerMenu.waitToAppear(30).tap();
        UiObject bingoMenu = new UiSelector().text(bingoMenu_name).makeUiObject();
        bingoMenu.waitToAppear(30).tap();
        Assert.assertEquals(featuredSection.getText(), "Bingo");
    }

    /**
     * Data provider for object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "checkAppsInSeeMoreForSamePublisher")
    public Object[][] dataProviderFor()
    {
        Object[][] data = new Object[1][4];

        data[0][0] = "William Hill Sportsbook";
        data[0][1] = "com.betcade.android.market:id/btnSeeMore";
        data[0][2] = "William Hill Casino Club";
        data[0][3] = "William Hill Bingo";

        return data;
    }

    /**
     * Can check see more button.
     *
     * @param selectWilliamHillSportsBook_name the select william hill sports book name
     * @param checkSeeMoreSection_id           the check see more section id
     * @param checkWilliamCasinoClub_name      the check william casino club name
     * @param checkWilliamHillBingo_name       the check william hill bingo name
     * @throws Exception the exception
     */
    @Test(description = "Can Check see more button for apps from same publisher", dependsOnMethods = "navigateInBetcadeApp", dataProvider = "checkAppsInSeeMoreForSamePublisher")
    public void canCheckSeeMoreButton(String selectWilliamHillSportsBook_name, String checkSeeMoreSection_id, String checkWilliamCasinoClub_name, String checkWilliamHillBingo_name) throws Exception
    {
        UiObject selectWilliamHillSports = new UiSelector().text(selectWilliamHillSportsBook_name).makeUiObject();
        selectWilliamHillSports.waitToAppear(5).tap();
        UiObject navigateToSeeMoreSection = new UiSelector().resourceId(checkSeeMoreSection_id).makeUiObject();
        navigateToSeeMoreSection.waitToAppear(30).tap();
        UiObject checkWilliamCasinoClub = new UiSelector().text(checkWilliamCasinoClub_name).makeUiObject();
        Assert.assertEquals(checkWilliamCasinoClub.getText(), "William Hill Casino Club");
        UiObject checkWilliamHillBingo = new UiSelector().text(checkWilliamHillBingo_name).makeUiObject();
        Assert.assertEquals(checkWilliamHillBingo.getText(), "William Hill Bingo");
        driver.navigate().back();
        driver.navigate().back();
    }

    //There is some issue with UiObjects need to look for a different method
    /*@Test(description = "Search For An app in search field.")
    public void canSearchForAAppInSearchField() throws Exception {
        UiObject searchField = new UiSelector().resourceId("com.betcade.android.market:id/txtToolbar").makeUiObject();
        searchField.tap();
        UiObject enterTextInSearchField = new UiSelector().resourceId("com.betcade.android.market:id/txtToolbar").makeUiObject();
        enterTextInSearchField.typeText("PokerStars");
        UiObject searchResponseId = new UiSelector().resourceId("com.betcade.android.market:id/rlSearch").makeUiObject();
        UiObject appIcon = new UiSelector().resourceId("com.betcade.android.market:id/imgAppIcon").makeUiObject();
        UiObject searchResponse = new UiSelector().text("PokerStars").makeUiObject();
        if (searchResponseId.isClickable() && searchResponse.isEnabled() && appIcon.isEnabled()) {
            appIcon.waitToAppear(5).tap();

        }
        adb.navigateBack();
    }

    @Test(description = "check if search query that we had already searched is populating.")
    public void canCheckForSearchQueryInSearchField() throws Exception {
        UiObject searchField = new UiSelector().resourceId("com.betcade.android.market:id/txtToolbar").makeUiObject();
        searchField.tap();
        UiObject searchResponseId = new UiSelector().resourceId("com.betcade.android.market:id/rlSearch").makeUiObject();
        UiObject searchResponse = new UiSelector().resourceId("com.betcade.android.market:id/rlApp").makeUiObject();
        Assert.assertEquals(searchResponseId.getText(), "PokerStars");
        if (searchResponseId.isClickable() && searchResponse.isEnabled()) {
            searchResponse.waitToAppear(5).tap();
        }
        adb.navigateBack();
    }*/

    /**
     * Can logout of google login.
     *
     * @throws Exception the exception
     */
    @AfterClass
    public void canLogoutOfGoogleLogin() throws Exception{
        Object [][] objects = abstractTest.dataProviderForCanLogoutOfTheApp();
        abstractTest.canLogoutOfTheApp(objects[0][0].toString(), objects[0][1].toString(), objects[0][2].toString(), objects[0][3].toString());
        driver = abstractTest.driver;
    }
}
