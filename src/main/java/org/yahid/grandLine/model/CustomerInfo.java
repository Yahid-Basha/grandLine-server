package org.yahid.grandLine.model;

/**
 * Projection for {@link Customer}
 */
public interface CustomerInfo {
    Integer getId();

    String getUserName();

    String getEmail();

    String getAddress();

    String getPhoneNumber();
}