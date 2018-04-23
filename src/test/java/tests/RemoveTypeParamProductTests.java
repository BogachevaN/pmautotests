package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

public class RemoveTypeParamProductTests extends TestBase {

    @Test
    public void testRemoveTypeParamWithoutProduct() throws InterruptedException, SQLException {
        List<Object[]> typesParamBefor = app.db().TypesParam();
        app.getNavigationHelper().gotoTypeParamTab();
        By locator = app.db().findTypeParamWithoutProduct();
        app.getTypeParamHelper().initTypeParamRemove(locator);
        app.getTypeParamHelper().submitTypeParamRemove();
        Thread.sleep(500);
        List<Object[]> typesParamAfter = app.db().TypesParam();
        Assert.assertEquals(typesParamAfter.size(),typesParamBefor.size()-1);
    }




}
