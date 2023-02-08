package hello.core.singleton;

public class SingletonService {

    // static 영역에 생성해 보관
    private static final SingletonService instance = new SingletonService();

    // 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용
    public static SingletonService getInstance() {
        return instance;
    }

    //외부에서 만들지 못하도록 private을 사용해 new 키워드를 사용한 객체 생성 막기
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
