package top.zhacker.ms.auth.jwt.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.zhacker.ms.auth.jwt.controller.vm.LoginUser;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * DATE: 17/2/17 下午5:54 <br>
 * MAIL: zhanghecheng@terminus.io <br>
 * AUTHOR: zhanghecheng
 */
@RestController
@Slf4j
public class Tokens {

    @Value("${jwt.secret:secret}")
    private String jwtSecret;

    @RequestMapping(value = "/api/tokens", method = RequestMethod.POST)
    public String genToken(@RequestBody LoginUser loginUser){
        try {
            String token = JWT.create()
                    .withIssuer("bear")
                    .withClaim("name",loginUser.getUsername())
                    .withClaim("authorities", "ROLE_USER")
                    .withExpiresAt(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                    .sign(Algorithm.HMAC256(jwtSecret));
            return token;
        } catch (Exception e){
            log.error("{}", e);
            return null;
            //Invalid Signing configuration / Couldn't convert Claims.
        }
    }

    @RequestMapping(value = "/api/tokens/check", method = RequestMethod.GET)
    public Map<String, String> verfyToken(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtSecret))
                    .withIssuer("bear")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);

            Map<String,String> content = new HashMap<>();
            for(String key : jwt.getClaims().keySet()){
                content.put(key, jwt.getClaim(key).asString());
            }
            return content;

        } catch (Exception e){
            //Invalid signature/claims
            log.error("{}", e);
            return Collections.emptyMap();
        }
    }

}
