package io.mobile.finalproject.dlc;

import io.mobile.conf.Conf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DLCService {
    private static DLC setDLC(ResultSet rs) throws SQLException {
        int gameNo = rs.getInt("game_number");
        String dlcName = rs.getString("DLC_name");

        return new DLC(gameNo, dlcName);
    }

    public static List<DLC> selectAll() {

        ResultSet rs = null;
        PreparedStatement psmtQuery = null;

        List<DLC> DLCList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM DLC";
            psmtQuery = conn.prepareStatement(query);
            rs = psmtQuery.executeQuery();

            while (rs.next()) {
                DLC dlc = setDLC(rs);
                DLCList.add(dlc);
            }
            return DLCList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public static int insert(int gameNo, String dlcName) {

        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM DLC D, game G WHERE G.gameNo = D.gameNo AND gameNo = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setInt(1, gameNo);
            rs = psmtQuery.executeQuery();
            if (!rs.next()) { //해당 고객이 없을 때만(추가해야 할 때만) 진행
                String insertStatement =
                        "INSERT INTO DLC(game_number, DLC_name)" +
                                " VALUES (?, ?)"; // 문자열을 이을 때에는 공백 필수입, 위에 넣든 아래 넣든 둘 다 넣든 ㄱㅊ
                psmtUpdate = conn.prepareStatement(insertStatement);
                psmtUpdate.setInt(1, gameNo);
                psmtUpdate.setString(2, dlcName);

                return psmtUpdate.executeUpdate();
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public static int update(int gameNo, String dlcName) {

        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM DLC D, game G WHERE G.gameNo = D.gameNo AND gameNo = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setInt(1, gameNo);
            rs = psmtQuery.executeQuery();
            if (rs.next()) { //해당 고객이 있을 때만(수정할 수 있을 때만) 진행
                String updateStatement =
                        "UPDATE DLC\n" +
                                " SET game_number = ?, DLC_name = ?" +
                                " WHERE game_number =?"; // 문자열을 이을 때에는 공백 필수입, 위에 넣든 아래 넣든 둘 다 넣든 ㄱㅊ
                psmtUpdate = conn.prepareStatement(updateStatement);
                psmtUpdate.setInt(1, gameNo);
                psmtUpdate.setString(2, dlcName);
                psmtUpdate.setInt(3, gameNo);

                return psmtUpdate.executeUpdate();
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public static int deleteById(int gameNo) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM DLC D, game G WHERE G.gameNo = D.gameNo AND gameNo = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setInt(1, gameNo);
            rs = psmtQuery.executeQuery();
            if (rs.next()) { //해당 고객이 있을 때만(수정할 수 있을 때만) 진행
                String deleteStatement =
                        "Delete FROM customer Where game_number = ?";
                psmtUpdate = conn.prepareStatement(deleteStatement);
                psmtUpdate.setInt(1, gameNo);

                return psmtUpdate.executeUpdate();
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
