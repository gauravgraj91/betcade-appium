package com.betcade.appiumtests;

import com.betcade.appiumtests.api.android.Android;
import com.betcade.appiumtests.core.UiObject;
import com.betcade.appiumtests.core.UiSelector;
import com.betcade.appiumtests.core.managers.DriverManager;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;
import static com.betcade.appiumtests.api.android.Android.adb;

/**
 * Created by Gaurav on 5/21/2017.
 */
@Component
public class AppDetailsTests {

    public AndroidDriver driver;
    AbstractTest abstractTest = new AbstractTest();

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

        data[0][0] = "Winner Sports Betting";
        data[0][1] = "com.betcade.android.market:id/lblFabState";
        data[0][2] = "com.android.packageinstaller:id/ok_button";
        data[0][3] = "com.android.packageinstaller:id/done_button";

        return data;
    }

    /**
     * Can select and download an app for rating.
     *
     * @param selectWinnerSportsBetting_name      the select winner sports betting name
     * @param downloadWinnerSportsBetting_id      the download winner sports betting id
     * @param installWinnerSportsBettingButton_id the install winner sports betting button id
     * @param doneAfterInstalling_id              the done after installing id
     * @throws Exception the exception
     */
    @Test(description = "Can download an app to rate it", dataProvider = "selectAndDownloadAnApp")
    public void canSelectAndDownloadAnAppForRating(String selectWinnerSportsBetting_name, String downloadWinnerSportsBetting_id, String installWinnerSportsBettingButton_id, String doneAfterInstalling_id ) throws Exception{
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
        }
        UiObject selectWinnerSportsBetting = new UiSelector().text(selectWinnerSportsBetting_name).makeUiObject();
        selectWinnerSportsBetting.waitToAppear(30).tap();
        UiObject downloadWinnerSportsBetting = new UiSelector().resourceId(downloadWinnerSportsBetting_id).makeUiObject();
        downloadWinnerSportsBetting.waitToAppear(30).tap();
        UiObject installWinnerSportsBetting =  new UiSelector().resourceId(installWinnerSportsBettingButton_id).makeUiObject();
        installWinnerSportsBetting.waitToAppear(30).tap();
        UiObject doneInstallingWinnerSportsBetting = new UiSelector().resourceId(doneAfterInstalling_id).makeUiObject();
        doneInstallingWinnerSportsBetting.waitToAppear(30).tap();
    }

    /**
     * Data provider for can rate an app that is downloaded object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "canRateADownloadedApp")
    public Object[][] dataProviderForCanRateAnAppThatIsDownloaded()
    {
        Object[][] data = new Object[1][6];

        data[0][0] = "com.betcade.android.market:id/imgBonus";
        data[0][1] = "com.betcade.android.market:id/llEase";
        data[0][2] = "com.betcade.android.market:id/btnRate";
        data[0][3] = "com.betcade.android.market:id/lblRatingQuestion";
        data[0][4] = "com.betcade.android.market:id/ratingBar";
        data[0][5] = "com.betcade.android.market:id/lblSubmit";

        return data;
    }

    /**
     * Can rate an app that is downloaded.
     *
     * @param imageBonusButton_id       the image bonus button id
     * @param easyToUseRating_id        the easy to use rating id
     * @param rateAppButton_id          the rate app button id
     * @param easeOfUseQuestionLabel_id the ease of use question label id
     * @param easeOfUseRating_id        the ease of use rating id
     * @param nextButton_id             the next button id
     * @throws Exception the exception
     */
    @Test(description = "Can download and rate an app that is downloaded", dependsOnMethods = "canSelectAndDownloadAnAppForRating", dataProvider = "canRateADownloadedApp")
    public void canRateAnAppThatIsDownloaded(String imageBonusButton_id, String easyToUseRating_id, String rateAppButton_id, String easeOfUseQuestionLabel_id, String easeOfUseRating_id, String nextButton_id) throws Exception
    {
        AndroidElement swipeUpAppDetails = (AndroidElement) (driver.findElement(By.id(imageBonusButton_id)));
        swipeUpAppDetails.swipe(SwipeElementDirection.UP, 3000);
        AndroidElement swipeUpNextAppDetails = (AndroidElement) (driver.findElement(By.id(easyToUseRating_id)));
        swipeUpNextAppDetails.swipe(SwipeElementDirection.UP, 3000);
        UiObject tapOnRateButton = new UiSelector().resourceId(rateAppButton_id).makeUiObject();
        tapOnRateButton.waitToAppear(30).tap();
        UiObject checkEaseOfUse = new UiSelector().resourceId(easeOfUseQuestionLabel_id).makeUiObject();
        Assert.assertEquals(checkEaseOfUse.waitToAppear(30).getText(), "Is Winner Sports Betting easy of use?");
        UiObject tapOnRatingsEasyToUse = new UiSelector().resourceId(easeOfUseRating_id).makeUiObject();
        tapOnRatingsEasyToUse.waitToAppear(30).tap();
        UiObject tapNextButton = new UiSelector().resourceId(nextButton_id).makeUiObject();
        tapNextButton.waitToAppear(30).tap();
    }

    /**
     * Data provider for can rate design and gameplay of the app object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "canRateDesignAndGameplay")
    public Object[][] dataProviderForCanRateDesignAndGameplayOfTheApp()
    {
        Object[][] data = new Object[1][5];

        data[0][0] = "com.betcade.android.market:id/lblSubmit";
        data[0][1] = "com.betcade.android.market:id/lblRatingQuestion";
        data[0][2] = "com.betcade.android.market:id/ratingBar";
        data[0][3] = "com.betcade.android.market:id/lblRatingQuestion";
        data[0][4] = "com.betcade.android.market:id/ratingBar";

        return data;
    }

    /**
     * Can rate design and gameplay of the app.
     *
     * @param nextButton_id        the next button id
     * @param appDesignLabel_id    the app design label id
     * @param rateAppDesign_id     the rate app design id
     * @param rateGameplayLabel_id the rate gameplay label id
     * @param rateGamePlay_id      the rate game play id
     * @throws Exception the exception
     */
    @Test(description = "can rate design and game play for a downloaded app", dependsOnMethods = "canRateAnAppThatIsDownloaded", dataProvider = "canRateDesignAndGameplay")
    public void canRateDesignAndGameplayOfTheApp(String nextButton_id, String appDesignLabel_id, String rateAppDesign_id, String rateGameplayLabel_id, String rateGamePlay_id) throws Exception
    {
        UiObject tapNextButton = new UiSelector().resourceId(nextButton_id).makeUiObject();
        UiObject checkAppDesign = new UiSelector().resourceId(appDesignLabel_id).makeUiObject();
        Assert.assertEquals(checkAppDesign.waitToAppear(30).getText(), "Do you like the app design?");
        UiObject tapOnRatingsAppDesign = new UiSelector().resourceId(rateAppDesign_id).makeUiObject();
        tapOnRatingsAppDesign.waitToAppear(30).tap();
        tapNextButton.waitToAppear(30).tap();
        UiObject checkGameplay = new UiSelector().resourceId(rateGameplayLabel_id).makeUiObject();
        Assert.assertEquals(checkGameplay.waitToAppear(30).getText(), "How was the gameplay experience?");
        UiObject tapOnRatingsGamePlay = new UiSelector().resourceId(rateGamePlay_id).makeUiObject();
        tapOnRatingsGamePlay.waitToAppear(30).tap();
        tapNextButton.waitToAppear(30).tap();
    }

    /**
     * Data provider for can re rate an app that is downloaded object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "canReRateADownloadedApp")
    public Object[][] dataProviderForCanReRateAnAppThatIsDownloaded()
    {
        Object[][] data = new Object[1][4];

        data[0][0] = "com.betcade.android.market:id/btnRate";
        data[0][1] = "com.betcade.android.market:id/lblRatingQuestion";
        data[0][2] = "com.betcade.android.market:id/ratingBar";
        data[0][3] = "com.betcade.android.market:id/lblSubmit";

        return data;
    }

    /**
     * Can rate an app again that is downloaded.
     *
     * @param rateAppButton_id          the rate app button id
     * @param easeOfUseQuestionLabel_id the ease of use question label id
     * @param easeOfUseRating_id        the ease of use rating id
     * @param nextButton_id             the next button id
     * @throws Exception the exception
     */
    @Test(description = "Can download and rate an app", dependsOnMethods = "canRateDesignAndGameplayOfTheApp", dataProvider = "canReRateADownloadedApp")
    public void canRateAnAppAgainThatIsDownloaded(String rateAppButton_id, String easeOfUseQuestionLabel_id, String easeOfUseRating_id, String nextButton_id) throws Exception
    {
        AndroidElement swipeUpAppDetails = (AndroidElement) (driver.findElement(By.id("com.betcade.android.market:id/imgBonus")));
        swipeUpAppDetails.swipe(SwipeElementDirection.UP, 3000);
        AndroidElement swipeUpNextAppDetails = (AndroidElement) (driver.findElement(By.id("com.betcade.android.market:id/llEase")));
        swipeUpNextAppDetails.swipe(SwipeElementDirection.UP, 3000);
        UiObject tapOnRateButton = new UiSelector().resourceId(rateAppButton_id).makeUiObject();
        tapOnRateButton.waitToAppear(30).tap();
        UiObject checkEaseOfUse = new UiSelector().resourceId(easeOfUseQuestionLabel_id).makeUiObject();
        Assert.assertEquals(checkEaseOfUse.waitToAppear(30).getText(), "Is Winner Sports Betting easy of use?");
        UiObject tapOnRatingsEasyToUse = new UiSelector().resourceId(easeOfUseRating_id).makeUiObject();
        tapOnRatingsEasyToUse.waitToAppear(30).tap();
        UiObject tapNextButton = new UiSelector().resourceId(nextButton_id).makeUiObject();
        tapNextButton.waitToAppear(30).tap();
    }

    /**
     * Data provider for can rate design and gameplay again for the app object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "canRateDesignAndGamePlayAgain")
    public Object[][] dataProviderForCanRateDesignAndGameplayAgainForTheApp()
    {
        Object[][] data = new Object[1][5];

        data[0][0] = "com.betcade.android.market:id/lblSubmit";
        data[0][1] = "com.betcade.android.market:id/lblRatingQuestion";
        data[0][2] = "com.betcade.android.market:id/ratingBar";
        data[0][3] = "com.betcade.android.market:id/lblRatingQuestion";
        data[0][4] = "com.betcade.android.market:id/ratingBar";

        return data;
    }

    /**
     * Can rate design and game play again for the app.
     *
     * @param nextButton_id        the next button id
     * @param appDesignLabel_id    the app design label id
     * @param rateAppDesign_id     the rate app design id
     * @param rateGameplayLabel_id the rate gameplay label id
     * @param rateGamePlay_id      the rate game play id
     * @throws Exception the exception
     */
    @Test(description = "can rate design and game play again for a app", dependsOnMethods = "canRateAnAppAgainThatIsDownloaded", dataProvider = "canRateDesignAndGamePlayAgain")
    public void canRateDesignAndGamePlayAgainForTheApp(String nextButton_id, String appDesignLabel_id, String rateAppDesign_id, String rateGameplayLabel_id, String rateGamePlay_id) throws Exception
    {
        UiObject tapNextButton = new UiSelector().resourceId(nextButton_id).makeUiObject();
        UiObject checkAppDesign = new UiSelector().resourceId(appDesignLabel_id).makeUiObject();
        Assert.assertEquals(checkAppDesign.waitToAppear(30).getText(), "Do you like the app design?");
        UiObject tapOnRatingsAppDesign = new UiSelector().resourceId(rateAppDesign_id).makeUiObject();
        tapOnRatingsAppDesign.waitToAppear(30).tap();
        tapNextButton.waitToAppear(30).tap();
        UiObject checkGameplay = new UiSelector().resourceId(rateGameplayLabel_id).makeUiObject();
        Assert.assertEquals(checkGameplay.waitToAppear(30).getText(), "How was the gameplay experience?");
        UiObject tapOnRatingsGamePlay = new UiSelector().resourceId(rateGamePlay_id).makeUiObject();
        tapOnRatingsGamePlay.waitToAppear(30).tap();
        tapNextButton.waitToAppear(30).tap();
        adb.uninstallApp("sports.winner.com.winnersports");
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

        data[0][0] = "com.betcade.android.market:id/imgToolbarMenu";
        data[0][1] = "My Apps";
        data[0][2] = "ALL APPS";
        data[0][3] = "Winner Sports Betting";

        return data;
    }

    /**
     * Can check download status.
     *
     * @param hamburgerMenuButton_Id          the hamburger menu button id
     * @param myAppsSubMenu_name              the my apps sub menu name
     * @param allAppsTab_name                 the all apps tab name
     * @param checkWilliamHillSportsBook_name the check william hill sports book name
     * @throws Exception the exception
     */
    @Test(description = "Can check download status of the app downloads.", dependsOnMethods = "canRateDesignAndGamePlayAgainForTheApp", dataProvider = "checkDownloadStatus")
    public void canCheckDownloadStatus(String hamburgerMenuButton_Id, String myAppsSubMenu_name, String allAppsTab_name, String checkWilliamHillSportsBook_name) throws Exception
    {
        UiObject navigateStoreHome =  new UiSelector().resourceId("com.betcade.android.market:id/imgToolbarBack").makeUiObject();
        navigateStoreHome.waitToAppear(30).tap();
        UiObject hamburgerMenu =  new UiSelector().resourceId(hamburgerMenuButton_Id).makeUiObject();
        hamburgerMenu.waitToAppear(30).tap();
        UiObject selectMyApps = new UiSelector().text(myAppsSubMenu_name).makeUiObject();
        selectMyApps.waitToAppear(30).tap();
        UiObject checkAllApps = new UiSelector().text(allAppsTab_name).makeUiObject();
        checkAllApps.waitToAppear(30).tap();
        UiObject.scrollVerticalBottomToTop();
        UiObject checkWilliamHillSports = new UiSelector().text(checkWilliamHillSportsBook_name).makeUiObject();
        //Assert.assertEquals(checkWilliamHillSports.waitToAppear(30).getText(), "Winner Sports Betting");
        adb.uninstallApp("sports.winner.com.winnersports");
    }

    /**
     * Data provider for can check game play images in app details object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "checkGamePlayImagesInAppDetails")
    public Object[][] dataProviderForCanCheckGamePlayImagesInAppDetails()
    {
        Object[][] data = new Object[1][4];

        data[0][0] = "com.betcade.android.market:id/lblPublisherName";
        data[0][1] = "Betsafe";
        data[0][2] = "com.betcade.android.market:id/imgBonus";
        data[0][3] = "com.betcade.android.market:id/imgSliderImage";

        return data;
    }

    /**
     * Can check game play images in app details.
     *
     * @param scrollUsingPublisher_id          the scroll using publisher id
     * @param gameDetails_name                 the game details name
     * @param scrollAppDetailsBonus_id         the scroll app details bonus id
     * @param checkSliderImagesInAppDetails_id the check slider images in app details id
     * @throws Exception the exception
     */
    @Test(description = "Can check game play images in app details screen.", dependsOnMethods = "canCheckDownloadStatus", dataProvider = "checkGamePlayImagesInAppDetails")
    public void canCheckGamePlayImagesInAppDetails(String scrollUsingPublisher_id, String gameDetails_name, String scrollAppDetailsBonus_id, String checkSliderImagesInAppDetails_id) throws Exception
    {
        adb.navigateBack();
        AndroidElement scrollStoreHome = (AndroidElement)(driver.findElement(By.id(scrollUsingPublisher_id)));
        scrollStoreHome.swipe(SwipeElementDirection.LEFT, 3000);
        UiObject selectAnApp = new UiSelector().text(gameDetails_name).makeUiObject();
        selectAnApp.waitToAppear(30).tap();
        AndroidElement swipeUpAppDetailsScreen = (AndroidElement) (driver.findElement(By.id(scrollAppDetailsBonus_id)));
        swipeUpAppDetailsScreen.swipe(SwipeElementDirection.UP, 3000);
        List<WebElement> sliderImages = driver.findElements(By.id(checkSliderImagesInAppDetails_id));
        TouchAction t = new TouchAction(driver);
        t.tap(sliderImages.get(0)).perform();
        adb.navigateBack();
        adb.navigateBack();
    }

    /**
     * Data provider for can check app desc in app details object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "checkAppDescInAppDetails")
    public Object[][] dataProviderForCanCheckAppDescInAppDetails()
    {
        Object[][] data = new Object[1][5];

        data[0][0] = "com.betcade.android.market:id/lblPublisherName";
        data[0][1] = "Winner";
        data[0][2] = "Betsafe";
        data[0][3] = "com.betcade.android.market:id/imgBonus";
        data[0][4] = "com.betcade.android.market:id/lblMoreLess";

        return data;
    }

    /**
     * Can check app desc in app details.
     *
     * @param publisherId         the publisher id
     * @param winnerApp_name      the winner app name
     * @param selectApp_name      the select app name
     * @param imageBonusButton_id the image bonus button id
     * @param readLessMore_id     the read less more id
     * @throws Exception the exception
     */
    @Test(description = "Can check app description in the app details screen.", dependsOnMethods = "canCheckGamePlayImagesInAppDetails", dataProvider = "checkAppDescInAppDetails")
    public void canCheckAppDescInAppDetails(String publisherId, String winnerApp_name, String selectApp_name, String imageBonusButton_id, String readLessMore_id) throws Exception
    {
        AndroidElement scrollHorizontalCardLayout = (AndroidElement)(driver.findElement(By.id(publisherId)));
        scrollHorizontalCardLayout.swipe(SwipeElementDirection.LEFT, 4000);
        AndroidElement scrollHorizontalCardLayout2 = (AndroidElement)(driver.findElement(By.name(winnerApp_name)));
        scrollHorizontalCardLayout2.swipe(SwipeElementDirection.LEFT, 4000);
        UiObject selectBetSafe = new UiSelector().text(selectApp_name).makeUiObject();
        selectBetSafe.waitToAppear(30).tap();
        AndroidElement swipeUpAppDetails = (AndroidElement) (driver.findElement(By.id(imageBonusButton_id)));
        swipeUpAppDetails.swipe(SwipeElementDirection.UP, 3000);
        UiObject tapReadMore = new UiSelector().resourceId(readLessMore_id).makeUiObject();
        tapReadMore.waitToAppear(30).tap();
        Assert.assertEquals(tapReadMore.getText(), "READ LESS");
        tapReadMore.waitToAppear(30).tap();
        Assert.assertEquals(tapReadMore.getText(), "READ MORE");
        adb.navigateBack();
    }

    /**
     * Data provider for can verify app category and rating object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "canCheckAppCategoryNameAndRating")
    public Object[][] dataProviderForCanVerifyAppCategoryAndRating()
    {
        Object[][] data = new Object[1][3];

        data[0][0] = "William Hill Sportsbook";
        data[0][1] = "com.betcade.android.market:id/lblCatName";
        data[0][2] = "com.betcade.android.market:id/lblRating";

        return data;
    }

    /**
     * Can verify app category and rating.
     *
     * @param selectAnApp_name  the select an app name
     * @param checkCategory_id  the check category id
     * @param checkAppRating_id the check app rating id
     * @throws Exception the exception
     */
    @Test(description = "Can verify app category and rating of a sportsbetting app", dependsOnMethods = "canCheckAppDescInAppDetails", dataProvider = "canCheckAppCategoryNameAndRating")
    public void canVerifyAppCategoryAndRating(String selectAnApp_name, String checkCategory_id, String checkAppRating_id) throws Exception
    {
        UiObject goToSportsBettingApp = new UiSelector().text(selectAnApp_name).makeUiObject();
        goToSportsBettingApp.waitToAppear(30).tap();
        UiObject checkCategoryName = new UiSelector().resourceId(checkCategory_id).makeUiObject();
        Assert.assertEquals(checkCategoryName.waitToAppear(30).getText(), "Sportsbetting");
        UiObject checkAppRating = new UiSelector().resourceId(checkAppRating_id).makeUiObject();
        checkAppRating.waitToAppear(30).isEnabled();
        adb.navigateBack();
    }

    @AfterClass
    public void canLogoutOfGoogleLogin() throws Exception{
        Object [][] objects = abstractTest.dataProviderForCanLogoutOfTheApp();
        abstractTest.canLogoutOfTheApp(objects[0][0].toString(), objects[0][1].toString(), objects[0][2].toString(), objects[0][3].toString());
        driver = abstractTest.driver;
    }
}
