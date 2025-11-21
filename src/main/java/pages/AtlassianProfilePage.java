package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.File;

public class AtlassianProfilePage extends BasePage {
    public AtlassianProfilePage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//div[@data-test-selector='profile-hover-info']")
    WebElement btnProfilePhoto;
    @FindBy(xpath = "//button[@data-testid='change-avatar']")
    WebElement btnChangeProfilePhoto;
    @FindBy(xpath = "//input[@data-testid='image-navigator-input-file']")
    WebElement uploadAPhoto;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnUpload;
    @FindBy(xpath = "//div[@class='_11c82smr _1reo1wug _18m91wug _c71l53f4 _1i4qfg65']")
    WebElement popUpMessage;


    public void changeMyProfilePhoto(String photoPath){
        //clickWait(btnProfilePhoto);
        Actions actions = new Actions(driver);
        actions.moveToElement(btnProfilePhoto).pause(1000).click().perform();
        clickWait(btnChangeProfilePhoto);

        File photo = new File(photoPath);
        System.out.println(photo.getAbsolutePath());
        uploadAPhoto.sendKeys(photo.getAbsolutePath());
        clickWait(btnUpload);
    }

    public boolean validateMessage(String text){
        return validateTextInElement(popUpMessage, text);
    }

}
