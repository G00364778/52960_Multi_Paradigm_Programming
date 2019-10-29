//inheritance example
public class Student extends Person{

    private String[] classes; //classes student is attending

    public Student(String n, int a, String[] c){
        super(n, a);
        this.classes = c;
    }

    public String toString(){
        String repr = super.toString() + "\nCLASSES: " + classes[0] + "\n";
        for(int i=1; i<classes.length; i++){
            repr += "         " + classes[i] + "\n";
        }
        return repr;
    }

    public static void main(String[] args){
        String[] classes = new String[] {"Introduction to Maths", "Management for Computing", "Programming 1"};
        Student s = new Student("Gerhard", 44, classes);
        s.setAge(53);
        System.out.println(s);
    }
}