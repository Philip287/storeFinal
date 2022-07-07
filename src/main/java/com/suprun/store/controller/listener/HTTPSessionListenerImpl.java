package com.suprun.store.controller.listener;

import com.suprun.store.controller.command.AppRole;
import com.suprun.store.controller.command.SessionAttribute;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * {@code HttpSessionListenerImpl} class is an implementation of {@link HttpSessionListener} interface.
 * It sets user application role for newly created session to GUEST value of {@code AppRole} enum.
 *
 * @author Philip Suprun
 */
@WebListener
public class HTTPSessionListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        session.setAttribute(SessionAttribute.USER_ROLE, AppRole.GUEST);
    }
}
