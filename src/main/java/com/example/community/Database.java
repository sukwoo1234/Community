package com.example.community;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Post> posts;

    public Database() {
        posts = new ArrayList<>();
        Post.loadPosts(posts); // 게시글 로드
    }

    public void addPost(Post post) {
        posts.add(post);
        Post.savePosts(posts); // 게시글 저장
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Post getPostById(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null; // 게시글이 없음
    }

    public String searchPosts(String keyword) {
        StringBuilder result = new StringBuilder();
        for (Post post : posts) {
            if (post.getTitle().contains(keyword) || post.getContent().contains(keyword)) {
                result.append(post.toString()).append("\n");
            }
        }
        return result.length() > 0 ? result.toString() : "검색 결과가 없습니다.";
    }
}
