package pages;

import data.SettingsError;
import data.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import sun.awt.SunHints;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * Created by techmagic on 03.05.15.
 */
public class SettingsPage extends AbstarctPage {

    public SettingsPage(WebDriver driver){
        super(driver);
    }

    SettingsError sessionErrors = new SettingsError();

    @FindBy(css ="#id_first_name")
    WebElement firstNameField;

    @FindBy(css="#id_last_name")
    WebElement lastNameField;

    @FindBy(css = "#id_email")
    WebElement emailField;

    @FindBy(xpath = ".//*[@id='user-settings-form']/div[5]/a")
    WebElement cancel;

    @FindBy(css = ".btn.medium-blue-btn.js-save-changes")
    WebElement submit;

    @FindBy(xpath = "//a[contains(text(),'Publish')]")
    WebElement publishBookButton;

    @FindBy(css = ".error")
    WebElement error;

    @FindBy(xpath ="//p[@class=\"info\"]")
    WebElement infoPanel;

    @FindBy(css = ".profile-image-upload-link")
    WebElement imageUploadLink;

    @FindBy(css = ".user-profile-image")
    WebElement profileImage;

    public void verifyInformationAboutTheUser(User user){
        waitForElement(1000,firstNameField);
        Assert.assertEquals(firstNameField.getAttribute("Value"),user.getFirstName());
        Assert.assertEquals(lastNameField.getAttribute("Value"),user.getLastName());
        Assert.assertEquals(emailField.getAttribute("Value"),user.getUsername());
    }

    public void verifyUserEmail(String email){
        Assert.assertEquals(emailField.getAttribute("Value"),email);
    }

    public void verifyActivationNotification(User user){
            waitForElement(10,error);
            Assert.assertEquals(error.getText(),sessionErrors.getNotActivatedAccountError());
    }

    public void changeUserFirstAndLasName(User user){
        firstNameField.clear();
        lastNameField.clear();
        firstNameField.sendKeys(user.getFirstName());
        lastNameField.sendKeys(user.getLastName());
        waitForElement(3000,submit);
        submit.click();
    }

    public void changeUserEmail(User user){
        emailField.clear();
        emailField.sendKeys(user.getNewEmail());
        submit.click();
        Assert.assertEquals(infoPanel.getText(),sessionErrors.getEmailModificationWarning(user.getUsername()));
    }
    public void setProfileImage(User user) throws AWTException, InterruptedException {
        imageUploadLink.click();

        StringSelection ss = new StringSelection(user.getProfileImage());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);


        waitForElement(5000,submit);
        submit.click();
        waitForElement(5000,infoPanel);
        Assert.assertEquals(infoPanel.getText(),sessionErrors.getSubmitSuccessMessage());
        Assert.assertEquals(verifyIfElementIsDisplayed(profileImage),true);
    }

}
