package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by 275 on 22.04.2018.
 */
public class ProductHelper extends HelperBase{
    public ProductHelper(WebDriver wd) {
        super(wd);
    }

    public void submitProductCreation() {
        click(By.xpath("//div[@class='mat-dialog-actions']//button[.='Добавить']"));
    }

    public void fillProductForm(String parent, String name) throws InterruptedException {
        fillField(By.xpath("//*[@ng-reflect-placeholder=\"Наименование\"]"), name);
        click(By.xpath("//div[@class='mat-select-value']/span/span"));
        if (parent.equals("Верхний уровень")) {
            click(By.cssSelector("span.mat-option-text"));

        }
    }

    public void initProductCreation() throws InterruptedException {
        int t=0;
        click(By.xpath("//mat-card-content[@class='mat-card-content']//button[.='Add']"));
    }



    public void initProductRemove(By locator) {
        click(locator);
        click(By.xpath("//mat-card-content[@class='mat-card-content']//button[.='Delete']"));
    }

    public void submitProductRemove() {
        click(By.xpath("//div[@class='mat-dialog-actions']//button[.='Удалить']"));
    }
}
