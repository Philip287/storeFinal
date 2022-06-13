package com.suprun.store.controller.listener;

import com.suprun.store.controller.command.SessionAttribute;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@code HttpSessionAttributeListenerImpl} class is an implementation of {@link HttpSessionAttributeListener} interface.
 * It collects all created sessions to a map.
 *
 * @author Philip Suprun
 */
public class HttpSessionAttributeListenerImpl implements HttpSessionAttributeListener {
    private static final Map<Long, HttpSession> userSession = new ConcurrentHashMap<>();

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        String attributeName = event.getName();

        if (StringUtils.equals(attributeName, SessionAttribute.USER_ID)) {
            long userId = Long.parseLong(session.getAttribute(SessionAttribute.USER_ID).toString());
            userSession.put(userId, session);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        String attributeName = event.getName();

        if (attributeName.equals(SessionAttribute.USER_ID)) {
            long userId = Long.parseLong(event.getValue().toString());
            userSession.remove(userId);
        }
    }

    /**
     * Find created session by its repspective user id.
     *
     * @param userId unique id of the user.
     * @return {@link HttpSession} instance wrapped with {@link Optional}.
     */
    public static Optional<HttpSession> findSession(long userId) {
        HttpSession session = userSession.get(userId);

        return session != null
                ? Optional.of(session)
                : Optional.empty();
    }
}
