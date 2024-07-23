package OOPs.LearnClasses_Objects;
public class Main {
    public static void main(String[] args) {

        Pokemon p1 = new Pokemon();
        p1.name = "Pikachu";
        p1.level = 10;
        p1.type = "Electric";

        Pokemon p2 = new Pokemon();
        p2.name = "Squirtle";
        p2.level = 17;
        p2.type = "Water";

        Owner owner1 = new Owner();
        owner1.name = "Ash";
        owner1.age = 10;
        owner1.Pokebadge = 53;
        owner1.totalPokemons = 105;

        Owner owner2 = new Owner();
        owner2.name = "Misty";
        owner2.age = 10;
        owner2.Pokebadge = 1;
        owner2.totalPokemons = 14;

        //System.out.println(p1.name+" "+ p1.level +" lvl");
        //System.out.println(p2.name+" "+ p2.level +" lvl");
        
        //p1.attack();
        //p1.printOwner();
        //p2.printOwner();
        //p1.whichType();
        //p2.whichType();

    }
}

