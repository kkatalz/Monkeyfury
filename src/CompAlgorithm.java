import java.io.IOException;
import java.util.*;
import java.util.logging.*;

// to create computer's algorithm for placement bananas
public class CompAlgorithm {
    private static final Logger LOGGER = Logger.getLogger(CompAlgorithm.class.getName());
    private ArrayList<Integer> numbers;
    private ArrayList<Character> chars;
    private ArrayList<String> points6, points4, points4n2, unavailablePoints6, unavailablePoints4, unavailablePoints4n2;
    private ArrayList<String> points3n1, unavailablePoints3n1, points3n2, unavailablePoints3n2, points3n3, unavailablePoints3n3;
    private ArrayList<String> points2n1, unavailablePoints2n1, points2n2, unavailablePoints2n2, points2n3, unavailablePoints2n3; // letter and number
    private ArrayList<String> points2n4, unavailablePoints2n4;
    private int countUn;
    private ArrayList<String> unavailablePointsForShooting = new ArrayList<>();
    private Map<String, String> potentionalPlacesForShoot = new HashMap<>();
    // for demo version
    ArrayList<String> coordinatesDemo = new ArrayList<>();
    private String coordinateForShoot;



    public static void main(String[] args) {
        new CompAlgorithm();
    }

    CompAlgorithm (){
        setup();
        boolean b;
        do {
            b = placeBananas();
        } while(!b);
        setLogger();

        // demo
        coordinatesDemo.add("a1");
        coordinatesDemo.add("b1");
        // demo
    }

    private void setup() {
        numbers = new ArrayList<>();
        chars = new ArrayList<>();
        points6 = new ArrayList<>();
        unavailablePoints6 = new ArrayList<>();
        points4 = new ArrayList<>();
        unavailablePoints4 = new ArrayList<>();
        points4n2 = new ArrayList<>();
        unavailablePoints4n2 = new ArrayList<>();
        points3n1 = new ArrayList<>();
        unavailablePoints3n1 = new ArrayList<>();
        points3n2 = new ArrayList<>();
        unavailablePoints3n2 = new ArrayList<>();
        points3n3 = new ArrayList<>();
        unavailablePoints3n3 = new ArrayList<>();
        points2n1 = new ArrayList<>();
        unavailablePoints2n1 = new ArrayList<>();
        points2n2 = new ArrayList<>();
        unavailablePoints2n2 = new ArrayList<>();
        points2n3 = new ArrayList<>();
        unavailablePoints2n3 = new ArrayList<>();
        points2n4 = new ArrayList<>();
        unavailablePoints2n4 = new ArrayList<>();

        for (int i = 1; i < 11; i++) {
            numbers.add(i);
        }
        chars.add('a');
        chars.add('b');
        chars.add('c');
        chars.add('d');
        chars.add('e');
        chars.add('f');
        chars.add('g');
        chars.add('h');
        chars.add('i');
        chars.add('j');
        // "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"
    }

