package framework.utils;

import static framework.dataobjects.TestContentData.*;
import java.sql.*;

/**
 * Created by yaremenko on 14.04.16.
 */
public class SQLManager {

    private static final String DRIVER_DB = "oracle.jdbc.driver.OracleDriver";

    static Connection conn;
    static Statement st;

    public SQLManager() {
        try {
            Class.forName(DRIVER_DB);
            conn = java.sql.DriverManager.getConnection(PropertyLoader.getInstance().getDBUrl(), PropertyLoader.getInstance().getDBLogin(), PropertyLoader.getInstance().getDBPassword());
            if (conn != null) System.out.println("Connection to DB - Success");
            else System.out.println("Not connected to DB");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void closeConnect() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getSystemProperty(String property) {
        String value = null;
        try {
            try {
                st = conn.createStatement();
                String query = String.format("select * from TABLE where FIELD ='%s'", property);
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    value = rs.getString("VALUE");
                }
            } finally {
                st.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public String getUserName(String SMID) {
        String name = null;
        try {
            try {
                st = conn.createStatement();
                String query = String.format("select * from TABLE where id = (select id from TABLE2 where id = (select id from TABLE3 where login = '%s'))", SMID);
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    name = rs.getString("FIRST_NAME");
                }
            } finally {
                st.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    public void setUserLocal(String param, String SMID) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE TABLE " +
                    "SET FIELD1 = ? " +
                    "WHERE ID = (SELECT ID FROM TABLE2 WHERE FIELD2 = ?)");
            try {
                ps.setString(1, param);
                ps.setString(2, SMID);
                ps.executeUpdate();
            } finally {
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
