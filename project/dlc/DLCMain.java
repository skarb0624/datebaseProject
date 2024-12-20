package io.mobile.finalproject.dlc;

import java.util.Date;
import java.util.List;

public class DLCMain {
    public static void main(String[] args) {
        // 전체 DLC 정보 검색
        System.out.println("\n전체 DLC 정보 검색\n----------------------------------------");
        List<DLC> DLCList = DLCService.selectAll();
        for (DLC dlc : DLCList) {
            System.out.printf("%-5s : %-12s : %-12s : %-5s : %-12s : %10d : %-20s : %-12s\n",
                    dlc.getGameNo(), dlc.getDlc_name());
        }



        // 새로운 DLC 추가하기
        System.out.println("\n새로운 DLC 추가하기\n----------------------------------------");
        int newDLCNo = DLCService.insert(0011, "DLC1");
        if (newDLCNo != 0) {
            DLC dlc = DLCService.selectById(newDLCNo);
            if (dlc != null) {
                System.out.println(dlc);
            } else {
                System.out.println(newDLCNo + " not exist !!");
            }
        } else {
            System.out.println(newDLCNo + " 주문 추가에 실패했습니다.");
        }

        // DLC 정보 수정하기
        System.out.println("\nDLC 정보 수정하기 - " + newDLCNo + "\n----------------------------------------");
        if (DLCService.update(newDLCNo, "DLC2") > 0) {
            DLC dlc = DLCService.selectById(newDLCNo);
            if (dlc != null) {
                System.out.println(dlc);
            } else {
                System.out.println(newDLCNo + " not exist !!");
            }
        } else {
            System.out.println(newDLCNo + " DLC 정보 수정에 실패했습니다.");
        }

        // DLC 정보 삭제하기
        System.out.println("\nDLC 정보 삭제하기 - " + newDLCNo + "\n----------------------------------------");
        if (DLCService.deleteById(newDLCNo) > 0) {
            DLC dlc = DLCService.selectById(newDLCNo);
            if (dlc != null) {
                System.out.println(newDLCNo + " exist !!");
            } else {
                System.out.println(newDLCNo + " 정보 삭제에 성공했습니다 !!");
            }
        } else {
            System.out.println(newDLCNo + " 주문 정보 삭제에 실패했습니다.");
        }

    }

}
