package util;

import java.sql.*;

/**
 * @author 软工1801温蟾圆
 * @date 2020/06/11
 */
public class Connect {
    private Connection connect = null;

    private Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection(String dbName, String username, String password) {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName
                    + "?user=" + username + "&password=" + password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }

    /**
     * 执行查询语句
     *
     * @param statement  查询语句
     * @param parameters 查询语句中的参数
     * @return 返回查询结果
     */
    public static ResultSet executeSelect(String statement, String[] parameters) throws SQLException {
        return getPrepared(statement, parameters).executeQuery();
    }

    /**
     * 执行更新语句
     *
     * @param statement  更新语句
     * @param parameters 更新语句中的参数
     */
    public static void executeUpdate(String statement, String[] parameters) throws SQLException {
        getPrepared(statement, parameters).executeUpdate();
    }

    private static PreparedStatement getPrepared(String statement, String[] parameters) throws SQLException {
        Connect connect = new Connect();
        Connection connection = connect.getConnection("2018011135", "root", "zxcvbnm123");
        PreparedStatement sql = connection.prepareStatement(statement);
        for (int i = 0; i < parameters.length; i++) {
            sql.setString(i + 1, parameters[i]);
        }
        return sql;
    }
}
