import java.util.ArrayList;
import java.util.Random;

// to create computer's algorithm for placement bananas
public class CompAlgorithm {
    private ArrayList<Integer> numbers;
    private ArrayList<Character> chars;
    private ArrayList<String> points, unavailablePoints; // letter and number


    public static void main(String[] args) {
        CompAlgorithm compAlgorithm = new CompAlgorithm();
        compAlgorithm.placeBananas();
    }

    public void placeBananas(){
        numbers = new ArrayList<>();
        chars = new ArrayList<>();
        points = new ArrayList<>();
        unavailablePoints = new ArrayList<>();
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
        s = s + r; // r - number, s - letter
        System.out.println(s);
        points.add(s); //  перша точка шестипалубного
        unavailablePoints.add(s);
        System.out.println("UN ::" + unavailablePoints.get(unavailablePoints.size() -1));

        if(r !=10) unavailablePoints.add(String.valueOf(s.charAt(0)) + (r + 1));
        System.out.println("UN 48::" + unavailablePoints.get(unavailablePoints.size() -1));
        if(r != 1) unavailablePoints.add(String.valueOf(s.charAt(0)) + (r -1));
        System.out.println("UN 50::" + unavailablePoints.get(unavailablePoints.size() -1));


        // 1 - горизонталь, 2 - вертикаль
        int horVer = random.nextInt(1, 2);
        if(horVer == 1){
            int placeRight = chars.size() - (chars.indexOf(s.charAt(0))) - 1 ;
            if(placeRight >= 5) placeHorizontalInRight(s.charAt(0), r);
            else placeHorizontalInBothSides(s.charAt(0), r, placeRight);
        }
    }

    private void placeHorizontalInRight(char ch, int num){
        int count =1;
        String s ="";
        if(ch != 'а') unavailablePoints.add(String.valueOf(chars.get(chars.indexOf(ch) -1)) + String.valueOf(num));
        System.out.println("UN in placeHorizontalInRight::" + unavailablePoints.get(unavailablePoints.size() -1));
        for(int i =0; i < 5; i++){
            int put = chars.indexOf(ch) + count;
            s = String.valueOf(chars.get(put));
            s = s + num;
            points.add(s);
            unavailablePoints.add(s);
            System.out.println("UN ::" + unavailablePoints.get(unavailablePoints.size() -1));
            if(num !=10) unavailablePoints.add(String.valueOf(s.charAt(0)) + (num + 1));
            System.out.println("UN ::" + unavailablePoints.get(unavailablePoints.size() -1));
            if(num != 1) unavailablePoints.add(String.valueOf(s.charAt(0)) + (num -1));
            System.out.println("UN ::" + unavailablePoints.get(unavailablePoints.size() -1));
            count++;
            System.out.println(s);
        }
        if(s.charAt(0) != 'з') unavailablePoints.add(String.valueOf(chars.get(chars.indexOf(s.charAt(0))+1)) + num);// в кінці корабля
        System.out.println("UN placeHorizontalInRight::" + unavailablePoints.get(unavailablePoints.size() -1));
    }

    private void placeHorizontalInBothSides(char ch, int num, int placeRight){
        int count = 1;
        String s="";
        // помістити справа
            for (int i = 0; i < placeRight; i++) {
                int put = chars.indexOf(ch) + count;
                s = String.valueOf(chars.get(put));
                s = s + num;
                points.add(s);
                unavailablePoints.add(s);
                System.out.println("UN ::" + unavailablePoints.get(unavailablePoints.size() -1));
                if(num !=10) unavailablePoints.add(String.valueOf(s.charAt(0)) + (num + 1));
                System.out.println("UN ::" + unavailablePoints.get(unavailablePoints.size() -1));
                if(num != 1) unavailablePoints.add(String.valueOf(s.charAt(0)) + (num -1));
                System.out.println("UN ::" + unavailablePoints.get(unavailablePoints.size() -1));
                count++;
                System.out.println(s);
            }

        if(s.charAt(0) != 'з') unavailablePoints.add(String.valueOf(chars.get(chars.indexOf(s.charAt(0))+1)) + num);// в кінці корабля
        System.out.println("UN 105::" + unavailablePoints.get(unavailablePoints.size() -1));

        count = 1;
        int placeLeft = 6 - placeRight -1;
        for(int i =0; i < placeLeft; i++){
            int put = chars.indexOf(ch) - count;
            s = String.valueOf(chars.get(put));
            s = s + num;
            points.add(s);
            unavailablePoints.add(s);
            System.out.println("UN 105::" + unavailablePoints.get(unavailablePoints.size() -1));

            if(num != 1) {
                char temp = s.charAt(0);
               int next = Integer.parseInt(String.valueOf(s.charAt(1))) - 1;
                unavailablePoints.add(String.valueOf(temp) + next);
                System.out.println("UN 111::" + unavailablePoints.get(unavailablePoints.size() -1));
            }

            if(num != 10){ // зробити недоступною точку під бананом
                char temp = s.charAt(0);
                int next = Integer.parseInt(String.valueOf(s.charAt(1))) + 1;
                unavailablePoints.add(String.valueOf(temp) + next);
                System.out.println("UN 118 ::" + unavailablePoints.get(unavailablePoints.size() -1));
            }
            count++;
            System.out.println(s);
        }

        if(s.charAt(0) != 'а') {
            unavailablePoints.add(String.valueOf(chars.get(chars.indexOf(s.charAt(0))-1)) + num);
            System.out.println("UN 135::" + unavailablePoints.get(unavailablePoints.size() -1));
        }
    }
}