package io.mobile.project.game;


import java.sql.Date;
import java.util.List;

public class gameMain {

    public static void main(String[] args) {
        System.out.println("\n 전체 게임 정보 검색하기\n");
        List<game> gamelist = gameService.selectAll();
        for (game games : gamelist) {
            System.out.println(games);
        }
        System.out.println("\n게임 번호로 정보 검색하기\n");
        game game_number = gameService.selectById(12);
        if (game_number != null) {
            System.out.println(game_number);
        } else {
            System.out.println("게임을 찾는데 실패하였습니다.");
        }

        System.out.println("\n새로운 게임을 추가하기\n");
        if (gameService.insert(1, "게임1", "매우 긍정적", "개발사1",15, Date.valueOf("2022-01-02"))>0) {
            game addGame = gameService.selectById(1);
            if (addGame != null) {
                System.out.println(addGame);
            } else {
                System.out.println("게임을 찾는데 실패하였습니다.");
            }
        } else {
            System.out.println("게임을 추가하는데 실패하였습니다.");
        }


        System.out.println("\n게임 정보 수정하기\n");
        if (gameService.update(1,"대체로 긍정적"  , 12)>0) {
            game alterGame = gameService.selectById(1);
            if (alterGame != null) {
                System.out.println(alterGame);
            } else {
                System.out.println(alterGame+"는 존재 하지 않습니다.");
            }
        } else {
            System.out.println("게임을 업데이트 하는데 실패 하였습니다.");
        }

        //게임 번호로 삭제
        System.out.println("\n게임 삭제하기\n");
        if (gameService.delete(1)>0) {
            game deleteGame = gameService.selectById(1);
            if (deleteGame != null) {
                System.out.println(deleteGame+"는 존재하지 않습니다. ");
            } else {
                System.out.println("성공적으로 삭제");
            }
        } else {
            System.out.println("게임을 삭제하는데 실패하였습니다.");
        }


    }
}
