package com.example.jpapractice.domain.item;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Album extends Item {
    // 상속 받았기 때문에 Item 의 필수 속성들을 명시하지 않아도 테이블에 알아서 매핑된다.

//    @Id
//    @GeneratedValue
//    @JoinColumn(name = "item_id")
//    @OneToOne
//    private Item item;

    @Column(name = "artist_name", length = 63)
    private String artistName;

    public void changeAlbumInfo(String artistName) {
        this.artistName = artistName;
    }
}
