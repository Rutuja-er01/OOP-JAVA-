    class dataEncapsulation {
    private String name;
    private int age;
    public void setName(String n){
        name=n;
    }
    public void setAge(int a){
        age=a;
    }
    public int getAge(){
        return age;
    }
    public String getName(){
        return name;
    }
}

    public class Demo{
        public static void main(String[] args){
            dataEncapsulation s=new dataEncapsulation();
            s.setName("Rutuja");
           s. setAge(20 );
            System.out.println("Name"+s.getName());
           System.out.println("Age"+s.getAge());
        }
    }


