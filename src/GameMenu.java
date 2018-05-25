import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameMenu {

    private JFrame frame;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameMenu window = new GameMenu();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GameMenu() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 845, 456);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblPodajWielkoPlanszy = new JLabel("Podaj wielko\u015B\u0107 planszy");
        lblPodajWielkoPlanszy.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPodajWielkoPlanszy.setBounds(28, 11, 189, 33);
        frame.getContentPane().add(lblPodajWielkoPlanszy);

        textField = new JTextField();
        textField.setBounds(28, 55, 86, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("OK\r\n");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                GameField.dimSize = Integer.parseInt(textField.getText());
                GameField gameField  = new GameField();
                gameField.frame.setVisible(true);
            }
        });
        btnNewButton.setBounds(128, 55, 89, 23);
        frame.getContentPane().add(btnNewButton);
    }
    public int getPointsForPosition(int position)
    {
        return 0;
    }

}

