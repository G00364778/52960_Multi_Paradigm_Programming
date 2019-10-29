public class Person {
   
   private String name;
   private int age;

   public Person(String n, int a){
       this.name = n;
       this.age = a;
   }

   public String toString(){
       return "   NAME: " + this.name + "\n    AGE: " + this.age;
   }
   public void setAge(int n){
       if (n < 0){
           return;
       }
       this.age = n;
   }
/* 
   public static void main(String[] args) {
        Person a = new Person("John", 14);
        Person b = new Person("Paul", 16);
        System.out.println(a);
        //a.setAge(-1);
        a.age = -1;
        System.out.println(a);
        a.setAge(40);
        System.out.println(a);
   } */
}