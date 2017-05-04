package top.zhacker.ms.auth.oauth2.jwt.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

@Data
public class Client implements ClientDetails {

//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private Long id;

    private Long userId;

    private String clientId;

    private String clientSecret;

    private String clientName;

    private String clientUrl;

    private String clientLogoUrl;

    private Map<String, String> extra;

    private Set<String> scope;

    private Set<String> authorizedGrantTypes;

    private Set<String> registeredRedirectUri;

    private Set<String> resourceIds;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private Date createdAt;

    private Date updatedAt;

    @Override
    public Set<String> getResourceIds() {
        return resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return true; // TODO: only support secret for simple impl
    }

    @Override
    public boolean isScoped() {
        return true; // TODO: currently must follow scope
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        Map<String, Object> ad = new HashMap<>();
        ad.put("clientName", clientName);
        ad.put("clientUrl", clientUrl);
        ad.put("clientLogoUrl", clientLogoUrl);
        return Collections.unmodifiableMap(ad);
    }
}