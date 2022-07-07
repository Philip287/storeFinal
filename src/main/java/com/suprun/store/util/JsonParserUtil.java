package com.suprun.store.util;

import com.suprun.store.entity.Card;
import jakarta.servlet.http.Part;

/**
 * {@code JsonParserUtil} interface provides functionality for parse JSON data.
 *
 * @author Philip Suprun
 */
public interface JsonParserUtil {
    /**
     * Method that uploads given image {@link Part} to a directory specified in resources
     * and returning a path to it.
     * Or if part isn't containing any files returns path to a default image.
     *
     * @param jsonCard containing json string card object
     * @return object class Card
     */

    Card parse(String jsonCard);
}
