package appmanager;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.openqa.selenium.By;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 275 on 21.04.2018.
 */
public class DbHelper {
    private Connection conn = null;
    private Statement st = null;

    public List<Object[]> TypesParam() throws SQLException {
        ResultSet rs = makeRequest("select code, name, value_type, array_param_type from parameter_types");
        List<Object[]> listTypesParam = new ArrayList<Object[]>();
        while (rs.next()){
            listTypesParam.add(new Object[]{rs.getString("code"), rs.getString("name"),
                    rs.getString("value_type"), rs.getString("array_param_type")});
        }
        closeCon(rs);

        return listTypesParam;
    }

    private void closeCon(ResultSet rs) throws SQLException {
        rs.close();
        st.close();
        conn.close();
    }

    public ResultSet makeRequest (String request) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://192.168.99.100:3306/DB?" +
                "user=admin&password=admin");
        st = conn.createStatement();
        ResultSet rs = st.executeQuery(request);
        return rs;
    }

    public By findTypeParamWithoutProduct() throws SQLException {
        ResultSet rs = makeRequest("select code, name from parameter_types lt left outer join parameters rt on lt.code = rt.param_type_code where rt.param_type_code is null");
        By locator = null;
        while (rs.next() & locator == null){
            String typeName = rs.getString("name");
            String a = "//mat-table[@class='mat-table']//mat-cell[.=' " + typeName + " ']";
            locator = By.xpath("//mat-table[@class='mat-table']//mat-cell[.=' " + typeName + " ']");
        }
        closeCon(rs);

        return locator;
    }

    public List<Object[]> Products() throws SQLException {
        ResultSet rs = makeRequest("select parentId, name from products");
        List<Object[]> listTypesParam = new ArrayList<Object[]>();
        while (rs.next()){
            listTypesParam.add(new Object[]{rs.getString("parentId"), rs.getString("name")});
        }
        closeCon(rs);

        return listTypesParam;
    }
}
