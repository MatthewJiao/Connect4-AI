import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//use ternary operators (hold.equals("joe") ? print "joe" : print "not joe"

public class Gui extends JFrame implements ActionListener {
    public static int[][] state = new int[6][7];//state array to keep track of board
    public static int maxDepth = 0;//minimax depth. The greater the integer, the deeper it goes

    static boolean players = false; // who goes first
    boolean turn = true; // keeps track of turn
  boolean draw = true; // if there is a draw
    int buttonState = 0; // keeps track of the state of the buttons. This controls when certain buttons will be disabled 

    JButton[] color = new JButton[4];   // This is an array of buttons to display the color options
    Color[] colorChoice = new Color[2]; // This array of type Color holds the two colors the player choses
    String[] colorChoiceOutput = new String[2];  // This string array holds the names of the colors the player choses

    JButton[] gameType = new JButton[2];  // gameType is a button array that lets the player select difficulty level in addition to who goes first (player or ai)
    JButton[] selectMove = new JButton[7];  // array of buttons for player to select which column to place disc
    JButton clear = new JButton("Clear Board"); //this button resets all variable states and removes all discs form the screen. It prepares the application for a new game
    JButton singleMode = new JButton("Single player");  // this is a button for single player mode
    JButton twoMode = new JButton("Two player"); // this is a button for double player mode

    JLabel outputLabel = new JLabel("Select 2 Colors", JLabel.CENTER);  // this label displays the state of the game in addition to instructions to aid the player in selecting game mode

    static RoundedBorder[][] circleButton = new RoundedBorder[6][7];   // this is a declares 2d array of RoundedBorder type which are essentially circles

    
    public Gui() { // start of constructor 
        setTitle("Connect 4"); // set title 
        setSize(1080, 720); // set initial size
        setResizable(true);  // allow user to resize

        setLayout(new GridBagLayout());  // set layout to gridbag
        add(outputLabel, makeConstraints(0, 0, 2, 2)); // x,y,sizex,sizey for the output label

// instantiates color buttons
        color[0] = new JButton("Red");
        color[1] = new JButton("Yellow");
        color[2] = new JButton("Green");
        color[3] = new JButton("Blue");

        // add color buttons to grid bag layout and also adding action listeners to each
        add(color[0], makeConstraints(2, 0, 1, 1));//red button
        color[0].addActionListener(this);
        add(color[1], makeConstraints(2, 1, 1, 1));//yellow button
        color[1].addActionListener(this);
        add(color[2], makeConstraints(3, 0, 1, 1));//green
        color[2].addActionListener(this);
        add(color[3], makeConstraints(3, 1, 1, 1));//blue
        color[3].addActionListener(this);

        //instantiating gameType buttons
        gameType[0] = new JButton("Easy");
        gameType[1] = new JButton("Hard");

        // adding buttons to gridbag layout and adding action listener
        add(gameType[0], makeConstraints(4, 0, 1, 1));//easy button
        gameType[0].addActionListener(this);
        add(gameType[1], makeConstraints(4, 1, 1, 1));//hard button
        gameType[1].addActionListener(this);

        // disable buttons
        gameType[0].setEnabled(false);
        gameType[1].setEnabled(false);
        
        
        // setting up more buttons the same way
        add(clear, makeConstraints(5, 0, 1, 2));
        clear.addActionListener(this);

        add(singleMode, makeConstraints(6, 0, 1, 1));
        singleMode.addActionListener(this);
        singleMode.setEnabled(false);
        
        add(twoMode, makeConstraints(6, 1, 1, 1));
        twoMode.addActionListener(this);
        twoMode.setEnabled(false);

        
        for (int i = 0; i < 7; i++) {//create the line of buttons to place a piece in a column
            selectMove[i] = new JButton("Place in column " + (i + 1)); // propts user
            selectMove[i].addActionListener(this);
            selectMove[i].setEnabled(false);
            add(selectMove[i], makeConstraints(i, 2, 1, 2));
        }

        for (int i = 0; i < 6; i++) {//create the grid to display the state of the game
            for (int ii = 0; ii < 7; ii++) {
                circleButton[i][ii] = new RoundedBorder(); // create instance of RoundedBorder class
                add(circleButton[i][ii], makeConstraints(ii, (i*2) + 4, 1, 2)); // adding to grid bag layout
            }
        }

        setVisible(true);// visibility of GUI to true
    }

    public GridBagConstraints makeConstraints(int x, int y, int width, int height) { // method to manage grid bag constraints. The position and size
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = x;
        g.gridy = y;
        g.gridwidth = width;
        g.gridheight = height;
        g.fill = GridBagConstraints.BOTH;
        g.weightx = 1;
        g.weighty = 1;
        return g;
    }

