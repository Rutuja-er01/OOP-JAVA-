class Help {
    static int multiply(int a,int b){
        return a*b;
    }
    static double multiply(double a,double b){
        return a*b;
    }
}
public class overloading{
    public static void main(String[] args){
        System.out.println(Help.multiply(2,4));
        System.out.println(Help.multiply(3.4,6.7));
    }
} 
