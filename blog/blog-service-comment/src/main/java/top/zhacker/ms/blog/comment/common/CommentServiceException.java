package top.zhacker.ms.blog.comment.common;

/**
 * DATE: 2017/4/10 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
public class CommentServiceException extends RuntimeException {

    private String message = "comment.service.exception";

    public CommentServiceException() {
    }

    public CommentServiceException(String message) {
        this.message = message;
    }


    public CommentServiceException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public CommentServiceException(Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
