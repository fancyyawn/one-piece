package top.zhacker.ms.auth.oauth2.jwt.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DATE: 17/2/15 下午12:46 <br>
 * MAIL: zhanghecheng@terminus.io <br>
 * AUTHOR: zhanghecheng
 */
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    Map<String, BaseClientDetails> clientMap = new ConcurrentHashMap<>();
    {
        BaseClientDetails details = new BaseClientDetails();
        details.setClientId("acme");
        details.setClientSecret("acmesecret");
        details.setAuthorizedGrantTypes(Arrays.asList("implicit","authorization_code", "refresh_token", "password"));
        details.setScope(Arrays.asList("openid"));
        details.setAuthorities(Arrays.asList(
                new SimpleGrantedAuthority("FOO_READ"),
                new SimpleGrantedAuthority("FOO_WRITE")));
        clientMap.put("acme", details);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientMap.get(clientId);
    }
}
