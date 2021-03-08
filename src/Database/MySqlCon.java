package Database;

import java.sql.*;
import java.util.ArrayList;

public class MySqlCon {
    private static Statement stmt;
    private static Connection con;
//    public static void main(String[] args) {
//        MySqlCon myconn = new MySqlCon();
//        ArrayList<String> res = myconn.query("SELECT * FROM FLUSS;");
//        for (String re : res) {
//            System.out.println(re);
//        }
//    }

    public static void Connect(String connString, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connString, user, password);
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://45.146.252.232:3306/db_ProjectTesting", "root", "^LqM9=,Kae_`.AQ[");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void update(String query){
        if (con == null)
            MySqlCon.Connect();
        try {
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static ArrayList<String> query(String query) {
        if (con == null)
            MySqlCon.Connect();
        try {
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            String header = "";
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                header += rsmd.getColumnName(i) + " ";
            }
            header = header.trim();
            ArrayList<String> results = new ArrayList<>();
            results.add(header);
            while (rs.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.append(rs.getString(i)).append(" ");
                }
                results.add(row.toString().trim());
            }
            return results;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}