    public boolean placeBananas() {
        Random random = new Random();
        // порахувати скільки невдалих спроб було розташувати банани
        countUn = 0;

        // шестипалубний
        int r = random.nextInt(1, 11);
        int r2 = random.nextInt(1, 11);
        String s = String.valueOf(chars.get(r2 - 1));
        s = s + r; // r - number, s - letter

        // 1 - горизонталь, 2 - вертикаль
        int horVer = random.nextInt(1, 3);
        if (horVer == 1) { // шестипалубний
            placeHorizontalInBothSides(6, 1, s.charAt(0), r);
        } else if (horVer == 2) {
            String t = String.valueOf(s.charAt(1));
            if (s.length() > 2) t = t + s.charAt(2);
            t = t.trim();
            placeVertical(6, s.charAt(0), t, 1);
        }


        // булева зміна щоб згенерувати ще одну координату за необхідності
        boolean next = false;
        do {
            // перший чотирипалубний
            r = random.nextInt(1, 11);
            r2 = random.nextInt(1, 11);
            s = String.valueOf(chars.get(r2 - 1));
            s = s + r; // r - number, s - letter
            horVer = random.nextInt(1, 3);

            if (horVer == 1) { // горизонтально
                next = placeHorizontalInBothSides(4, 1, s.charAt(0), r);
            } else if (horVer == 2) {
                String t = String.valueOf(s.charAt(1));
                if (s.length() > 2) t = t + s.charAt(2);
                t = t.trim();
                next = placeVertical(4, s.charAt(0), t, 1);
            }
            if (!next) {
                clearArray(4, 1);
                countUn++;
                if(countUn >= 200){
                    clearEveryArray();
                    setLogger();
                    return false;
                }
            }
        } while (!next);

        do {
            // другий чотирипалубний
            r = random.nextInt(1, 11);
            r2 = random.nextInt(1, 11);
            s = String.valueOf(chars.get(r2 - 1));
            s = s + r; // r - number, s - letter
            horVer = random.nextInt(1, 3);

            if (horVer == 1) { // горизонтально
                next = placeHorizontalInBothSides(4, 2, s.charAt(0), r);
            } else if (horVer == 2) {
                String t = String.valueOf(s.charAt(1));
                if (s.length() > 2) t = t + s.charAt(2);
                t = t.trim();
                next = placeVertical(4, s.charAt(0), t, 2);
            }
            if (!next) {
                clearArray(4, 2);
                countUn++;
                if(countUn >= 200){
                    clearEveryArray();
                    setLogger();
                    return false;
                }
            }
        } while (!next);


        do {
            // перший трипалубний
            r = random.nextInt(1, 11);
            r2 = random.nextInt(1, 11);
            s = String.valueOf(chars.get(r2 - 1));
            s = s + r; // r - number, s - letter
            horVer = random.nextInt(1, 3);

            if (horVer == 1) { // горизонтально
                next = placeHorizontalInBothSides(3, 1, s.charAt(0), r);
            } else if (horVer == 2) {
                String t = String.valueOf(s.charAt(1));
                if (s.length() > 2) t = t + s.charAt(2);
                t = t.trim();
                next = placeVertical(3, s.charAt(0), t, 1);
            }
            if (!next) {
                clearArray(3, 1);
                countUn++;
                if(countUn >= 200){
                    clearEveryArray();
                    setLogger();

                    return false;
                }
            }
        } while (!next);

        do {
            // другий трипалубний
            r = random.nextInt(1, 11);
            r2 = random.nextInt(1, 11);
            s = String.valueOf(chars.get(r2 - 1));
            s = s + r; // r - number, s - letter
            horVer = random.nextInt(1, 3);

            if (horVer == 1) { // горизонтально
                next = placeHorizontalInBothSides(3, 2, s.charAt(0), r);
            } else if (horVer == 2) {
                String t = String.valueOf(s.charAt(1));
                if (s.length() > 2) t = t + s.charAt(2);
                t = t.trim();
                next = placeVertical(3, s.charAt(0), t, 2);
            }
            if (!next) {
                clearArray(3, 2);
                countUn++;
                if(countUn >= 200){
                    clearEveryArray();
                    setLogger();

                    return false;
                }
            }
        } while (!next);


        do {
            // третій трипалубний
            r = random.nextInt(1, 11);
            r2 = random.nextInt(1, 11);
            s = String.valueOf(chars.get(r2 - 1));
            s = s + r; // r - number, s - letter
            horVer = random.nextInt(1, 3);

            if (horVer == 1) { // горизонтально
                next = placeHorizontalInBothSides(3, 3, s.charAt(0), r);
            } else if (horVer == 2) {
                String t = String.valueOf(s.charAt(1));
                if (s.length() > 2) t = t + s.charAt(2);
                t = t.trim();
                next = placeVertical(3, s.charAt(0), t, 3);
            }
            if (!next) {
                clearArray(3, 3);
                countUn++;
                if(countUn >= 200){
                    clearEveryArray();
                    setLogger();

                    return false;
                }
            }
        } while (!next);


        do {
            // перший двопалалубний
            r = random.nextInt(1, 11);
            r2 = random.nextInt(1, 11);
            s = String.valueOf(chars.get(r2 - 1));
            s = s + r; // r - number, s - letter
            horVer = random.nextInt(1, 3);

            if (horVer == 1) { // горизонтально
                next = placeHorizontalInBothSides(2, 1, s.charAt(0), r);
            } else if (horVer == 2) {
                String t = String.valueOf(s.charAt(1));
                if (s.length() > 2) t = t + s.charAt(2);
                t = t.trim();
                next = placeVertical(2, s.charAt(0), t, 1);
            }
            if (!next) {
                clearArray(2, 1);
                countUn++;
                if(countUn >= 200){
                    clearEveryArray();
                    setLogger();

                    return false;
                }
            }
        } while (!next);

        next = false;
        do {
            // другий двопалалубний
            r = random.nextInt(1, 11);
            r2 = random.nextInt(1, 11);
            s = String.valueOf(chars.get(r2 - 1));
            s = s + r; // r - number, s - letter
            horVer = random.nextInt(1, 3);

            if (horVer == 1) { // горизонтально
                next = placeHorizontalInBothSides(2, 2, s.charAt(0), r);
            } else if (horVer == 2) {
                String t = String.valueOf(s.charAt(1));
                if (s.length() > 2) t = t + s.charAt(2);
                t = t.trim();
                next = placeVertical(2, s.charAt(0), t, 2);
            }
            if (!next) {
                clearArray(2, 2);
                countUn++;
                if(countUn >= 200){
                    clearEveryArray();
                    setLogger();
                    return false;
                }
            }
        } while (!next);


        next = false;

        do {
            // третій двопалалубний
            r = random.nextInt(1, 11);
            r2 = random.nextInt(1, 11);
            s = String.valueOf(chars.get(r2 - 1));
            s = s + r; // r - number, s - letter
            horVer = random.nextInt(1, 3);

            if (horVer == 1) { // горизонтально
                next = placeHorizontalInBothSides(2, 3, s.charAt(0), r);
            } else if (horVer == 2) {
                String t = String.valueOf(s.charAt(1));
                if (s.length() > 2) t = t + s.charAt(2);
                t = t.trim();
                next = placeVertical(2, s.charAt(0), t, 3);
            }
            if (!next) {
                clearArray(2, 3);
                countUn++;
                if(countUn >= 200){
                    clearEveryArray();
                    setLogger();
                    return false;
                }
            }
        } while (!next);


        next = false;
        do {
            // четвертий двопалалубний
            r = random.nextInt(1, 11);
            r2 = random.nextInt(1, 11);
            s = String.valueOf(chars.get(r2 - 1));
            s = s + r; // r - number, s - letter
            horVer = random.nextInt(1, 3);

            if (horVer == 1) { // горизонтально
                next = placeHorizontalInBothSides(2, 4, s.charAt(0), r);
            } else if (horVer == 2) {
                String t = String.valueOf(s.charAt(1));
                if (s.length() > 2) t = t + s.charAt(2);
                t = t.trim();
                next = placeVertical(2, s.charAt(0), t, 4);
            }
            if (!next) {
                clearArray(2, 4);
                countUn++;
                if(countUn >= 200){
                    clearEveryArray();
                    setLogger();
                    return false;
                }
            }
        } while (!next);

        System.out.println(Arrays.toString(points6.toArray()));
        System.out.println("-" + Arrays.toString(unavailablePoints6.toArray()));

        System.out.println(Arrays.toString(points4.toArray()));
        System.out.println("-" + Arrays.toString(unavailablePoints4.toArray()));

        System.out.println(Arrays.toString(points4n2.toArray()));
        System.out.println("-" + Arrays.toString(unavailablePoints4n2.toArray()));

        System.out.println(Arrays.toString(points3n1.toArray()));
        System.out.println("-" + Arrays.toString(unavailablePoints3n1.toArray()));

        System.out.println(Arrays.toString(points3n2.toArray()));
        System.out.println("-" + Arrays.toString(unavailablePoints3n2.toArray()));

        System.out.println(Arrays.toString(points3n3.toArray()));
        System.out.println("-" + Arrays.toString(unavailablePoints3n3.toArray()));
        System.out.println(Arrays.toString(points2n1.toArray()));
        System.out.println("-" + Arrays.toString(unavailablePoints2n1.toArray()));

        System.out.println(Arrays.toString(points2n2.toArray()));
        System.out.println("-" + Arrays.toString(unavailablePoints2n2.toArray()));

        System.out.println(Arrays.toString(points2n3.toArray()));
        System.out.println("-" + Arrays.toString(unavailablePoints2n3.toArray()));

        System.out.println(Arrays.toString(points2n4.toArray()));
        System.out.println("-" + Arrays.toString(unavailablePoints2n4.toArray()));

        return true;
    }

    private void clearEveryArray() {
        points6.clear();
        unavailablePoints6.clear();

        points4.clear();
        unavailablePoints4.clear();

        points4n2.clear();
        unavailablePoints4n2.clear();

        points3n1.clear();
        unavailablePoints3n1.clear();
        points3n2.clear();
        unavailablePoints3n2.clear();
        points3n3.clear();
        unavailablePoints3n3.clear();
        points2n1.clear();
        unavailablePoints2n1.clear();
        points2n2.clear();
        unavailablePoints2n2.clear();
        points2n3.clear();
        unavailablePoints2n3.clear();

        points2n4.clear();
        unavailablePoints2n4.clear();
    }

