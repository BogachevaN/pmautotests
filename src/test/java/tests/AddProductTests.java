package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import tests.TestBase;

import static org.openqa.selenium.OutputType.*;



public class AddProductTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validProduct () throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        //загрузка тестовых данных из файла
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/products.csv")))) {
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(";");
                list.add(new Object[]{split[0], split[1]});
                line = reader.readLine();
            }
            return list.iterator();
        }
    }

    
    @Test (dataProvider = "validProduct")
    public void testAddProductLevelOne(String parent, String name) throws InterruptedException, SQLException {
        List<Object[]> productsBefor = app.db().Products();
        app.getNavigationHelper().gotoProductTab();
        app.getProductHelper().initProductCreation();
        app.getProductHelper().fillProductForm(parent,name);
        app.getProductHelper().submitProductCreation();
        Thread.sleep(500); //без этой задержки данные не успевают сохраниться в базу
        List<Object[]> productsAfter = app.db().Products();
        Assert.assertEquals(productsAfter.size(),productsBefor.size()+1);
    }
    

}
