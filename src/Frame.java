import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Frame extends JFrame {

    public int levelOfGame;
    public byte numberOfSteps;
    public static final int FRAME_WIDTH = 1350;
    public static final int FRAME_HEIGHT = 700;
    public static final int PANEL_FOR_FIGHT_WIDTH_HEIGHT = 400;
    public static final int PANEL_Y_FROM_TOP = 50;
    public static final int PANEL_X_FROM_BORDER = 40;
    public static final Color PANEL_BACKGROUND_COLOR = Color.white;
    public byte BANANA_WIDTH = 40;
    private JLabel gameLevelLabel, bananasType, informationForUser, showGameLevel, happyMonkeyLabel, angryMonkeyLabel, monkeyLabel, informationForUserAboutDecker;
    private JButton level1Button, level2Button, level3Button, start, putBanana, shootButton, deleteBanana;
    private JComboBox<String> listOfBananas;

    private JPanel areaForUser, areaForComputer;
    private JTextField typeCoordinate;
    public BackgroundPanel backgroundPanel;
    private CompAlgorithm compAlgorithm;
    private ArrayList<String> shootedPlaces = new ArrayList<>();
    private ArrayList<int[]> userCoordinates = new ArrayList<>();
    private int p6, p4, p4n2, p3n1, p3n2, p3n3, p2n1, p2n2, p2n3, p2n4;
    int BOARD_WIDTH = 10;
    int BOARD_HEIGHT = 10;
    int attemptsCount = 0;
    int singleDeckAttempts, deckerAmount;
    int previousChosenItem;
    boolean twoDeckSet, threeDeckSet, fourDeckSet, sixDeckSet = false, toGiveCoordinates = true;


    boolean[][] availableCells = new boolean[10][10];
    JPanel[][] bananaPanels = new JPanel[BOARD_WIDTH + 1][BOARD_HEIGHT + 1];


    Frame() {
        compAlgorithm = new CompAlgorithm();
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                availableCells[x][y] = true;
            }
        }

        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        showAreasForFight();

    }

    public void showAreasForFight() {
        backgroundPanel = new BackgroundPanel();
        this.setContentPane(backgroundPanel);

        informationForUser = new JLabel();
        informationForUser.setBounds(FRAME_WIDTH - 350, 200, 500, 50);
        informationForUser.setForeground(Color.black);
        informationForUser.setFont(new Font("f", Font.PLAIN, 17));
        backgroundPanel.add(informationForUser);

        areaForUser = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x = PANEL_FOR_FIGHT_WIDTH_HEIGHT / 10;
                int y = PANEL_FOR_FIGHT_WIDTH_HEIGHT / 10;

                // Draw horizontal lines
                for (int i = 0; i < 9; i++) {
                    g.drawLine(0, y, PANEL_FOR_FIGHT_WIDTH_HEIGHT, y);
                    y += PANEL_FOR_FIGHT_WIDTH_HEIGHT / 10;
                }

                // Draw vertical lines
                for (int i = 0; i < 9; i++) {
                    g.drawLine(x, 0, x, PANEL_FOR_FIGHT_WIDTH_HEIGHT);
                    x += PANEL_FOR_FIGHT_WIDTH_HEIGHT / 10;
                }
            }
        };
        areaForUser.setBounds(PANEL_X_FROM_BORDER, PANEL_Y_FROM_TOP, PANEL_FOR_FIGHT_WIDTH_HEIGHT, PANEL_FOR_FIGHT_WIDTH_HEIGHT);
        areaForUser.setBackground(PANEL_BACKGROUND_COLOR);
        backgroundPanel.add(areaForUser);

        areaForComputer = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x = PANEL_FOR_FIGHT_WIDTH_HEIGHT / 10;
                int y = PANEL_FOR_FIGHT_WIDTH_HEIGHT / 10;

                // Draw horizontal lines
                for (int i = 0; i < 9; i++) {
                    g.drawLine(0, y, PANEL_FOR_FIGHT_WIDTH_HEIGHT, y);
                    y += PANEL_FOR_FIGHT_WIDTH_HEIGHT / 10;
                }

                // Draw vertical lines
                for (int i = 0; i < 9; i++) {
                    g.drawLine(x, 0, x, PANEL_FOR_FIGHT_WIDTH_HEIGHT);
                    x += PANEL_FOR_FIGHT_WIDTH_HEIGHT / 10;
                }
            }
        };

        areaForComputer.setBounds(3 * PANEL_X_FROM_BORDER + PANEL_FOR_FIGHT_WIDTH_HEIGHT, PANEL_Y_FROM_TOP, PANEL_FOR_FIGHT_WIDTH_HEIGHT, PANEL_FOR_FIGHT_WIDTH_HEIGHT);
        areaForComputer.setBackground(PANEL_BACKGROUND_COLOR);
        backgroundPanel.add(areaForComputer);

        String[] monkeyfury = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        int x = PANEL_X_FROM_BORDER + 10;
        int x2 = PANEL_FOR_FIGHT_WIDTH_HEIGHT + 3 * PANEL_X_FROM_BORDER + 10;
        JLabel[] horizontal = new JLabel[monkeyfury.length];
        JLabel[] vertical = new JLabel[monkeyfury.length];
        for (int i = 0; i < monkeyfury.length; i++) {
            horizontal[i] = new JLabel(monkeyfury[i]);
            horizontal[i].setBounds(x, PANEL_Y_FROM_TOP - 35, 40, 40);
            horizontal[i].setForeground(Color.WHITE);
            x += PANEL_FOR_FIGHT_WIDTH_HEIGHT / 10;
            horizontal[i].setFont(new Font("f", Font.BOLD, 20));

            this.add(horizontal[i]);

            vertical[i] = new JLabel(monkeyfury[i]);
            vertical[i].setBounds(x2, PANEL_Y_FROM_TOP - 35, 40, 40);
            x2 += PANEL_FOR_FIGHT_WIDTH_HEIGHT / 10;
            vertical[i].setForeground(Color.WHITE);
            vertical[i].setFont(new Font("f", Font.BOLD, 20));
            this.add(vertical[i]);
        }
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        int y = PANEL_Y_FROM_TOP;
        int xVer = 480;
        JLabel[] horY = new JLabel[numbers.length];
        JLabel[] verY = new JLabel[numbers.length];
        for (int i = 0; i < 10; i++) {
            horY[i] = new JLabel(numbers[i]);
            horY[i].setBounds(PANEL_X_FROM_BORDER - 25, y, 40, 40);
            horY[i].setForeground(Color.WHITE);

            horY[i].setFont(new Font("f", Font.BOLD, 18));

            this.add(horY[i]);

            verY[i] = new JLabel(numbers[i]);
            verY[i].setBounds(xVer, y, 40, 40);
            verY[i].setForeground(Color.WHITE);
            verY[i].setFont(new Font("f", Font.BOLD, 18));
            this.add(verY[i]);

            y += PANEL_FOR_FIGHT_WIDTH_HEIGHT / 10;
        }

        //INSTRUCTION BUTTON
        JButton readInstuctions = new JButton("Інструкція");
        readInstuctions.setBackground(Color.white);
        readInstuctions.setForeground(Color.black);
        readInstuctions.setBounds(17 * PANEL_X_FROM_BORDER + PANEL_FOR_FIGHT_WIDTH_HEIGHT, PANEL_Y_FROM_TOP, 100, 50);
        backgroundPanel.add(readInstuctions);
        JFrame instruction = new JFrame();
        instruction.setSize(460, 460);
        instruction.setLocationRelativeTo(null);
        JTextArea textInstruction = new JTextArea(
                "1. Гравці:  Квадрат ліворуч - належить комп’ютеру , другий-користувачу.\n" +
                        "\n2. Розташування та кількість мавпячих кораблів: Кожен гравець має 10 кораблів(один шестипалубний, 2 чотирипалубних, 3 трипалубних, 4 двопалубних). Для гри, необхідно розмістити кораблі горизонтально або вертикально, так, щоб вони не торкались один одного . Користувач має розмістити 10 кораблів тільки на своїй частині прямокутника - лівому квадраті. Розташування кораблів комп'ютера заповнюється автоматично. Праворуч від поля бою знаходиться список , у якому відображаються види кораблів, їх кількість і кількість клітинок, які відводяться для конкретного корабля(1 палуба=1 клітинка. Відповідно, для розміщення шестипалубного корабля необхідно вести координати 6-ти послідовних клітинок(формат: а8), які заповнюються бананами). У разі неправильно розташування, користувач може стерти корабель - нажавши на кнопку 'прибрати' і вести координати.\n" +
                        "\n" +
                        "\n3. Влучання: Кількість влучань обмежена(про їх кількість свідчитиме лічильник). Із збільшенням рівня складності,  кількість ходів у користувача зменшується. У ході гри кількість ходів зменшується тільки тоді, коли гравець не знайшов банани противника. Після пострілу гравцю повідомляється результат: 'знайдено-забрано повністю', 'знайдено один банан' або 'промахнулись '. У разі “знайдено” - банан змінить колір на червоний і Ви маєте ходити ще раз, поки не промахнетесь; “промахнулись ”-користувач побачить повідомлення “корабля на цій клітинці немає” і гра продовжується; коли ж користувач потопить корабель, з'явиться повідомлення  ”Ваш корабель потоплено/ потоплено корабель супротивника !” Гра триває до моменту, коли мавпочка не знайде всі банани іншої.\n" +
                        "\n" +
                        "\n4. Рівні складності: гра містить три рівні складності, позначені чорними кружечками в куті екрана." +
                        "\n" +
                        "    Перший рівень - стовідсоткове знайдення всіх бананів. Дається 70 ходів. Так як загальна кількість клітинок - 100, з яких 31 - заповнені бананами, то навіть використавши 69 ходів, стріляючи по клітинках, які не містять кораблів , 70-та клітинка міститиме корабель. А у разі влучання кількість ходів не зменшується;\n" +
                        "\n" +
                        "   Другий рівень - 80-відсоткове попадання . Надається 54 ходи;\n" +
                        "\n" +
                        "   Третій рівень - 40 -відсоткове попадання.  Надається 24 ходи.\n" +
                        "\n" +
                        "\n" +
                        "\n5. Правила перемоги: Гравець, який першим знайде всі банани противника, вважається переможцем."

        );
        textInstruction.setLineWrap(true);
        textInstruction.setWrapStyleWord(true);
        JPanel instructionContainer = new JPanel();
        JScrollPane instructionScroll = new JScrollPane(textInstruction);

        instructionContainer.add(instructionScroll);

        instructionScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        instructionScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        readInstuctions.addActionListener(e -> {
            instruction.getContentPane().add(instructionScroll);
            instruction.setVisible(true);

        });

        //Choose level BUTTON
        gameLevelLabel = new JLabel("Оберіть рівень гри: ");
        gameLevelLabel.setBounds(17 * PANEL_X_FROM_BORDER + PANEL_FOR_FIGHT_WIDTH_HEIGHT - 35, PANEL_Y_FROM_TOP + 55, 350, 50);
        gameLevelLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        backgroundPanel.add(gameLevelLabel);

        //level 1,2,3 BUTTONS
        level1Button = new JButton("1");
        level1Button.setBackground(Color.white);
        level1Button.setForeground(Color.black);
        level1Button.setBounds(35 / 2 * PANEL_X_FROM_BORDER + PANEL_FOR_FIGHT_WIDTH_HEIGHT - 50, PANEL_Y_FROM_TOP + 110, 45, 45);
        backgroundPanel.add(level1Button);
        level1Button.addActionListener(e -> {
            levelOfGame = 1;
        });

        level2Button = new JButton("2");
        level2Button.setBackground(Color.white);
        level2Button.setForeground(Color.black);
        level2Button.setBounds(35 / 2 * PANEL_X_FROM_BORDER + PANEL_FOR_FIGHT_WIDTH_HEIGHT + 25, PANEL_Y_FROM_TOP + 120, 50, 50);
        backgroundPanel.add(level2Button);
        level2Button.addActionListener(e -> {
            levelOfGame = 2;
        });

        level3Button = new JButton("3");
        level3Button.setBackground(Color.white);
        level3Button.setForeground(Color.black);
        level3Button.setBounds(35 / 2 * PANEL_X_FROM_BORDER + PANEL_FOR_FIGHT_WIDTH_HEIGHT + 100, PANEL_Y_FROM_TOP + 110, 45, 45);
        backgroundPanel.add(level3Button);
        level3Button.addActionListener(e -> {
            levelOfGame = 3;
        });

        //CHOOSE BANANAS TYPE LABEL
        bananasType = new JLabel("Оберіть тип банана ");
        bananasType.setBounds(17 * PANEL_X_FROM_BORDER + PANEL_FOR_FIGHT_WIDTH_HEIGHT - 25, PANEL_Y_FROM_TOP + 190, 350, 50);
        bananasType.setFont(new Font("Calibri", Font.PLAIN, 20));
        backgroundPanel.add(bananasType);

        //LIST OF BANANAS TYPE BUTTON
        String[] choices = {"", "двопалубні x4", "трипалубні x3", "чотирипалубні x2", "шестипалубні x1"};
        listOfBananas = new JComboBox(choices);
        listOfBananas.setBackground(Color.white);
        listOfBananas.setForeground(Color.black);
        listOfBananas.setBounds(17 * PANEL_X_FROM_BORDER + PANEL_FOR_FIGHT_WIDTH_HEIGHT - 15, PANEL_Y_FROM_TOP + 270, 135, 40);
        backgroundPanel.add(listOfBananas);

        //COORDINATE LABEL
        JLabel coordinateLabel = new JLabel("Напишіть координати (a8)");
        coordinateLabel.setForeground(Color.black);
        coordinateLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        coordinateLabel.setBounds(17 * PANEL_X_FROM_BORDER + PANEL_FOR_FIGHT_WIDTH_HEIGHT - 45, PANEL_Y_FROM_TOP + 320, 275, 50);
        backgroundPanel.add(coordinateLabel);

        //FILL COORDINATES
        typeCoordinate = new JTextField();
        typeCoordinate.setBackground(Color.white);
        typeCoordinate.setBounds(17 * PANEL_X_FROM_BORDER + PANEL_FOR_FIGHT_WIDTH_HEIGHT + 35, PANEL_Y_FROM_TOP + 365, 35, 35);
        backgroundPanel.add(typeCoordinate);

        //CHOOSE BANANAS TYPE BUTTON
        putBanana = new JButton("Поставити банан");
        putBanana.setBackground(Color.white);
        putBanana.setForeground(Color.black);
        putBanana.setBounds(17 * PANEL_X_FROM_BORDER + PANEL_FOR_FIGHT_WIDTH_HEIGHT - 115, PANEL_Y_FROM_TOP + 425, 155, 50);
        backgroundPanel.add(putBanana);


        //CHOOSE BANANAS TYPE BUTTON
        deleteBanana = new JButton("Прибрати банан");
        deleteBanana.setBackground(Color.white);
        deleteBanana.setForeground(Color.black);
        deleteBanana.setBounds(17 * PANEL_X_FROM_BORDER + PANEL_FOR_FIGHT_WIDTH_HEIGHT + 70, PANEL_Y_FROM_TOP + 425, 155, 50);
        backgroundPanel.add(deleteBanana);

        // button to shoot with coordinates
        shootButton = new JButton("Вистрілити");
        shootButton.setBackground(Color.WHITE);
        shootButton.setForeground(Color.BLACK);
        shootButton.setBounds(17 * PANEL_X_FROM_BORDER + PANEL_FOR_FIGHT_WIDTH_HEIGHT - 20, PANEL_Y_FROM_TOP + 425, 155, 50);
        shootButton.addActionListener(e -> {
            toShoot();
            typeCoordinate.setText("");
        });

        //Check if coordinates are written in correct format
        putBanana.addActionListener(e -> {
            checkFormatOfCoordinates();
            String inputCoordinates = typeCoordinate.getText();
            char letterInCoordinates = inputCoordinates.charAt(0);
            int xCoordinate = (int) letterInCoordinates - 96;
            int yCoordinate;
            if (inputCoordinates.length() == 3 && inputCoordinates.charAt(1) == '1' && inputCoordinates.charAt(2) == '0') {
                yCoordinate = 10;
            } else if (inputCoordinates.length() == 2) {
                yCoordinate = Integer.parseInt(String.valueOf(inputCoordinates.charAt(1)));
            } else {
                return;
            }
//            {"", "двопалубні x4", "трипалубні x3", "чотирипалубні x2", "шестипалубні x1"};
            int times = 1;
            int chosenItem = listOfBananas.getSelectedIndex();

            informationForUserAboutDecker = new JLabel("<html>Будь ласка, спочатку розставте всю необхідну кількість<br>  для конкретної" +
                    " палуби, і тільки тоді переходьте до іншої.<br> У разі зайвого корабля, Ви можете його видалити ");
            informationForUserAboutDecker.setBounds(25, FRAME_HEIGHT - 205, 800, 100);
            informationForUserAboutDecker.setForeground(Color.white);
            informationForUserAboutDecker.setFont(new Font("f", Font.PLAIN, 17));
            backgroundPanel.add(informationForUserAboutDecker);

            if (chosenItem == 1) {
                deckerAmount = 2;
                times = 4;

            } else if (chosenItem == 2) {
                deckerAmount = 3;
                times = 3;
            } else if (chosenItem == 3) {
                deckerAmount = 4;
                times = 2;
            } else if (chosenItem == 4) {
                deckerAmount = 6;
                times = 1;
            }


            System.out.println("X: " + xCoordinate + " Y: " + yCoordinate);
            if (attemptsCount / deckerAmount >= times) {
                JOptionPane.showMessageDialog(backgroundPanel, "Оберіть нову палубу", "ERROR", JOptionPane.ERROR_MESSAGE);
                attemptsCount = 0;

                if (deckerAmount == 2) twoDeckSet = true;
                if (deckerAmount == 3) threeDeckSet = true;
                if (deckerAmount == 4) fourDeckSet = true;
                if (deckerAmount == 6) sixDeckSet = true;

//check that checks
            } else {
                if (deckerAmount == 2 && twoDeckSet) {
                    JOptionPane.showMessageDialog(backgroundPanel, "Ви вже розмістили всі 2-палубні.Оберіть нову палубу", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else if (deckerAmount == 3 && threeDeckSet) {
                    JOptionPane.showMessageDialog(backgroundPanel, "Ви вже розмістили всі 3-палубні.Оберіть нову палубу", "ERROR", JOptionPane.ERROR_MESSAGE);

                } else if (deckerAmount == 4 && threeDeckSet) {
                    JOptionPane.showMessageDialog(backgroundPanel, "Ви вже розмістили всі 4-палубні.Оберіть нову палубу", "ERROR", JOptionPane.ERROR_MESSAGE);

                } else if (deckerAmount == 6 && threeDeckSet) {
                    JOptionPane.showMessageDialog(backgroundPanel, "Ви вже розмістили всі 6-палубні.Оберіть нову палубу", "ERROR", JOptionPane.ERROR_MESSAGE);

                } else {
                    placeBanana(xCoordinate, yCoordinate, chosenItem);
                }
            }
            typeCoordinate.setText("");


        });
        deleteBanana.addActionListener(e -> {
            checkFormatOfCoordinates();
            String inputCoordinates = typeCoordinate.getText();
            char letterInCoordinates = inputCoordinates.charAt(0);
            int xCoordinate = (int) letterInCoordinates - 96;
            int yCoordinate;
            if (inputCoordinates.length() == 3 && inputCoordinates.charAt(1) == '1' && inputCoordinates.charAt(2) == '0') {
                yCoordinate = 10;
            } else if (inputCoordinates.length() == 2) {
                yCoordinate = Integer.parseInt(String.valueOf(inputCoordinates.charAt(1)));
            } else {
                return;
            }
            removeBanana(xCoordinate, yCoordinate);
        });


        //START GAME BUTTON
        start = new JButton("START");
        start.setBackground(Color.white);
        start.setForeground(Color.black);
        start.setBounds(17 * PANEL_X_FROM_BORDER + PANEL_FOR_FIGHT_WIDTH_HEIGHT - 25, PANEL_Y_FROM_TOP + 505, 155, 50);
        backgroundPanel.add(start);

        //check if game level is chosen
        showGameLevel = new JLabel();
        showGameLevel.setBounds(25, FRAME_HEIGHT - 205, 350, 50);
        showGameLevel.setForeground(Color.white);
        showGameLevel.setFont(new Font("Calibri", Font.PLAIN, 25));
        backgroundPanel.add(showGameLevel);

        start.addActionListener(e -> {

            //ПЕРЕВІРКА, ЧИ ВСІ ККОРАБЛІ РОЗСТАВЛЕНІ
            if (!twoDeckSet || !threeDeckSet || !fourDeckSet || !sixDeckSet) {
                String whichDeckIsnotPlaced = "Не розставлені палуби: ";
                if (!twoDeckSet) whichDeckIsnotPlaced += " 2-палубні ";
                if (!threeDeckSet) whichDeckIsnotPlaced += " 3-палубні ";
                if (!fourDeckSet) whichDeckIsnotPlaced += " 4-палубні ";
                if (!sixDeckSet) whichDeckIsnotPlaced += " 6-палубні ";

                JOptionPane.showMessageDialog(backgroundPanel, "Ви не розставили всі кораблі \n" + whichDeckIsnotPlaced, "ERROR", JOptionPane.ERROR_MESSAGE);

            } else if (levelOfGame != 1 && levelOfGame != 2 && levelOfGame != 3) {
                JOptionPane.showMessageDialog(backgroundPanel, "Необхідно обрати рівень гри", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                if (levelOfGame == 1) numberOfSteps = 70;
                else if (levelOfGame == 2) numberOfSteps = 54;
                else numberOfSteps = 24;
                showGameLevel.setText("<html> Обраний рівень гри - " + levelOfGame + "<br> Кількість кроків: " + numberOfSteps);

                backgroundPanel.remove(start);
                backgroundPanel.remove(level1Button);
                backgroundPanel.remove(level2Button);
                backgroundPanel.remove(level3Button);
                backgroundPanel.remove(gameLevelLabel);
                backgroundPanel.remove(putBanana);
                backgroundPanel.remove(deleteBanana);
                backgroundPanel.remove(bananasType);
                backgroundPanel.remove(listOfBananas);
                backgroundPanel.remove(informationForUserAboutDecker);
                backgroundPanel.add(shootButton);
                SwingUtilities.updateComponentTreeUI(backgroundPanel);

            }

        });
        SwingUtilities.updateComponentTreeUI(backgroundPanel);

    }

    // дії у разі вистрілу користувачем


    private void toShoot() {
        if (!checkFormatOfCoordinatesForShooting()) {
            JOptionPane.showMessageDialog(backgroundPanel, "Невірно введені координати або задані координати були задані раніше", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String stringCoordinates = typeCoordinate.getText();

        // дізнатися, чи користувач влучив у банан
        String check = compAlgorithm.isABanana(stringCoordinates);
        boolean res;
        if (check.equals("")) {
            res = false;
            informationForUser.setText("Ви не влучили в кошик з бананами!");
            numberOfSteps--;
            showGameLevel.setText("<html> Обраний рівень гри - " + levelOfGame + "<br> Кількість кроків: " + numberOfSteps);

            // ЗЛА МАВПОЧКА, БО НЕ ЗНАЙШЛА БАНАН
            // Zlata: C:\Users\plato\IdeaProjects\Monkeyfury\src\angryMonkey.jpg
            // Liza: /home/liza/IdeaProjects/Monkeyfury/src/angryMonkey.jpg
            String imagePath = "/home/liza/IdeaProjects/Monkeyfury/src/angryMonkey.jpg";
            ImageIcon icon = new ImageIcon(imagePath);
            Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Remove previous monkey label, if any
            if (monkeyLabel != null) {
                backgroundPanel.remove(monkeyLabel);
            }

            monkeyLabel = new JLabel(scaledIcon);
            monkeyLabel.setBounds(FRAME_WIDTH - 260, 130, 80, 80);
            backgroundPanel.add(monkeyLabel);

        } else {
            res = true;
            if (!makeStatistics(check)) informationForUser.setText("Ви влучили у кошик з бананами!");
            else informationForUser.setText("Ви знайшли увесь кошик з бананами!");

            // ЩАСЛИВА МАВПОЧКА, БО ЗНАЙШЛА БАНАН
            // Zlata: C:\Users\plato\IdeaProjects\Monkeyfury\src\happyMonkey.jpg
            // Liza: /home/liza/IdeaProjects/Monkeyfury/src/happyMonkey.jpg
            String imagePath = "/home/liza/IdeaProjects/Monkeyfury/src/happyMonkey.jpg";
            ImageIcon icon = new ImageIcon(imagePath);
            Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Remove previous monkey label, if any
            if (monkeyLabel != null) {
                backgroundPanel.remove(monkeyLabel);
            }

            monkeyLabel = new JLabel(scaledIcon);
            monkeyLabel.setBounds(FRAME_WIDTH - 260, 130, 80, 80);
            backgroundPanel.add(monkeyLabel);
        }

        char letter = stringCoordinates.charAt(0);
        int x = (int) letter - 96;
        int y = Character.getNumericValue(stringCoordinates.charAt(1));
        if (stringCoordinates.length() == 3) y = 10;
        if (!res) placeBananaInComputerArea(x, y, false);
        else placeBananaInComputerArea(x, y, true);

        // постріл комп'ютера
        organiseComputerShoot();
    }

    private void organiseComputerShoot(){
        if(toGiveCoordinates) giveCoordinates();
        String computerShootCoordinate = compAlgorithm.shoot();
        char letter = computerShootCoordinate.charAt(0);
        int x = (int) letter - 96;
        int y = Character.getNumericValue(computerShootCoordinate.charAt(1));
        if (computerShootCoordinate.length() == 3) y = 10;

        // визначити результат
        placeBananaInUserArea(x, y, true);

    }

    // змінити значення відповідної змінної
    private boolean makeStatistics(String s) { // повертає false, якщо користувач ще не збив увесь банан
        if (s.charAt(0) == '6') {
            p6++;
            return p6 == 6;
        }

        if (s.charAt(0) == '4') {
            if (s.charAt(1) == '1') {
                p4++;
                return p4 == 4;
            } else if (s.charAt(1) == '2') {
                p4n2++;
                return p4n2 == 4;
            }
        } else if (s.charAt(0) == '3') {
            if (s.charAt(1) == '1') {
                p3n1++;
                return p3n1 == 3;
            } else if (s.charAt(1) == '2') {
                p3n2++;
                return p3n2 == 3;
            } else if (s.charAt(1) == '3') {
                p3n3++;
                return p3n3 == 3;
            }
        } else if (s.charAt(0) == '2') {
            if (s.charAt(1) == '1') {
                p2n1++;
                return p2n1 == 2;
            } else if (s.charAt(1) == '2') {
                p2n2++;
                return p2n2 == 2;
            } else if (s.charAt(1) == '3') {
                p2n3++;
                return p2n3 == 2;
            } else if (s.charAt(1) == '4') {
                p2n4++;
                return p2n4 == 2;
            }
        }
        return false;
    }


    private void placeBananaInComputerArea(int x, int y, boolean result) { // result - true, якщо там був банан
        JPanel panel = new JPanel();

        panel.setLayout(null);
        panel.setBounds((x - 1) * BANANA_WIDTH, (y - 1) * BANANA_WIDTH, 40, 40);

        // liza: /home/liza/IdeaProjects/Monkeyfury/src/banana.jpeg
        // liza: /home/liza/IdeaProjects/Monkeyfury/src/peel.jpeg

        // zlata: C:\Users\plato\IdeaProjects\Monkeyfury\src\banana.jpeg
        // zlata: C:\Users\plato\IdeaProjects\Monkeyfury\src\peel.jpeg

        String imagePath;
        if (result) {
            imagePath = "/home/liza/IdeaProjects/Monkeyfury/src/banana.jpeg";
        } else imagePath = "/home/liza/IdeaProjects/Monkeyfury/src/peel.jpeg";

        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        label.setBounds(0, 0, 40, 40);

        panel.add(label);

        areaForComputer.setLayout(null);
        areaForComputer.add(panel);

        SwingUtilities.updateComponentTreeUI(backgroundPanel);

    }

    private void placeBananaInUserArea(int x, int y, boolean result){ // розташувати банан або шкірку від банана після вистрілу комп'ютера
        JPanel panel = new JPanel();

        panel.setLayout(null);
        panel.setBounds((x - 1) * BANANA_WIDTH, (y - 1) * BANANA_WIDTH, 40, 40);

        // liza: /home/liza/IdeaProjects/Monkeyfury/src/banana.jpeg
        // liza: /home/liza/IdeaProjects/Monkeyfury/src/peel.jpeg

        // zlata: C:\Users\plato\IdeaProjects\Monkeyfury\src\banana.jpeg
        // zlata: C:\Users\plato\IdeaProjects\Monkeyfury\src\peel.jpeg

        String imagePath;
        if (result) {
            imagePath = "/home/liza/IdeaProjects/Monkeyfury/src/banana.jpeg";
        } else imagePath = "/home/liza/IdeaProjects/Monkeyfury/src/peel.jpeg";

        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        label.setBounds(0, 0, 40, 40);

        panel.add(label);

        areaForUser.setLayout(null);
        areaForUser.add(panel);

        SwingUtilities.updateComponentTreeUI(backgroundPanel);
    }

    private boolean checkFormatOfCoordinatesForShooting() {
        //  typeCoordinate
        String s = typeCoordinate.getText();

        for (int i = 0; i < shootedPlaces.size(); i++) {
            if (s.equals(shootedPlaces.get(i))) {
                return false;
            }
        }
        if (s.length() > 0) {
            char firstChar = Character.toLowerCase(s.charAt(0));
            if (!(firstChar >= 'a')) return false;
            if (!(firstChar <= 'j')) return false;
        }
        int n = Character.getNumericValue(s.charAt(1));

        if (!(n >= 1 && n <= 10)) return false;

        if (s.length() == 3) {
            if (!(s.charAt(1) == '1')) return false;
            if (!(s.charAt(2) == '0')) return false;
            else n = 10;
        }
        shootedPlaces.add(s);
        return true;
    }

//    public void placeBanana(int x, int y) {
//        JPanel panel = new JPanel();
//
//        panel.setLayout(null); // Set the layout manager to null for absolute positioning
//        panel.setBounds((x - 1) * BANANA_WIDTH, (y - 1) * BANANA_WIDTH, 40, 40);
//
//        // liza: /home/liza/IdeaProjects/Monkeyfury/src/banana.jpg
//        // zlata: C:\Users\plato\IdeaProjects\Monkeyfury\src\banana.jpeg
//
//        String imagePath = "C:\\Users\\plato\\IdeaProjects\\Monkeyfury\\src\\banana.jpeg";
//        ImageIcon icon = new ImageIcon(imagePath);
//        Image scaledImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
//        ImageIcon scaledIcon = new ImageIcon(scaledImage);
//        JLabel label = new JLabel(scaledIcon);
//        label.setBounds(0, 0, 40, 40); // Manually set the bounds of the label
//
//        panel.add(label); // Add the label to the panel
//
//        areaForUser.setLayout(null);
//        areaForUser.add(panel);
//
//        SwingUtilities.updateComponentTreeUI(backgroundPanel);
//
//    }


    //the method that makes unavailable points around single point
    public void setUnavailiablePoints(int x, int y) {
        if (!((x == 0 && y == 0) || (x == 0 && y == BOARD_HEIGHT - 1) ||
                (x == BOARD_WIDTH - 1 && y == 0) || (x == BOARD_WIDTH - 1 && y ==
                BOARD_HEIGHT - 1))) {
            availableCells[x][y] = false;
        }
        if (x > 0 && !(x == 1 && (y == 0 || y == BOARD_HEIGHT - 1))) {
            availableCells[x - 1][y] = false;
            if (y > 0) {
                availableCells[x - 1][y - 1] = false;
            }
            if (y < BOARD_HEIGHT - 1) {
                availableCells[x - 1][y + 1] = false;
            }
        }
        if (x < BOARD_WIDTH - 1 && !(x == BOARD_WIDTH - 2 && (y == 0 || y == BOARD_HEIGHT - 1))) {
            availableCells[x + 1][y] = false;
            if (y > 0) {
                availableCells[x + 1][y - 1] = false;
            }
            if (y < BOARD_HEIGHT - 1) {
                availableCells[x + 1][y + 1] = false;
            }
        }
        if (y > 0 && !(y == 1 && (x == 0 || x == BOARD_WIDTH - 1))) {
            availableCells[x][y - 1] = false;
            if (x > 0) {
                availableCells[x - 1][y - 1] = false;
            }
            if (x < BOARD_WIDTH - 1) {
                availableCells[x + 1][y - 1] = false;
            }
        }
        if (y < BOARD_HEIGHT - 1 && !(y == BOARD_HEIGHT - 2 && (x == 0 || x == BOARD_WIDTH - 1))) {
            availableCells[x][y + 1] = false;
            if (x > 0) {
                availableCells[x - 1][y + 1] = false;
            }
            if (x < BOARD_WIDTH - 1) {
                availableCells[x + 1][y + 1] = false;
            }
        }
    }

    //placing banana on the user's map
    public void placeBanana(int x, int y, int chosenItem) {
        if (!availableCells[x - 1][y - 1]) {
            JOptionPane.showMessageDialog(backgroundPanel, "Ви не можете ставити банан так,щоб він торкався іншого", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (previousChosenItem != chosenItem) {
            attemptsCount = 0;
            singleDeckAttempts = 0;
        }

        previousChosenItem = chosenItem;


// Add the banana image to the made panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds((x - 1) * BANANA_WIDTH, (y - 1) * BANANA_WIDTH, 40, 40);


        // liza: /home/liza/IdeaProjects/Monkeyfury/src/banana.jpeg
        // zlata: C:\Users\plato\IdeaProjects\Monkeyfury\src\banana.jpeg
        String imagePath = "/home/liza/IdeaProjects/Monkeyfury/src/banana.jpeg";
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        label.setBounds(0, 0, 40, 40);
        panel.add(label);
        areaForUser.setLayout(null);
        areaForUser.add(panel);

        availableCells[x - 1][y - 1] = false;


        int[] coords = {x - 1, y - 1};
        userCoordinates.add(coords);
        singleDeckAttempts++;
        attemptsCount++;

// mark adjacent cells as unavailable
        if (deckerAmount == singleDeckAttempts) {
            for (int[] array : userCoordinates) {
                setUnavailiablePoints(array[0], array[1]);
            }

            singleDeckAttempts = 0;
        }


        bananaPanels[x][y] = panel;
        SwingUtilities.updateComponentTreeUI(backgroundPanel);
    }

    //ПРИБРАТИ БАНАНИ
    public void removeBanana(int x, int y) {
        JPanel panelToRemove = bananaPanels[x][y];
        availableCells[x - 1][y - 1] = true;
        singleDeckAttempts--;
        attemptsCount--;

        Container parent = panelToRemove.getParent();
        parent.remove(panelToRemove);
        parent.revalidate();
        parent.repaint();
    }

    //Check if coordinates are written in correct format on the user's map
    public void checkFormatOfCoordinates() {
        String inputCoordinates = typeCoordinate.getText();

        System.out.println(inputCoordinates.length());

        if (inputCoordinates.isEmpty() || inputCoordinates == null) {
            JOptionPane.showMessageDialog(backgroundPanel, "Заповніть координати ", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else if (inputCoordinates.length() == 1) {
            JOptionPane.showMessageDialog(backgroundPanel, "<html>Неправильний формат координат.<br> У координатах обов'язково має бути літера й цифра", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else if (!Character.isLetter(inputCoordinates.charAt(0))) {
            JOptionPane.showMessageDialog(backgroundPanel, "<html>Неправильний формат координат.<br> Перший символ має бути літерою", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (inputCoordinates.length() == 3) {
            if (!Character.isDigit(inputCoordinates.charAt(1)) || !Character.isDigit(inputCoordinates.charAt(2))) {
                JOptionPane.showMessageDialog(backgroundPanel, "<html>Неправильний формат координат.<br> Другий й третій символи мають бути цифрами. ", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (inputCoordinates.charAt(1) != '1' && inputCoordinates.charAt(2) != '0') {
                JOptionPane.showMessageDialog(backgroundPanel, "<html>Неправильний формат координат.<br> Другий символ має бути '1', а другий = '0 '. ", "ERROR", JOptionPane.ERROR_MESSAGE);

            } else if (((int) inputCoordinates.charAt(0) - 96 > 10) && inputCoordinates.charAt(1) != '1' && inputCoordinates.charAt(2) != '0') {
                JOptionPane.showMessageDialog(backgroundPanel, "<html>Неправильний формат координат.<br>Перший символ має бути літерою до 'j'. Другий символ має бути '1', а третій = '0 '. ", "ERROR", JOptionPane.ERROR_MESSAGE);

            } else if (inputCoordinates.charAt(1) != '1') {
                JOptionPane.showMessageDialog(backgroundPanel, "<html>Неправильний формат координат.<br> Другий символ має бути '1'. ", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (inputCoordinates.charAt(2) != '0') {
                JOptionPane.showMessageDialog(backgroundPanel, "<html>Неправильний формат координат.<br> Третій символ має бути '0'. ", "ERROR", JOptionPane.ERROR_MESSAGE);

            }
        } else if (inputCoordinates.length() == 2) {
            if (((int) inputCoordinates.charAt(0) - 96 > 10)) {
                JOptionPane.showMessageDialog(backgroundPanel, "<html>Неправильний формат координат.<br>Перший символ має бути літерою до 'j' ", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (!Character.isDigit(inputCoordinates.charAt(1))) {
                JOptionPane.showMessageDialog(backgroundPanel, "<html>Неправильний формат координат.<br> Другий символ має бути цифрою. ", "ERROR", JOptionPane.ERROR_MESSAGE);

            }
        } else if (inputCoordinates.length() > 3) {
            JOptionPane.showMessageDialog(backgroundPanel, "У координаті не може бути більше трьох символів ", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }

    // передати в клас CompAlgorithm список точок корисувача
    private void giveCoordinates(){
        toGiveCoordinates = false;
        compAlgorithm.setBananas(userCoordinates);
    }

//    public void addMonkeyImage(String imagePath ){
//
//        ImageIcon icon = new ImageIcon(imagePath);
//        Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
//        ImageIcon scaledIcon = new ImageIcon(scaledImage);
//        JLabel label = new JLabel(scaledIcon);
//        label.setBounds(FRAME_WIDTH - 260, 130, 80, 80);
//
//        backgroundPanel.add(label);
//    }


    public static void main(String[] args) {
        new Frame();

    }

    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        BackgroundPanel() {
            setLayout(null); // Disable layout manager for custom positioning
            //Path
            // liza:/home/liza/IdeaProjects/Monkeyfury/src/beach.jpg
            // zlata: C:\Users\plato\IdeaProjects\Monkeyfury\src\beach.jpg
            backgroundImage = Toolkit.getDefaultToolkit().getImage("/home/liza/IdeaProjects/Monkeyfury/src/beach.jpg");
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}