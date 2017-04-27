package top.zhacker.ms.blog.post.common;

/**
 * DATE: 2017/4/10 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
public class PostServiceException extends RuntimeException {

    private String message = "post.service.exception";

    public PostServiceException() {
    }

    public PostServiceException(String message) {
        this.message = message;
    }


    public PostServiceException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public PostServiceException(Throwable cause) {
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
