class parent {
    void print(){
  System.out.println("this is me ");
    }
}
class child1 extends parent{
    void print(){
        System.out.println("this is you");
    }
}
class child2 extends parent{
    void print(){
        System.out.println("this is monkey");
    }
}
public class Overriding{
   public static void main(String[] args){
    parent a;
    a=new child1();
    a.print();
    parent b;
    b=new child2();
    b.print();
   }
}
