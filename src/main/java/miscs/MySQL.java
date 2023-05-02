package miscs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class MySQL {

    private static Connection conn = null;
    private static Connection localDBConnection = null;


    public static Connection getRemoteConnection() {
        if(conn!=null)
            return conn;

        String url = "jdbc:mysql://AgamRimon.com:3306/agam-rimon---main-schema---on-remote?useSSL=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";

        Properties info = new Properties();
        info.put("user", "gil-home");
        info.put("password", "gil-home-GV231313");
        try {

            conn=DriverManager.getConnection(url, info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;

    }

    public static Connection getLocalConnection() {
        if (localDBConnection != null)
            return localDBConnection;

        String url = "jdbc:mysql://localhost:3306/agam-rimon---main-schema---on-local?useSSL=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";

        Properties info = new Properties();
        info.put("user", "gilveber");
        info.put("password", "mysql-GV231313");

        try {
            localDBConnection = DriverManager.getConnection(url, info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localDBConnection;

    }

    public static void closeStatementAndResultSet(Statement stmt, ResultSet rs) {
        if (stmt != null)
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        if (rs != null)
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


}


