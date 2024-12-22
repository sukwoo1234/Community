package com.example.community;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Post {
    private static int idCounter = 1;
    private final int id;
    private final String title;
    private final String content;
    private final List<Comment> comments;

    public Post(String title, String content) {
        this.id = idCounter++;
        this.title = title;
        this.content = content;
        this.comments = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return title; // 제목만 보여줌
    }

    // 게시글 로드 메서드
    public static void loadPosts(List<Post> posts) {
        try (BufferedReader br = new BufferedReader(new FileReader("posts.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 3); // 제목과 내용을 ','로 구분
                if (parts.length >= 2) {
                    Post post = new Post(parts[0], parts[1]);
                    loadComments(post); // 댓글 로드
                    posts.add(post);
                }
            }
        } catch (IOException e) {
            // 파일이 없으면 무시
        }
    }

    // 게시글 저장 메서드
    public static void savePosts(List<Post> posts) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("posts.txt"))) {
            for (Post post : posts) {
                bw.write(post.getTitle() + "," + post.getContent());
                bw.newLine();
                saveComments(post); // 댓글 저장
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 댓글 저장 메서드
    private static void saveComments(Post post) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("comments_" + post.getId() + ".txt"))) {
            for (Comment comment : post.getComments()) {
                bw.write(comment.getText());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 댓글 로드 메서드
    private static void loadComments(Post post) {
        try (BufferedReader br = new BufferedReader(new FileReader("comments_" + post.getId() + ".txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                post.addComment(new Comment(line));
            }
        } catch (IOException e) {
            // 댓글 파일이 없으면 무시
        }
    }
}
