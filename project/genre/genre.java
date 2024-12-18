package io.mobile.project.genre;

public class genre {
    private int game_number;
    private String genre;
    public genre(int game_number, String genre) {
        this.game_number = game_number;
        this.genre = genre;
    }


    public int getGame_number() {
        return game_number;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "genre{" +
                "game_number=" + game_number +
                ", genre='" + genre + '\'' +
                '}';
    }
}
