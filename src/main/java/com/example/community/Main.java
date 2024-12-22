package com.example.community;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static Database database = new Database(); // 데이터베이스 객체 생성
    private static User currentUser; // 현재 로그인한 사용자
    private static JFrame frame; // 메인 프레임
    private static JTextArea contentArea; // 게시글 내용 입력 영역

    public static void main(String[] args) {
        User.loadUsers(); // 사용자 로드
        frame = new JFrame("익명 커뮤니티");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // 로그인/회원가입 패널
        JPanel loginPanel = new JPanel();
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("로그인");
        JButton registerButton = new JButton("회원가입");

        // 로그인 패널 구성
        loginPanel.add(new JLabel("사용자명:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("비밀번호:"));
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);

        // 게시글 작성 패널
        JPanel postPanel = new JPanel();
        JTextField titleField = new JTextField(15); // 제목 입력 필드
        contentArea = new JTextArea(5, 20); // 내용 입력 필드
        JButton postButton = new JButton("게시글 작성");
        JButton viewPostsButton = new JButton("게시글 보기");
        JButton backToLoginButton = new JButton("돌아가기");

        // 게시글 작성 패널 구성
        postPanel.add(new JLabel("제목:"));
        postPanel.add(titleField);
        postPanel.add(new JLabel("내용:"));
        postPanel.add(new JScrollPane(contentArea)); // 스크롤 가능한 영역
        postPanel.add(postButton);
        postPanel.add(viewPostsButton);
        postPanel.add(backToLoginButton);

        // 게시글 보기 패널
        JPanel viewPanel = new JPanel();
        DefaultListModel<Post> postListModel = new DefaultListModel<>(); // 게시글 리스트 모델
        JList<Post> postList = new JList<>(postListModel); // 게시글 리스트
        JButton backToPostButton = new JButton("게시글 작성하기");
        JButton logoutButton = new JButton("로그아웃"); // 로그아웃 버튼 추가

        // 게시글 보기 패널 구성
        viewPanel.setLayout(new BorderLayout());
        viewPanel.add(new JScrollPane(postList), BorderLayout.CENTER); // 게시글 리스트를 중앙에 배치
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backToPostButton); // 돌아가기 버튼 추가
        buttonPanel.add(logoutButton); // 로그아웃 버튼 추가
        viewPanel.add(buttonPanel, BorderLayout.SOUTH);

        // 게시글 내용 표시 패널
        JPanel contentPanel = new JPanel();
        JTextArea postContentArea = new JTextArea(10, 40); // 읽기 전용 게시글 내용
        postContentArea.setEditable(false); // 수정 불가
        JButton goBackButton = new JButton("뒤로 가기"); // 뒤로 가기 버튼 추가
        contentPanel.add(new JScrollPane(postContentArea)); // 스크롤 가능한 영역
        contentPanel.add(goBackButton); // 뒤로 가기 버튼 추가

        // 댓글 입력 패널
        JPanel commentPanel = new JPanel();
        JTextField commentField = new JTextField(30); // 댓글 입력 필드
        JButton commentButton = new JButton("댓글 달기"); // 댓글 달기 버튼
        commentPanel.add(commentField);
        commentPanel.add(commentButton);

        // 댓글 보기 영역
        JTextArea commentArea = new JTextArea(5, 30); // 댓글 보기 영역
        commentArea.setEditable(false); // 수정 불가
        contentPanel.add(new JScrollPane(commentArea)); // 스크롤 가능한 영역

        // 이벤트 리스너
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (User.login(username, password)) {
                    currentUser = new User(username, password);
                    JOptionPane.showMessageDialog(frame, "로그인 성공");
                    // 로그인 후 게시글 목록 화면으로 전환
                    postListModel.clear(); // 리스트 초기화
                    for (Post post : database.getPosts()) {
                        postListModel.addElement(post); // 게시글 제목 추가
                    }
                    frame.setContentPane(viewPanel); // 게시글 보기 화면으로 전환
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
                database.addPost(post); // 게시글 추가
                titleField.setText("");
                contentArea.setText("");
                JOptionPane.showMessageDialog(frame, "게시글이 작성되었습니다.");
            }
        });

        viewPostsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postListModel.clear(); // 리스트 초기화
                for (Post post : database.getPosts()) {
                    postListModel.addElement(post); // 게시글 제목 추가
                }
                frame.setContentPane(viewPanel); // 게시글 보기 화면으로 전환
                frame.revalidate();
            }
        });

        backToPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(postPanel); // 게시글 작성 화면으로 전환
                frame.revalidate();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentUser = null; // 현재 사용자 로그아웃
                frame.setContentPane(loginPanel); // 로그인 화면으로 전환
                frame.revalidate();
            }
        });

        postList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Post selectedPost = postList.getSelectedValue(); // 선택된 게시글
                if (selectedPost != null) {
                    postContentArea.setText("제목: " + selectedPost.getTitle() + "\n\n내용: " + selectedPost.getContent());
                    commentArea.setText(""); // 이전 댓글 초기화
                    for (Comment comment : selectedPost.getComments()) {
                        commentArea.append(comment.getText() + "\n"); // 댓글 추가
                    }
                    contentPanel.add(commentPanel); // 댓글 입력 패널 추가
                    frame.setContentPane(contentPanel); // 게시글 내용 화면으로 전환
                    frame.revalidate();
                }
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(viewPanel); // 게시글 보기 화면으로 돌아가기
                frame.revalidate();
            }
        });

        commentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Post selectedPost = postList.getSelectedValue(); // 선택된 게시글
                if (selectedPost != null) {
                    String commentText = commentField.getText();
                    if (!commentText.trim().isEmpty()) {
                        selectedPost.addComment(new Comment(commentText)); // 댓글 추가
                        commentField.setText(""); // 입력 필드 초기화
                        commentArea.append(commentText + "\n"); // 댓글 영역에 추가
                    } else {
                        JOptionPane.showMessageDialog(frame, "댓글 내용을 입력하세요."); // 빈 댓글 방지
                    }
                }
            }
        });

        // 초기 화면 설정
        frame.add(loginPanel);
        frame.setVisible(true);
    }
}
