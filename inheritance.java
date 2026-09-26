 class Parent {
    void eat(){
        System.out.println("humans eat food");
    }
}
class Child extends Parent{
      void sound(){
        System.out.println("humans can make the sound");
      }
    }
    public class inheritance{
      public static void main(String[] args){
      Child c=new Child();
    c.eat();
    c.sound();
    }
  }
