package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue("a")
@Getter
public class Album extends Item {
    private String artistName;
    private String etc;

    public void changeAlbumInfo(String name, int price, int stockQuantity, String artistName, String etc) {
        super.changeItemInfo(name, price, stockQuantity);
        this.artistName = artistName;
        this.etc = etc;
    }

    public static Album createAlbum(String name, int price, int stockQuantity, String artistName, String etc) {
        Album album = new Album();
        album.changeAlbumInfo(name, price, stockQuantity, artistName, etc);

        return album;
    }
}
