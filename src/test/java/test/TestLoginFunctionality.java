package test;

import data.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LandingPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by techmagic on 03.05.15.
 */
public class TestLoginFunctionality {

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
    public void verifyEmptyEmailAndPassword() {
        user.setUsername("");
        user.setPassword("");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.openLoginForm();
        landingPage.login(user);
    }

    @Test
    public void verifyEmptyPassword() {
        user.setPassword("");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.login(user);
    }

    @Test
    public void verifyInvalidUserLogin() {
        user.setUsername("reader1@techmagic.co");
        user.setPassword("1254678");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.loginInvalidUser(user);
    }

    @Test
    public void verifyLogIn() {
        user.setUsername("reader2@techmagic.co");
        user.setPassword("03123781");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.login(user);
        landingPage.logOut();
    }

    @AfterTest
    public void finishTest() {
        driver.quit();
    }
}
