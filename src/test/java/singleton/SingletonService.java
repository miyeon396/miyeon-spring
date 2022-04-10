package singleton;

public class SingletonService {

    //완벽한 싱글톤을 구현한 것.
    //자바가 뜨면서 스태틱 영역에 있는 얘를 초기화 하면서 new를 딱 한번 생성해서 instance에 가지고 있고
    //이 인스턴스에 대한 참조를 꺼낼 수 있는 방법은 getInstance밖에 없고, 얘를 생성할 수 있는 다른 곳도 없다.
    private static final SingletonService instance = new SingletonService(); //자기 자신을 클래스 내부에 private으로 하나 가지고 있는데 static로 가지고 있음.

    public static SingletonService getInstance() {
        return instance;
    } //jvm 뜰 때 싱글톤 뉴라는 것 보면 자기자신 객체 인스턴스 하나 생성해서 instance에 넣어둠.
    //조회시에는 getInstance()를 사용하면됨.

    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}