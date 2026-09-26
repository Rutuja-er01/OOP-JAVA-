class Demo
{
    private int emp_id;
    private String emp_name;
    private float emp_salary;
    public void setEmp_id(int c){
        emp_id=c;
    }
    public void setEmp_Name(String s){
        emp_name=s;
    }
    public void setEmp_Salary(float f){
        emp_salary=f;
    }
    public int getEmp_id(){
        return emp_id;
    }
    public String getEmp_Name(){
        return emp_name;
    }
    public float getEmp_Salary(){
        return emp_salary;
    }

}
public class Employee{
    public static void main(String[] args){
        Demo D=new Demo();
        D.setEmp_id(12);
        D.setEmp_Name("Rutuja");
        D.setEmp_Salary(1200);
         System.out.println("emp_id"+D.getEmp_id());
         System.out.println("Emp Name: " + D.getEmp_Name());
        System.out.println("Emp Salary: " + D.getEmp_Salary());
    }
}
       