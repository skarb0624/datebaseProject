package io.mobile.project.genre;

import io.mobile.conf.Conf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class genreService {
    private static genre setGenre(ResultSet rs) throws SQLException {
            int game_number = rs.getInt("game_number"); // 컬럼 인덱스는 권장하지 않음, 속성 이름으로 쓰는 것을 권장.
            String genre = rs.getString("genre");
            return new genre(game_number, genre);
        }


        public static List<genre> selectAll() {
            ResultSet rs = null; // 초기값이 null
            PreparedStatement psmtQuery = null; // 초기 값이 null
            List<genre> genreList = new ArrayList<>();
            try (
                    Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {

                // 전체 고객 정보 검색문
                String query = "SELECT E.*,game_number FROM game G, genre E " +
                        " WHERE G.game_number = E.game_number";
                // vip 고객에 대한 정보를 검색해서 출력하시오


                // Statement를 가져온다.
                psmtQuery = conn.prepareStatement(query);

                // SQL문을 실행한다.
                // rs : 검색 결과 집합
                rs = psmtQuery.executeQuery();
                while (rs.next()) { // 결과 집합으로부터 각 레코드(튜블을) 가져온다
                    // genres의 각속성 get

                    genre genres = setGenre(rs);
                    genreList.add(genres);

                }
                return genreList;
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

        public static genre selectById(final int game_number) {
            ResultSet rs = null; // 초기값이 null
            PreparedStatement psmtQuery = null; // 초기 값이 null
            try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
                String query = "SELECT E.*, game_number FROM  game G, genre E "+" WHERE game_number =? and G.game_number = E.game_number";
                psmtQuery = conn.prepareStatement(query);
                psmtQuery.setInt(1, game_number);
                rs = psmtQuery.executeQuery();
                if (rs.next()) {
                    genre genres = setGenre(rs);
                    return genres;
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

        public static int insert(int game_number, String genre) {
            ResultSet rs = null; // 초기값이 null
            PreparedStatement psmtQuery = null;
            PreparedStatement psmtUpdate = null; // 초기 값이 null

            try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
                String query = "SELECT E.*,game_number FROM game G, genre E "+ " WHERE game_number =? and G.game_number = E.game_number";
                psmtQuery = conn.prepareStatement(query);
                psmtQuery.setInt(1, game_number);
                rs = psmtQuery.executeQuery();
                if (!rs.next()) {
                    String insertStatment = "INSERT INTO genre(game_number,genre) " +
                            "Values(?,?)";
                    psmtUpdate = conn.prepareStatement(insertStatment);
                    psmtUpdate.setInt(1, game_number);
                    psmtUpdate.setString(2, genre);

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

        public static int update(int game_number, String genre ) {
            ResultSet rs = null; // 초기값이 null
            PreparedStatement psmtQuery = null;
            PreparedStatement psmtUpdate = null; // 초기 값이 null

            try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
                String query = "SELECT E.*,game_number , genre FROM game G, genre E "+ " WHERE game_number =? and G.game_number = E.game_number";
                psmtQuery = conn.prepareStatement(query);
                psmtQuery.setInt(1, game_number);
                rs = psmtQuery.executeQuery();
                if (rs.next()) {
                    String updateStatement = "UPDATE genre "+
                            "genre =?"+
                            "WHERE game_number = ?";
                    psmtUpdate = conn.prepareStatement(updateStatement);
                    psmtUpdate.setString(1, genre);
                    psmtUpdate.setInt(2, game_number);


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
                String query = "SELECT E.*,game_number " + " FROM game G, genre E WHERE game_number =? and G.game_number = E.game_number";
                psmtQuery = conn.prepareStatement(query);
                psmtQuery.setInt(1, game_number);
                rs = psmtQuery.executeQuery();
                if (rs.next()) {
                    String deleteStatement = "Delete from genre WHERE game_number = ?";
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
