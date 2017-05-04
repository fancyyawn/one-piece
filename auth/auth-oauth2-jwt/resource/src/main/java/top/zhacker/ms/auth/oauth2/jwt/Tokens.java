package top.zhacker.ms.auth.oauth2.jwt;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DATE: 2017/5/4 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@Slf4j
@RestController
public class Tokens {

    private TokenExtractor extractor = new BearerTokenExtractor();

    @RequestMapping("/token")
    public String token(HttpServletRequest request){

        Authentication authentication = extractor.extract(request);
        log.info("{}",authentication);
        String content = JwtHelper.decode((String)authentication.getPrincipal()).getClaims();
        log.info("{}",content);
        return content;
    }

}
