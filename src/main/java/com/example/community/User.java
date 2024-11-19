package com.example.community;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private String password; // 실제로는 해시된 비밀번호
    private static Map<String, String> users = new HashMap<>(); // 사용자 저장소

    public User(String username, String password) {
        this.username = username;
        this.password = password; // 실제로는 해시해야 함
    }

    public static boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false; // 이미 존재하는 사용자
        }
        users.put(username, password); // 비밀번호는 해시하여 저장해야 함
        return true;
    }

    public static boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public String getUsername() {
        return username;
    }
}
