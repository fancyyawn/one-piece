package top.zhacker.ms.auth.jwt.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.zhacker.ms.auth.jwt.common.UserUtil;
import top.zhacker.ms.auth.jwt.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * DATE: 17/2/17 下午8:42 <br>
 * MAIL: zhanghecheng@terminus.io <br>
 * AUTHOR: zhanghecheng
 */
@Slf4j
//@Component
public class BlogLoginInterceptor extends HandlerInterceptorAdapter {

    @Value("${jwt.secret: secret}")
    private String jwtSecret;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");
        if(authHeader==null){
            return false;
        }
        String token = authHeader.split(" ")[1];
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtSecret))
                    .withIssuer("bear")
                    .build(); //Reushable verifier instance
            DecodedJWT jwt = verifier.verify(token);

            Map<String,String> content = new HashMap<>();
            for(String key : jwt.getClaims().keySet()){
                content.put(key, jwt.getClaim(key).asString());
            }
            User user = new User();
            user.setName(content.get("name"));
            UserUtil.putCurrentUser(user);
        } catch (Exception e){
            log.error("{}", e);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
