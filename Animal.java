abstract class Animal {
    abstract void sound();
    void eat(){
        System.out.println("animal is eating ");
    }
}
class Dog extends Animal{
    @Override
    void sound()
    {
        System.out.println("Dog barks");
    }
    }
    public class animal{
        public static void main(String[] args)
        {
            Dog d=new Dog();
            d.sound();
            d.eat();
        }
    }

