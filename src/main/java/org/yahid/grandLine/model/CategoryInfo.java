package org.yahid.grandLine.model;

import java.time.Instant;

/**
 * Projection for {@link Category}
 */
public interface CategoryInfo {
    Integer getId();

    String getName();

    Instant getCreatedAt();

    Instant getUpdatedAt();
}