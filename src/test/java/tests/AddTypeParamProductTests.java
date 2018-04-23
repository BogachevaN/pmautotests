package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddTypeParamProductTests extends TestBase {


    @DataProvider
    public Iterator<Object[]> validTypeParam () throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        //загрузка тестовых данных из файла
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/typesParam.csv")))) {
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(";");
                list.add(new Object[]{split[0], split[1], split[2], split[3]});
                line = reader.readLine();
            }
            return list.iterator();
        }
    }

    @Test(dataProvider = "validTypeParam")
    public void testAddTypeParamProduct(String code, String name, String type, String struct) throws InterruptedException, SQLException {
        List<Object[]> typesParamBefor = app.db().TypesParam();
        app.getNavigationHelper().gotoTypeParamTab();
        app.getTypeParamHelper().initTypeParamCreation();
        app.getTypeParamHelper().fillTypeParamForm(code, name, type, struct);
        app.getTypeParamHelper().submitTypeParamCreation();
        Thread.sleep(500); //без этой задержки данные не успевают сохраниться в базу
        List<Object[]> typesParamAfter = app.db().TypesParam();
        Assert.assertEquals(typesParamAfter.size(),typesParamBefor.size()+1);
    }

}
