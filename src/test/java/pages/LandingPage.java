package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import data.User;
import org.testng.Assert;

public class LandingPage extends AbstarctPage{

    public LandingPage(WebDriver driver){
        super(driver);
    }
// landing page elements
    @FindBy(xpath = "//*[contains(text(),'Login')]")
    WebElement loginButtonTop;

    @FindBy(xpath = "//*[contains(text(),'Sign Up')]")
    WebElement signUpButtonTop;

    @FindBy(xpath = ".//*[@id='dLabel']/div")
    WebElement userIcon;

    @FindBy(xpath = "//div[2]/ul/li[1]/a")
    WebElement userProfileLink;

    @FindBy(xpath = ".//*[@id='dLabel']/div")
    WebElement readerProfileLink;

    @FindBy(xpath = "//*[contains(text(),'Settings')]")
    WebElement accountSettingsLink;

    @FindBy(xpath = "//*[contains(text(),'Log out')]")
    WebElement logOutLink;



// Login page elements

    @FindBy(css = "#id_email")
    WebElement eMailField;

    @FindBy(css = "#id_password")
    WebElement passwordField;

    @FindBy(xpath= "//form/input[4]")
    WebElement submitLogSignIn;

    @FindBy(css = ".error")
    WebElement errorText;


    public void openSignUpForm(){
        signUpButtonTop.click();
    }

    private void clearCredentials(){
        if(!eMailField.getText().equals(""))
            eMailField.clear();
        if(!passwordField.getText().equals(""))
            passwordField.clear();
    }

    public void signUp(User user){
        clearCredentials();
        eMailField.sendKeys(user.getUsername());
        passwordField.sendKeys(user.getPassword());

        submitLogSignIn.click();

        if(user.getPassword().equals("") && user.getUsername().equals(""))
            Assert.assertEquals(errorText.getText(), ("Please enter a valid email address."));

            else if(user.getUsername().equals("'"))
            Assert.assertEquals(errorText.getText(), ("Please enter a valid email address."));


            else if(user.getPassword().equals(""))
            Assert.assertEquals(errorText.getText(), ("Please enter password."));

        else
        Assert.assertEquals(driver.getCurrentUrl(),"http://127.0.0.1:8000/accounts/settings/");

    }

    public void signUpExistingUser(User user){
        clearCredentials();
        eMailField.sendKeys(user.getUsername());
        passwordField.sendKeys(user.getPassword());
        submitLogSignIn.click();
        Assert.assertEquals(errorText.getText(), ("A user with this email address already exists."));

    }

    public void login(User user){
        loginButtonTop.click();
        eMailField.sendKeys(user.getUsername());
        passwordField.sendKeys(user.getPassword());
        submitLogSignIn.click();
        Assert.assertEquals(true, verifyIfElementIsDisplayed(userIcon));
    }




}
