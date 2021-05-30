/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theballgame2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Apatuha
 */
public class Service {

    public Connection getConn() throws SQLException {
        String connectionString = "jdbc:mariadb://localhost:3306/test?user=root&password=";
        Connection con = DriverManager.getConnection(connectionString);

        return con;
    }

//    public void saveStats(String username, int touchCount) {
//        String q = "insert into test.ballgame_stats (username , touch_count) values(?, ?)";
//        try ( Connection c = getConn();  PreparedStatement ps = c.prepareStatement(q);) {
//            ps.setString(1, username);
//            ps.setInt(2, touchCount);
//            ps.executeUpdate();
//        } catch (SQLException exc) {
//            exc.printStackTrace();
//        }
//    }
    
    public long getStatsId(){
        long id = 0;
        String qp = "select NEXTVAL(s1)";
        try ( Connection c = getConn();  PreparedStatement ps = c.prepareStatement(qp);) {
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getLong(1);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return id;
    }

    public void saveStats(String username, Map<String, Counter> m) {
        long id = getStatsId();
        String q = "insert test.ballgame2_stats (username, id) values (?, ?)";
        try ( Connection c = getConn();  PreparedStatement ps = c.prepareStatement(q);) {
            ps.setString(1, username);
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        
        String p = "INSERT INTO test.ballgame2_stats_detail (counter, object_name, parent_id) VALUES(?, ?, ?);";
        for (String key : m.keySet()) {
            try ( Connection c = getConn();  PreparedStatement ps = c.prepareStatement(p);) {
                ps.setInt(1, m.get(key).getTouchCount());
                ps.setString(2, key);
                ps.setLong(3, id);
                ps.executeUpdate();
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
        }
    }
}
