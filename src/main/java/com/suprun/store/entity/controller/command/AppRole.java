package com.suprun.store.entity.controller.command;


/**
 * {@code AppRole} enum contains roles used in application.
 *
 * @author Philip Suprun
 */
public enum AppRole {
    /**
     * System administrator role.
     */
    ADMIN,
    /**
     * Site manager role.
     */
    MANAGER,
    /**
     * Site client role.
     */
    CLIENT,
    /**
     * Not confirmed user.
     */
    NOT_CONFIRMED,
    /**
     * Guest unauthenticated user.
     */
    GUEST
}
