package main;

import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class App {

    static Input input;
    public static void main(String[] args) throws Exception {

        JFrame window = new JFrame();
        input = new Input();
        Panel panel = new Panel();

        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);

        panel.addMouseListener(input);
        panel.startThread();

    }
}
