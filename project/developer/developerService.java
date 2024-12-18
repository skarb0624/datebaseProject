package io.mobile.project.developer;

import io.mobile.conf.Conf;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class developerService {
        private static developer setDeveloper(ResultSet rs) throws SQLException {
             // 컬럼 인덱스는 권장하지 않음, 속성 이름으로 쓰는 것을 권장.
            String developer_name = rs.getString("developer_name");
            String developer_email = rs.getString("developer_email");
            String phone_number = rs.getString("phone_number");

            return new developer(developer_name, developer_email, phone_number);
        }


        public static List<developer> selectAll() {
            ResultSet rs = null; // 초기값이 null
            PreparedStatement psmtQuery = null; // 초기 값이 null
            List<developer> developersList = new ArrayList<>();
            try (
                    Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {

                // 전체 고객 정보 검색문
                String query = "SELECT * FROM developer";
                // vip 고객에 대한 정보를 검색해서 출력하시오


                // Statement를 가져온다.
                psmtQuery = conn.prepareStatement(query);

                // SQL문을 실행한다.
                // rs : 검색 결과 집합
                rs = psmtQuery.executeQuery();
                while (rs.next()) { // 결과 집합으로부터 각 레코드(튜블을) 가져온다
                    // developer 각속성 get

                    developer developers = setDeveloper(rs);
                    developersList.add(developers);

                }
                return developersList;
                // 결과 집합의 내용을 출력한다.
                // 결과가 여러줄 일수 있으니 while(rs.next)
            } catch (
                    SQLException e) {
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

        public static developer selectById(final String developer_name) {
            ResultSet rs = null; // 초기값이 null
            PreparedStatement psmtQuery = null; // 초기 값이 null
            try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
                String query = "SELECT * FROM developer WHERE developer_name =?";
                psmtQuery = conn.prepareStatement(query);
                psmtQuery.setString(1, developer_name);
                rs = psmtQuery.executeQuery();
                if (rs.next()) {
                    developer developers = setDeveloper(rs);
                    return developers;
                } else {
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

        public static int insert(String developer_name, String developer_email, String phone_number) {
            ResultSet rs = null; // 초기값이 null
            PreparedStatement psmtQuery = null;
            PreparedStatement psmtUpdate = null; // 초기 값이 null

            try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
                String query = "SELECT * FROM developer WHERE developer_name =?";
                psmtQuery = conn.prepareStatement(query);
                psmtQuery.setString(1, developer_name);
                rs = psmtQuery.executeQuery();
                if (!rs.next()) {
                    String insertStatment = "INSERT INTO developer(developer_name,developer_email,phone_number) " +
                            "Values(?,?,?)";
                    psmtUpdate = conn.prepareStatement(insertStatment);
                    psmtUpdate.setString(1, developer_name);
                    psmtUpdate.setString(2, developer_email);
                    psmtUpdate.setString(3, phone_number);

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

        public static int update(String developer_name, String developer_email, String phone_number ) {
            ResultSet rs = null; // 초기값이 null
            PreparedStatement psmtQuery = null;
            PreparedStatement psmtUpdate = null; // 초기 값이 null

            try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
                String query = "SELECT * FROM developer WHERE developer_name =?";
                psmtQuery = conn.prepareStatement(query);
                psmtQuery.setString(1, developer_name);
                rs = psmtQuery.executeQuery();
                if (rs.next()) {
                    String updateStatement = "UPDATE developer "+
                            "SET developer_email =?, phone_number=? "+
                            "WHERE developer_name = ?";
                    psmtUpdate = conn.prepareStatement(updateStatement);
                    //psmtUpdate.setString(1, customerId);
                    //psmtUpdate.setString(2, customer_name);
                    psmtUpdate.setString(1, developer_email);
                    psmtUpdate.setString(2, phone_number);
                    psmtUpdate.setString(3, developer_name);


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

        public static int delete(String developer_name) {
            ResultSet rs = null; // 초기값이 null
            PreparedStatement psmtQuery = null;
            PreparedStatement psmtUpdate = null; // 초기 값이 null

            try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
                String query = "SELECT * FROM developer WHERE developer_name =?";
                psmtQuery = conn.prepareStatement(query);
                psmtQuery.setString(1, developer_name);
                rs = psmtQuery.executeQuery();
                if (rs.next()) {
                    String deleteStatement = "Delete from developer WHERE developer_name = ?";
                    psmtUpdate = conn.prepareStatement(deleteStatement);
                    psmtUpdate.setString(1, developer_name);

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
