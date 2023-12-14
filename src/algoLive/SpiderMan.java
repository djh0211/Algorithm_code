package algoLive;

public class SpiderMan extends Person{
    boolean isSpider=true;

    @Override
    void print() {
        System.out.println("슉");
    }
    void shoot(){
        System.out.println("슉슉");
    }

    public static void main(String[] args) {
        Person p = new Person();
        SpiderMan sm = (SpiderMan) p;
        
    }
}
