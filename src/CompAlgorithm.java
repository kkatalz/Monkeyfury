import java.util.ArrayList;
import java.util.Random;

// to create computer's algorithm for placement bananas
public class CompAlgorithm {
    private ArrayList<Integer> numbers;
    private ArrayList<Character> chars;
    private ArrayList<String> points6, points4, point4n2, unavailablePoints6, unavailablePoints4, unavailablePoints4n2; // letter and number


    public static void main(String[] args) {
        CompAlgorithm compAlgorithm = new CompAlgorithm();
        compAlgorithm.placeBananas();
    }

    public void placeBananas() {
        numbers = new ArrayList<>();
        chars = new ArrayList<>();
        points6 = new ArrayList<>();
        unavailablePoints6 = new ArrayList<>();
        points4 = new ArrayList<>();
        unavailablePoints4 = new ArrayList<>();
        point4n2 = new ArrayList<>();
        unavailablePoints4n2 = new ArrayList<>();

        Random random = new Random();

        for (int i = 1; i < 11; i++) {
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
        String s = String.valueOf(chars.get(r2 - 1));
        s = s + r; // r - number, s - letter
        System.out.println(s);
        points6.add(s); //  перша точка шестипалубного
        unavailablePoints6.add(s);
        System.out.println("UN ::" + unavailablePoints6.get(unavailablePoints6.size() - 1));

        if (r != 10) unavailablePoints6.add(String.valueOf(s.charAt(0)) + (r + 1));
        System.out.println("UN 48::" + unavailablePoints6.get(unavailablePoints6.size() - 1));
        if (r != 1) unavailablePoints6.add(String.valueOf(s.charAt(0)) + (r - 1));
        System.out.println("UN 50::" + unavailablePoints6.get(unavailablePoints6.size() - 1));


        // 1 - горизонталь, 2 - вертикаль
        int horVer = random.nextInt(2, 3);
        if (horVer == 1) { // шестипалубний
            int placeRight = chars.size() - (chars.indexOf(s.charAt(0))) - 1;
            if (placeRight >= 5) placeHorizontalInRight(s.charAt(0), r);
            else placeHorizontalInBothSides(s.charAt(0), r, placeRight);
        } else if(horVer == 2){
            String t = String.valueOf(s.charAt(1));
            if(s.length() > 2) t = t + s.charAt(2);
            t = t.trim();
            placeVertical(6, s.charAt(0), t, 1);
        }
    }

    private void placeHorizontalInRight(char ch, int num) {
        int count = 1;
        String s = "";
        if (ch != 'а') unavailablePoints6.add(String.valueOf(chars.get(chars.indexOf(ch) - 1)) + String.valueOf(num));
        System.out.println("UN in placeHorizontalInRight::" + unavailablePoints6.get(unavailablePoints6.size() - 1));

        if (ch != 'а' && num != 1) { // лівий верхній кут
            char t = chars.get(chars.indexOf(ch) - 1);
            int te = num - 1;
            String temp = String.valueOf(t) + te;
            unavailablePoints6.add(temp);
            System.out.println("UN 73 ------" + unavailablePoints6.get(unavailablePoints6.size() - 1));
        }

        if (ch != 'а' && num != 10) { // лівий нижній кут
            char t = chars.get(chars.indexOf(ch) - 1);
            int te = num + 1;
            String temp = String.valueOf(t) + te;
            unavailablePoints6.add(temp);
            System.out.println("UN 81 ------" + unavailablePoints6.get(unavailablePoints6.size() - 1));
        }


        for (int i = 0; i < 5; i++) {
            int put = chars.indexOf(ch) + count;
            s = String.valueOf(chars.get(put));
            s = s + num;
            points6.add(s);
            unavailablePoints6.add(s);
            System.out.println("UN ::" + unavailablePoints6.get(unavailablePoints6.size() - 1));
            if (num != 10) unavailablePoints6.add(String.valueOf(s.charAt(0)) + (num + 1));
            System.out.println("UN ::" + unavailablePoints6.get(unavailablePoints6.size() - 1));
            if (num != 1) unavailablePoints6.add(String.valueOf(s.charAt(0)) + (num - 1));
            System.out.println("UN ::" + unavailablePoints6.get(unavailablePoints6.size() - 1));
            count++;
            System.out.println(s);
        }
        if (s.charAt(0) != 'з')
            unavailablePoints6.add(String.valueOf(chars.get(chars.indexOf(s.charAt(0)) + 1)) + num);// в кінці корабля
        System.out.println("UN placeHorizontalInRight::" + unavailablePoints6.get(unavailablePoints6.size() - 1));
        int temp = s.charAt(1);
        if(s.length() > 2) temp = Integer.parseInt(String.valueOf(s.charAt(1) + s.charAt(2)));
        if (s.charAt(0) != 'з' && temp != 10) {// правий верхній кут
            char t = chars.get(chars.indexOf(s.charAt(0)) + 1);
            int te = s.charAt(1);
            if(s.length()>2)  {
                te = 9;
            }
             te = te - 1;
            String str = String.valueOf(t) + te;
            unavailablePoints6.add(str);
            System.out.println("UN 107 ------" + unavailablePoints6.get(unavailablePoints6.size() - 1));
        }

        if (s.charAt(0) != 'з' && (Integer.parseInt(String.valueOf(s.charAt(1)))) != 10) { // правий нижній кут
            char t = chars.get(chars.indexOf(s.charAt(0)) + 1);
            int te = Integer.parseInt(String.valueOf(s.charAt(1)));
            if(s.length() > 2) te = 9;
            te++;
            String str = String.valueOf(t) + te;
            unavailablePoints6.add(str);
            System.out.println("UN  ------" + unavailablePoints6.get(unavailablePoints6.size() - 1));
        }
    }

    private void placeHorizontalInBothSides(char ch, int num, int placeRight) {
        int count = 1;
        String s = "";
        // помістити справа
        if (placeRight > 0) {
            for (int i = 0; i < placeRight; i++) {
                int put = chars.indexOf(ch) + count;
                s = String.valueOf(chars.get(put));
                s = s + num;
                points6.add(s);
                unavailablePoints6.add(s);
                System.out.println("UN ::" + unavailablePoints6.get(unavailablePoints6.size() - 1));
                if (num != 10) unavailablePoints6.add(String.valueOf(s.charAt(0)) + (num + 1));
                System.out.println("UN ::" + unavailablePoints6.get(unavailablePoints6.size() - 1));
                if (num != 1) unavailablePoints6.add(String.valueOf(s.charAt(0)) + (num - 1));
                System.out.println("UN ::" + unavailablePoints6.get(unavailablePoints6.size() - 1));
                count++;
                System.out.println(s);
            }
            int tempInt =  (Integer.parseInt(String.valueOf(s.charAt(1))));
            if(s.length() > 2) tempInt = 10;
            if (s.charAt(0) != 'з' && tempInt != 10) {// правий верхній кут
                char t = chars.get(chars.indexOf(s.charAt(0)) + 1);
                int te = Integer.parseInt(String.valueOf(s.charAt(1))) - 1;
                String temp = String.valueOf(t) + te;
                unavailablePoints6.add(temp);
                System.out.println("UN 143 ------" + unavailablePoints6.get(unavailablePoints6.size() - 1));
            }

            if (s.charAt(0) != 'з' && tempInt != 10) { // правий нижній кут
                char t = chars.get(chars.indexOf(s.charAt(0)) + 1);
                int te = Integer.parseInt(String.valueOf(s.charAt(1))) + 1;
                String temp = String.valueOf(t) + te;
                unavailablePoints6.add(temp);
                System.out.println("UN 151 ------" + unavailablePoints6.get(unavailablePoints6.size() - 1));
            }

        }


        if (placeRight > 0 && s.charAt(0) != 'з')
            unavailablePoints6.add(String.valueOf(chars.get(chars.indexOf(s.charAt(0)) + 1)) + num);// в кінці корабля
        System.out.println("UN 139::" + unavailablePoints6.get(unavailablePoints6.size() - 1));

        count = 1;
        int placeLeft = 6 - placeRight - 1;
        for (int i = 0; i < placeLeft; i++) {
            int put = chars.indexOf(ch) - count;
            s = String.valueOf(chars.get(put));
            s = s + num;
            points6.add(s);
            unavailablePoints6.add(s);
            System.out.println("UN 149::" + unavailablePoints6.get(unavailablePoints6.size() - 1));

            if (num != 1) { // зробити недоступною точку над бананом
                char temp = s.charAt(0);
                int next = Integer.parseInt(String.valueOf(s.charAt(1)));
                if(s.length() > 2) {
                    next = 10;
                }
                 next = next - 1;
                unavailablePoints6.add(String.valueOf(temp) + next);
                System.out.println("UN 155::" + String.valueOf(temp) + next);
            }

            if (num != 10) { // зробити недоступною точку під бананом
                char temp = s.charAt(0);
                int next = Integer.parseInt(String.valueOf(s.charAt(1))) + 1;
                unavailablePoints6.add(String.valueOf(temp) + next);
                System.out.println("UN 162 ::" + unavailablePoints6.get(unavailablePoints6.size() - 1));
            }
            count++;
            System.out.println(s);
        }

        if (s.charAt(0) != 'а' && num != 1) { // лівий верхній кут
            char t = chars.get(chars.indexOf(s.charAt(0)) - 1);
            int te = num - 1;
            String temp = String.valueOf(t) + te;
            unavailablePoints6.add(temp);
            System.out.println("UN 192 ------" + unavailablePoints6.get(unavailablePoints6.size() - 1));
        }

        if (s.charAt(0) != 'а' && num != 10) { // лівий нижній кут
            char t = chars.get(chars.indexOf(s.charAt(0)) - 1);
            int te = num + 1;
            String temp = String.valueOf(t) + te;
            unavailablePoints6.add(temp);
            System.out.println("UN 200 ------" + unavailablePoints6.get(unavailablePoints6.size() - 1));
        }


        if (s.charAt(0) != 'а') {
            unavailablePoints6.add(String.valueOf(chars.get(chars.indexOf(s.charAt(0)) - 1)) + num);
            System.out.println("UN 170::" + unavailablePoints6.get(unavailablePoints6.size() - 1));
        }
    }

    private boolean placeVertical(int sizeOfBanana, char randomCh, String randomNum, int numberOfBanana) { // розташувати банан вертикально
        String s = String.valueOf(randomCh) + randomNum;
        boolean down = false;
        boolean ch = !checkPlace(s);
     //   if (!checkPlace(s)) return false;
        if(sizeOfBanana == 6){
            unavailablePoints6.add(s);
            makeUnPlaceInSidesVertical(s, 6, 1);
        }
        if(sizeOfBanana == 4){
            if(numberOfBanana == 1){
                points4.add(s);// додаємо початкову точку
                unavailablePoints4.add(s);
                makeUnPlaceInSidesVertical(s, 4, 1);
            }
        }
        System.out.println(s);
        System.out.println("UN ---------------" + s);

        int placeUp = Integer.parseInt(randomNum) - 1;
        if(placeUp < (sizeOfBanana - 1)){
            down = true;
        }

            int count = 1;
            if (placeUp > 0) {
                for (int i = 0; i < sizeOfBanana -1; i++) { // помістити згори
                    int t = Integer.parseInt(randomNum) - count;
                    if(t < 1) break;
                    s = String.valueOf(randomCh) + t;
                 //   if (!checkPlace(s)) return false;
                    if (sizeOfBanana == 6) {
                        points6.add(s);
                        unavailablePoints6.add(s);
                        makeUnPlaceInSidesVertical(s, 6, 1);
                    }
                    if (sizeOfBanana == 4) {
                        if (numberOfBanana == 1) {
                            points4.add(s);
                            unavailablePoints4.add(s);
                            makeUnPlaceInSidesVertical(s, 4, 1);
                        }
                    }
                    System.out.println(s);
                    System.out.println("UN ---------------" + s);
                    count++;
                }
            }

                if(down) {
                    int placeDown = sizeOfBanana - 1 - placeUp;
                    if (placeDown > 0) {
                        count = 1;
                        for (int i = 0; i < placeDown; i++) { // помістити знизу
                            int t = Integer.parseInt(randomNum) + count;
                            s = String.valueOf(randomCh) + t;
                        //    if (!checkPlace(s)) return false;
                            if(sizeOfBanana == 6){
                                points6.add(s);
                                unavailablePoints6.add(s);
                                makeUnPlaceInSidesVertical(s, 6, 1);
                            }
                            if (sizeOfBanana == 4) {
                                if (numberOfBanana == 1) {
                                    points4.add(s);
                                    unavailablePoints4.add(s);
                                    makeUnPlaceInSidesVertical(s, 4, 1);
                                }
                            }
                            System.out.println(s);
                            System.out.println("UN ---------------" + s);
                            count++;
                        }
                    }
                }


        return true;
    }

    private boolean checkPlace(String s) { // повертає true, якщо місце вільне
        for (int i = 0; i < unavailablePoints6.size(); i++) {
            if (s.equals(unavailablePoints6.get(i))) return false;
        }
        for (int i = 0; i < unavailablePoints4.size(); i++) {
            if (s.equals(unavailablePoints4.get(i))) return false;
        }
        for (int i = 0; i < unavailablePoints4n2.size(); i++) {
            if (s.equals(unavailablePoints4n2.get(i))) return false;
        }
        return true;
    }

    private void makeUnPlaceInSidesVertical(String place, int sizeOfBanana, int numberOfBanana) {
        // чотирипалубний
        if (place.charAt(0) != 'а') { // left side
            char t = chars.get(chars.indexOf(place.charAt(0)) - 1);
            String s = String.valueOf(t) + place.charAt(1);
            if(s.length() == 3) s = s + place.charAt(2);

            if (sizeOfBanana == 4) {
                if (numberOfBanana == 1) unavailablePoints4.add(s);
                else if (numberOfBanana == 2) unavailablePoints4n2.add(s);
            }
            System.out.println("UN -------------" + s);
        }

        if (place.charAt(0) != 'з') { // right side
            char t = chars.get(chars.indexOf(place.charAt(0)) + 1);
            String s = String.valueOf(t) + place.charAt(1);
            if(s.length() == 3) s = s + place.charAt(2);
            if (sizeOfBanana == 4) {
                if (numberOfBanana == 1) unavailablePoints4.add(s);
                else if (numberOfBanana == 2) unavailablePoints4n2.add(s);
            }
            System.out.println("UN -------------" + s);
        }

    }

    private String makeUnPlaceLeftUp(String place){ // зліва зверху, returns place which has to be unavailable
        int num = place.charAt(1);
        if(place.length() > 2) num = 10;
        if(place.charAt(0) != 'а' && num != 1){
            char ch = chars.get(chars.indexOf(place.charAt(0)) - 1);
            num--;
            return String.valueOf(ch) + num;
        }
        return "";
    }
}
