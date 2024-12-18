package io.mobile.project.game;

import io.mobile.conf.Conf;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class gameService {
    private static game setGame(ResultSet rs) throws SQLException {
            int game_number = rs.getInt("game_number"); // 컬럼 인덱스는 권장하지 않음, 속성 이름으로 쓰는 것을 권장.
            String game_name = rs.getString("game_name");
            String review = rs.getString("review");
            String developer_name = rs.getString("developer_name");
            int age_limit = rs.getInt("age_limit");
            Date upload_date = rs.getDate("upload_date");

            return new game(game_number, game_name, review, developer_name, age_limit, upload_date);
        }


        public static List<game> selectAll() {
            ResultSet rs = null; // 초기값이 null
            PreparedStatement psmtQuery = null; // 초기 값이 null
            List<game> gameList = new ArrayList<>();
            try (
                    Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {

                // 전체 고객 정보 검색문
                String query = "SELECT * FROM game G, developer D " +
                        " WHERE G.developer_name = D.developer_name";
                // vip 고객에 대한 정보를 검색해서 출력하시오


                // Statement를 가져온다.
                psmtQuery = conn.prepareStatement(query);

                // SQL문을 실행한다.
                // rs : 검색 결과 집합
                rs = psmtQuery.executeQuery();
                while (rs.next()) { // 결과 집합으로부터 각 레코드(튜블을) 가져온다
                    // games의 각속성 get

                    game games = setGame(rs);
                    gameList.add(games);

                }
                return gameList;
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

        public static game selectById(final int game_number) {
            ResultSet rs = null; // 초기값이 null
            PreparedStatement psmtQuery = null; // 초기 값이 null
            try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
                String query = "SELECT G.*, developer_name FROM  game G, developer D "+" WHERE game_number =? and G.developer_name = D.developer_name";
                psmtQuery = conn.prepareStatement(query);
                psmtQuery.setInt(1, game_number);
                rs = psmtQuery.executeQuery();
                if (rs.next()) {
                    game games = setGame(rs);
                    return games;
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

        public static int insert(int game_number, String game_name, String review, String developer_name, int age_limit, Date upload_date) {
            ResultSet rs = null; // 초기값이 null
            PreparedStatement psmtQuery = null;
            PreparedStatement psmtUpdate = null; // 초기 값이 null

            try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
                String query = "SELECT G.*,developer_name FROM game G, developer D "+ " WHERE game_number =? and G.developer_name = D.developer_name";
                psmtQuery = conn.prepareStatement(query);
                psmtQuery.setInt(1, game_number);
                rs = psmtQuery.executeQuery();
                if (!rs.next()) {
                    String insertStatment = "INSERT INTO game(game_number,game_name,review,developer_name,age_limit,upload_date) " +
                            "Values(?,?,?,?,?,?)";
                    psmtUpdate = conn.prepareStatement(insertStatment);
                    psmtUpdate.setInt(1, game_number);
                    psmtUpdate.setString(2, game_name);
                    psmtUpdate.setString(3, review);
                    psmtUpdate.setString(4, developer_name);
                    psmtUpdate.setInt(5, age_limit);
                    psmtUpdate.setDate(6, upload_date);

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

        public static int update(int game_number, String review, int age_limit ) {
            ResultSet rs = null; // 초기값이 null
            PreparedStatement psmtQuery = null;
            PreparedStatement psmtUpdate = null; // 초기 값이 null

            try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
                String query = "SELECT G.* , developer_name FROM game G, developer D "+ " WHERE game_number =? and G.developer_name = P.developer_name";
                psmtQuery = conn.prepareStatement(query);
                psmtQuery.setInt(1, game_number);
                rs = psmtQuery.executeQuery();
                if (rs.next()) {
                    String updateStatement = "UPDATE game "+
                            "SET review=?, age_limit =?"+
                            "WHERE game_number = ?";
                    psmtUpdate = conn.prepareStatement(updateStatement);
                    //psmtUpdate.setString(1, customerId);
                    //psmtUpdate.setString(2, customer_name);
                    psmtUpdate.setString(1, review);
                    psmtUpdate.setInt(2, age_limit);
                    psmtUpdate.setInt(3, game_number);


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

        public static int delete(int game_number) {
            ResultSet rs = null; // 초기값이 null
            PreparedStatement psmtQuery = null;
            PreparedStatement psmtUpdate = null; // 초기 값이 null

            try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
                String query = "SELECT G.*,developer_name " + " FROM game G, developer D WHERE game_number =? and G.developer_name = D.developer_name";
                psmtQuery = conn.prepareStatement(query);
                psmtQuery.setInt(1, game_number);
                rs = psmtQuery.executeQuery();
                if (rs.next()) {
                    String deleteStatement = "Delete from game WHERE game_number = ?";
                    psmtUpdate = conn.prepareStatement(deleteStatement);
                    psmtUpdate.setInt(1, game_number);

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