    // обробити методи makeUnPlaceDown та makeUnPlaceUp до конкретної точки в залежності від розміру та номеру банана
    private void addPointsPlace_Down_Up(String s, int sizeOfBanana, int numberOfBanana) {
        if (sizeOfBanana == 6) { //6
            unavailablePoints6.add(s);
            points6.add(s);
            unavailablePoints6.add(makeUnPlaceDown(s));
            unavailablePoints6.add(makeUnPlaceUp(s));
        } else if (sizeOfBanana == 4) {
            if (numberOfBanana == 1) { // 4n1
                unavailablePoints4.add(s);
                points4.add(s);
                unavailablePoints4.add(makeUnPlaceDown(s));
                unavailablePoints4.add(makeUnPlaceUp(s));
            } else if (numberOfBanana == 2) {  // 4n2
                unavailablePoints4n2.add(s);
                points4n2.add(s);
                unavailablePoints4n2.add(makeUnPlaceDown(s));
                unavailablePoints4n2.add(makeUnPlaceUp(s));
            }
        } else if (sizeOfBanana == 3) {
            if (numberOfBanana == 1) { //3n1
                unavailablePoints3n1.add(s);
                points3n1.add(s);
                unavailablePoints3n1.add(makeUnPlaceDown(s));
                unavailablePoints3n1.add(makeUnPlaceUp(s));
            } else if (numberOfBanana == 2) { // 3n2
                unavailablePoints3n2.add(s);
                points3n2.add(s);
                unavailablePoints3n2.add(makeUnPlaceDown(s));
                unavailablePoints3n2.add(makeUnPlaceUp(s));
            } else if (numberOfBanana == 3) { // 3n3
                unavailablePoints3n3.add(s);
                points3n3.add(s);
                unavailablePoints3n3.add(makeUnPlaceDown(s));
                unavailablePoints3n3.add(makeUnPlaceUp(s));
            }
        } else if (sizeOfBanana == 2) {
            if (numberOfBanana == 1) { // 2n1
                unavailablePoints2n1.add(s);
                points2n1.add(s);
                unavailablePoints2n1.add(makeUnPlaceDown(s));
                unavailablePoints2n1.add(makeUnPlaceUp(s));
            } else if (numberOfBanana == 2) { // 2n2
                unavailablePoints2n2.add(s);
                points2n2.add(s);
                unavailablePoints2n2.add(makeUnPlaceDown(s));
                unavailablePoints2n2.add(makeUnPlaceUp(s));
            } else if (numberOfBanana == 3) { //2n3
                unavailablePoints2n3.add(s);
                points2n3.add(s);
                unavailablePoints2n3.add(makeUnPlaceDown(s));
                unavailablePoints2n3.add(makeUnPlaceUp(s));
            } else if (numberOfBanana == 4) { // 2n4
                unavailablePoints2n4.add(s);
                points2n4.add(s);
                unavailablePoints2n4.add(makeUnPlaceDown(s));
                unavailablePoints2n4.add(makeUnPlaceUp(s));
            }
        }
    }

    // використати методи makeUnPlaceLeftDown, makeUnPlaceLeftUp, makeUnPlaceLeft для відповідного масиву точок
    private void addPointsPlace_LeftDown_LeftUp_Left(String s, int sizeOfBanana, int numberOfBanana) {
        if (sizeOfBanana == 6) { //6
            unavailablePoints6.add(makeUnPlaceLeftDown(s));
            unavailablePoints6.add(makeUnPlaceLeftUp(s));
            unavailablePoints6.add(makeUnPlaceLeft(s));
        } else if (sizeOfBanana == 4) {
            if (numberOfBanana == 1) { // 4n1
                unavailablePoints4.add(makeUnPlaceLeftDown(s));
                unavailablePoints4.add(makeUnPlaceLeftUp(s));
                unavailablePoints4.add(makeUnPlaceLeft(s));
            } else if (numberOfBanana == 2) { // 4n2
                unavailablePoints4n2.add(makeUnPlaceLeftDown(s));
                unavailablePoints4n2.add(makeUnPlaceLeftUp(s));
                unavailablePoints4n2.add(makeUnPlaceLeft(s));
            }
        } else if (sizeOfBanana == 3) {
            if (numberOfBanana == 1) { // 3n1
                unavailablePoints3n1.add(makeUnPlaceLeftDown(s));
                unavailablePoints3n1.add(makeUnPlaceLeftUp(s));
                unavailablePoints3n1.add(makeUnPlaceLeft(s));
            } else if (numberOfBanana == 2) { // 3n2
                unavailablePoints3n2.add(makeUnPlaceLeftDown(s));
                unavailablePoints3n2.add(makeUnPlaceLeftUp(s));
                unavailablePoints3n2.add(makeUnPlaceLeft(s));
            } else if (numberOfBanana == 3) {// 3n3
                unavailablePoints3n3.add(makeUnPlaceLeftDown(s));
                unavailablePoints3n3.add(makeUnPlaceLeftUp(s));
                unavailablePoints3n3.add(makeUnPlaceLeft(s));
            }
        } else if (sizeOfBanana == 2) {
            if (numberOfBanana == 1) { // 2n1
                unavailablePoints2n1.add(makeUnPlaceLeftDown(s));
                unavailablePoints2n1.add(makeUnPlaceLeftUp(s));
                unavailablePoints2n1.add(makeUnPlaceLeft(s));
            } else if (numberOfBanana == 2) { // 2n2
                unavailablePoints2n2.add(makeUnPlaceLeftDown(s));
                unavailablePoints2n2.add(makeUnPlaceLeftUp(s));
                unavailablePoints2n2.add(makeUnPlaceLeft(s));
            } else if (numberOfBanana == 3) { // 2n3
                unavailablePoints2n3.add(makeUnPlaceLeftDown(s));
                unavailablePoints2n3.add(makeUnPlaceLeftUp(s));
                unavailablePoints2n3.add(makeUnPlaceLeft(s));
            } else if (numberOfBanana == 4) { // 2n4
                unavailablePoints2n4.add(makeUnPlaceLeftDown(s));
                unavailablePoints2n4.add(makeUnPlaceLeftUp(s));
                unavailablePoints2n4.add(makeUnPlaceLeft(s));
            }
        }
    }

    // використати метод makeUnPlaceRightUp для точки в залежності від розміру та номеру банана
    private void addPointsPlace_RightUp(String s, int sizeOfBanana, int numberOfBanana) {
        if (sizeOfBanana == 6) {
            unavailablePoints6.add(makeUnPlaceRightUp(s));
        } else if (sizeOfBanana == 4) {
            if (numberOfBanana == 1) {
                unavailablePoints4.add(makeUnPlaceRightUp(s)); // 4n1
            } else if (numberOfBanana == 2) unavailablePoints4n2.add(makeUnPlaceRightUp(s)); // 4n2
        } else if (sizeOfBanana == 3) {
            if (numberOfBanana == 1) unavailablePoints3n1.add(makeUnPlaceRightUp(s)); // 3n1
            else if (numberOfBanana == 2) unavailablePoints3n2.add(makeUnPlaceRightUp(s)); // 3n2
            else if (numberOfBanana == 3) unavailablePoints3n3.add(makeUnPlaceRightUp(s)); // 3n3
        } else if (sizeOfBanana == 2) {
            if (numberOfBanana == 1) unavailablePoints2n1.add(makeUnPlaceRightUp(s));
            else if (numberOfBanana == 2) unavailablePoints2n2.add(makeUnPlaceRightUp(s));
            else if (numberOfBanana == 3) unavailablePoints2n3.add(makeUnPlaceRightUp(s));
            else if (numberOfBanana == 4) unavailablePoints2n4.add(makeUnPlaceRightUp(s));
        }
    }

    private void addPointsPlace_RightDown(String s, int sizeOfBanana, int numberOfBanana) {
        if (sizeOfBanana == 6) {
            unavailablePoints6.add(makeUnPlaceRightDown(s));
        } else if (sizeOfBanana == 4) {
            if (numberOfBanana == 1) {
                unavailablePoints4.add(makeUnPlaceRightDown(s)); // 4n1
            } else if (numberOfBanana == 2) unavailablePoints4n2.add(makeUnPlaceRightDown(s)); // 4n2
        } else if (sizeOfBanana == 3) {
            if (numberOfBanana == 1) unavailablePoints3n1.add(makeUnPlaceRightDown(s)); // 3n1
            else if (numberOfBanana == 2) unavailablePoints3n2.add(makeUnPlaceRightDown(s)); // 3n2
            else if (numberOfBanana == 3) unavailablePoints3n3.add(makeUnPlaceRightDown(s)); // 3n3
        } else if (sizeOfBanana == 2) {
            if (numberOfBanana == 1) unavailablePoints2n1.add(makeUnPlaceRightDown(s));
            else if (numberOfBanana == 2) unavailablePoints2n2.add(makeUnPlaceRightDown(s));
            else if (numberOfBanana == 3) unavailablePoints2n3.add(makeUnPlaceRightDown(s));
            else if (numberOfBanana == 4) unavailablePoints2n4.add(makeUnPlaceRightDown(s));
        }
    }

