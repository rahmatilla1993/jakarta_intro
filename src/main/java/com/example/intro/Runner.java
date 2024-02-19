package com.example.intro;

import com.example.intro.database.dao.GroupDao;
import com.example.intro.database.dao.StudentDao;
import com.example.intro.database.domain.Group;
import com.example.intro.database.domain.Student;

public class Runner {
    public static void main(String[] args) {
//        groupCrud();
        StudentDao studentDao = StudentDao.getInstance();
        Student student = studentDao.getOne(3);
        student.setAge(30);
        student.setGroupId(3);
        studentDao.update(student);
//        Student student = Student.builder()
//                .firstName("Alan")
//                .lastName("Turing")
//                .age(25)
//                .groupId(2)
//                .build();
//        System.out.println(studentDao.save(student));
        studentDao.getAll().forEach(System.out::println);
    }

    private static void groupCrud() {
        GroupDao groupDao = GroupDao.getInstance();
        Group group = Group.builder()
                .name("IT")
                .studentsCount(50)
                .build();
        System.out.println(groupDao.save(group));
        groupDao.getAll().forEach(System.out::println);
    }
}
