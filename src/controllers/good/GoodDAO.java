package controllers.good;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Good;

/*
 * いいねに関するＤＡＯクラス
 */
public class GoodDAO {

     private final String url = "jdbc:mysql://localhost:8080/Good";
        private final String user = "repuser";
        private final String passWord = "reppass";

        /********************************************************************************
         * 「いいね」テーブルから記事IDを検索し、いいねを集計して検索結果を返します。
         ********************************************************************************/

        public ResultSet select(String report_id) {
            Connection con = null;
            PreparedStatement st = null;
            ResultSet rs = null;


            try {
                 /* JDBCドライバの定義 */
                 Class.forName("com.mysql.jdbc.Driver");

                 /* mySQLへの接続 */
                 con = DriverManager.getConnection(url, user, passWord);

                 /* SELECT文の準備 */
                 String sql = "select report_id, count(*) ";
                 sql += "from Good ";
                 sql += "group by report_id ";
                 sql += "order by count(*) DESC; ";
                 st = con.prepareStatement(sql);


                 /* SELECT文の実行 */
                 rs = st.executeQuery();


            } catch(Exception e) {
                System.out.println("DBアクセス時にエラーが発生しました。");
                e.printStackTrace();
            } finally {
                 /* PostgreSQLとの接続を切断 */
                if(rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {}
                }

                if(st != null) {
                    try {
                        st.close();
                    } catch (SQLException e) {}
                }

                if(con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {}
                }
            }

            return rs;
        }

        /********************************************************************************
         * 「いいね」テーブルにいいねを追加します
         ********************************************************************************/

        public int insert(Good g) {
            Connection con = null;
            PreparedStatement st = null;
            int rs = 0;//更新件数

            try {
                 /* JDBCドライバの定義 */
                 Class.forName("com.mysql.jdbc.Driver");

                 /* mySQLへの接続 */
                 con = DriverManager.getConnection(url, user, passWord);

                 /* INSERT文の準備 */
                 String sql = "";
                 sql = "INSERT INTO Good(employee_id, report_id) ";
                 sql += "VALUES(?, ?);";

                 st = con.prepareStatement(sql);
//                 st.setString(1, good.getEmployee_id());
//                 st.setString(2, good.getReport_id());

                 /* SELECT文の実行 */
                 rs = st.executeUpdate();

            } catch(Exception e) {
                System.out.println("DBアクセス時にエラーが発生しました。");
                e.printStackTrace();
            } finally {
                /* PostgreSQLとの接続を切断 */
                if(st != null) {
                    try {
                        st.close();
                    } catch (SQLException e) {}
                }

                if(con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {}
                }
            }


            return rs;
        }
}