    private void addPointsPlace_Right(String s, int sizeOfBanana, int numberOfBanana) {
        if (sizeOfBanana == 6) {
            unavailablePoints6.add(makeUnPlaceRight(s));
        } else if (sizeOfBanana == 4) {
            if (numberOfBanana == 1) {
                unavailablePoints4.add(makeUnPlaceRight(s)); // 4n1
            } else if (numberOfBanana == 2) unavailablePoints4n2.add(makeUnPlaceRight(s)); // 4n2
        } else if (sizeOfBanana == 3) {
            if (numberOfBanana == 1) unavailablePoints3n1.add(makeUnPlaceRight(s)); // 3n1
            else if (numberOfBanana == 2) unavailablePoints3n2.add(makeUnPlaceRight(s)); // 3n2
            else if (numberOfBanana == 3) unavailablePoints3n3.add(makeUnPlaceRight(s)); // 3n3
        } else if (sizeOfBanana == 2) {
            if (numberOfBanana == 1) unavailablePoints2n1.add(makeUnPlaceRight(s));
            else if (numberOfBanana == 2) unavailablePoints2n2.add(makeUnPlaceRight(s));
            else if (numberOfBanana == 3) unavailablePoints2n3.add(makeUnPlaceRight(s));
            else if (numberOfBanana == 4) unavailablePoints2n4.add(makeUnPlaceRight(s));
        }
    }

    private boolean placeHorizontalInBothSides(int sizeOfBanana, int numberOfBanana, char ch, int num) {
        // вирахувати місце справа
        int placeRight;
        int placeRightAll = findPlaceRightHorizontal(ch);
        if (placeRightAll >= sizeOfBanana - 1) {
            placeRight = sizeOfBanana - 1;
        } else placeRight = placeRightAll;
        String s = String.valueOf(ch) + num;
        // перевірка чи місце зайняте
        if (!checkPlace(s)) return false;
        addPointsPlace_Down_Up(s, sizeOfBanana, numberOfBanana);

        int count = 1;
        // вирахувати чи знадобиться поміщати банан зліва
        int placeLeft = sizeOfBanana - placeRight - 1;
        if (placeLeft == 0) {
            addPointsPlace_LeftDown_LeftUp_Left(s, sizeOfBanana, numberOfBanana);
        }
        // помістити справа, якщо є можливість
        for (int i = 0; i < placeRight; i++) {
            int put = chars.indexOf(ch) + count;
            s = String.valueOf(chars.get(put));
            s = s + num;
            // перевірка
            if (!checkPlace(s)) return false;
            // додати точку до масиву, зробити її недійсною, зробити недійсними місця згори та знизу від точки
            addPointsPlace_Down_Up(s, sizeOfBanana, numberOfBanana);

            if (i == placeRight - 1) addPointsPlace_Right(s, sizeOfBanana, numberOfBanana); // new
            count++;
        }

        removeDuplicates(sizeOfBanana, numberOfBanana);
        addPointsPlace_RightUp(s, sizeOfBanana, numberOfBanana);
        addPointsPlace_RightDown(s, sizeOfBanana, numberOfBanana);

        if (placeRight > 0) addPointsPlace_Right(s, sizeOfBanana, numberOfBanana); // в кінці корабля


        count = 1;

        // помістити зліва, якщо необхідно
        for (int i = 0; i < placeLeft; i++) {
            int put = chars.indexOf(ch) - count;
            s = String.valueOf(chars.get(put));
            s = s + num;
            // перевірка
            if (!checkPlace(s)) return false;

            /**
             if (sizeOfBanana == 6) {
             points6.add(s);
             unavailablePoints6.add(s);
             unavailablePoints6.add(makeUnPlaceUp(s));
             unavailablePoints6.add(makeUnPlaceDown(s));
             }
             **/

            // додати точку, зробити її недійсною, зробити недійсними точки знизу та згори
            addPointsPlace_Down_Up(s, sizeOfBanana, numberOfBanana);

            count++;
        }

        // лівий верхній та нижній кут

        /**
         if (sizeOfBanana == 6) {
         unavailablePoints6.add(makeUnPlaceLeftUp(s));
         unavailablePoints6.add(makeUnPlaceLeftDown(s));
         }
         // зліва від банана
         if (sizeOfBanana == 6) {
         unavailablePoints6.add(makeUnPlaceLeft(s));
         }
         **/

        addPointsPlace_LeftDown_LeftUp_Left(s, sizeOfBanana, numberOfBanana);

        removeDuplicates(sizeOfBanana, numberOfBanana);
        return true;
    }

    private String makeUnPlaceRight(String s) {
        int i = Character.getNumericValue(s.charAt(1));
        if (s.length() > 2) i = 10;
        if (s.charAt(0) == 'j') return "";
        char ch = chars.get(chars.indexOf(s.charAt(0)) + 1);
        String str = String.valueOf(ch) + i;
        return str;
    }

    private String makeUnPlaceLeft(String s) {
        char ch;
        int i = Character.getNumericValue(s.charAt(1));
        if (s.length() > 2) i = 10;
        if (i == 0 || s.charAt(0) == 'a') return "";
        ch = chars.get(chars.indexOf(s.charAt(0)) - 1);
        return String.valueOf(ch) + i;
    }

    private int findPlaceRightHorizontal(char ch) {
        int index = chars.indexOf(ch) + 1;
        return 10 - index;
    }

    private void addPoints(String s, int sizeOfBanana, int numberOfBanana) {
        if (sizeOfBanana == 6) {
            points6.add(s);
            unavailablePoints6.add(s);
        } else if (sizeOfBanana == 4) {
            if (numberOfBanana == 1) { // 4n1
                points4.add(s);
                unavailablePoints4.add(s);
            } else if (numberOfBanana == 2) { // 4n2
                points4n2.add(s);
                unavailablePoints4n2.add(s);
            }
        } else if (sizeOfBanana == 3) {
            if (numberOfBanana == 1) { // 3n1
                points3n1.add(s);
                unavailablePoints3n1.add(s);
            } else if (numberOfBanana == 2) { // 3n2
                points3n2.add(s);
                unavailablePoints3n2.add(s);
            } else if (numberOfBanana == 3) { // 3n3
                points3n3.add(s);
                unavailablePoints3n3.add(s);
            }
        } else if (sizeOfBanana == 2) {
            if (numberOfBanana == 1) { // 2n1
                points2n1.add(s);
                unavailablePoints2n1.add(s);
            } else if (numberOfBanana == 2) { // 2n2
                points2n2.add(s);
                unavailablePoints2n2.add(s);
            } else if (numberOfBanana == 3) { // 2n3
                points2n3.add(s);
                unavailablePoints2n3.add(s);
            } else if (numberOfBanana == 4) { // 2n4
                points2n4.add(s);
                unavailablePoints2n4.add(s);
            }
        }
    }

