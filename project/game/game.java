package io.mobile.project.game;

import java.util.Date;

public class game {
    private int game_number;
    private String game_name ;
    private String review ;
    private String developer_name ;
    private int age_limit ;
    private Date upload_date;

    public game(final int game_number, final String game_name, final String review, final String developer_name, final int age_limit, final Date upload_date) {
        this.game_number = game_number;
        this.game_name = game_name;
        this.review = review;
        this.developer_name = developer_name;
        this.age_limit = age_limit;
        this.upload_date = upload_date;

    }

    public int getGame_number() {
        return game_number;
    }

    public String getGame_name() {
        return game_name;
    }

    public String getreview() {
        return review;
    }

    public String getDeveloper_name() {
        return developer_name;
    }

    public int getAge_limit() {
        return age_limit;
    }

    public Date getUpload_date() {
        return upload_date;
    }


    @Override
    public String toString() {
        return "Game{" +
                "game_number='" + game_number + '\'' +
                ", game_name='" + game_name + '\'' +
                ", review=" + review +
                ", developer_name='" + developer_name + '\'' +
                ", age_limit='" + age_limit + '\'' +
                ", upload_date=" + upload_date +
                '}';
    }


}
