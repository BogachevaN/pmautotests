package appmanager;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by 275 on 19.04.2018.
 */
public class ApplicationManager {
    private final Properties properties;
    private NavigationHelper navigationHelper;
    private TypeParamHelper typeParamHelper;
    private ProductHelper productHelper;
    WebDriver wd;
    private String browser;
    private DbHelper dbHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public static boolean isAlertPresent(WebDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void init() throws IOException, SQLException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        dbHelper = new DbHelper();

        if (browser.equals(BrowserType.CHROME)) {
            System.setProperty("webdriver.chrome.driver", "F:\\Projects\\webdrivers\\chromedriver.exe");
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
        navigationHelper = new NavigationHelper(wd);
        typeParamHelper = new TypeParamHelper(wd);
        productHelper = new ProductHelper(wd);

    }


    public void stop() {
        wd.quit();
    }

    public TypeParamHelper getTypeParamHelper() {
        return typeParamHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public DbHelper db() {return dbHelper;}


    public ProductHelper getProductHelper() {
        return productHelper;
    }
}
