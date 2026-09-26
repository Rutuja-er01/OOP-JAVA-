 class Vehicle {
    void color(){
      System.out.println("this is red ");
    }
}
class Car extends Vehicle{
    void model(){
        System.out.println("this is BMW");
    }
}
public class Test{
    public static void main(String[] args){
        Car c=new Car();
        c.color();
        c.model();

    }
}
