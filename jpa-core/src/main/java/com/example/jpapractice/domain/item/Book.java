package com.example.jpapractice.domain.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends Item {
    @Column(name = "author_name", length = 63)
    private String authorName;

    @Column(name = "isbn", length = 127)
    private String isbn;

    public void changeBookInfo(String authorName, String isbn) {
        this.authorName = authorName;
        this.isbn = isbn;
    }
}
