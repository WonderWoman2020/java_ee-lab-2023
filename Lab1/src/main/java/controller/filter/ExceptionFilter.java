package controller.filter;

import controller.ApiServlet;
import controller.exception.HttpRequestException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Web filter with mechanism for catching exceptions and rewriting them to appropriate HTTP response statutes.
 * It's important - without it, custom exceptions (like NotFoundException) are not catched and rewritten,
 * but are thrown by application server and displayed straight to the user face as a response XD
 */
@WebFilter(urlPatterns = {
        ApiServlet.Paths.API + "/*"
})
public class ExceptionFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            super.doFilter(request, response, chain);
        } catch (HttpRequestException ex) {
            response.sendError(ex.getResponseCode()); // only response error code is sent for now, no messages
        }
    }
}