    // викорсистати метод
    private void addPointsPlace_LeftDown(String s, int sizeOfBanana, int numberOfBanana) {
        if (sizeOfBanana == 6) {
            unavailablePoints6.add(makeUnPlaceLeftDown(s));
        } else if (sizeOfBanana == 4) {
            if (numberOfBanana == 1) {
                unavailablePoints4.add(makeUnPlaceLeftDown(s)); // 4n1
            } else if (numberOfBanana == 2) unavailablePoints4n2.add(makeUnPlaceLeftDown(s)); // 4n2
        } else if (sizeOfBanana == 3) {
            if (numberOfBanana == 1) unavailablePoints3n1.add(makeUnPlaceLeftDown(s)); // 3n1
            else if (numberOfBanana == 2) unavailablePoints3n2.add(makeUnPlaceLeftDown(s)); // 3n2
            else if (numberOfBanana == 3) unavailablePoints3n3.add(makeUnPlaceLeftDown(s)); // 3n3
        } else if (sizeOfBanana == 2) {
            if (numberOfBanana == 1) unavailablePoints2n1.add(makeUnPlaceLeftDown(s));
            else if (numberOfBanana == 2) unavailablePoints2n2.add(makeUnPlaceLeftDown(s));
            else if (numberOfBanana == 3) unavailablePoints2n3.add(makeUnPlaceLeftDown(s));
            else if (numberOfBanana == 4) unavailablePoints2n4.add(makeUnPlaceLeftDown(s));
        }
    }

    private void addPointsPlace_Down(String s, int sizeOfBanana, int numberOfBanana) {
        if (sizeOfBanana == 6) {
            unavailablePoints6.add(makeUnPlaceDown(s));
        } else if (sizeOfBanana == 4) {
            if (numberOfBanana == 1) {
                unavailablePoints4.add(makeUnPlaceDown(s)); // 4n1
            } else if (numberOfBanana == 2) unavailablePoints4n2.add(makeUnPlaceDown(s)); // 4n2
        } else if (sizeOfBanana == 3) {
            if (numberOfBanana == 1) unavailablePoints3n1.add(makeUnPlaceDown(s)); // 3n1
            else if (numberOfBanana == 2) unavailablePoints3n2.add(makeUnPlaceDown(s)); // 3n2
            else if (numberOfBanana == 3) unavailablePoints3n3.add(makeUnPlaceDown(s)); // 3n3
        } else if (sizeOfBanana == 2) {
            if (numberOfBanana == 1) unavailablePoints2n1.add(makeUnPlaceDown(s));
            else if (numberOfBanana == 2) unavailablePoints2n2.add(makeUnPlaceDown(s));
            else if (numberOfBanana == 3) unavailablePoints2n3.add(makeUnPlaceDown(s));
            else if (numberOfBanana == 4) unavailablePoints2n4.add(makeUnPlaceDown(s));
        }
    }

    private void addPointsPlace_LeftUp(String s, int sizeOfBanana, int numberOfBanana) {
        if (sizeOfBanana == 6) {
            unavailablePoints6.add(makeUnPlaceLeftUp(s));
        } else if (sizeOfBanana == 4) {
            if (numberOfBanana == 1) {
                unavailablePoints4.add(makeUnPlaceLeftUp(s)); // 4n1
            } else if (numberOfBanana == 2) unavailablePoints4n2.add(makeUnPlaceLeftUp(s)); // 4n2
        } else if (sizeOfBanana == 3) {
            if (numberOfBanana == 1) unavailablePoints3n1.add(makeUnPlaceLeftUp(s)); // 3n1
            else if (numberOfBanana == 2) unavailablePoints3n2.add(makeUnPlaceLeftUp(s)); // 3n2
            else if (numberOfBanana == 3) unavailablePoints3n3.add(makeUnPlaceLeftUp(s)); // 3n3
        } else if (sizeOfBanana == 2) {
            if (numberOfBanana == 1) unavailablePoints2n1.add(makeUnPlaceLeftUp(s));
            else if (numberOfBanana == 2) unavailablePoints2n2.add(makeUnPlaceLeftUp(s));
            else if (numberOfBanana == 3) unavailablePoints2n3.add(makeUnPlaceLeftUp(s));
            else if (numberOfBanana == 4) unavailablePoints2n4.add(makeUnPlaceLeftUp(s));
        }
    }

    private void addPointsPlace_Up(String s, int sizeOfBanana, int numberOfBanana) {
        if (sizeOfBanana == 6) {
            unavailablePoints6.add(makeUnPlaceUp(s));
        } else if (sizeOfBanana == 4) {
            if (numberOfBanana == 1) {
                unavailablePoints4.add(makeUnPlaceUp(s)); // 4n1
            } else if (numberOfBanana == 2) unavailablePoints4n2.add(makeUnPlaceUp(s)); // 4n2
        } else if (sizeOfBanana == 3) {
            if (numberOfBanana == 1) unavailablePoints3n1.add(makeUnPlaceUp(s)); // 3n1
            else if (numberOfBanana == 2) unavailablePoints3n2.add(makeUnPlaceUp(s)); // 3n2
            else if (numberOfBanana == 3) unavailablePoints3n3.add(makeUnPlaceUp(s)); // 3n3
        } else if (sizeOfBanana == 2) {
            if (numberOfBanana == 1) unavailablePoints2n1.add(makeUnPlaceUp(s));
            else if (numberOfBanana == 2) unavailablePoints2n2.add(makeUnPlaceUp(s));
            else if (numberOfBanana == 3) unavailablePoints2n3.add(makeUnPlaceUp(s));
            else if (numberOfBanana == 4) unavailablePoints2n4.add(makeUnPlaceUp(s));
        }
    }

    private void clearArray(int sizeOfBanana, int numberOfBanana) {
        if (sizeOfBanana == 6) {
            points6.clear();
            unavailablePoints6.clear();
        } else if (sizeOfBanana == 4) {
            if (numberOfBanana == 1) {
                points4.clear();
                unavailablePoints4.clear();
            } else if (numberOfBanana == 2) {
                points4n2.clear();
                unavailablePoints4n2.clear();
            }
        } else if (sizeOfBanana == 3) {
            if (numberOfBanana == 1) {
                points3n1.clear();
                unavailablePoints3n1.clear();
            } else if (numberOfBanana == 2) {
                points3n2.clear();
                unavailablePoints3n2.clear();
            } else if (numberOfBanana == 3) {
                points3n3.clear();
                unavailablePoints3n3.clear();
            }
        } else if (sizeOfBanana == 2) {
            if (numberOfBanana == 1) {
                points2n1.clear();
                unavailablePoints2n1.clear();
            } else if (numberOfBanana == 2) {
                points2n2.clear();
                unavailablePoints2n2.clear();
            } else if (numberOfBanana == 3) {
                points2n3.clear();
                unavailablePoints2n3.clear();
            } else if (numberOfBanana == 4) {
                points2n4.clear();
                unavailablePoints2n4.clear();
            }
        }
    }


