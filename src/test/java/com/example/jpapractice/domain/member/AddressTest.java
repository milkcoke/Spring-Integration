package com.example.jpapractice.domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AddressTest {

    @DisplayName("주소 동등성 동일성 비교")
    @Test
    void testEquivalence() {
        var address = new Address("대한민국", "테헤란로", "20번길", "292102");
        var equiAddress = new Address("대한민국", "테헤란로", "20번길", "292102");

        assertThat(address).isEqualTo(equiAddress);
        assertThat(address).isNotSameAs(equiAddress);
    }
}