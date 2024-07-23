package OOPs.LearnClasses_Objects;

public class Pokemon {
    String name;
    int level;
    String owner;
    String type;

    void attack(){
        System.out.println(name+" ATTACK!");
    }

    void whichType(){
        System.out.println(name+ " is a "+type+" Type Pokemon");
    }
}
