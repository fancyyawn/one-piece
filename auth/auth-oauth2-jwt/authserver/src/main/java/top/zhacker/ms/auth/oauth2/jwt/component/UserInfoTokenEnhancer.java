package top.zhacker.ms.auth.oauth2.jwt.component;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class UserInfoTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(accessToken);
        OAuth2Request auth2Request = authentication.getOAuth2Request();
        if (auth2Request.getScope().contains("openid") && !authentication.isClientOnly()) {
            String username = authentication.getName();
            Map<String, Object> additionalInfo = new HashMap<>();
            additionalInfo.put("username", username);
            token.setAdditionalInformation(additionalInfo);
        }
        return token;
    }
}