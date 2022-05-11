package com.suprun.store.controller.listener;

import com.suprun.store.controller.command.AppRole;
import com.suprun.store.controller.command.SessionAttribute;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class HTTPSessionListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        session.setAttribute(SessionAttribute.USER_ROLE, AppRole.GUEST);
    }
}
