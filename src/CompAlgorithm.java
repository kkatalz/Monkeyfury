import java.util.*;

// to create computer's algorithm for placement bananas
public class CompAlgorithm {
    private ArrayList<Integer> numbers;
    private ArrayList<Character> chars;
    private ArrayList<String> points6, points4, points4n2, unavailablePoints6, unavailablePoints4, unavailablePoints4n2;
    private ArrayList<String> points3n1, unavailablePoints3n1, points3n2, unavailablePoints3n2, points3n3, unavailablePoints3n3;
    private ArrayList<String> points2n1, unavailablePoints2n1, points2n2, unavailablePoints2n2, points2n3, unavailablePoints2n3; // letter and number
    private ArrayList<String> points2n4, unavailablePoints2n4;

    public static void main(String[] args) {
        CompAlgorithm compAlgorithm = new CompAlgorithm();
        compAlgorithm.setup();
        compAlgorithm.placeBananas();
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
    }

    public void placeBananas() {
        Random random = new Random();

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
            System.out.println("p:" + Arrays.toString(points6.toArray()));
            System.out.println("---------" + Arrays.toString(unavailablePoints6.toArray()));
        }
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

            count++;
            System.out.println(s);
        }
        if (sizeOfBanana == 6) {
            removeDuplicates(6, 0);
            unavailablePoints6.add(makeUnPlaceRightUp(s)); // правий верхній кут
            unavailablePoints6.add(makeUnPlaceRightDown(s)); // правий нижній кут
            if (placeRight > 0) unavailablePoints6.add(makeUnPlaceRight(s)); // в кінці корабля
        }

        count = 1;
        // помістити зліва
        for (int i = 0; i < placeLeft; i++) {
            int put = chars.indexOf(ch) - count;
            s = String.valueOf(chars.get(put));
            s = s + num;
            // перевірка
            if (!checkPlace(s)) return false;

            if (sizeOfBanana == 6) {
                points6.add(s);
                unavailablePoints6.add(s);
                unavailablePoints6.add(makeUnPlaceUp(s));
                unavailablePoints6.add(makeUnPlaceDown(s));
            }
            count++;
            System.out.println(s);
        }

        // лівий верхній та нижній кут
        if (sizeOfBanana == 6) {
            unavailablePoints6.add(makeUnPlaceLeftUp(s));
            unavailablePoints6.add(makeUnPlaceLeftDown(s));
        }
        // зліва від банана
        if (sizeOfBanana == 6) {
            unavailablePoints6.add(makeUnPlaceLeft(s));
        }

        removeDuplicates(sizeOfBanana, numberOfBanana);
        // вивести в консоль точки банана
        System.out.println("points: " + Arrays.toString(points6.toArray()));
        System.out.println("---" + Arrays.toString(unavailablePoints6.toArray()));
        return true;
    }

    private String makeUnPlaceRight(String s) {
        int i = Character.getNumericValue(s.charAt(1));
        if (s.length() > 2 || s.charAt(0) == 'з') return "";
        char ch = chars.get(chars.indexOf(s.charAt(0)) + 1);
        return String.valueOf(ch) + i;
    }

    private String makeUnPlaceLeft(String s) {
        char ch;
        int i = Character.getNumericValue(s.charAt(1));
        if (s.length() > 2) i = 10;
        if (i == 0 || s.charAt(0) == 'а') return "";
        ch = chars.get(chars.indexOf(s.charAt(0)) - 1);
        return String.valueOf(ch) + i;
    }

    private int findPlaceRightHorizontal(char ch) {
        int index = chars.indexOf(ch) + 1;
        return 10 - index;
    }


    private boolean placeVertical(int sizeOfBanana, char randomCh, String randomNum, int numberOfBanana) { // розташувати банан вертикально
        System.out.println("БАНАН вертикально------- " + sizeOfBanana);

        String s = String.valueOf(randomCh) + randomNum;
        boolean down = false;
        String resLeftDown, resRightDown;
        if (!checkPlace(s)) {
            System.out.println("no " + s);
            return false;
        }

        if (sizeOfBanana == 6) {
            points6.add(s);
            unavailablePoints6.add(s);
            makeUnPlaceInSidesVertical(s, 6, 1);
        }
        if (sizeOfBanana == 4) {
            if (numberOfBanana == 1) {
                points4.add(s);// додаємо початкову точку
                unavailablePoints4.add(s);
                makeUnPlaceInSidesVertical(s, 4, 1);
            }
        }
        System.out.println(s);
        System.out.println("UN 288 ---------------" + s);

        int placeUp = Integer.parseInt(randomNum) - 1;
        if (placeUp < (sizeOfBanana - 1)) {
            down = true;
        }

        //// FINISH
        if (!down) { // зарезервувати знизу по вертикалі
            resLeftDown = makeUnPlaceLeftDown(s);
            resRightDown = makeUnPlaceRightDown(s);
            if (sizeOfBanana == 6) {
                unavailablePoints6.add(resRightDown);
                unavailablePoints6.add(resLeftDown);
                if (!makeUnPlaceDown(s).equals("")) {
                    unavailablePoints6.add(makeUnPlaceDown(s));
                }
            } else if (sizeOfBanana == 4) {
                if (numberOfBanana == 1) {
                    unavailablePoints4.add(resLeftDown);
                    unavailablePoints4.add(resRightDown);
                    System.out.println("UN 309-------------" + resRightDown);
                    System.out.println("UN 310--------------" + resLeftDown);
                }
            }

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
                    System.out.println("no 307-----" + s);
                    return false;
                }


                ///// ДОПИСАТИ ПРО ІНШІ БАНАНИ
                if (i == sizeOfBanana - 2) {   // зліва та справа зверху по боках
                    if (sizeOfBanana == 6) {
                        unavailablePoints6.add(makeUnPlaceLeftUp(s));
                        unavailablePoints6.add(makeUnPlaceRightUp(s));
                        unavailablePoints6.add(makeUnPlaceUp(s));
                    }
                }
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
                        if (!makeUnPlaceRightUp(s).equals("")) unavailablePoints4.add(makeUnPlaceRightUp(s));
                        if (!makeUnPlaceLeftUp(s).equals("")) unavailablePoints4.add(makeUnPlaceLeftUp(s));
                    }
                }
                System.out.println(s);
                System.out.println("UN 356---------------" + s);
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
                        System.out.println("no 352---------" + s);
                        return false;
                    }

                    if (sizeOfBanana == 6) {
                        points6.add(s);
                        unavailablePoints6.add(s);
                        makeUnPlaceInSidesVertical(s, 6, 1);
                        if (i == placeDown - 1) {
                            unavailablePoints6.add(makeUnPlaceRightDown(s));
                            unavailablePoints6.add(makeUnPlaceLeftDown(s));
                            unavailablePoints6.add(makeUnPlaceDown(s));
                        }
                    }
                    if (sizeOfBanana == 4) {
                        if (numberOfBanana == 1) {
                            points4.add(s);
                            unavailablePoints4.add(s);
                            makeUnPlaceInSidesVertical(s, 4, 1);
                        }
                    }
                    System.out.println(s);
                    System.out.println("UN 395---------------" + s);
                    count++;
                }
            }
        }
        System.out.println("БАНАН------- " + sizeOfBanana + " закінчився");
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
                System.out.println("співпадає " + s + " та " + value);
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
        if (place.charAt(0) != 'а') { // left side
            char t = chars.get(chars.indexOf(place.charAt(0)) - 1);
            String s = String.valueOf(t) + place.charAt(1);
            if (s.length() == 3) s = s + place.charAt(2);

            if (sizeOfBanana == 6) {
                unavailablePoints6.add(s);
            }
            if (sizeOfBanana == 4) {
                if (numberOfBanana == 1) unavailablePoints4.add(s);
                else if (numberOfBanana == 2) unavailablePoints4n2.add(s);
            }
            System.out.println("UN 445-------------" + s);
        }

        if (place.charAt(0) != 'з') { // right side
            char t = chars.get(chars.indexOf(place.charAt(0)) + 1);
            String s = String.valueOf(t) + place.charAt(1);
            if (s.length() == 3) s = s + place.charAt(2);
            if (sizeOfBanana == 6) {
                unavailablePoints6.add(s);
            }
            if (sizeOfBanana == 4) {
                if (numberOfBanana == 1) unavailablePoints4.add(s);
                else if (numberOfBanana == 2) unavailablePoints4n2.add(s);
            }
            System.out.println("UN 456-------------" + s);
        }

    }

    private String makeUnPlaceLeftUp(String place) { // зліва зверху, returns place which has to be unavailable
        int num = Integer.parseInt(String.valueOf(place.charAt(1)));
        if (place.length() > 2) num = 10;
        if (place.charAt(0) != 'а' && num != 1) {
            char ch = chars.get(chars.indexOf(place.charAt(0)) - 1);
            num--;
            System.out.println("400---------------" + String.valueOf(ch) + num);
            return String.valueOf(ch) + num;
        }
        return "";
    }

    private String makeUnPlaceLeftDown(String place) { // зліва знизу
        int num = Integer.parseInt(String.valueOf(place.charAt(1)));
        if (place.length() > 2) num = 10;
        if (place.charAt(0) != 'а' && num != 10) {
            char ch = chars.get(chars.indexOf(place.charAt(0)) - 1);
            num++;
            System.out.println("412----------------" + String.valueOf(ch) + num);
            return String.valueOf(ch) + num;
        }
        return "";
    }

    private String makeUnPlaceRightUp(String place) { // справа згори
        int num = Integer.parseInt(String.valueOf(place.charAt(1)));
        if (place.length() > 2) num = 10;
        if (place.charAt(0) != 'з' && num != 1) {
            char ch = chars.get(chars.indexOf(place.charAt(0)) + 1);
            num--;
            System.out.println("424----------------" + String.valueOf(ch) + num);
            return String.valueOf(ch) + num;
        }
        return "";
    }

    private String makeUnPlaceRightDown(String place) { // справа знизу
        int num = Integer.parseInt(String.valueOf(place.charAt(1)));
        if (place.length() > 2) num = 10;
        if (place.charAt(0) != 'з' && num != 10) {
            char ch = chars.get(chars.indexOf(place.charAt(0)) + 1);
            num++;
            System.out.println("436--------------" + String.valueOf(ch) + num);
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
        System.out.println("448---------------" + s);
        return s;
    }

    private String makeUnPlaceUp(String place) {
        char ch = place.charAt(0);
        int num = Character.getNumericValue(place.charAt(1));
        if (place.length() > 2) num = 10;
        if (num == 1) return "";
        num = num - 1;
        String s = String.valueOf(ch) + num;
        System.out.println("458----------------" + s);
        return s;
    }
}