    public void actionPerformed(ActionEvent event) { 
        String command = event.getActionCommand(); // the button the user clicks

        if (buttonState < 2) { // after both colors are choosen
            if (command.equals("Red")) {
                colorChoice[buttonState] = Color.RED;
                colorChoiceOutput[buttonState] = "Red";
                color[0].setEnabled(false);
            } else if (command.equals("Yellow")) {
                colorChoice[buttonState] = Color.YELLOW;
                colorChoiceOutput[buttonState] = "Yellow";
                color[1].setEnabled(false);
            } else if (command.equals("Green")) {
                colorChoice[buttonState] = Color.GREEN;
                colorChoiceOutput[buttonState] = "Green";
                color[2].setEnabled(false);
            } else if (command.equals("Blue")) {
                colorChoice[buttonState] = Color.BLUE;
                colorChoiceOutput[buttonState] = "Blue";
                color[3].setEnabled(false);
            }
            buttonState++; // only allows user to chose two colors. then loop terminates
        }
        if (buttonState == 2) { // after both colors are choosen
            for (int i = 0; i < 4; i++) {
                color[i].setEnabled(false); // disable color options 
            }
            singleMode.setEnabled(true); // enable the next two buttons the user can select
            twoMode.setEnabled(true);
            outputLabel.setText("Single Player or Double Player"); // prompts user with instructions
        }


        if (command.equalsIgnoreCase("Single player")) {
            outputLabel.setText("Select the Difficulty");
            gameType[0].setEnabled(true); // enable the next buttons the user can select
            gameType[1].setEnabled(true);
            singleMode.setEnabled(false); // disable these two buttons 
            twoMode.setEnabled(false);
            buttonState = 3; // button state helps the gui make decisions depending on the state
        } else if (command.equalsIgnoreCase("Two player")) {
            buttonState = 5;
            singleMode.setEnabled(false);
            twoMode.setEnabled(false);
        }


        if (command.equals("Easy")) {
            maxDepth = 5; // easy mode
            buttonState = 4;
        } else if (command.equals("Hard")) {
            maxDepth = 8; // hard mode (takes longer to run)
            buttonState = 4;
        }
        if (buttonState == 4) { // player selects additional options
            gameType[0].setText("AI Goes First");
            gameType[1].setText("Player Goes First");
            outputLabel.setText("Select Who goes First");
        }

        if (command.equalsIgnoreCase("AI Goes First")) {
            turn = false;
            buttonState = 5;
        } else if (command.equalsIgnoreCase("Player Goes First")) {
            buttonState = 5;
        }
        if (buttonState == 5) {
            gameType[0].setEnabled(false); // disable buttons
            gameType[1].setEnabled(false);
        }


        if (command.equalsIgnoreCase("Clear Board")) { // clear board will clear the board and will also reset and previously chosen game modes
            outputLabel.setText("Select 2 Colors");
            // reset values for a new game
            buttonState = 0;
            turn = true;
            gameType[0].setText("Easy");
            gameType[1].setText("Hard");
            gameType[0].setEnabled(false);
            gameType[1].setEnabled(false);
            singleMode.setEnabled(false);
            twoMode.setEnabled(false);

            for (int i = 0; i < 7; i++) {
                selectMove[i].setEnabled(false); // reset buttons
                if (i < 4) {
                    color[i].setEnabled(true);
                }
            }
            reset(state); // reset board
        } else if (buttonState > 4 && command.equalsIgnoreCase("Two player") || command.equalsIgnoreCase("AI Goes First") || command.equalsIgnoreCase("Player Goes First")) { // after player selects game mode, the game starts

            if (!turn) {
                outputLabel.setText(colorChoiceOutput[0] + " is going"); //tells user who is going
            } else {
                outputLabel.setText(colorChoiceOutput[1] + " is going");
            }

            for (int i = 0; i < 7; i++) {
                selectMove[i].setEnabled(true);
            }
            if (command.equalsIgnoreCase("AI Goes First") || command.equalsIgnoreCase("Player Goes First")) {
                players = false; // keeps track of the who is going first
                if (!turn) {
                    makeMove(3, 2);
                    turn = true;
                }
            } else {
                players = true;
            }
        } else {

            for (int i = 0; i < 7; i++) { // if player makes a move
                if (event.getSource() == selectMove[i]) {
                    if (validMove(i)) { // validation of move first
                        if (!players) {
                            makeMove(i, 1); // makes move
                            if (outputLabel.getText().contains("win")) {
                                turn = true;//if player 1 wins, then player 2 goes first next game
                                break;
                            }
                            outputLabel.setText(colorChoiceOutput[0] + " is going"); // tells user who is going
                            makeMove(ai(), 2);//if ai wins remember to change turn to false
                        } else if (!turn) {
                            outputLabel.setText(colorChoiceOutput[1] + " is going");
                            makeMove(i, 1);
                            turn = true;
                        } else {
                            outputLabel.setText(colorChoiceOutput[0] + " is going");
                            makeMove(i, 2);
                            turn = false;
                        }
                    } else {
                        outputLabel.setText("Invalid move"); // invalid move
                    }
                }
            }
        }
    }

