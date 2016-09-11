package bzh.abr.config.security;

import bzh.abr.user.model.User;
import bzh.abr.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Value("${jwt.cookie}")
    private String cookie;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String token = jwtUtil.generateToken((User) authentication.getPrincipal());

        Cookie c = new Cookie(cookie, token);
        c.setHttpOnly(true);
//        TODO make it secure
//        c.setSecure(true);
        c.setPath("/");

        httpServletResponse.addCookie(c);
    }

}
