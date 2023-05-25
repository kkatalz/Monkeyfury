import java.util.ArrayList;
import java.util.Random;

// to create computer's algorithm for placement bananas
public class CompAlgorithm {
    private ArrayList<Integer> numbers;
    private ArrayList<Character> chars;

    public static void main(String[] args) {
        CompAlgorithm compAlgorithm = new CompAlgorithm();
        compAlgorithm.placeBananas();
    }

    public void placeBananas(){
        numbers = new ArrayList<>();
        chars = new ArrayList<>();
        ArrayList<String> points = new ArrayList<>();
        Random random = new Random();

        for(int i =1; i < 11; i++){
            numbers.add(i);
        }
        chars.add('а');
        chars.add('б');
        chars.add('в');
        chars.add('г');
        chars.add('ґ');
        chars.add('д');
        chars.add('е');
        chars.add('є');
        chars.add('ж');
        chars.add('з');


        // шестипалубний
        int r = random.nextInt(1, 11);
        int r2 = random.nextInt(1, 11);
        String s = String.valueOf(chars.get(r2 -1));
        s = r + s;
        System.out.println(s);
        points.add(s); //  перша точка шестипалубного



    }
}