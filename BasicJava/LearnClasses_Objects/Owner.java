package BasicJava.LearnClasses_Objects;

public class Owner {

    String name;
    int age;
    int Pokebadge;
    int totalPokemons;

    void showName(){
        System.out.println(name);
    }

    void showAge(){
        System.out.println(name+ " is "+age+" years old");
    }

    void showPokebadge(){
        System.out.println(name+ " has "+Pokebadge+" PokeBadge");
    }

    void showTotalPokemons(){
        System.out.println(name+ " has "+totalPokemons+" Pokemons");
    }
}