    private boolean placeVertical(int sizeOfBanana, char randomCh, String randomNum, int numberOfBanana) { // розташувати банан вертикально

        String s = String.valueOf(randomCh) + randomNum;
        boolean down = false;
        String resLeftDown, resRightDown;
        if (!checkPlace(s)) {
            return false;
        }

        addPoints(s, sizeOfBanana, numberOfBanana);
        makeUnPlaceInSidesVertical(s, sizeOfBanana, numberOfBanana);

        int placeUp = Integer.parseInt(randomNum) - 1;
        if (placeUp < (sizeOfBanana - 1)) {
            down = true;
        }

        if (!down) { // зарезервувати знизу по вертикалі

            addPointsPlace_RightDown(s, sizeOfBanana, numberOfBanana);
            addPointsPlace_LeftDown(s, sizeOfBanana, numberOfBanana);
            addPointsPlace_Down(s, sizeOfBanana, numberOfBanana);
        }

        int count = 1;
        if (placeUp > 0) {
            for (int i = 0; i < sizeOfBanana - 1; i++) { // помістити згори
                int t = Integer.parseInt(randomNum) - count;
                if (t < 1) break;

                s = String.valueOf(randomCh) + t;
                removeDuplicates(sizeOfBanana, numberOfBanana);
                // перевірка місця
                if (!checkPlace(s)) {
                    return false;
                }

                if (i == sizeOfBanana - 2) {// зліва та справа зверху по боках
                    addPointsPlace_LeftUp(s, sizeOfBanana, numberOfBanana);
                    addPointsPlace_RightUp(s, sizeOfBanana, numberOfBanana);
                    addPointsPlace_Up(s, sizeOfBanana, numberOfBanana);
                }

                addPoints(s, sizeOfBanana, numberOfBanana);
                makeUnPlaceInSidesVertical(s, sizeOfBanana, numberOfBanana);

                count++;
            }
        }

        if (down) {
            int placeDown = sizeOfBanana - 1 - placeUp;
            if (placeDown > 0) {
                count = 1;
                for (int i = 0; i < placeDown; i++) { // помістити знизу
                    int t = Integer.parseInt(randomNum) + count;
                    s = String.valueOf(randomCh) + t;
                    removeDuplicates(sizeOfBanana, numberOfBanana);
                    // перевірка місця
                    if (!checkPlace(s)) {
                        return false;
                    }

                    addPoints(s, sizeOfBanana, numberOfBanana);
                    makeUnPlaceInSidesVertical(s, sizeOfBanana, numberOfBanana);

                    if (i == placeDown - 1) {
                        addPointsPlace_Down(s, sizeOfBanana, numberOfBanana);
                        addPointsPlace_RightDown(s, sizeOfBanana, numberOfBanana);
                        addPointsPlace_LeftDown(s, sizeOfBanana, numberOfBanana);
                    }
                    count++;
                }
            }
        }
        return true;
    }

    private void removeDuplicates(int size, int numberOfBanana) {
        if (size == 6) {
            Set<String> s = new LinkedHashSet<>(unavailablePoints6);
            unavailablePoints6.clear();
            unavailablePoints6.addAll(s);

            s.clear();
            s.addAll(points6);
            points6.clear();
            points6.addAll(s);
            for (int i = 0; i < unavailablePoints6.size(); i++) {
                if (unavailablePoints6.get(i).equals("")) unavailablePoints6.remove(i);
            }
        } else if (size == 4) {
            if (numberOfBanana == 1) {
                Set<String> s = new LinkedHashSet<>(unavailablePoints4);
                unavailablePoints4.clear();
                unavailablePoints4.addAll(s);

                s.clear();
                s.addAll(points4);
                points4.clear();
                points4.addAll(s);
                for (int i = 0; i < unavailablePoints4.size(); i++) {
                    if (unavailablePoints4.get(i).equals("")) unavailablePoints4.remove(i);
                }
            } else if (numberOfBanana == 2) {
                Set<String> s = new LinkedHashSet<>(unavailablePoints4n2);
                unavailablePoints4n2.clear();
                unavailablePoints4n2.addAll(s);

                s.clear();
                s.addAll(points4n2);
                points4n2.clear();
                points4n2.addAll(s);
                for (int i = 0; i < unavailablePoints4n2.size(); i++) {
                    if (unavailablePoints4n2.get(i).equals("")) unavailablePoints4n2.remove(i);
                }
            }
        } else if (size == 3) {
            if (numberOfBanana == 1) {
                Set<String> s = new LinkedHashSet<>(unavailablePoints3n1);
                unavailablePoints3n1.clear();
                unavailablePoints3n1.addAll(s);

                s.clear();
                s.addAll(points3n1);
                points3n1.clear();
                points3n1.addAll(s);
                for (int i = 0; i < unavailablePoints3n1.size(); i++) {
                    if (unavailablePoints3n1.get(i).equals("")) unavailablePoints3n1.remove(i);
                }
            } else if (numberOfBanana == 2) { // 3n2
                Set<String> s = new LinkedHashSet<>(unavailablePoints3n2);
                unavailablePoints3n2.clear();
                unavailablePoints3n2.addAll(s);

                s.clear();
                s.addAll(points3n2);
                points3n2.clear();
                points3n2.addAll(s);
                for (int i = 0; i < unavailablePoints3n2.size(); i++) {
                    if (unavailablePoints3n2.get(i).equals("")) unavailablePoints3n2.remove(i);
                }
            } else if (numberOfBanana == 3) { // 3n3
                Set<String> s = new LinkedHashSet<>(unavailablePoints3n3);
                unavailablePoints3n3.clear();
                unavailablePoints3n3.addAll(s);

                s.clear();
                s.addAll(points3n3);
                points3n3.clear();
                points3n3.addAll(s);
                for (int i = 0; i < unavailablePoints3n3.size(); i++) {
                    if (unavailablePoints3n3.get(i).equals("")) unavailablePoints3n3.remove(i);
                }
            }
        } else if (size == 2) {
            if (numberOfBanana == 1) { // 2n1
                Set<String> s = new LinkedHashSet<>(unavailablePoints2n1);
                unavailablePoints2n1.clear();
                unavailablePoints2n1.addAll(s);

                s.clear();
                s.addAll(points2n1);
                points2n1.clear();
                points2n1.addAll(s);
                for (int i = 0; i < unavailablePoints2n1.size(); i++) {
                    if (unavailablePoints2n1.get(i).equals("")) unavailablePoints2n1.remove(i);
                }
            } else if (numberOfBanana == 2) {//2n2
                Set<String> s = new LinkedHashSet<>(unavailablePoints2n2);
                unavailablePoints2n2.clear();
                unavailablePoints2n2.addAll(s);

                s.clear();
                s.addAll(points2n2);
                points2n2.clear();
                points2n2.addAll(s);
                for (int i = 0; i < unavailablePoints2n2.size(); i++) {
                    if (unavailablePoints2n2.get(i).equals("")) unavailablePoints2n2.remove(i);
                }
            } else if (numberOfBanana == 3) { // 2n3
                Set<String> s = new LinkedHashSet<>(unavailablePoints2n3);
                unavailablePoints2n3.clear();
                unavailablePoints2n3.addAll(s);

                s.clear();
                s.addAll(points2n3);
                points2n3.clear();
                points2n3.addAll(s);
                for (int i = 0; i < unavailablePoints2n3.size(); i++) {
                    if (unavailablePoints2n3.get(i).equals("")) unavailablePoints2n3.remove(i);
                }
            } else if (numberOfBanana == 4) { //2n4
                Set<String> s = new LinkedHashSet<>(unavailablePoints2n4);
                unavailablePoints2n4.clear();
                unavailablePoints2n4.addAll(s);

                s.clear();
                s.addAll(points2n4);
                points2n4.clear();
                points2n4.addAll(s);
                for (int i = 0; i < unavailablePoints2n4.size(); i++) {
                    if (unavailablePoints2n4.get(i).equals("")) unavailablePoints2n4.remove(i);
                }
            }
        }
    }

