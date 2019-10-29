public class PersonAppRunner{

    public static void main(String[] args) {
        Person a = new Person("John", 23);
        Person b = new Person("Paul", 51);
        System.out.println(a);
        //a.age = -1;
        a.setAge(-1);
        System.out.println(a);
        a.setAge(10);
        System.out.println(a);

    }
}