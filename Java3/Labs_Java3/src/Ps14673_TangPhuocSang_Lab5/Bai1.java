/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab5;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author TuanDuc
 */
public class Bai1 {
    public static void main(String[] args) {
        try 
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost; databaseName=PS10576_QLSV; user=sa;password=123456;";
            Connection con = DriverManager.getConnection(url);
            String sql = "select * from SINHVIEN";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                System.out.print(rs.getString("MASV") + ", ");
                System.out.print(rs.getString("HOTEN") + ", ");
                System.out.print(rs.getString("EMAIL") + ", ");
                System.out.print(rs.getString("SDT") + ", ");
                System.out.print(rs.getString("GIOITINH") + ", ");
                System.out.println(rs.getString("DIACHI"));
            }
            con.close();
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }
}
