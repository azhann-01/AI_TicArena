package gui;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {

    JButton[][] board;

    boolean xTurn = true;
    boolean gameOver = false;

    public GameFrame(String username){

        setTitle("TicArena - Game");
        setSize(600,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel boardPanel = new JPanel();

        boardPanel.setLayout(
                new GridLayout(5,5,5,5)
        );

        board = new JButton[5][5];

        Font buttonFont = new Font("Arial", Font.BOLD, 30);

        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                board[i][j] = new JButton();
                board[i][j].setFont(buttonFont);

                int row = i;
                int col = j;

                board[i][j].addActionListener(
                    e -> {
                        if(gameOver)
                            return;

                        if(xTurn){
                            board[row][col].setText("X");
                        }

                        else{
                            board[row][col].setText("O");
                        }
                        board[row][col].setEnabled(false);

                        xTurn = !xTurn;

                        checkWinner();
                        }
                );

                boardPanel.add(board[i][j]);
            }
        }

        add(boardPanel);

        setVisible(true);
    }

    public void checkWinner(){

        // check rows

        for(int i=0; i<5; i++){
            String first = board[i][0].getText();

            if(first.equals(""))
                continue;

            boolean same = true;

            for(int j=1; j<5; j++){
                if(!board[i][j].getText().equals(first)){
                    same = false;
                    break;
                }
            }

            if(same){
                gameOver = true;
                JOptionPane.showMessageDialog(null, first + " Wins!");

                disableBoard();

                return;
            }
        }

        // check columns

        for(int j=0; j<5; j++){
            String first = board[0][j].getText();

            if(first.equals(""))
                continue;

            boolean same = true;

            for(int i=1; i<5; i++){

                if(!board[i][j].getText().equals(first)){
                    same = false;
                    break;
                }
            }

            if(same){

                gameOver = true;

                JOptionPane.showMessageDialog(null, first + " Wins!");

                disableBoard();

                return;
            }
        }

        // check main diagonal

        String first = board[0][0].getText();

        if(!first.equals("")){

            boolean same = true;

            for(int i=1; i<5; i++){

                if(!board[i][i].getText().equals(first)){
                    same = false;
                    break;
                }
            }

            if(same){

                gameOver = true;

                JOptionPane.showMessageDialog(null, first + " Wins!");
                disableBoard();
                return;
            }
        }

        // check opposite diagonal

        first = board[0][4].getText();

        if(!first.equals("")){

            boolean same = true;

            for(int i=1; i<5; i++){

                if(!board[i][4-i].getText().equals(first)){
                    same = false;
                    break;
                }
            }

            if(same){

                gameOver = true;

                JOptionPane.showMessageDialog(null, first + " Wins!");

                disableBoard();

                return;
            }
        }

        // draw condition

        boolean boardFull = true;

        for(int i=0; i<5; i++){

            for(int j=0; j<5; j++){

                if(board[i][j].getText().equals("")){
                    boardFull = false;
                    break;
                }
            }
        }

        if(boardFull){

            gameOver = true;

            JOptionPane.showMessageDialog(null,"Game Over!");

            disableBoard();
        }
    }

    public void disableBoard(){

        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){

            board[i][j].setEnabled(false);
            }
        }
    }
}