package top.zhacker.blog.web.component;

import com.github.jknack.handlebars.Options;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;

import java.io.IOException;
import java.util.Objects;

/**
 * DATE: 17/1/11 下午8:07 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 *
 * 定义HandleBars扩展方法
 */
@HandlebarsHelper
public class HandlebarsHelpers {

    /**
     * 判断用户是否为当前博客或评论的作者
     *
     * @param authorId 作者
     * @param userId 用户
     * @param options 其他信息
     * @return
     * @throws IOException
     */
    public CharSequence owner(Long authorId, Long userId, Options options) throws IOException {

        if (Objects.equals(authorId, userId))
            return options.fn();
        else
            return options.inverse();

    }
}
