import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public static final int FRAME_WIDTH = 1350;
    public static final int FRAME_HEIGHT = 700;
    public static final int PANEL_FOR_FIGHT_WIDTH_HEIGHT = 400;
    public static final int PANEL_Y_FROM_TOP = 50;
    public static final int PANEL_X_FROM_BORDER = 40;
    public static final Color PANEL_BACKGROUND_COLOR = Color.LIGHT_GRAY;

    private JPanel areaForUser, areaForComputer;

    Frame() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        showAreasForFight();
    }

    public void showAreasForFight(){
        BackgroundPanel backgroundPanel = new BackgroundPanel();
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

        areaForComputer.setBounds( 3 *PANEL_X_FROM_BORDER + PANEL_FOR_FIGHT_WIDTH_HEIGHT, PANEL_Y_FROM_TOP, PANEL_FOR_FIGHT_WIDTH_HEIGHT, PANEL_FOR_FIGHT_WIDTH_HEIGHT);
        areaForComputer.setBackground(PANEL_BACKGROUND_COLOR);
        backgroundPanel.add(areaForComputer);

        String[] monkeyfury = {"а", "б", "в", "г", "ґ", "д", "е", "є", "ж", "з"};
        int x = PANEL_X_FROM_BORDER + 10;
        int x2 = PANEL_FOR_FIGHT_WIDTH_HEIGHT + 3 *PANEL_X_FROM_BORDER + 10;
        JLabel[] horizontal  = new JLabel[monkeyfury.length];
        JLabel[] vertical = new JLabel[monkeyfury.length];
        for(int i =0; i < monkeyfury.length; i++) {
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
        for(int i =0; i < 10; i++){
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

            y+= PANEL_FOR_FIGHT_WIDTH_HEIGHT / 10;
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