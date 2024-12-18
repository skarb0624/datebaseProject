package io.mobile.finalproject.gcustomer;

import io.mobile.finalproject.gcustomer.Gcustomer;
import io.mobile.finalproject.gcustomer.GcustomerService;

import java.util.List;

public class GcustomerMain {
    public static void main(String[] args) {
        System.out.println("\n전체 고객 정보 검색하기\n");
        List<Gcustomer> customerList = GcustomerService.selectAll();
        for (Gcustomer gcustomer : customerList) {
            System.out.println(gcustomer);
        }

        //아이디로 검색하기
        System.out.println("\n고객 id 정보 검색하기");
        Gcustomer customerId = GcustomerService.selectById("carrot");
        if (customerId != null) {
            System.out.println(customerId);
        } else {
            System.out.println("고객 아이디 없음");
        }
        // 새로운 고객을 추가하기 -> 추가 확인 검색
        System.out.println("\n새로운 고객 추가하기\n");
        if (GcustomerService.insert("grape", "홍길동","1312",13, 12,23,"이멜", 10000) > 0 ){
            Gcustomer grape = GcustomerService.selectById("grape");
            if (grape != null){
                System.out.println(grape);
            }else {
                System.out.println("grape not exist !!");
            }
        } else{
            System.out.println("grape 고객 추가에 실패했습니다.");
        }
        // 고객 정보 수정하기 -> 추가 확인 검색
        System.out.println("\n고객 정보 수정하기\n");
        if (GcustomerService.update("grape","이름2","12312", "이멜", 10000) > 0 ){
            Gcustomer grape = GcustomerService.selectById("grape");
            if (grape != null){
                System.out.println(grape);
            }else {
                System.out.println( grape +"not exist !!");
            }
        } else{
            System.out.println("grape 고객 수정에 실패했습니다.");
        }
        // 고객 정보 삭제하기 -> 추가 확인 검색
        System.out.println("\n고객 정보 수정하기\n");
        if (GcustomerService.deleteById("grape") > 0 ){
            Gcustomer grape = GcustomerService.selectById("grape");
            if (grape != null){
                System.out.println("grape 삭제 안됨");
            }else {
                System.out.println("고객 정보 삭제에 성공했습니다.");
            }
        } else{
            System.out.println("고객 삭제에 실패했습니다.");
        }

    }
}
