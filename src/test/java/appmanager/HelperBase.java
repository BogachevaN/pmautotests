package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by 275 on 19.04.2018.
 */
public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    //клик
    protected void click(By locator) {
        int click = 0;
        while (click!=1) {
            try {
                wd.findElement(locator).click();
                click = 1;
            } catch (Throwable t) {

            }
        }
    }

    //заполнение любого поля
    protected void fillField(By locator, String text) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    //ожидание появления
    protected boolean waitUntilVisible(By locator, int time) {
        //принимает на вход локатор, время ожидания
        try {
            WebDriverWait wait = new WebDriverWait(wd, time);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException ex) { //срабатывает, если время истекло
            return false;
        }
        return true;
    }

    //ожидание возможности кликнуть по полю
    protected boolean waitUntilClickable(By locator, int time) {
        //принимает на вход локатор, время ожидания
        try {
            WebDriverWait wait = new WebDriverWait(wd, time);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException ex) { //срабатывает, если время истекло
            return false;
        }
        return true;
    }
}
