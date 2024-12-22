package com.example.community;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * User 클래스는 사용자 정보를 관리하고, 사용자 등록 및 로그인 기능을 제공합니다.
 * <p>
 * 사용자 정보는 메모리에 저장되며, 사용자의 정보는 파일에 영구적으로 저장됩니다.
 * </p>
 * @author Shin Suk Woo (tnrdn6712@naver.com)
 * @version 1.0
 * @since 1.0
 *
 * created 2024-11-19
 * lastModified 2024-11-19
 * @see <a href="https://chatgpt.com/">ChatGPT</a>
 * @see <a href="https://wrtn.ai/">wrtn</a>
 *
 * changelog
 * <ul>
 *     <li>2024-11-19 : 최초 생성 (Shin Suk Woo)</li>
 * </ul>
 */
public class User {
    private final String username; // 사용자명
    private final String password; // 실제로는 해시된 비밀번호
    private static final Map<String, String> users = new HashMap<>(); // 사용자 저장소
    private static final String USER_FILE = "users.txt"; // 사용자 정보를 저장할 파일 경로

    /**
     * User 객체를 생성합니다.
     *
     * @param username 사용자명
     * @param password 비밀번호 (해시화하여 저장해야 함)
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password; // 실제로는 해시해야 함
    }

    /**
     * 사용자 정보를 파일에서 로드합니다.
     * 파일이 존재하지 않으면 무시합니다.
     */
    public static void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]); // 사용자명과 비밀번호 저장
                }
            }
        } catch (IOException e) {
            // 파일이 없으면 무시
        }
    }

    /**
     * 사용자 정보를 파일에 저장합니다.
     */
    public static void saveUsers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue()); // 사용자명과 비밀번호 저장
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 새로운 사용자를 등록합니다.
     *
     * @param username 사용자명
     * @param password 비밀번호 (해시화하여 저장해야 함)
     * @return 등록 성공 여부
     */
    public static boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false; // 이미 존재하는 사용자
        }
        users.put(username, password); // 비밀번호는 해시하여 저장해야 함
        saveUsers(); // 사용자 저장
        return true; // 등록 성공
    }

    /**
     * 사용자가 로그인할 수 있는지 확인합니다.
     *
     * @param username 사용자명
     * @param password 비밀번호
     * @return 로그인 성공 여부
     */
    public static boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password); // 사용자명과 비밀번호 확인
    }

}
