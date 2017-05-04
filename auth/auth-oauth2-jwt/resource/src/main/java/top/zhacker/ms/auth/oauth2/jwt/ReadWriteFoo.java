package top.zhacker.ms.auth.oauth2.jwt;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

/**
 * Created by on 01.02.16.
 *
 * @author David Steiman
 */
@RestController
@RequestMapping("/foo")
public class ReadWriteFoo {

    @PreAuthorize("hasAuthority('FOO_READ')")
    @RequestMapping(method = RequestMethod.GET)
    public Map<String, String> readFoo() {
        return Collections.singletonMap("data", "read foo " + UUID.randomUUID().toString());
    }

    @PreAuthorize("hasAuthority('FOO_WRITE')")
    @RequestMapping(method = RequestMethod.POST)
    public String writeFoo() {
        return "write foo " + UUID.randomUUID().toString();
    }
}
