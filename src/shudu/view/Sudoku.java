package shudu.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import shudu.controller.ButtonController;
import shudu.controller.SudokuController;
import shudu.model.Game;

/**
 * Main class of program.
 *
 *
 */
public class Sudoku extends JFrame {
    public Sudoku() {
        super("Sudoku");
//        Image image=new ImageIcon("resource/bgss3.png").getImage();  
	    JPanel pane = new JPanel(); 
	    pane.setLayout(new BorderLayout(2,2)); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        Game game = new Game();

        ButtonController buttonController = new ButtonController(game);
        ButtonPanel buttonPanel = new ButtonPanel();
        buttonPanel.setController(buttonController);
        pane.add(buttonPanel, BorderLayout.SOUTH);

        SudokuPanel sudokuPanel = new SudokuPanel();
        SudokuController sudokuController = new SudokuController(sudokuPanel, game);
        sudokuPanel.setGame(game);
        sudokuPanel.setController(sudokuController);
        pane. add(sudokuPanel, BorderLayout.CENTER);
        
        Time_score timescore=new Time_score();
        pane.add(timescore, BorderLayout.NORTH);
        
        game.addObserver(buttonPanel);
        game.addObserver(sudokuPanel);

        
        add(pane);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false); 
        setSize(472, 680);
        setTitle("Êý¶ÀÓÎÏ·");
        System.out.println(this.getWidth()+""+this.getHeight());
    }

    /**
     * Main entry point of program.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Use System Look and Feel
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception ex) { ex.printStackTrace(); }
        new Sudoku();
    }
}