    private boolean checkPlace(String s) { // повертає true, якщо місце вільне
        for (String value : unavailablePoints6) { //6
            if (s.equals(value)) {
                return false;
            }
        }
        for (String value : unavailablePoints4) { //4
            if (s.equals(value)) return false;
        }
        for (String value : unavailablePoints4n2) { // 4n2
            if (s.equals(value)) return false;
        }
        for (String value : unavailablePoints3n1) { // 3n1
            if (s.equals(value)) return false;
        }
        for (String value : unavailablePoints3n2) { // 3n2
            if (s.equals(value)) return false;
        }
        for (String value : unavailablePoints3n3) { // 3n3
            if (s.equals(value)) return false;
        }
        for (String value : unavailablePoints2n1) { // 2n1
            if (s.equals(value)) return false;
        }
        for (String value : unavailablePoints2n2) { // 2n2
            if (s.equals(value)) return false;
        }
        for (String value : unavailablePoints2n3) { // 2n3
            if (s.equals(value)) return false;
        }
        for (String value : unavailablePoints2n4) { // 2n4
            if (s.equals(value)) return false;
        }
        return true;
    }

    private void makeUnPlaceInSidesVertical(String place, int sizeOfBanana, int numberOfBanana) {
        if (place.charAt(0) != 'a') { // left side
            int num = Character.getNumericValue(place.charAt(1));
            char t = chars.get(chars.indexOf(place.charAt(0)) - 1);
            if (place.length() > 2) num = 10;
            String s = String.valueOf(t) + num;
            //  if (s.length() == 3) s = s + place.charAt(2);

            if (sizeOfBanana == 6) unavailablePoints6.add(s);

            if (sizeOfBanana == 4) {
                if (numberOfBanana == 1) unavailablePoints4.add(s);
                else if (numberOfBanana == 2) unavailablePoints4n2.add(s);
            } else if (sizeOfBanana == 3) {
                if (numberOfBanana == 1) unavailablePoints3n1.add(s);
                else if (numberOfBanana == 2) unavailablePoints3n2.add(s);
                else if (numberOfBanana == 3) unavailablePoints3n3.add(s);
            } else if (sizeOfBanana == 2) {
                if (numberOfBanana == 1) unavailablePoints2n1.add(s);
                else if (numberOfBanana == 2) unavailablePoints2n2.add(s);
                else if (numberOfBanana == 3) unavailablePoints2n3.add(s);
                else if (numberOfBanana == 4) unavailablePoints2n4.add(s);
            }


        }

        if (place.charAt(0) != 'j') { // right side
            char t = chars.get(chars.indexOf(place.charAt(0)) + 1);
            int num = Character.getNumericValue(place.charAt(1));
            if (place.length() > 2) num = 10;
            String s = String.valueOf(t) + num;
            //   if (s.length() == 3) s = s + place.charAt(2);
            if (sizeOfBanana == 6) unavailablePoints6.add(s);

            if (sizeOfBanana == 4) {
                if (numberOfBanana == 1) unavailablePoints4.add(s);
                else if (numberOfBanana == 2) unavailablePoints4n2.add(s);
            } else if (sizeOfBanana == 3) {
                if (numberOfBanana == 1) unavailablePoints3n1.add(s);
                else if (numberOfBanana == 2) unavailablePoints3n2.add(s);
                else if (numberOfBanana == 3) unavailablePoints3n3.add(s);
            } else if (sizeOfBanana == 2) {
                if (numberOfBanana == 1) unavailablePoints2n1.add(s);
                else if (numberOfBanana == 2) unavailablePoints2n2.add(s);
                else if (numberOfBanana == 3) unavailablePoints2n3.add(s);
                else if (numberOfBanana == 4) unavailablePoints2n4.add(s);
            }
        }

    }

    private String makeUnPlaceLeftUp(String place) { // зліва зверху, returns place which has to be unavailable
        int num = Integer.parseInt(String.valueOf(place.charAt(1)));
        if (place.length() > 2) num = 10;
        if (place.charAt(0) != 'a' && num != 1) {
            char ch = chars.get(chars.indexOf(place.charAt(0)) - 1);
            num--;
            return String.valueOf(ch) + num;
        }
        return "";
    }

    private String makeUnPlaceLeftDown(String place) { // зліва знизу
        int num = Integer.parseInt(String.valueOf(place.charAt(1)));
        if (place.length() > 2) num = 10;
        if (place.charAt(0) != 'a' && num != 10) {
            char ch = chars.get(chars.indexOf(place.charAt(0)) - 1);
            num++;
            return String.valueOf(ch) + num;
        }
        return "";
    }

    private String makeUnPlaceRightUp(String place) { // справа згори
        int num = Integer.parseInt(String.valueOf(place.charAt(1)));
        if (place.length() > 2) num = 10;
        if (place.charAt(0) != 'j' && num != 1) {
            char ch = chars.get(chars.indexOf(place.charAt(0)) + 1);
            num--;
            return String.valueOf(ch) + num;
        }
        return "";
    }

    private String makeUnPlaceRightDown(String place) { // справа знизу
        int num = Integer.parseInt(String.valueOf(place.charAt(1)));
        if (place.length() > 2) num = 10;
        if (place.charAt(0) != 'j' && num != 10) {
            char ch = chars.get(chars.indexOf(place.charAt(0)) + 1);
            num++;
            return String.valueOf(ch) + num;
        }
        return "";
    }

    private String makeUnPlaceDown(String place) {
        char ch = place.charAt(0);
        int num = Character.getNumericValue(place.charAt(1));
        if (place.length() > 2) {
            return "";
        }
        num = num + 1;
        String s = String.valueOf(ch) + num;
        return s;
    }

    private String makeUnPlaceUp(String place) {
        char ch = place.charAt(0);
        int num = Character.getNumericValue(place.charAt(1));
        if (place.length() > 2) num = 10;
        if (num == 1) return "";
        num = num - 1;
        String s = String.valueOf(ch) + num;
        return s;
    }

