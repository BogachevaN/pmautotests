package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by 275 on 19.04.2018.
 */
public class TypeParamHelper extends HelperBase{

    public TypeParamHelper(WebDriver wd) {
        super(wd);
    }

    public void submitTypeParamCreation() {
        click(By.xpath("//div/div[2]/div/mat-dialog-container/parametertypeadddialog/div[2]/button[2]"));
    }

    public void fillTypeParamForm(String code, String name, String type, String struct) throws InterruptedException {
        if (waitUntilClickable(By.cssSelector("button.mat-raised-button"),10)) {
            fillField(By.xpath("//*[@ng-reflect-placeholder=\"Код\"]"), code);
            fillField(By.xpath("//*[@ng-reflect-placeholder=\"Наименование\"]"), name);
            if (type.equals("string")) {
                click(By.xpath("//div[@class='mat-dialog-content']/mat-form-field[3]/div/div[1]/div"));
                click(By.cssSelector("span.mat-option-text"));
            } else if (type.equals("array")) {
                click(By.xpath("//div[@class='mat-dialog-content']/mat-form-field[3]/div/div[1]/div"));
                click(By.xpath("//*[@ng-reflect-value=\"array\"]"));
                click(By.xpath("//div[@class='mat-dialog-content']/mat-form-field[4]/div/div[1]/div"));
                if (struct.equals("Структура страховки")) {
                    click(By.xpath("//*[@ng-reflect-value=\"insurance\"]"));
                } else if (struct.equals("Структура процентных ставок")) {
                    click(By.xpath("//*[@ng-reflect-value=\"rateStruct\"]"));
                }
            } else if (type.equals("struct")) {
                click(By.xpath("//div[@class='mat-dialog-content']/mat-form-field[3]/div/div[1]/div"));
                click(By.xpath("//*[@ng-reflect-value=\"struct\"]"));
            }
        }
    }

    public void initTypeParamCreation() throws InterruptedException {
        click(By.xpath("//mat-card-content[@class='mat-card-content']//button[.='Add']"));
    }



    public void initTypeParamRemove(By locator) {
        click(locator);
        click(By.xpath("//mat-card-content[@class='mat-card-content']//button[.='Delete']"));
    }

    public void submitTypeParamRemove() {
        click(By.xpath("//div[@class='mat-dialog-actions']//button[.='Удалить']"));
    }
}
