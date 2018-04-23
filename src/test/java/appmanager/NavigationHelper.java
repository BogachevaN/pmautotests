package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by 275 on 19.04.2018.
 */
public class NavigationHelper {
    private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void gotoTypeParamTab() throws InterruptedException {
        wd.findElement(By.id("mat-tab-label-0-1")).click();
    }

    public void gotoProductTab() {
        wd.findElement(By.id("mat-tab-label-0-0")).click();
    }
}
