package hello.core.singleton;

public class StatefulService {

    //private int price; // 상태 유지 필드

    public int order(String name, int price){
        System.out.println("name = " + name);
        System.out.println("price = " + price);
//        this.price = price; // 문제 발생 지점
        return price; //지역 변수
    }

//    public int getPrice(){
//        return price;
//    }
}
