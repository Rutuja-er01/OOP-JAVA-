 class Vehicle {
    void color(){
        System.out.println("this is balck vehicle");
    }
}
class Car extends Vehicle{
    void model(){
        System.out.println("this is farari");
    }
}
class fourWheeler extends Vehicle{
    void tyers(){
        System.out.println("this has four tyers");
    }
}
public class Herarchical{
    public static void main(String[] args){
        fourWheeler f=new fourWheeler();
        f.tyers();
        f.color();
        Car c=new Car();
        c.model();
        c.color();
    }
}

