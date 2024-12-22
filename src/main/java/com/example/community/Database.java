package com.example.community;

import java.util.ArrayList;
import java.util.List;

/**
 * Database 클래스는 게시글 데이터를 관리합니다.
 * <p>
 * 이 클래스는 게시글 목록을 저장하고, 게시글을 추가하거나 로드하는 기능을 제공합니다.
 * </p>
 * 
 * @author Shin Suk Woo (tnrdn6712@naver.com)
 * @version 1.0
 * @since 1.0
 *
 * created 2024-11-19
 * lastModified 2024-12-23
 * @see <a href="https://chatgpt.com/">ChatGPT</a>
 * @see <a href="https://wrtn.ai/">wrtn</a>
 *
 * changelog
 * <ul>
 *     <li>2024-11-19 : 최초 생성 (Shin Suk Woo)</li>
 *     <li>2024-11-19 : 데이터베이스 생성</li>
 *     <li>2024-12-22 : 게시글 목록 관련 생성</li>
 * </ul>
 */
public class Database {
    private final List<Post> posts; // 게시글 목록

    /**
     * Database 객체를 생성합니다.
     * 생성 시 게시글을 파일에서 로드합니다.
     */
    public Database() {
        posts = new ArrayList<>();
        Post.loadPosts(posts); // 게시글 로드
    }

    /**
     * 게시글을 목록에 추가하고, 저장소에 저장합니다.
     *
     * @param post 추가할 게시글
     */
    public void addPost(Post post) {
        posts.add(post);
        Post.savePosts(posts); // 게시글 저장
    }

    /**
     * 게시글 목록을 파일에 저장합니다.
     *
     * @param posts 저장할 게시글 목록
     */
    public void savePosts(List<Post> posts) {
        Post.savePosts(posts); // 게시글과 댓글 저장
    }

    /**
     * 현재 저장된 게시글 목록을 반환합니다.
     *
     * @return 게시글 목록
     */
    public List<Post> getPosts() {
        return posts; // 게시글 목록 반환
    }
}