    public void reset(int[][] state) { // reset the state of the board
        for (int i = 0; i < 6; i++) {
            for (int ii = 0; ii < 7; ii++) {
                circleButton[i][ii].setColor(Color.WHITE); // reset color
                repaint(); // repaints the GUI using the RoundedBorder class
                state[i][ii] = 0; // reset the state
            }
        }
    }


    public void makeMove(int move, int player) { // make a move
        for (int i = 5; i >= 0; i--) { // goes form bottom to top
            if (state[i][move] == 0) { // the first empty slot
                state[i][move] = player;
                if (player == 1) {
                    circleButton[i][move].setColor(colorChoice[0]); // set color
                    repaint();
                } else {
                    circleButton[i][move].setColor(colorChoice[1]);
                    repaint();
                }
                break;
            }
        }

        if (winningMove(move, player)) { // if move is a winning move
            outputLabel.setText((player == 2 ? colorChoiceOutput[1] : colorChoiceOutput[0]) + " wins"); // displays who wins
           buttonReset(selectMove); // reset move selection buttons
        }

      
        for (int i = 0; i < 7; i++) {//check if the move just made resulted in a draw
            if (state[0][i] == 0) {
                draw = false;
                break;
            }
        }
        if (draw) {
            outputLabel.setText("Game is a draw"); // display if game is draw. when entire board is filled and there is no winner
            buttonReset(selectMove); // reset move selection buttons
        }
    }
    
    public static void buttonReset(JButton[] selectMove){ // reset move selection buttons method
      for (int j = 0; j < 7; j++) {
                selectMove[j].setEnabled(false); //disable buttons
            }
    }

    public static String[] moveSurrounding(int x, int player) {// returns an array with the state of the board around the move made
        int y = getTop(x);// returns the y value of the move
        String[] check = new String[4]; // array of state around the move

        for (int i = 0; i < 4; i++) { // first initialize the state of the move made
            check[i] = Integer.toString(player);
        }

    // these loops will populate array check with the surrounding states
        // adding states to the beginning AND the end of check to retain order
        for (int j = 1; j <= 3; j++) { // will loop 4 times because string can onyl be 3 in length to the right or the left
            if (y + j < 6) { //if y coordinate is valid 
                check[2] = check[2] + Integer.toString(state[y + j][x]); // adding to end of the String
                if (x + j < 7) { //if x coordinate is valid 
                    check[3] = check[3] + Integer.toString(state[y + j][x + j]);
                }
            }
            if (x + j < 7) { //if x coordinate is valid 
                check[0] = check[0] + Integer.toString(state[y][x + j]);
                if (y - j >= 0) { //if y coordinate is valid 
                    check[1] = check[1] + Integer.toString(state[y - j][x + j]);
                }
            }
            if (x - j >= 0) { //if x coordinate is valid 
                check[0] = Integer.toString(state[y][x - j]) + check[0]; // adding to beginning of the String
                if (y + j < 6) { //if y coordinate is valid 
                    check[1] = Integer.toString(state[y + j][x - j]) + check[1];
                }
            }
            if (y - j >= 0) { //if y coordinate is valid 
                check[2] = Integer.toString(state[y - j][x]) + check[2];
                if (x - j >= 0) { //if x coordinate is valid 
                    check[3] = Integer.toString(state[y - j][x - j]) + check[3];
                }
            }
        }
        return check; // return array
    }

    public static boolean winningMove(int x, int player) {//method overloading allowing winningMove to be called with just column number and player
        String[] check = moveSurrounding(x, player);
        return winningMove(check, player); 
    }

    public static boolean winningMove(String[] check, int player) {//or with a player and a pre-made check string
        String winning = String.valueOf(player * 1111); // result will either be "1111" or "2222" which are the states of winning moves

        for (int k = 0; k < 4; k++) {
            if (check[k].contains(winning)) {
                return true; // if there is winning move
            }
        }
        return false; // else there is no winning  move
    }

