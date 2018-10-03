package com.potapovich.project.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Setting the current encoding in UTF-8
 */
@WebFilter(urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param")})
public class EncodingFilter implements Filter {
        private String code;

        @Override
        public void init(FilterConfig filterConfig) {
            code = filterConfig.getInitParameter("encoding");
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            String encoding = request.getCharacterEncoding();
            if (code!=null && !code.equalsIgnoreCase(encoding)) {
                request.setCharacterEncoding(code);
                response.setCharacterEncoding(code);
            }
            chain.doFilter(request, response);
        }

        @Override
        public void destroy() {
            code = null;
        }
    }

