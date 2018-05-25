import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GameField {

    public static int dimSize = 5;
    public static  int redPlayerScore=0;
    public static  int bluePlayerScore=0;
    public static boolean playerTurn = true;
    private JLabel redPlayerLabel;
    public JFrame frame;
    private ArrayList<JButton> listOfButtons;
    private JLabel bluePlayerLabel;
    private JLabel currentPlayersTurnLabel;
    private JButton btnNewButton_2;
    private JButton btnExit;
    private JPanel panel_2;
    private JPanel panel_3;
    private JTextField textField;
    private JLabel lblNewLabel_1;
    private JTextField textField_1;
    private JButton btnSet;
    private JButton btnNewButton_5;
    private JButton btnNewButton_6;

    /**
     * Launch the application.
     */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameField window = new GameField();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
    private void buttonActionPerformed(ActionEvent e)
    {
        JButton button =listOfButtons.get(Integer.valueOf(e.getActionCommand()));
        {

            if (playerTurn) {
                button.setBackground(Color.red);
                playerTurn = false;
                button.setEnabled(false);
                redPlayerScore+= calculatePoints(Integer.valueOf(e.getActionCommand()));
            } else {
                button.setBackground(Color.blue);
                playerTurn = true;
                button.setEnabled(false);
                bluePlayerScore+= calculatePoints(Integer.valueOf(e.getActionCommand()));

            }
        }
        redPlayerLabel.setText("Red Player: "+ redPlayerScore);
        bluePlayerLabel.setText("Blue Player: "+ bluePlayerScore);
        if(playerTurn)
        {
            currentPlayersTurnLabel.setText("This turn: RED");
        }
        else
        {
            currentPlayersTurnLabel.setText("This turn: BLUE");
        }


    }
    private void buttonPointsCheckSet(MouseEvent e)
    {
        JButton button =(JButton)e.getSource();
        button.setText(String.valueOf((calculatePoints(listOfButtons.indexOf(button)))));

    }
    private void buttonPointsCheckReset(MouseEvent e)
    {
        JButton button =(JButton)e.getSource();
        button.setText("");

    }

    /**
     * Create the application.
     */
    public GameField() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        playerTurn =true;
        bluePlayerScore = 0;
        redPlayerScore=0;
        frame = new JFrame();
        listOfButtons = new ArrayList<>();
        frame.setBounds(100, 100, 1173, 597);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 259, 536);
        frame.getContentPane().add(panel);
        panel.setLayout(new GridLayout(11, 0, 0, 0));

        redPlayerLabel = new JLabel("Red Player: 0");
        panel.add(redPlayerLabel);

        bluePlayerLabel = new JLabel("Blue Player: 0");
        panel.add(bluePlayerLabel);

        currentPlayersTurnLabel = new JLabel("This turn: RED");
        panel.add(currentPlayersTurnLabel);

        btnNewButton_2 = new JButton("RESET\r\n");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.dispose();
                GameField gfField = new GameField();
                gfField.frame.setVisible(true);
                MinMax.depth = 3;
                AlphaBeta.depth = 3;
            }
        });
        panel.add(btnNewButton_2);

        btnExit = new JButton("EXIT");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        panel.add(btnExit);

        JButton btnNewButton_1 = new JButton("MinMax move");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                MinMax.test= 0;
                int chosenIndex = MinMax.getMoveId(listOfButtons);
                if (playerTurn) {
                    listOfButtons.get(chosenIndex).setBackground(Color.red);
                    playerTurn = false;
                    listOfButtons.get(chosenIndex).setEnabled(false);
                    redPlayerScore+= calculatePoints(chosenIndex);
                } else {
                    listOfButtons.get(chosenIndex).setBackground(Color.blue);
                    playerTurn = true;
                    listOfButtons.get(chosenIndex).setEnabled(false);
                    bluePlayerScore+= calculatePoints(chosenIndex);

                }

                redPlayerLabel.setText("Red Player: "+ redPlayerScore);
                bluePlayerLabel.setText("Blue Player: "+ bluePlayerScore);
                if(playerTurn)
                {
                    currentPlayersTurnLabel.setText("This turn: RED");
                }
                else
                {
                    currentPlayersTurnLabel.setText("This turn: BLUE");
                }
            }
        });

        panel_2 = new JPanel();
        panel.add(panel_2);
        panel_2.setLayout(null);

        JLabel lblNewLabel = new JLabel("Wysokosc drzewa");
        lblNewLabel.setBounds(79, 0, 118, 14);
        panel_2.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(38, 23, 86, 20);
        panel_2.add(textField);
        textField.setColumns(10);

        JButton btnNewButton_4 = new JButton("Set\r\n");
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                MinMax.depth = Integer.parseInt(textField.getText())-1;
            }
        });
        btnNewButton_4.setBounds(136, 20, 89, 23);
        panel_2.add(btnNewButton_4);
        panel.add(btnNewButton_1);

        JButton btnNewButton_3 = new JButton("AlphaBeta move");
        btnNewButton_3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                AlphaBeta.test = 0;
                int chosenIndex = AlphaBeta.getMoveId(listOfButtons);
                if (playerTurn) {
                    listOfButtons.get(chosenIndex).setBackground(Color.red);
                    playerTurn = false;
                    listOfButtons.get(chosenIndex).setEnabled(false);
                    redPlayerScore+= calculatePoints(chosenIndex);
                } else {
                    listOfButtons.get(chosenIndex).setBackground(Color.blue);
                    playerTurn = true;
                    listOfButtons.get(chosenIndex).setEnabled(false);
                    bluePlayerScore+= calculatePoints(chosenIndex);

                }

                redPlayerLabel.setText("Red Player: "+ redPlayerScore);
                bluePlayerLabel.setText("Blue Player: "+ bluePlayerScore);
                if(playerTurn)
                {
                    currentPlayersTurnLabel.setText("This turn: RED");
                }
                else
                {
                    currentPlayersTurnLabel.setText("This turn: BLUE");
                }
            }
        });

        panel_3 = new JPanel();
        panel.add(panel_3);
        panel_3.setLayout(null);

        lblNewLabel_1 = new JLabel("Wysokosc drzewa\r\n");
        lblNewLabel_1.setBounds(74, 0, 106, 19);
        panel_3.add(lblNewLabel_1);

        textField_1 = new JTextField();
        textField_1.setBounds(40, 23, 86, 20);
        panel_3.add(textField_1);
        textField_1.setColumns(10);

        btnSet = new JButton("Set");
        btnSet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AlphaBeta.depth = Integer.parseInt(textField_1.getText())-1;
            }
        });
        btnSet.setBounds(135, 20, 89, 23);
        panel_3.add(btnSet);
        panel.add(btnNewButton_3);

        btnNewButton_5 = new JButton("Next");
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int chosenIndex = getNext();

                if (playerTurn) {
                    listOfButtons.get(chosenIndex).setBackground(Color.red);
                    playerTurn = false;
                    listOfButtons.get(chosenIndex).setEnabled(false);
                    redPlayerScore+= calculatePoints(chosenIndex);
                } else {
                    listOfButtons.get(chosenIndex).setBackground(Color.blue);
                    playerTurn = true;
                    listOfButtons.get(chosenIndex).setEnabled(false);
                    bluePlayerScore+= calculatePoints(chosenIndex);

                }

                redPlayerLabel.setText("Red Player: "+ redPlayerScore);
                bluePlayerLabel.setText("Blue Player: "+ bluePlayerScore);
                if(playerTurn)
                {
                    currentPlayersTurnLabel.setText("This turn: RED");
                }
                else
                {
                    currentPlayersTurnLabel.setText("This turn: BLUE");
                }
            }
        });
        panel.add(btnNewButton_5);

        btnNewButton_6 = new JButton("Rand");
        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int chosenIndex = getRand();

                if (playerTurn) {
                    listOfButtons.get(chosenIndex).setBackground(Color.red);
                    playerTurn = false;
                    listOfButtons.get(chosenIndex).setEnabled(false);
                    redPlayerScore+= calculatePoints(chosenIndex);
                } else {
                    listOfButtons.get(chosenIndex).setBackground(Color.blue);
                    playerTurn = true;
                    listOfButtons.get(chosenIndex).setEnabled(false);
                    bluePlayerScore+= calculatePoints(chosenIndex);

                }

                redPlayerLabel.setText("Red Player: "+ redPlayerScore);
                bluePlayerLabel.setText("Blue Player: "+ bluePlayerScore);
                if(playerTurn)
                {
                    currentPlayersTurnLabel.setText("This turn: RED");
                }
                else
                {
                    currentPlayersTurnLabel.setText("This turn: BLUE");
                }
            }
        });
        panel.add(btnNewButton_6);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(279, 11, 868, 434);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(new GridLayout(dimSize, dimSize, 0, 0));

        for (int i =0;i<dimSize*dimSize;i++)
        {
            JButton btnNewButton = new JButton();
            btnNewButton.addMouseListener(new MouseListener() {

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e))
                        buttonPointsCheckReset(e);

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e))
                        buttonPointsCheckSet(e);

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    // TODO Auto-generated method stub

                }
            });

            btnNewButton.addActionListener(new ActionListener() {

                                               @Override
                                               public void actionPerformed(ActionEvent e) {
                                                   buttonActionPerformed(e);

                                               }

                                           }
            );


            panel_1.add(btnNewButton);
            listOfButtons.add(btnNewButton);
            btnNewButton.setActionCommand(String.valueOf(listOfButtons.indexOf(btnNewButton)));;
        }

    }
    public int checkVerical(int buttonId)
    {

        for(int i = buttonId-dimSize; i>-1;i=i-dimSize)
        {
            if(listOfButtons.get(i).isEnabled())
                return 0;

        }
        for(int i = buttonId+dimSize; i<listOfButtons.size();i=i+dimSize)
        {
            if(listOfButtons.get(i).isEnabled())
                return 0;

        }

        return dimSize;
    }
    public int checkHorizontal(int buttonId)
    {
        for (int i =1; i<=buttonId % dimSize;i++)
        {
            if(listOfButtons.get(buttonId-i).isEnabled()) return 0;
        }
        for (int i =1; i<=dimSize-1-(buttonId % dimSize);i++)
        {
            if(listOfButtons.get(buttonId+i).isEnabled()) return 0;
        }
        return dimSize;
    }
    public int checkCrossWiseLeft(int buttonId)
    {
        int returnvalue = 0;
        boolean flag = true;
        for(int i =buttonId-dimSize-1; (i>-1) && (dimSize-1 != i % dimSize);i = i-dimSize-1)
        {
            if(listOfButtons.get(i).isEnabled()) flag = false;
            returnvalue++;
        }

        for(int i =buttonId+1+dimSize; i < (listOfButtons.size()) && (0 != i % dimSize);i = i+dimSize+1)
        {
            if(listOfButtons.get(i).isEnabled()) flag = false;
            returnvalue++;
        }

        returnvalue++;
        if(flag)
        {
            return returnvalue >1 ? returnvalue:0;
        }
        return 0;

    }
    public int checkCrossWiseRight(int buttonId)
    {
        int returnvalue = 0;
        boolean flag = true;
        for(int i =buttonId+dimSize-1; i < (listOfButtons.size()) && (dimSize-1 != i % dimSize);i = i+dimSize-1)
        {
            if(listOfButtons.get(i).isEnabled()) flag = false;
            returnvalue++;
        }

        for(int i =buttonId+1-dimSize; i >-1 && (0 != i % dimSize);i = i-dimSize+1)
        {
            if(listOfButtons.get(i).isEnabled()) flag = false;
            returnvalue++;
        }

        returnvalue++;
        if(flag)
        {
            return returnvalue >1 ? returnvalue:0;
        }
        return 0;

    }
    public int calculatePoints(int position)
    {
        return checkVerical(position) + checkHorizontal(position) + checkCrossWiseLeft(position) + checkCrossWiseRight(position);
    }
    public int getNext()
    {
        for( int i =0;i<dimSize*dimSize;i++)
        {
            if(listOfButtons.get(i).isEnabled()) return i;
        }
        return -999;
    }
    public int getRand()
    {
        ArrayList<JButton> freeButtons = new ArrayList<>();
        Random r = new Random();

        for( int i =0;i<dimSize*dimSize;i++)
        {
            if(listOfButtons.get(i).isEnabled()) freeButtons.add(listOfButtons.get(i));
        }
        return listOfButtons.indexOf(freeButtons.get(r.nextInt(freeButtons.size())));

    }
}
