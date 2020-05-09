package com.gagful.security;

import com.gagful.exception.TokenIsEmptyException;
import com.gagful.exception.TokenMalformedException;
import com.gagful.util.CommonUtils;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.gagful.constant.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(KEY_JWT_AUTH_HEADER);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(KEY_JWT_AUTH_HEADER);
        if (CommonUtils.isNotEmpty(token) && token.startsWith(TOKEN_PREFIX)) {
            try {
                String user = Jwts.parser()
                        .setSigningKey(KEY_JWT_SECRET.getBytes())
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();

                List<GrantedAuthority> authorities = ((List<?>) Jwts.parser()
                        .setSigningKey(KEY_JWT_SECRET.getBytes())
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
                        .get("rol")).stream()
                        .map(r -> {
                            return new SimpleGrantedAuthority(((Object) r).toString());
                        }).collect(Collectors.toList());


                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(user, null, authorities);
                }
                throw new TokenIsEmptyException("JWT claims string is empty.");
            } catch (SignatureException ex) {
                throw new TokenMalformedException("Invalid JWT signature");
            } catch (MalformedJwtException ex) {
                throw new TokenMalformedException("Invalid JWT token");
            } catch (ExpiredJwtException ex) {
                throw new TokenMalformedException("Expired JWT token");
            } catch (UnsupportedJwtException ex) {
                throw new TokenMalformedException("Unsupported JWT token");
            } catch (IllegalArgumentException ex) {
                throw new TokenIsEmptyException("JWT claims string is empty.");
            }
        }
        throw new TokenIsEmptyException("No token ıs found ın header.");
    }
}