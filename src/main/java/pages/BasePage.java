package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    static WebDriver driver;

    public static void setDriver(WebDriver wd) {
        driver = wd;
    }

    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickWait(WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(element))
                .click();
    }

    public boolean validateTextInElement(WebElement element, String text){
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                    .textToBePresentInElement(element, text));
        }catch (NoSuchElementException| TimeoutException e){
            e.printStackTrace();
            System.out.println("created exception");
            return false;
        }
    }
}
