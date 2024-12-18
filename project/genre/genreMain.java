package io.mobile.project.genre;

import io.mobile.project.game.game;
import io.mobile.project.game.gameService;

import java.util.List;

public class genreMain {
    public static void main(String[] args) {
        System.out.println("\n 전체 장르 정보 검색하기\n");
        List<genre> genrelist = genreService.selectAll();
        for (genre genres : genrelist) {
            System.out.println(genres);
        }
        System.out.println("\n게임 번호로 정보 검색하기\n");
        genre genre_number = genreService.selectById(12);
        if (genre_number != null) {
            System.out.println(genre_number);
        } else {
            System.out.println("게임번호로 장르를 찾는데 실패하였습니다.");
        }

        System.out.println("\n장르를 추가하기\n");
        if (genreService.insert(1, "장르1")>0) {
            game addGenre = gameService.selectById(1);
            if (addGenre != null) {
                System.out.println(addGenre);
            } else {
                System.out.println("게임을 장르를 찾는데 실패하였습니다.");
            }
        } else {
            System.out.println("게임을 장르를 추가하는데 실패하였습니다.");
        }


        System.out.println("\n게임 장르를 정보 수정하기\n");
        if (genreService.update(1,"장르1")>0) {
            genre alterGenre = genreService.selectById(1);
            if (alterGenre != null) {
                System.out.println(alterGenre);
            } else {
                System.out.println(alterGenre+"는 존재 하지 않습니다.");
            }
        } else {
            System.out.println("장르를 업데이트 하는데 실패 하였습니다.");
        }

        //게임 번호로 삭제
        System.out.println("\n게임안에 장르를 삭제하기\n");
        if (genreService.delete(1)>0) {
            genre deleteGenre = genreService.selectById(1);
            if (deleteGenre != null) {
                System.out.println(deleteGenre+"는 존재하지 않습니다. ");
            } else {
                System.out.println("성공적으로 삭제하였습니다.");
            }
        } else {
            System.out.println("게임 장르를 삭제하는데 실패하였습니다.");
        }


    }

}
