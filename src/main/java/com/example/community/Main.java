package com.example.community;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static Database database = new Database();
    private static User currentUser;
    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("익명 커뮤니티");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // 로그인/회원가입 패널
        JPanel loginPanel = new JPanel();
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("로그인");
        JButton registerButton = new JButton("회원가입");

        loginPanel.add(new JLabel("사용자명:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("비밀번호:"));
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);

        // 게시글 작성 패널
        JPanel postPanel = new JPanel();
        JTextField titleField = new JTextField(15);
        JTextArea contentArea = new JTextArea(5, 20);
        JButton postButton = new JButton("게시글 작성");
        JButton viewPostsButton = new JButton("게시글 보기");
        JButton backToLoginButton = new JButton("돌아가기");

        postPanel.add(new JLabel("제목:"));
        postPanel.add(titleField);
        postPanel.add(new JLabel("내용:"));
        postPanel.add(new JScrollPane(contentArea));
        postPanel.add(postButton);
        postPanel.add(viewPostsButton);
        postPanel.add(backToLoginButton);

        // 게시글 보기 패널
        JPanel viewPanel = new JPanel();
        DefaultListModel<Post> postListModel = new DefaultListModel<>();
        JList<Post> postList = new JList<>(postListModel);
        JButton backToPostButton = new JButton("게시글 작성하기");
        JButton viewPostButton = new JButton("게시글 보기");

        viewPanel.setLayout(new BorderLayout());
        viewPanel.add(new JScrollPane(postList), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backToPostButton);
        viewPanel.add(buttonPanel, BorderLayout.SOUTH);

        // 댓글 달기 패널
        JPanel commentPanel = new JPanel();
        JTextArea commentArea = new JTextArea(3, 40);
        JButton commentButton = new JButton("댓글 달기");
        JTextField postIdField = new JTextField(5);

        commentPanel.add(new JLabel("게시글 ID:"));
        commentPanel.add(postIdField);
        commentPanel.add(new JLabel("댓글:"));
        commentPanel.add(new JScrollPane(commentArea));
        commentPanel.add(commentButton);

        // 이벤트 리스너
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (User.login(username, password)) {
                    currentUser = new User(username, password);
                    JOptionPane.showMessageDialog(frame, "로그인 성공");
                    frame.setContentPane(postPanel);
                    frame.revalidate();
                } else {
                    JOptionPane.showMessageDialog(frame, "로그인 실패");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (User.register(username, password)) {
                    JOptionPane.showMessageDialog(frame, "회원가입 성공");
                } else {
                    JOptionPane.showMessageDialog(frame, "이미 존재하는 사용자");
                }
            }
        });

        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String content = contentArea.getText();
                Post post = new Post(title, content);
                database.addPost(post);
                titleField.setText("");
                contentArea.setText("");
                JOptionPane.showMessageDialog(frame, "게시글이 작성되었습니다.");
            }
        });

        viewPostsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postListModel.clear();
                for (Post post : database.getPosts()) {
                    postListModel.addElement(post);
                }
                frame.setContentPane(viewPanel);
                frame.revalidate();
            }
        });

        backToPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(postPanel);
                frame.revalidate();
            }
        });

        postList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Post selectedPost = postList.getSelectedValue();
                if (selectedPost != null) {
                    JOptionPane.showMessageDialog(frame,
                            "제목: " + selectedPost.getTitle() + "\n내용: " + selectedPost.getContent());
                }
            }
        });

        commentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int postId = Integer.parseInt(postIdField.getText());
                String commentText = commentArea.getText();
                Post post = database.getPostById(postId);
                if (post != null) {
                    post.addComment(new Comment(commentText));
                    JOptionPane.showMessageDialog(frame, "댓글이 달렸습니다.");
                    commentArea.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "유효하지 않은 게시글 ID입니다.");
                }
            }
        });

        // 초기 화면 설정
        frame.add(loginPanel);
        frame.setVisible(true);
    }
}