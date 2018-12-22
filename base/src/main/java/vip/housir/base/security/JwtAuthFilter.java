package vip.housir.base.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import vip.housir.base.constant.Constant;
import vip.housir.base.utils.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author housirvip
 */
public class JwtAuthFilter extends BasicAuthenticationFilter {

    private final JwtUtils jwtUtils;

    public JwtAuthFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        super(authenticationManager);
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader(Constant.AUTHORIZATION);
        //以下情形直接放行
        if (request.getRequestURI().contains(Constant.PATH_AUTH) || token == null || !token.startsWith(Constant.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        DecodedJWT jwt = jwtUtils.decode(token.replace(Constant.TOKEN_PREFIX, ""));
        if (jwt == null) {
            return;
        }

        Integer uid = jwt.getClaim(Constant.UID).asInt();
        String[] role = jwt.getClaim(Constant.ROLE).asArray(String.class);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(uid, null, AuthorityUtils.createAuthorityList(role)));

        //放行
        chain.doFilter(request, response);
    }
}
