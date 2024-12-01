package org.yahid.grandLine.model;

import java.time.Instant;

/**
 * Projection for {@link Business}
 */
public interface BusinessInfo {
    Integer getId();

    String getOwnerName();

    String getBusinessName();

    String getEmail();

    String getPassword();

    String getAddress();

    String getPhoneNumber();

    Instant getCreatedAt();

    Instant getUpdatedAt();

    CategoryInfo getCategory();

    /**
     * Projection for {@link Category}
     */
    interface CategoryInfo {
        Integer getId();

        String getName();
    }
}