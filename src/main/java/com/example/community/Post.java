package com.example.community;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Post 클래스는 게시글을 나타내며, 게시글의 제목, 내용 및 댓글을 관리합니다.
 * <p>
 * 게시글은 파일에 저장되고 로드될 수 있으며, 각 게시글에는 여러 댓글이 추가될 수 있습니다.
 * </p>
 * 
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
 *     <li>2024-11-19 : 게시글 관련 생성</li>
 * </ul>
 */
public class Post {
    private static int idCounter = 1; // 게시글 ID 카운터
    private final int id; // 게시글 ID
    private final String title; // 게시글 제목
    private final String content; // 게시글 내용
    private final List<Comment> comments; // 게시글에 달린 댓글 목록

    /**
     * <p>
     * 게시글 객체를 생성합니다.
     * </p>
     * @param title   게시글 제목
     * @param content 게시글 내용
     * created 2024-11-19
     * lastModified 2024-12-22
     * @see <a href="https://chatgpt.com/">ChatGPT</a>
     * @see <a href="https://wrtn.ai/">wrtn</a>
     *
     * changelog
     * <ul>
     *     <li>2024-11-19 : 최초 생성 (Shin Suk Woo)</li>
     *     <li>2024-11-19 : 게시글 관련 구현</li>
     *     <li>2024-11-21 : 게시글 ID 반환 구현</li>
     *     <li>2024-11-21 : 게시글 제목 반환 구현</li>
     *     <li>2024-11-21 : 게시글 내용 반환 구현</li>
     *     <li>2024-11-21 : 게시글 댓글 반환 구현</li>
     *     <li>2024-11-21 : 게시글 댓글목록 반환 구현</li>
     *     <li>2024-11-21 : 게시글 제목을 문자열로 반환 구현</li>
     * </ul>
     */
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

    /**
     * 파일에서 게시글을 로드합니다.
     *
     * @param posts 게시글을 저장할 리스트
     * created 2024-11-19
     * lastModified 2024-12-22
     * @see <a href="https://chatgpt.com/">ChatGPT</a>
     * @see <a href="https://wrtn.ai/">wrtn</a>
     *
     * changelog
     * <ul>
     *     <li>2024-11-19 : 최초 생성 (Shin Suk Woo)</li>
     *     <li>2024-11-19 : 파일에서 게시글 로드 구현</li>
     *     <li>2024-11-19 : 게시글 목록 파일 저장 구현</li>
     *     <li>2024-11-21 : 게시글 댓글 파일에 저장 구현</li>
     *     <li>2024-11-21 : 게시글 댓글 파일에서 로드 구현</li>
     * </ul>
     */
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
