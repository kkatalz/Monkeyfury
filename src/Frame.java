import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    private JLabel gameLevelLabel;
    private JButton level1Button, level2Button, level3Button, start;


    private JPanel areaForUser, areaForComputer;
    private JTextField typeCoordinate;
    private BackgroundPanel backgroundPanel;

    Frame() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        showAreasForFight();

    }

    public void showAreasForFight() {
        backgroundPanel = new BackgroundPanel();
        this.setContentPane(backgroundPanel);
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
        JLabel bananasType = new JLabel("Оберіть тип банана ");
        bananasType.setBounds(17 * PANEL_X_FROM_BORDER + PANEL_FOR_FIGHT_WIDTH_HEIGHT - 25, PANEL_Y_FROM_TOP + 190, 350, 50);
        bananasType.setFont(new Font("Calibri", Font.PLAIN, 20));
        backgroundPanel.add(bananasType);

        //LIST OF BANANAS TYPE BUTTON
        String[] choices = {"", "двопалубні x4", "трипалубні x3", "чотирипалубні x2", "шестипалубні x1"};
        JComboBox<String> listOfBananas = new JComboBox(choices);
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
        JButton putBanana = new JButton("Поставити банан");
        putBanana.setBackground(Color.white);
        putBanana.setForeground(Color.black);
        putBanana.setBounds(17 * PANEL_X_FROM_BORDER + PANEL_FOR_FIGHT_WIDTH_HEIGHT - 115, PANEL_Y_FROM_TOP + 425, 155, 50);
        backgroundPanel.add(putBanana);


        //CHOOSE BANANAS TYPE BUTTON
        JButton deleteBanana = new JButton("Прибрати банан");
        deleteBanana.setBackground(Color.white);
        deleteBanana.setForeground(Color.black);
        deleteBanana.setBounds(17 * PANEL_X_FROM_BORDER + PANEL_FOR_FIGHT_WIDTH_HEIGHT + 70, PANEL_Y_FROM_TOP + 425, 155, 50);
        backgroundPanel.add(deleteBanana);

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
            System.out.println("X: " + xCoordinate + " Y: " + yCoordinate);
            placeBanana(xCoordinate, yCoordinate);
            typeCoordinate.setText("");


        });
        deleteBanana.addActionListener(e -> {
            checkFormatOfCoordinates();
        });


        //START GAME BUTTON
        start = new JButton("START");
        start.setBackground(Color.white);
        start.setForeground(Color.black);
        start.setBounds(17 * PANEL_X_FROM_BORDER + PANEL_FOR_FIGHT_WIDTH_HEIGHT - 25, PANEL_Y_FROM_TOP + 505, 155, 50);
        backgroundPanel.add(start);

        //check if game level is chosen
        JLabel showGameLevel = new JLabel();
        showGameLevel.setBounds(25, FRAME_HEIGHT - 205, 350, 50);
        showGameLevel.setForeground(Color.white);
        showGameLevel.setFont(new Font("Calibri", Font.PLAIN, 25));
        backgroundPanel.add(showGameLevel);

        start.addActionListener(e -> {
            if (levelOfGame != 1 && levelOfGame != 2 && levelOfGame != 3) {
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
                SwingUtilities.updateComponentTreeUI(backgroundPanel);
            }

        });


        SwingUtilities.updateComponentTreeUI(backgroundPanel);

    }

    public void placeBanana(int x, int y) {
        JPanel panel = new JPanel();

        panel.setLayout(null); // Set the layout manager to null for absolute positioning
        panel.setBounds((x - 1) * BANANA_WIDTH, (y - 1) * BANANA_WIDTH, 40, 40);

        String imagePath = "C:\\Users\\plato\\IdeaProjects\\Monkeyfury\\src\\banana.jpg";
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        label.setBounds(0, 0, 40, 40); // Manually set the bounds of the label

        panel.add(label); // Add the label to the panel

        areaForUser.setLayout(null);
        areaForUser.add(panel);

        SwingUtilities.updateComponentTreeUI(backgroundPanel);

    }


    //Check if coordinates are written in correct format
    public void checkFormatOfCoordinates() {
        String inputCoordinates = typeCoordinate.getText();

        System.out.println(inputCoordinates.length());

        if (inputCoordinates.isEmpty() || inputCoordinates == null) {
            JOptionPane.showMessageDialog(backgroundPanel, "Заповніть координати ", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else if (inputCoordinates.length() == 1) {
            JOptionPane.showMessageDialog(backgroundPanel, "<html>Неправильний формат координат.<br> У координатах обов'язково має бути літера й цифра", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else if (!Character.isLetter(inputCoordinates.charAt(0))) {
            JOptionPane.showMessageDialog(backgroundPanel, "<html>Неправильний формат координат.<br> Перший символ має бути літерою", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else if (inputCoordinates.length() == 3) {
            if (!Character.isDigit(inputCoordinates.charAt(1)) || !Character.isDigit(inputCoordinates.charAt(2))) {
                JOptionPane.showMessageDialog(backgroundPanel, "<html>Неправильний формат координат.<br> Другий й третій символи мають бути цифрами. ", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (inputCoordinates.charAt(1) != '1' && inputCoordinates.charAt(2) != '0') {
                JOptionPane.showMessageDialog(backgroundPanel, "<html>Неправильний формат координат.<br> Другий символ має бути '1', а другий = '0 '. ", "ERROR", JOptionPane.ERROR_MESSAGE);

            }
            else if (((int) inputCoordinates.charAt(0) - 96 > 10) && inputCoordinates.charAt(1) != '1' && inputCoordinates.charAt(2) != '0') {
                JOptionPane.showMessageDialog(backgroundPanel, "<html>Неправильний формат координат.<br>Перший символ має бути літерою до 'j'. Другий символ має бути '1', а третій = '0 '. ", "ERROR", JOptionPane.ERROR_MESSAGE);

            }
            else if (inputCoordinates.charAt(1) != '1') {
                JOptionPane.showMessageDialog(backgroundPanel, "<html>Неправильний формат координат.<br> Другий символ має бути '1'. ", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (inputCoordinates.charAt(2) != '0') {
                JOptionPane.showMessageDialog(backgroundPanel, "<html>Неправильний формат координат.<br> Третій символ має бути '0'. ", "ERROR", JOptionPane.ERROR_MESSAGE);

            }
        } else if (inputCoordinates.length() == 2) {,
            if (((int) inputCoordinates.charAt(0) - 96 > 10) ) {
                JOptionPane.showMessageDialog(backgroundPanel, "<html>Неправильний формат координат.<br>Перший символ має бути літерою до 'j' ", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else if (!Character.isDigit(inputCoordinates.charAt(1))) {
                JOptionPane.showMessageDialog(backgroundPanel, "<html>Неправильний формат координат.<br> Другий символ має бути цифрою. ", "ERROR", JOptionPane.ERROR_MESSAGE);

            }
        } else if (inputCoordinates.length() > 3) {
            JOptionPane.showMessageDialog(backgroundPanel, "У координаті не може бути більше трьох символів ", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }


    public static void main(String[] args) {
        new Frame();
    }

    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        BackgroundPanel() {
            setLayout(null); // Disable layout manager for custom positioning
            //Path
            // liza: /home/liza/Downloads/beach.jpeg
            // zlata: C:\Users\plato\IdeaProjects\Monkeyfury\src\beach.jpg
            backgroundImage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\plato\\IdeaProjects\\Monkeyfury\\src\\beach.jpg");
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}