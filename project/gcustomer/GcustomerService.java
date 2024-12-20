package io.mobile.finalproject.gcustomer;

import io.mobile.conf.Conf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GcustomerService {
        private static Gcustomer setGcustomer(ResultSet rs) throws SQLException {
            String customerId = rs.getString("customer_id");
            String customerName = rs.getString("customer_name");
            String phoneNumber = rs.getString("phone_number");
            int year = rs.getInt("byear");
            int month = rs.getInt("bmonth");
            int day = rs.getInt("bday");
            String email = rs.getString("email");
            int savedMoney = rs.getInt("saved_money");

            return new Gcustomer(customerId, customerName, phoneNumber, year, month, day, email, savedMoney);
        }

        public static List<Gcustomer> selectAll() {

            ResultSet rs = null;
            PreparedStatement psmtQuery = null;

            List<Gcustomer> gcustomerList = new ArrayList<>();

            try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
                String query = "SELECT * FROM gcustomer";
                psmtQuery = conn.prepareStatement(query);
                rs = psmtQuery.executeQuery();

                while (rs.next()) {
                    Gcustomer gcustomer = setGcustomer(rs);
                    gcustomerList.add(gcustomer);
                }
                return gcustomerList;

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

        public static Gcustomer selectById(final String customerId){
            ResultSet rs = null;
            PreparedStatement psmtQuery = null;

            try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
                String query = "SELECT * FROM gcustomer WHERE customer_id = ?";
                psmtQuery = conn.prepareStatement(query);
                psmtQuery.setString(1, customerId);
                rs = psmtQuery.executeQuery();
                if (rs.next()) {
                    Gcustomer gcustomer = setGcustomer(rs);
                    return gcustomer;
                }else{
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
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
        }

        public static int insert(String customerId, String customerName, String phoneNumber, int year, int month, int day, String email, int savedMoney) {

            ResultSet rs = null;
            PreparedStatement psmtQuery = null;
            PreparedStatement psmtUpdate = null;

            try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
                String query = "SELECT * FROM gcustomer WHERE customer_id = ?";
                psmtQuery = conn.prepareStatement(query);
                psmtQuery.setString(1, customerId);
                rs = psmtQuery.executeQuery();
                if (!rs.next()) { //해당 고객이 없을 때만(추가해야 할 때만) 진행
                    String insertStatement =
                            "INSERT INTO gcustomer(customer_id, customer_name, phone_number, byear, bmonth, bday, email, saved_money)" +
                                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; // 문자열을 이을 때에는 공백 필수입, 위에 넣든 아래 넣든 둘 다 넣든 ㄱㅊ
                    psmtUpdate = conn.prepareStatement(insertStatement);
                    psmtUpdate.setString(1, customerId);
                    psmtUpdate.setString(2, customerName);
                    psmtUpdate.setString(3, phoneNumber);
                    psmtUpdate.setInt(4, year);
                    psmtUpdate.setInt(5, month);
                    psmtUpdate.setInt(6, day);
                    psmtUpdate.setString(7, phoneNumber);
                    psmtUpdate.setInt(8, savedMoney);


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

        public static int update(String customerId, String customerName, String phoneNumber, String email, int savedMoney) {

            ResultSet rs = null;
            PreparedStatement psmtQuery = null;
            PreparedStatement psmtUpdate = null;

            try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
                String query = "SELECT * FROM gcustomer WHERE customer_id = ?";
                psmtQuery = conn.prepareStatement(query);
                psmtQuery.setString(1, customerId);
                rs = psmtQuery.executeQuery();
                if (rs.next()) { //해당 고객이 있을 때만(수정할 수 있을 때만) 진행
                    String updateStatement =
                            "UPDATE gcustomer\n" +
                                    " SET customer_name = ?, phone_number = ?, email = ?, saved_money =?" +
                                    " WHERE customer_id =?"; // 문자열을 이을 때에는 공백 필수입, 위에 넣든 아래 넣든 둘 다 넣든 ㄱㅊ
                    psmtUpdate = conn.prepareStatement(updateStatement);
                    psmtUpdate.setString(5, customerId);
                    psmtUpdate.setString(1, customerName);
                    psmtUpdate.setString(2, phoneNumber);
                    psmtUpdate.setString(3, email);
                    psmtUpdate.setInt(4, savedMoney);

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

        public static int deleteById(String customerId) {
            ResultSet rs = null;
            PreparedStatement psmtQuery = null;
            PreparedStatement psmtUpdate = null;

            try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
                String query = "SELECT * FROM gcustomer WHERE customer_id = ?";
                psmtQuery = conn.prepareStatement(query);
                psmtQuery.setString(1, customerId);
                rs = psmtQuery.executeQuery();
                if (rs.next()) { //해당 고객이 있을 때만(수정할 수 있을 때만) 진행
                    String deleteStatement =
                            "Delete FROM gcustomer Where customer_iD = ?";
                    psmtUpdate = conn.prepareStatement(deleteStatement);
                    psmtUpdate.setString(1, customerId);

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


