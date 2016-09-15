package bzh.abr.config.security;

import bzh.abr.user.model.AuthenticationImpl;
import bzh.abr.user.model.User;
import bzh.abr.user.repository.UserRepository;
import bzh.abr.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class AuthenticationFilter extends GenericFilterBean {

    @Value("${jwt.cookie}")
    private String cookie;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof  HttpServletRequest) {
            Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();

            Authentication authentication = null;

            if (cookies != null) {
                Optional<String> token = Arrays.asList(cookies)
                        .stream()
                        .filter(c -> c.getName().equals(cookie))
                        .map(Cookie::getValue)
                        .findFirst();

                if (token.isPresent() && !jwtUtil.isExpired(token.get())) {
                    Optional<User> user = userRepository.findByUsername(jwtUtil.getUsername(token.get()));
                    if (user.isPresent()) {
                        authentication = new AuthenticationImpl(user.get());
                    }
                }
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
        SecurityContextHolder.getContext().setAuthentication(null);
    }

}
