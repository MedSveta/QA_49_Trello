package pages;

import dto.Board;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BoardsPage extends BasePage {
    public BoardsPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//button[@data-testid='create-board-tile']")
    WebElement btnCreateNewBoard;
    @FindBy(xpath = "//input[@data-testid='create-board-title-input']")
    WebElement inputBoardTitle;
    @FindBy(xpath = "//button[@data-testid='create-board-submit-button']")
    WebElement btnCreate;
    @FindBy(xpath = "//span[@class='VmbXKMJLSqfD0U']")
    WebElement popUpMessageDelete;

    @FindBy(xpath = "//*[@data-testid='header-member-menu-avatar']")
    WebElement btnAccount;
    @FindBy(xpath = "//span[text()='Manage account']")
    WebElement btnManageAccount;

    public void openMyAccount() {
    clickWait(btnAccount);
    clickWait(btnManageAccount);
    }

    public boolean validatePopUpMessage(String text) {
        return validateTextInElement(popUpMessageDelete, text);
    }

    public void createNewBoard(Board board) {
        pause(5);
        btnCreateNewBoard.click();
        clickWait(inputBoardTitle);
        inputBoardTitle.sendKeys(board.getBoardTitle());
    }

    public void clickBtnCreate() {
        clickWait(btnCreate);
    }

    public boolean validateUrl(String url) {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains(url));
    }

    public boolean validateUrlWOCreateException(String url) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.urlContains(url));
        } catch (TimeoutException e) {
            e.printStackTrace();
            System.out.println("created exception");
            return false;
        }
    }

    public boolean buttonCreateIsNotClickable() {
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.not(ExpectedConditions
                        .elementToBeClickable(btnCreate)));
    }
}
