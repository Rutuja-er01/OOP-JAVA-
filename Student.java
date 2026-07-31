class Student{
    int roll_no;
    String name;
    double marks;
    void display()
    {
        System.out.println("Roll No:"+roll_no);
        System.out.println("Name:"+name);
        System.out.println("Marks:"+marks);
    }
    public static void main(String[] args){
        Student obj=new Student();
        obj.roll_no=5;
        obj.name="Rutuja";
        obj.marks=95.5;
        obj.display();
    }
}