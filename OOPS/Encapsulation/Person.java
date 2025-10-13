package OOPS.Encapsulation;
//Encapsulation :- put data and behaviour together and expose as little as possible.Not just "private fields" but enforcing valid states.


public class Person {
    private String name;
    private int age;

    Person(String name, int age) {
        setName(name);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("name required");
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age <= 0) throw new IllegalArgumentException("age must be >0");
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{name=" + name + ",age=" + age + "}";
    }

    public static void main(String[] args) {
        Person p = new Person("raman", 16);
        System.out.println("Example of the encapsulation with person class:- ");
        System.out.println("\nSuccessfully created: " + p);
        System.out.println("Fetching details with getters: Name is '" + p.getName() + "', Age is " + p.getAge());
        p.setName("Vikram");
        System.out.println("Name was changed using a setter. New state: " + p);
        try {
            System.out.println("\nAttempting to set an invalid age...");
            p.setAge(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught an expected exception: " + e.getMessage());
            System.out.println("Object state remains unchanged: " + p);
        }
        try {
            System.out.println("\nAttempting to create a person with an invalid name...");
            Person invalidPerson = new Person(null, 25);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught an expected exception: " + e.getMessage());
        }
    }
}
//Notes: Java enforces accessibility with private/protected/public. Use getters/setters to validate or present computed state.
