package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue("m")
@Getter
public class Movie extends Item {
    private String artistName;
    private String etc;

    public void changeMovieInfo(String name, int price, int stockQuantity, String artistName, String etc) {
        super.changeItemInfo(name, price, stockQuantity);
        this.artistName = artistName;
        this.etc = etc;
    }

    public static Movie createMovie(String name, int price, int stockQuantity, String artistName, String etc) {
        Movie movie = new Movie();
        movie.changeMovieInfo(name, price, stockQuantity, artistName, etc);

        return movie;
    }
}
