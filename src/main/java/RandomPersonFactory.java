import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class RandomPersonFactory {

    private static RandomPersonFactory factory = new RandomPersonFactory();
    private RandomPersonFactory(){

    }
    public static RandomPersonFactory getFactory(){
        return factory;
    }

    public Person getRandomPerson(){
        String name = getRandomName();
        String birth = "2000-01-01";
        char sex = getRandomSex();
     return new Person(name,birth,sex);
    }

    private String getRandomName(){
        String chars = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder name = new StringBuilder();
        int charNumber = (int)(Math.random()*25);
        for (int i = 0; i < 3; i++) {
            name.append(chars.toUpperCase(Locale.ROOT).charAt(charNumber));
            for (int j = 0; j < 5; j++) {
                name.append(chars.charAt(charNumber));
            }
            name.append(" ");
        }



        return name.toString().trim();

    }

    private char getRandomSex(){
        if(Math.random() > 0.5)
            return 'M';
        else return 'F';
    }
}
