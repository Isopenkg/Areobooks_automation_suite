package test;


import data.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.LandingPage;

import java.util.concurrent.TimeUnit;

public class SignUpFunctionality {

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
    public void verifyEmptyEmailAndPassword(){
        user.setUsername("");
        user.setPassword("");

        LandingPage landingPage = new LandingPage(driver);
        landingPage.openSignUpForm();
        landingPage.signUp(user);

    }

    @Test
    public void verifyEmptyPassword(){
        user.setPassword("");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.signUp(user);
    }

    @Test
    public void verifyExistingEmail(){

        user.setUsername("reader2@techamgic.co");
        user.setPassword("03123781");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.signUpExistingUser(user);

    }

    @Test
    public void verifySignIn() {
        user.setUsername("reader20@techmagic.co");
        user.setPassword("03123781");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.signUp(user);
    }

    //add functionality to delete account reader20@techamgic.co!!!

    @AfterTest
    public void finishTest(){
        driver.quit();
    }

}
