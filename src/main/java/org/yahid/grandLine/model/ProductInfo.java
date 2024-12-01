package org.yahid.grandLine.model;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Projection for {@link Product}
 */
public interface ProductInfo {
    Integer getId();

    String getName();

    BigDecimal getPrice();

    String getDescription();

    String getImageUrl();

    Instant getCreatedAt();

    CategoryInfo getCategory();

    BigDecimal getStock();
    /**
     * Projection for {@link Category}
     */
    interface CategoryInfo {
        String getName();
    }
}