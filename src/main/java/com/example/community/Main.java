package com.example.community;

import javax.swing.*;
import java.awt.*;

/**
 * *Community
 * Main 클래스는 익명 커뮤니티 애플리케이션의 메인 진입점입니다.
 * <p>
 * 이 애플리케이션은 사용자 로그인, 회원가입, 게시글 작성, 게시글 보기, 댓글 달기 등의 기능을 제공합니다.
 * <p>
 * 사용자 인터페이스(UI)는 Swing 라이브러리를 사용하여 구현되었습니다.
 * </p>
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
 *     <li>2024-12-22 : 데이터베이스 객체 생성</li>
 * </ul>
 */
public class Main {
    private static final Database database = new Database(); // 데이터베이스 객체 생성
    private static User currentUser; // 현재 로그인한 사용자
    private static JFrame frame; // 메인 프레임
    private static JTextArea contentArea; // 게시글 내용 입력 영역

    /**
     * 애플리케이션의 메인 메서드입니다.
     * <p>
     * 사용자 인터페이스를 설정하고 애플리케이션을 실행합니다.
     *</p>
     * @param args 명령줄 인수 (사용되지 않음)
     * created 2024-11-19
     * lastModified 2024-11-27
     * @see <a href="https://chatgpt.com/">ChatGPT</a>
     * @see <a href="https://wrtn.ai/">wrtn</a>
     *
     * changelog
     * <ul>
     *     <li>2024-11-19 : 최초 생성 (Shin Suk Woo)</li>
     *     <li>2024-11-19 : 로그인 패널 생성 및 구성</li>
     *     <li>2024-11-19 : 게시글 패널 생성 및 구성</li>
     *     <li>2024-11-21 : 댓글 패널 생성 및 구성</li>
     *     <li>2024-11-21 : 뒤로 가기 생성</li>
     *     <li>2024-11-27 : 로그 아웃 생성</li>
     *     <li>2024-11-27 : 데이터베이스 객체 생성</li>
     * </ul>
     */
    public static void main(String[] args) {
        User.loadUsers(); // 사용자 로드

        // 메인 프레임 설정
        frame = new JFrame("익명 커뮤니티");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // 로그인/회원가입 패널 생성 및 구성
        JPanel loginPanel = new JPanel();
        JTextField usernameField = new JTextField(15); // 사용자명 입력 필드
        JPasswordField passwordField = new JPasswordField(15); // 비밀번호 입력 필드
        JButton loginButton = new JButton("로그인"); // 로그인 버튼
        JButton registerButton = new JButton("회원가입"); // 회원가입 버튼

        // 로그인 패널에 컴포넌트 추가
        loginPanel.add(new JLabel("사용자명:")); // 사용자명 레이블
        loginPanel.add(usernameField); // 사용자명 입력 필드
        loginPanel.add(new JLabel("비밀번호:")); // 비밀번호 레이블
        loginPanel.add(passwordField); // 비밀번호 입력 필드
        loginPanel.add(loginButton); // 로그인 버튼
        loginPanel.add(registerButton); // 회원가입 버튼

        // 게시글 작성 패널 생성 및 구성
        JPanel postPanel = new JPanel();
        JTextField titleField = new JTextField(15); // 게시글 제목 입력 필드
        contentArea = new JTextArea(5, 20); // 게시글 내용 입력 필드
        JButton postButton = new JButton("게시글 작성"); // 게시글 작성 버튼
        JButton viewPostsButton = new JButton("게시글 보기"); // 게시글 보기 버튼
        JButton backToLoginButton = new JButton("돌아가기"); // 로그인 화면으로 돌아가기 버튼

        // 게시글 작성 패널에 컴포넌트 추가
        postPanel.add(new JLabel("제목:")); // 제목 레이블
        postPanel.add(titleField); // 제목 입력 필드
        postPanel.add(new JLabel("내용:")); // 내용 레이블
        postPanel.add(new JScrollPane(contentArea)); // 내용 입력 필드 (스크롤 가능)
        postPanel.add(postButton); // 게시글 작성 버튼
        postPanel.add(viewPostsButton); // 게시글 보기 버튼
        postPanel.add(backToLoginButton); // 로그인 화면으로 돌아가기 버튼

        // 게시글 보기 패널 생성 및 구성
        JPanel viewPanel = new JPanel();
        DefaultListModel<Post> postListModel = new DefaultListModel<>(); // 게시글 리스트 모델
        JList<Post> postList = new JList<>(postListModel); // 게시글 리스트
        JButton backToPostButton = new JButton("게시글 작성하기"); // 게시글 작성 화면으로 돌아가기 버튼
        JButton logoutButton = new JButton("로그아웃"); // 로그아웃 버튼

        // 게시글 보기 패널 구성
        viewPanel.setLayout(new BorderLayout());
        viewPanel.add(new JScrollPane(postList), BorderLayout.CENTER); // 게시글 리스트를 중앙에 배치
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backToPostButton); // 게시글 작성하기 버튼 추가
        buttonPanel.add(logoutButton); // 로그아웃 버튼 추가
        viewPanel.add(buttonPanel, BorderLayout.SOUTH); // 버튼 패널 추가

        // 게시글 내용 표시 패널 생성 및 구성
        JPanel contentPanel = new JPanel();
        JTextArea postContentArea = new JTextArea(10, 40); // 읽기 전용 게시글 내용
        postContentArea.setEditable(false); // 수정 불가
        JButton goBackButton = new JButton("뒤로 가기"); // 게시글 보기 화면으로 돌아가기 버튼
        contentPanel.add(new JScrollPane(postContentArea)); // 게시글 내용 영역 (스크롤 가능)
        contentPanel.add(goBackButton); // 뒤로 가기 버튼 추가

