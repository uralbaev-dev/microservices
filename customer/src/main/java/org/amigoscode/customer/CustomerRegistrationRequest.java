package org.amigoscode.customer;

/**
 * @className: CustomerRegistrationRequest
 * @date: 14.08.2023
 * @author: Uralbaev Diyorbek
 */

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {
}
