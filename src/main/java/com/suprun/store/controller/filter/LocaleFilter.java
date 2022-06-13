package com.suprun.store.controller.filter;

import com.suprun.store.controller.command.SessionAttribute;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * {@code LocaleFilter} class is an implementation of {@link Filter} interface.
 * It checks incoming cookie files for changes in locale relatively to current value from session.
 * If there is any difference it sets new locale value to the session.
 *
 * @author Philip Suprun
 */
@WebFilter(filterName = "LocaleFilter")
public class LocaleFilter implements Filter {

    private static final String DEFAULT_LOCALE = "en_US";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        Cookie[] cookie = httpServletRequest.getCookies();

        if (cookie != null) {
            Optional<String> optionalLocale = Arrays.stream(cookie)
                    .filter(key -> StringUtils.equals(key.getName(), "locale"))
                    .map(Cookie::getValue)
                    .findAny();
            if (optionalLocale.isPresent()) {
                String locale = optionalLocale.get();
                if (session.getAttribute(SessionAttribute.LOCALE) != locale) {
                    session.setAttribute(SessionAttribute.LOCALE, locale);
                }
            } else {
                session.setAttribute(SessionAttribute.LOCALE, DEFAULT_LOCALE);
            }
        } else {
            session.setAttribute(SessionAttribute.LOCALE, DEFAULT_LOCALE);
        }
        chain.doFilter(request, response);
    }
}
