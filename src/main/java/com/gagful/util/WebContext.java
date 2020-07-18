package com.gagful.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gagful.entity.User;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.gagful.constant.SecurityConstants.*;

public class WebContext {

    public static String getUser(HttpServletRequest request) {
        String token = request.getHeader(KEY_JWT_AUTH_HEADER);
        String user = Jwts.parser()
                .setSigningKey(KEY_JWT_SECRET.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        return user;
    }

    public static List<GrantedAuthority> getRoles(HttpServletRequest request) {
        String token = request.getHeader(KEY_JWT_AUTH_HEADER);
        return ((List<?>) Jwts.parser()
                .setSigningKey(KEY_JWT_SECRET.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
                .get("rol")).stream()
                .map(r -> {
                    return new SimpleGrantedAuthority(((Object) r).toString());
                }).collect(Collectors.toList());
    }

    public static User getUserObject(HttpServletRequest request) {
        try {
            return new ObjectMapper()
                    .readValue(request.getInputStream(), User.class);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
