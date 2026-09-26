interface A {
    default void display()    {
        System.out.println("this is A");
    }
}
interface B {
    default void display(){
        System.out.println("this is B");
    }
}
class C implements A,B{
   public void display(){
    A.super.display();
    B.super.display();
    System.out.println("class c display");
   }
}
public class Main{
    public static void main(String[] args){
 C obj=new C();
 obj.display();
    }
}
