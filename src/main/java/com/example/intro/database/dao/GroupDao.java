package com.example.intro.database.dao;

import com.example.intro.database.domain.Group;
import org.postgresql.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.intro.utils.PropertiesUtil.get;

public class GroupDao {

    private static final Connection connection;
    private static GroupDao instance;

    static {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    get("url"),
                    get("username"),
                    get("password")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private GroupDao() {
    }

    public static GroupDao getInstance() {
        if (instance == null) {
            instance = new GroupDao();
        }
        return instance;
    }

    public List<Group> getAll() {
        List<Group> groups = new ArrayList<>();
        try (var preparedStatement = connection.prepareStatement("select * from student_group")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                groups.add(
                        Group.builder()
                                .id(rs.getInt("id"))
                                .name(rs.getString("name"))
                                .studentsCount(rs.getInt("student_count"))
                                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                                .build()
                );
            }
            return groups;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Group getOne(int id) {
        try (var preparedStatement = connection.prepareStatement("select * from student_group where id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Group.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .studentsCount(rs.getInt("student_count"))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public int save(Group group) {
        try (var ps = connection.prepareStatement(
                "insert into student_group(name, student_count) VALUES (?, ?) returning id")) {
            ps.setString(1, group.getName());
            ps.setInt(2, group.getStudentsCount());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public boolean update(Group group) {
        try (var ps = connection.prepareStatement(
                "update student_group set name = ?, student_count = ? where id = ?")) {
            ps.setString(1, group.getName());
            ps.setInt(2, group.getStudentsCount());
            ps.setInt(3, group.getId());
            return ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        try (var ps = connection.prepareStatement("delete from student_group where id = ?")) {
            ps.setInt(1, id);
            return ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
