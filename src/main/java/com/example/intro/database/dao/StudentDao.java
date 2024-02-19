package com.example.intro.database.dao;

import com.example.intro.database.domain.Student;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.intro.utils.PropertiesUtil.get;

public class StudentDao {
    private static final Connection connection;
    private static StudentDao instance;

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

    private StudentDao() {
    }

    public static StudentDao getInstance() {
        if (instance == null) {
            instance = new StudentDao();
        }
        return instance;
    }

    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        try (var ps = connection.prepareStatement("select * from student")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                students.add(
                        Student.builder()
                                .id(rs.getInt("id"))
                                .firstName(rs.getString("first_name"))
                                .lastName(rs.getString("last_name"))
                                .age(rs.getInt("age"))
                                .groupId(rs.getInt("group_id"))
                                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                                .build()
                );
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Student getOne(int id) {
        try (var ps = connection.prepareStatement("select * from student where id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Student.builder()
                        .id(rs.getInt("id"))
                        .firstName(rs.getString("first_name"))
                        .lastName(rs.getString("last_name"))
                        .age(rs.getInt("age"))
                        .groupId(rs.getInt("group_id"))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public int save(Student student) {
        try (var ps = connection.prepareStatement(
                "insert into student(first_name, last_name, age, group_id) VALUES (?, ?, ?, ?) returning id;")) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setInt(3, student.getAge());
            ps.setInt(4, student.getGroupId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public void update(Student student) {
        try (var ps = connection.prepareStatement(
                "update student set first_name = ?, last_name = ?, age = ?, group_id = ? where id = ?")) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setInt(3, student.getAge());
            ps.setInt(4, student.getGroupId());
            ps.setInt(5, student.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try (var ps = connection.prepareStatement("delete from student where id = ?")) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
