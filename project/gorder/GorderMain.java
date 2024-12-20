package io.mobile.finalproject.gorder;

import java.util.Date;
import java.util.List;

public class GorderMain {
    public static void main(String[] args) {

        // 전체 주문 정보 검색
        System.out.println("\n전체 주문 정보 검색\n----------------------------------------");
        List<Gorder> gorderList = GorderService.selectAll();
        for (Gorder gorder : gorderList) {
            System.out.printf("%-5s : %-12s : %-12s : %-5s : %-12s : %10d : %-20s : %-12s\n", gorder.getOrderNo(), gorder.getOrderDate(),
                    gorder.getFixedPrice(), gorder.getDiscountRate(), gorder.getPaymentMethod(), gorder.getGameNo(), gorder.getCustomerId());
        }

        // 번호로 검색하기
        System.out.println("\n번호로 검색하기 - o07\n----------------------------------------");
        {
            Gorder gorder = GorderService.selectById("o07");
            if (gorder != null) {
                System.out.println(gorder);
            } else {
                System.out.println("o07 not exist !!");
            }
        }

        // 고객 번호로 검색하기
        System.out.println("\n고객 번호로 검색하기 - apple\n----------------------------------------");
        List<Gorder> gorderList2 = GorderService.selectByCustomerId("apple");
        for (Gorder gorder : gorderList2) {
            System.out.printf("%-5s : %-12s : %-12s : %-5s : %-12s : %10d : %-20s : %-12s\n", gorder.getOrderNo(), gorder.getOrderDate(),
                    gorder.getFixedPrice(), gorder.getDiscountRate(), gorder.getPaymentMethod(), gorder.getGameNo(), gorder.getCustomerId());
        }

        // 상품 번호로 검색하기
        System.out.println("\n상품 번호로 검색하기 - p03\n----------------------------------------");
        List<Gorder> gorderList3 = GorderService.selectByGameNo("p03");
        for (Gorder gorder : gorderList3) {
            System.out.printf("%-5s : %-12s : %-12s : %-5s : %-12s : %10d : %-20s : %-12s\n", gorder.getOrderNo(), gorder.getOrderDate(),
                    gorder.getFixedPrice(), gorder.getDiscountRate(), gorder.getPaymentMethod(), gorder.getGameNo(), gorder.getCustomerId());
        }

        // 새로운 주문 추가하기 -> 추가 확인 검새
        System.out.println("\n새로운 주문 추가하기\n----------------------------------------");
        int newOrderNo = GorderService.insert(12, new Date(), 123, 12, "지갑", 123, "게이머");
        if (newOrderNo != 0) {
            Gorder gorder = GorderService.selectById(newOrderNo);
            if (gorder != null) {
                System.out.println(gorder);
            } else {
                System.out.println(newOrderNo + " not exist !!");
            }
        } else {
            System.out.println(newOrderNo + " 주문 추가에 실패했습니다.");
        }

        // 주문 정보 삭제하기 -> 추가 확인 검새
        System.out.println("\n주문 정보 삭제하기 - " + newOrderNo + "\n----------------------------------------");
        if (GorderService.deleteById(newOrderNo) > 0) {
            Gorder order = GorderService.selectById(newOrderNo);
            if (order != null) {
                System.out.println(newOrderNo + " exist !!");
            } else {
                System.out.println(newOrderNo + " 정보 삭제에 성공했습니다 !!");
            }
        } else {
            System.out.println(newOrderNo + " 주문 정보 삭제에 실패했습니다.");
        }

    }
}