    public static boolean aiCase1(String[] check, int player) { // helps the ai play aggressive and defensive
        if (player == 1) {
            for (int k = 0; k < 4; k++) {
                if (check[k].contains("01110")) { // this string is a dangerous double win that the ai is scanning for (player)
                    return true;
                }
            }
        }
        if (player == 2) {
            for (int k = 0; k < 4; k++) {
                if (check[k].contains("02220")) { // this string is a dangerous double win that the ai is scanning for (ai)
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean aiCase2(String[] check, int player) { // helps the ai play aggressive and defensive
        if (player == 1) {
            for (int k = 0; k < 4; k++) {
                if (check[k].contains("0110") || check[k].contains("01010")) {  // thse strings are dangerous double wins that the ai is scanning for (player)
                    return true;
                }
            }
        }
        if (player == 2) {
            for (int k = 0; k < 4; k++) {
                if (check[k].contains("0220") || check[k].contains("02020")) {  // thse strings are dangerous double wins that the ai is scanning for (ai)
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean aiRegret(int move) { // scans where not to place a move if it results in the player winning on the next turn
        if (validMove(move)) {
            makeTestMove(move, true); // test move
            if (winningMove(move, 1)) { // if player wins on next move
                removeMove(move);
                return true; // player will win on next turn; return true
            }
            removeMove(move); // remove test move
        }
        return false; // player will not win on next turn; return false
    }

    public static int ai() { // this is the ai 

        String[] check; // holds the state of the surrounding discs of the last move
        for (int player = 2; player > 0; player--) { // check if a win can be made in the next move by the player or the AI. The AI has priority
            for (int i = 0; i < 7; i++) {
                if (validMove(i)) { // validation
                    makeTestMove(i, player == 1); 
                    check = moveSurrounding(i, player); 
                    if (winningMove(check, player)) { // if win can be accomplished 
                        removeMove(i);  
                        return i;
                    }
                    removeMove(i); // remove test move
                }
            }
        }
        

        for (int player = 1; player < 3; player++) {  // helps the ai play aggressive and defensive by looking for certain scenarios
            for (int i = 0; i < 7; i++) {
                if (validMove(i)) {
                    makeTestMove(i, player == 1); // test move
                    check = moveSurrounding(i, player); // the surrounding states
                    if (aiCase1(check, player) && !aiRegret(i)) {
                        removeMove(i);
                        System.out.println("playing player " + player + " with case 1");
                        return i; // return move to make for ai
                    }
                    removeMove(i);
                }
            }
        }
        for (int player = 1; player < 3; player++) { // helps the ai play aggressive and defensive by looking for certain scenarios
            for (int i = 0; i < 7; i++) {
                if (validMove(i)) {
                    makeTestMove(i, player == 1); // make test move
                    check = moveSurrounding(i, player); // the surrounding states
                    if (aiCase2(check, player) && !aiRegret(i)) { 
                        removeMove(i); // remove test move
                        System.out.println("playing player " + player + " with case 2");
                        return i; // return move to make for ai
                    }
                    removeMove(i);
                }
            }
        }


        int bestScore = Integer.MAX_VALUE;
        int bestMove = 0;
        int hold;

        for (int i = 0; i < 7; i++) {//finally try to find a decent move with minimax
            if (validMove(i)) {
                makeTestMove(i, false);
                hold = scoreMove(i, 0, true);
                if (hold <= bestScore) {
                    bestScore = hold;
                    bestMove = i;
                }
                removeMove(i);
            }
        }
        return bestMove; // return the best move
    }

    //call should be scoreMove(i, 0, true);
    public static int scoreMove(int move, int depth, boolean testTurn) {//if turn is false than it is the ai's turn
        if (depth == maxDepth || !validMove(move)) return 0;
        if (winningMove(move, testTurn ? 1 : 2)) {
            return (maxDepth / 2 + 1) - (depth / 2);
        }
        int score = 0;
        for (int i = 0; i < 7; i++) {
            makeTestMove(move, testTurn);
            score += scoreMove(i, depth + 1, !testTurn);
            removeMove(move);
        }
        return score;
    }

    public static int getTop(int x) {//send x value of column and get the y position of the last disc placed inth e column
        for (int i = 0; i < 6; i++) {
            if (state[i][x] != 0) {
                return i; // return y position
            }
        }
        return 0; 
    }

    public static boolean validMove(int move) {//check if a valid move can be made in that column
        return state[0][move] == 0; // if topmost state is not filled
    }

    public static void makeTestMove(int move, boolean testTurn) { // make a test move for AI. This allows the AI to check for bad moves
        for (int i = 5; i >= 0; i--) { // start from bottom and looks for first empty space
            if (state[i][move] == 0) {
                state[i][move] = testTurn ? 1 : 2;
                return;
            }
        }
    }

    public static void removeMove(int move) { // remove top most disc in state array
        for (int i = 0; i < 6; i++) { // start from top and looks for first not empty space
            if (state[i][move] != 0) {
                state[i][move] = 0;
                return;
            }
        }
    }

    public static void main(String[] args) {//move this to other class
        Gui frame = new Gui();
        frame.setMinimumSize(new Dimension(1080, 720));//also this does not work on my pc
    }
}

