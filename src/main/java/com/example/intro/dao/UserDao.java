package com.example.intro.dao;


import com.example.intro.domain.User;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDao {
    private static final Connection connection;
    private static UserDao instance;

    static {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/study_app",
                    "postgres",
                    "root"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private UserDao() {
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (var ps = connection.prepareStatement("select * from users")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(User
                        .builder()
                        .id(rs.getString("id"))
                        .username(rs.getString("username"))
                        .build());
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(String id) {
        try (var ps = connection.prepareStatement("select * from users where id = ?")) {
            ps.setObject(1, UUID.fromString(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return User.builder()
                        .id(rs.getString("id"))
                        .username(rs.getString("username"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Optional<User> findByUsername(String username) {
        User user = null;
        try (var ps = connection.prepareStatement("select * from users where username = ?")) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = User.builder()
                        .id(rs.getString("id"))
                        .username(rs.getString("username"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(user);
    }

    public boolean add(User user) {

        try (var ps = connection.prepareStatement("insert into users(username) values (?)")) {
            ps.setString(1, user.getUsername());
            return ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("This username already taken");
        }
    }

    public boolean edit(User user) {
        try (var ps = connection.prepareStatement("update users set username = ? where id == ?")) {
            ps.setObject(1, UUID.fromString(user.getId()));
            return ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("This username already taken");
        }
    }

    public boolean delete(String id) {
        try (var ps = connection.prepareStatement("delete from users where id = ?")) {
            ps.setObject(1, UUID.fromString(id));
            return ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("This username already taken");
        }
    }
}
