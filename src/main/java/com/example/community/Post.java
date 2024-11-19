package com.example.community;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private static int idCounter = 1;
    private int id;
    private String title;
    private String content;
    private List<Comment> comments;

    public Post(String title, String content) {
        this.id = idCounter++;
        this.title = title;
        this.content = sanitizeContent(content); // XSS 방지 대체 처리
        this.comments = new ArrayList<>();
    }

    // XSS 방지를 위한 간단한 콘텐츠 정리 메서드
    private String sanitizeContent(String content) {
        return content.replaceAll("<", "&lt;").replaceAll(">", "&gt;"); // HTML 태그 변환
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
        return "ID: " + id + ", 제목: " + title + "\n내용: " + content + "\n";
    }
}
