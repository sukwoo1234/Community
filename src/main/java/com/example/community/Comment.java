package com.example.community;

/**
 * Comment 클래스는 게시글에 대한 댓글을 나타냅니다.
 * 이 클래스는 댓글의 내용을 저장하고 반환하는 기능을 제공합니다.
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
 *     <li>2024-12-22 : comment 객체 생성</li>
 * </ul>
 */
public class Comment {
    private final String text; // 댓글 내용

    /**
     * Comment 객체를 생성합니다.
     *
     * @param text 댓글 내용
     */
    public Comment(String text) {
        this.text = text; // 댓글 내용 초기화
    }

    /**
     * 댓글의 내용을 반환합니다.
     *
     * @return 댓글 내용
     */
    public String getText() {
        return text; // 댓글 내용 반환
    }

    /**
     * 댓글 내용을 문자열로 반환합니다.
     *
     * @return 댓글 내용
     */
    @Override
    public String toString() {
        return text; // 댓글 내용 반환
    }
}
