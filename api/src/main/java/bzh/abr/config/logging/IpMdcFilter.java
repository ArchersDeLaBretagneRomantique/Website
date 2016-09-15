package bzh.abr.config.logging;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class IpMdcFilter implements Filter {

    private static final String IP = "ip";

    private static final String FORWARDED_FOR_HEADER = "X-Forwarded-For";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            MDC.put(IP, getIp(request));
            chain.doFilter(request, response);
        } finally {
            MDC.remove(IP);
        }
    }

    private String getIp(ServletRequest request) {
        if (request instanceof HttpServletRequest) {
            String forwarded = ((HttpServletRequest) request).getHeader(FORWARDED_FOR_HEADER);
            if (forwarded != null) {
                return forwarded;
            }
        }
        return request.getRemoteHost();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

}
