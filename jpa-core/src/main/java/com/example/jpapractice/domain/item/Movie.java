package com.example.jpapractice.domain.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie extends Item {
    @Column(name = "director_name", length = 63)
    private String directorName;

    @Column(name = "distributor_name", length = 63)
    private String distributorName;
}
