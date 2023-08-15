package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String age;

    public User() {
    }

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }


    public static void main(String[] args) {
        List<User> users = readUsersFromFile("src/main/java/com/example/file1.txt");
        writeUsersToJsonFile(users, "src/main/java/com/example/user.json");
    }

    public static List<User> readUsersFromFile(String fileName) {
        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String headerLine = br.readLine(); // Пропускаем заголовок
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(" ");
                if (columns.length == 2) {
                    String name = columns[0];
                    String ageStr = columns[1]; // Прочитать возраст как строку
                    users.add(new User(name, ageStr));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static void writeUsersToJsonFile(List<User> users, String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            objectMapper.writeValue(fileWriter, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}