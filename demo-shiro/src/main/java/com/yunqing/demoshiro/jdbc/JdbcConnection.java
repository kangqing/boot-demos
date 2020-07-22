package com.yunqing.demoshiro.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description jdbc连接mysql执行sql
 * @Author yx
 * @Data 2020/7/22 17:18
 */
@Slf4j
public class JdbcConnection {
    /**
     * 加载mysql驱动
     */
    public JdbcConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接数据库
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3307/shiro?characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                "root", "5678");
    }

    /**
     * 通过用户名查询密码
     * @param username
     * @return
     */
    public String getPasswordByUsername(String username) {
        String sql = "select `password` from user where `name` = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据用户名获取角色列表
     * @param username
     * @return
     */
    public Set<String> getRoleList(String username) {
        String sql = "select r.name from user u " +
                "left join user_role ur on u.id = ur.uid " +
                "left join role r on r.id = ur.rid " +
                "where u.name = ?";
        return getStrings(username, sql);
    }

    /**
     * 根据用户名查权限列表
     * @param username
     * @return
     */
    public Set<String> getPermitList(String username) {
        String sql = "select p.name from user u "+
                "left join user_role ru on u.id = ru.uid "+
                "left join role r on r.id = ru.rid "+
                "left join role_permission rp on r.id = rp.rid "+
                "left join permission p on p.id = rp.pid "+
                "where u.name =?";
        return getStrings(username, sql);
    }

    /**
     * 抽出公用的方法
     * @param username
     * @param sql
     * @return
     */
    private Set<String> getStrings(String username, String sql) {
        Set<String> sets = new HashSet<>();
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sets.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sets;
    }

    public static void main(String[] args) {
        log.info("zhang3拥有的角色包括[{}]", new JdbcConnection().getRoleList("zhang3"));
        log.info("zhang3拥有的权限包括[{}]", new JdbcConnection().getPermitList("zhang3"));
        log.info("li4拥有的角色包括[{}]", new JdbcConnection().getRoleList("li4"));
        log.info("li4拥有的权限包括[{}]", new JdbcConnection().getPermitList("li4"));
    }
}
