 class Vehicle {
    void color(){
        System.out.println("this is pink car");
    }
}
class Car extends Vehicle{
     void model(){
        System.out.println("this is BMW");
     }
}
class Bus extends Car{
    void tyers(){
        System.out.println("this has four tyers");
    }
}
public class MultiLevel{
    public static void main(String[] args){
        Bus B=new Bus();
        B.tyers();
        B.model();
        B.color();
    }
 }
