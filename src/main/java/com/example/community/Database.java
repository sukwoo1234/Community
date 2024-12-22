package com.example.community;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private final List<Post> posts;

    public Database() {
        posts = new ArrayList<>();
        Post.loadPosts(posts); // 게시글 로드
    }

    public void addPost(Post post) {
        posts.add(post);
        Post.savePosts(posts); // 게시글 저장
    }
    public void savePosts(List<Post> posts) {
        Post.savePosts(posts); // 게시글과 댓글 저장
    }


    public List<Post> getPosts() {
        return posts;
    }

}
