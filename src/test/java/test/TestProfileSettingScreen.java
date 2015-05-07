package test;

import data.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.SettingsPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by techmagic on 03.05.15.
 */

public class TestProfileSettingScreen {

    WebDriver driver;
    User user;

    @BeforeTest
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("http://127.0.0.1:8000/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        user = new User();
    }

    @Test
    public void _1linkForActivation(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.openSignUpForm();
        user.setUsername("reader100@techmagic.co");
        user.setPassword("123456");
        landingPage.signUp(user);
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.verifyActivationNotification(user);
    }

    @Test
    public void _2firstAndLastNameModification(){
        user.setFirstName("Georgiy");
        user.setLastName("Isopenko");
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.changeUserFirstAndLasName(user);
        LandingPage landingPage = new LandingPage(driver);
        landingPage.openProfileSettings();
        settingsPage.verifyInformationAboutTheUser(user);
        landingPage.logOut();
        landingPage.login(user);
        settingsPage.verifyInformationAboutTheUser(user);
    }

    @Test
    public void _3firstAndLastNameEmpty(){
        user.setFirstName(null);
        user.setLastName(null);
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.changeUserFirstAndLasName(user);
        LandingPage landingPage = new LandingPage(driver);
        landingPage.openProfileSettings();
        settingsPage.verifyInformationAboutTheUser(user);
        landingPage.logOut();
        landingPage.login(user);
        settingsPage.verifyInformationAboutTheUser(user);
    }

    @Test
    public void _4userEmailModification(){
            user.setNewEmail("reader1001@techmagic.co");
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.changeUserEmail(user);
        LandingPage landingPage = new LandingPage(driver);
        landingPage.logOut();
        landingPage.login(user);
        settingsPage.verifyUserEmail(user.getUsername());
    }

    @Test
    public void _5userImageUpload(){

        user.setProfileImage("/home/techmagic/Areobooksautomation/ProfileImages/6a00d8341bfa9853ef017d40016258970c.png");
        SettingsPage settingsPage = new SettingsPage(driver);

        try {
            settingsPage.setProfileImage(user);
        }

        catch (Exception e){
         e.printStackTrace();
        }
    }

    @AfterTest
    public void finishTest(){
        driver.quit();
    }
}
