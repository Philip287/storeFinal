package com.suprun.store.service.criteria;

/**
 * {@code SpeakerFilterCriteria} enum is used to choose a filter strategy
 * in {@link com.suprun.store.service.SpeakerService#filter} method.
 *
 * @author Philip Suprun
 */
public enum SpeakerFilterCriteria {
    NONE,
    ID,
    NAME,
    PRICE,
    PICTURE_PATH,
    DESCRIPTION
}
