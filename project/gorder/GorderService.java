package io.mobile.finalproject.gorder;

import io.mobile.conf.Conf;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GorderService {
    private static Gorder setGorder(ResultSet rs) throws SQLException {
        int orderNo = rs.getInt("order_number");
        Date orderDate = rs.getDate("order_date");
        int fixedPrice = rs.getInt("fixed_price");
        int discountRate = rs.getInt("discount_rate");
        String paymentMethod = rs.getString("payment_method");
        int gameNo = rs.getInt("game_number");
        String customerId = rs.getString("customer_id");


        return new Gorder(orderNo, orderDate, fixedPrice, discountRate, paymentMethod, gameNo, customerId);
    }


    public static List<Gorder> selectAll() {

        ResultSet rs = null;
        PreparedStatement psmtQuery = null;

        List<Gorder> gorderList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT O.*  "
                    + "WHERE C.customer_id = O.customer_id AND O.game_number = G.game_number";
            psmtQuery = conn.prepareStatement(query);
            rs = psmtQuery.executeQuery();
            while (rs.next()) {
                Gorder gorder = setGorder(rs);
                gorderList.add(gorder);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException ignored) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {
                }
            }
        }

        return gorderList;
    }

    public static Gorder selectById(final int orderNo) {

        ResultSet rs = null;
        PreparedStatement psmtQuery = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT O.* FROM gcustomer C, gorder O, game G "
                    + "WHERE C.customer_id = O.customer_id AND O.game_number = G.game_number AND O.order_number = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setInt(1, orderNo);
            rs = psmtQuery.executeQuery();
            if (rs.next()) {
                return setGorder(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException ignored) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {
                }
            }
        }

        return null;
    }

    public static List<Gorder> selectByCustomerId(final String customerId) {

        List<Gorder> gorderList = new ArrayList<>();

        ResultSet rs = null;
        PreparedStatement psmtQuery = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT O.* FROM gcustomer C, gorder O, game G "
                    + "WHERE C.customer_id = O.customer_id AND G.game_number = O.game_number AND C.customer_id = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, customerId);
            rs = psmtQuery.executeQuery();
            while (rs.next()) {
                Gorder product = setGorder(rs);
                gorderList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException ignored) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {
                }
            }
        }

        return gorderList;
    }

    public static List<Gorder> selectByGameNo(final int gameNo) {

        List<Gorder> gorderList = new ArrayList<>();

        ResultSet rs = null;
        PreparedStatement psmtQuery = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT O.* FROM gcustomer C, gorder O, game G "
                    + "WHERE C.customer_id = O.customer_id AND O.game_number = G.game_number AND G.game_number = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setInt(1, gameNo);
            rs = psmtQuery.executeQuery();
            while (rs.next()) {
                Gorder game = setGorder(rs);
                gorderList.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException ignored) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {
                }
            }
        }

        return gorderList;
    }

    public static int insert(final int orderNo, final Date orderDate, final int fixedPrice, final int discountRate, final String paymentMethod, final int gameNo, final String customerId) {

        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;
        PreparedStatement psmtUpdate2 = null;
        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            // 현재 order_no중 제일 큰 값
            System.out.println("AUTOCOMMIT: " + conn.getAutoCommit());
            conn.setAutoCommit(false);
            String query = "SELECT MAX(order_number) FROM gorder";
            psmtQuery = conn.prepareStatement(query);
            rs = psmtQuery.executeQuery();
            if (!rs.next()) {
                return 0;
            }
            int lastOrderNo = rs.getInt(1);
            int newOrderNo;
            if (lastOrderNo != 0) {
                newOrderNo = 1;
            } else {
                newOrderNo = lastOrderNo + 1;
            }
            String insertStatement = "INSERT INTO gorder VALUES (?,?,?,?,?,?,?)";

            psmtUpdate = conn.prepareStatement(insertStatement);

            psmtUpdate.setInt(1, newOrderNo);
            psmtUpdate.setDate(2, new java.sql.Date(orderDate.getTime()));
            psmtUpdate.setInt(3, fixedPrice);
            psmtUpdate.setInt(4, discountRate);
            psmtUpdate.setString(5, paymentMethod);
            psmtUpdate.setInt(6, gameNo);
            psmtUpdate.setString(7, customerId);


            if (psmtUpdate.executeUpdate() > 0) {
                return newOrderNo;
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
                } catch (SQLException ignored) {
                }
            }
            if (psmtUpdate != null) {
                try {
                    psmtUpdate.close();
                } catch (SQLException ignored) {
                }
            }
            if (psmtUpdate2 != null) {
                try {
                    psmtUpdate2.close();
                } catch (SQLException ignored) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    public static int deleteById(final int orderNo) {

        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;
        PreparedStatement psmtUpdate2 = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM gorder WHERE order_number = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setInt(1, orderNo);
            rs = psmtQuery.executeQuery();

            if (rs.next()) {
                String deleteStatement = "DELETE FROM gorder WHERE order_number = ?";
                psmtUpdate = conn.prepareStatement(deleteStatement);
                psmtUpdate.setInt(1, orderNo);

                return psmtUpdate.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException ignored) {
                }
            }
            if (psmtUpdate != null) {
                try {
                    psmtUpdate.close();
                } catch (SQLException ignored) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {
                }
            }
        }
        return 0;
    }
}
