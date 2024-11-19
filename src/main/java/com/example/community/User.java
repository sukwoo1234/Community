package com.example.community;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private String password; // 실제로는 해시된 비밀번호
    private static Map<String, String> users = new HashMap<>(); // 사용자 저장소
    private static final String USER_FILE = "users.txt";

    public User(String username, String password) {
        this.username = username;
        this.password = password; // 실제로는 해시해야 함
    }

    public static void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            // 파일이 없으면 무시
        }
    }

    public static void saveUsers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false; // 이미 존재하는 사용자
        }
        users.put(username, password); // 비밀번호는 해시하여 저장해야 함
        saveUsers(); // 사용자 저장
        return true;
    }

    public static boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public String getUsername() {
        return username;
    }
}