    private void setLogger() {
        try {
            Handler fileHandler = new FileHandler("log.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());

            LOGGER.addHandler(fileHandler);
            if(countUn < 200){
                LOGGER.setLevel(Level.INFO);
                LOGGER.info("Number of unsuccessful efforts to place a banana: " + countUn);
            } else {
                LOGGER.setLevel(Level.WARNING);
                LOGGER.warning("Number of unsuccessful efforts to place a banana was more than limit: " + countUn);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "unexpected error");
        }
    }

    // перевірити чи є банан на карті комп'ютера
    public String isABanana(String s) {
        for (String value : points6) {
            if (s.equals(value)) return "6";
        }
        for (String value : points4) {
            if (s.equals(value)) return "41";
        }
        for (String value : points4n2) {
            if (s.equals(value)) return "42";
        }
        for (String value : points3n1) {
            if (s.equals(value)) return "31";
        }
        for (String value : points3n2) {
            if (s.equals(value)) return "32";
        }
        for (String value : points3n3) {
            if (s.equals(value)) return "33";
        }
        for (String value : points2n1) {
            if (s.equals(value)) return "21";
        }
        for (String value : points2n2) {
            if (s.equals(value)) return "22";
        }
        for (String value : points2n3) {
            if (s.equals(value)) return "23";
        }
        for (String value : points2n4) {
            if (s.equals(value)) return "24";
        }
        return "";
    }

    /**
    public String shoot (){ // метод для вистрілу (повертає точку в яку стріляє)

    }
**/
    public void decideNextStepsForShooting() {
        if(potentionalPlacesForShoot.isEmpty()){ // якщо немає жодних потенційних точок для вистрілу
            coordinateForShoot = generateCoordinates();
            return;
        } else {
            if(potentionalPlacesForShoot.containsKey("left")) shootPotentionalPlaceLeft();
            else if(potentionalPlacesForShoot.containsKey("right")) shootPotentionlaPlaceRight();
            else if(potentionalPlacesForShoot.containsKey("up")) shootPotentionalPlaceUp();
            else if(potentionalPlacesForShoot.containsKey("down")) shootPotentionalPlaceDown();
        }
    }

    private void shootPotentionalPlaceLeft(){ // стріляти по точці зліва
        coordinateForShoot = potentionalPlacesForShoot.get("left");
        unavailablePointsForShooting.add(coordinateForShoot);
        // вистрілити

        // вставити метод, який каже, чи ця точка є бананом (постріл був вдалим)

        // якщо постріл був вдалим:
        if(!(makeUnPlaceLeft(coordinateForShoot).equals("")) && checkCoordinateForShooting(makeUnPlaceLeft(coordinateForShoot))){
            potentionalPlacesForShoot.put("left", (makeUnPlaceLeft(coordinateForShoot)));
            // зробити недоступними точки згори та знизу
            unavailablePointsForShooting.add(makeUnPlaceUp(coordinateForShoot));
            unavailablePointsForShooting.add(makeUnPlaceDown(coordinateForShoot));

            potentionalPlacesForShoot.remove("up");
            potentionalPlacesForShoot.remove("down");
        }

        // якщо постріл був не вдалим
        potentionalPlacesForShoot.remove("left");
    }

    private void shootPotentionlaPlaceRight(){
        coordinateForShoot = potentionalPlacesForShoot.get("right");
        unavailablePointsForShooting.add(coordinateForShoot);
        // вистрілити

        // вставити метод, який каже, чи ця точка є бананом (постріл був вдалим)

        // якщо постріл був вдалим:
        if(!(makeUnPlaceRight(coordinateForShoot).equals("")) && checkCoordinateForShooting(makeUnPlaceRight(coordinateForShoot))) {
            potentionalPlacesForShoot.put("right", makeUnPlaceRight(coordinateForShoot));
            // зробити недоступними точки згори та знизу
            unavailablePointsForShooting.add(makeUnPlaceUp(coordinateForShoot));
            unavailablePointsForShooting.add(makeUnPlaceDown(coordinateForShoot));

            potentionalPlacesForShoot.remove("up");
            potentionalPlacesForShoot.remove("down");
        }

        // якщо постріл був не вдалим
        potentionalPlacesForShoot.remove("right");

    }

    private void shootPotentionalPlaceUp(){
        coordinateForShoot = potentionalPlacesForShoot.get("up");
        unavailablePointsForShooting.add(coordinateForShoot);
        // вистрілити

        // вставити метод, який каже, чи ця точка є бананом (постріл був вдалим)

        // якщо постріл був вдалим:
        if(!(makeUnPlaceUp(coordinateForShoot).equals("")) && checkCoordinateForShooting(makeUnPlaceUp(coordinateForShoot))) {
            potentionalPlacesForShoot.put("up", makeUnPlaceUp(coordinateForShoot));
            // зробити недоступними точки зліва та права
            unavailablePointsForShooting.add(makeUnPlaceRight(coordinateForShoot));
            unavailablePointsForShooting.add(makeUnPlaceLeft(coordinateForShoot));

            potentionalPlacesForShoot.remove("left");
            potentionalPlacesForShoot.remove("right");
        }

        // якщо постріл був не вдалим
        potentionalPlacesForShoot.remove("up");
    }

    private void shootPotentionalPlaceDown(){
        coordinateForShoot = potentionalPlacesForShoot.get("down");
        unavailablePointsForShooting.add(coordinateForShoot);
        // вистрілити

        // вставити метод, який каже, чи ця точка є бананом (постріл був вдалим)

        // якщо постріл був вдалим:
        if(!(makeUnPlaceDown(coordinateForShoot).equals("")) && checkCoordinateForShooting(makeUnPlaceDown(coordinateForShoot))) {
            potentionalPlacesForShoot.put("down", makeUnPlaceDown(coordinateForShoot));
            // зробити недоступними точки зліва та права
            unavailablePointsForShooting.add(makeUnPlaceRight(coordinateForShoot));
            unavailablePointsForShooting.add(makeUnPlaceLeft(coordinateForShoot));

            potentionalPlacesForShoot.remove("left");
            potentionalPlacesForShoot.remove("right");
        }

        // якщо постріл був не вдалим
        potentionalPlacesForShoot.remove("down");
    }


    private String generateCoordinates() { // to generate coordinates for shooting
        Random random = new Random();
        int number = random.nextInt(1, 11);
        int r2 = random.nextInt(1, 11);
        String coordinate = String.valueOf(chars.get(r2 - 1));
        coordinate = coordinate + number; // r - number, s - letter

        // demo
        coordinate = "a1";
        // demo

        // checking this random coordinate
        if(!checkCoordinateForShooting(coordinate)) return "again"; // returns "again" to go to this method again
        // додати точку як недоступну для наступних пострілів
        unavailablePointsForShooting.add(coordinate);

        // check if this coordinate is a banana
        for(int i =0; i < coordinatesDemo.size(); i++){
             if(coordinate.equals(coordinatesDemo.get(i))) {
                 // якщо це банан, то перевірити точки на попередні постріли й
                // занести потенційні наступні точки для пострілів в HashMap
                if(!(makeUnPlaceLeft(coordinate).equals("")) && checkCoordinateForShooting(makeUnPlaceLeft(coordinate))){
                    potentionalPlacesForShoot.put("left", makeUnPlaceLeft(coordinate));
                }
                if(!(makeUnPlaceRight(coordinate).equals("")) && checkCoordinateForShooting(makeUnPlaceRight(coordinate))) {
                    potentionalPlacesForShoot.put("right", makeUnPlaceRight(coordinate));
                }
                if(!(makeUnPlaceUp(coordinate).equals("")) && checkCoordinateForShooting(makeUnPlaceUp(coordinate))) {
                    potentionalPlacesForShoot.put("up", makeUnPlaceUp(coordinate));
                }
                if(!(makeUnPlaceDown(coordinate).equals("")) && checkCoordinateForShooting(makeUnPlaceDown(coordinate))) {
                    potentionalPlacesForShoot.put("down", makeUnPlaceDown(coordinate));
                }

                break;
            }
        }
        // вистрілити
        coordinateForShoot = coordinate;
        return coordinate;
    }

    private boolean checkCoordinateForShooting(String s){ // returns false if this coordinate was already checked and is
        // unavailable
        for (String value : unavailablePointsForShooting) {
            if (s.equals(value)) return false;
        }
        return true;
    }
}
