package io.mobile.project.developer;

import java.util.List;

public class developerMain {
    public static void main(String[] args) {
        System.out.println("\n 전체 개발사 정보 검색하기\n");
        List<developer> developerList = developerService.selectAll();
        for (developer developers : developerList) {
            System.out.println(developers);
        }
        System.out.println("\n개발사 이름으로 정보 검색하기\n");
        developer developer_name = developerService.selectById("개발사1");
        if (developer_name != null) {
            System.out.println(developer_name);
        } else {
            System.out.println("개발사를 찾는데 실패하였습니다.");
        }

        System.out.println("\n새로운 개발사를 추가하기\n");
        if (developerService.insert("개발사1", "20221007@edu.hanbat.ac.kr", "010-0000-1111")>0) {
            developer developers = developerService.selectById("개발사1");
            if (developers != null) {
                System.out.println(developers);
            } else {
                System.out.println("개발사를 찾는데 실패하였습니다.");
            }
        } else {
            System.out.println("개발사를 추가하는데 실패하였습니다.");
        }


        System.out.println("\n개발사 정보 수정하기\n");
        if (developerService.update("개발사1","0000@edu.hanbat.ac.kr"  , "000-0000-0000")>0) {
            developer alterDeveloper = developerService.selectById("개발사1");
            if (alterDeveloper != null) {
                System.out.println(alterDeveloper);
            } else {
                System.out.println(alterDeveloper+"는 존재 하지 않습니다.");
            }
        } else {
            System.out.println("개발사 정보를 업데이트 하는데 실패 하였습니다.");
        }

        //게임 번호로 삭제
        System.out.println("\n개발사 정보 삭제하기\n");
        if (developerService.delete("개발사1")>0) {
            developer deleteDeveloper = developerService.selectById("개발사1");
            if (deleteDeveloper != null) {
                System.out.println(deleteDeveloper+"는 존재하지 않습니다. ");
            } else {
                System.out.println("성공적으로 삭제하였습니다.");
            }
        } else {
            System.out.println("개발사 정보를 삭제하는데 실패하였습니다.");
        }


    }
}