        // 댓글 입력 패널 생성 및 구성
        JPanel commentPanel = new JPanel();
        JTextField commentField = new JTextField(30); // 댓글 입력 필드
        JButton commentButton = new JButton("댓글 달기"); // 댓글 달기 버튼
        commentPanel.add(commentField); // 댓글 입력 필드 추가
        commentPanel.add(commentButton); // 댓글 달기 버튼 추가

        // 댓글 보기 영역 생성
        JTextArea commentArea = new JTextArea(5, 30); // 댓글 보기 영역
        commentArea.setEditable(false); // 수정 불가
        contentPanel.add(new JScrollPane(commentArea)); // 댓글 영역 (스크롤 가능)
/**
 * <p>
 * 애플리케이션의 이벤트 리스너입니다.
 *</p>
 * created 2024-11-19
 * lastModified 2024-12-22
 * @see <a href="https://chatgpt.com/">ChatGPT</a>
 * @see <a href="https://wrtn.ai/">wrtn</a>
 * @see 교과서
 *
 * changelog
 * <ul>
 *     <li>2024-11-19 : 최초 생성 (Shin Suk Woo)</li>
 *     <li>2024-11-19 : 로그인 리스너 구현</li>
 *     <li>2024-11-19 : 게시글 리스너 구현</li>
 *     <li>2024-11-21 : 댓글 리스너 구현</li>
 *     <li>2024-11-21 : 뒤로 가기 리스너 구현</li>
 *     <li>2024-11-27 : 로그 아웃 리스너 구현</li>
 *     <li>2024-11-27 : 뒤로가기 리스너 구현</li>
 *     <li>2024-11-27 : 댓글 달기 리스너 구현</li>
 *     <li>2024-11-27 : 댓글 저장 리스너 구현</li>
 * </ul>
 */
        // 로그인 버튼 클릭 이벤트 리스너
        loginButton.addActionListener(e -> {
            String username = usernameField.getText(); // 입력된 사용자명
            String password = new String(passwordField.getPassword()); // 입력된 비밀번호
            if (User.login(username, password)) {
                currentUser = new User(username, password); // 현재 사용자 설정
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
        });

        // 회원가입 버튼 클릭 이벤트 리스너
        registerButton.addActionListener(e -> {
            String username = usernameField.getText(); // 입력된 사용자명
            String password = new String(passwordField.getPassword()); // 입력된 비밀번호
            if (User.register(username, password)) {
                JOptionPane.showMessageDialog(frame, "회원가입 성공");
            } else {
                JOptionPane.showMessageDialog(frame, "이미 존재하는 사용자");
            }
        });

        // 게시글 작성 버튼 클릭 이벤트 리스너
        postButton.addActionListener(e -> {
            String title = titleField.getText(); // 입력된 게시글 제목
            String content = contentArea.getText(); // 입력된 게시글 내용
            Post post = new Post(title, content); // 새로운 게시글 객체 생성
            database.addPost(post); // 게시글 데이터베이스에 추가
            titleField.setText(""); // 제목 입력 필드 초기화
            contentArea.setText(""); // 내용 입력 필드 초기화
            JOptionPane.showMessageDialog(frame, "게시글이 작성되었습니다.");
        });

        // 게시글 보기 버튼 클릭 이벤트 리스너
        viewPostsButton.addActionListener(e -> {
            postListModel.clear(); // 리스트 초기화
            for (Post post : database.getPosts()) {
                postListModel.addElement(post); // 게시글 제목 추가
            }
            frame.setContentPane(viewPanel); // 게시글 보기 화면으로 전환
            frame.revalidate();
        });

        // 게시글 작성하기 버튼 클릭 이벤트 리스너
        backToPostButton.addActionListener(e -> {
            frame.setContentPane(postPanel); // 게시글 작성 화면으로 전환
            frame.revalidate();
        });

        // 로그아웃 버튼 클릭 이벤트 리스너
        logoutButton.addActionListener(e -> {
            currentUser = null; // 현재 사용자 로그아웃
            frame.setContentPane(loginPanel); // 로그인 화면으로 전환
            frame.revalidate();
        });

        // 게시글 리스트 선택 이벤트 리스너
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

        // 뒤로 가기 버튼 클릭 이벤트 리스너
        goBackButton.addAction
        // 뒤로 가기 버튼 클릭 이벤트 리스너
        goBackButton.addActionListener(e -> {
            frame.setContentPane(viewPanel); // 게시글 보기 화면으로 돌아가기
            frame.revalidate();
        });

        // 댓글 달기 버튼 클릭 이벤트 리스너
        commentButton.addActionListener(e -> {
            Post selectedPost = postList.getSelectedValue(); // 선택된 게시글
            if (selectedPost != null) {
                String commentText = commentField.getText(); // 입력된 댓글
                if (!commentText.trim().isEmpty()) {
                    selectedPost.addComment(new Comment(commentText)); // 댓글 추가
                    commentField.setText(""); // 입력 필드 초기화
                    commentArea.append(commentText + "\n"); // 댓글 영역에 추가
                    // 게시글 및 댓글 저장
                    database.savePosts(database.getPosts()); // 모든 게시글과 댓글 저장
                } else {
                    JOptionPane.showMessageDialog(frame, "댓글 내용을 입력하세요."); // 빈 댓글 방지
                }
            }
        });

        // 초기 화면 설정
        frame.add(loginPanel); // 로그인 패널을 프레임에 추가
        frame.setVisible(true); // 프레임을 보이도록 설정
    }
}
