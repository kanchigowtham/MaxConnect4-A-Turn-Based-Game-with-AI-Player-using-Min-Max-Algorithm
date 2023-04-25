/*
 * CSE 5360-003 Artificial Intelligence - I
 * Kanchi Gowtham Kumar
 * 1002044003
 */
import java.util.Scanner;

public class maxconnect4 {

    public static Scanner ipstream = null;
    public static GameBoard currentGame = null;
    public static AiPlayer aiPlayer = null;

    public static final int ONE = 1;
    public static final int TWO = 2;
    public static int HUMAN_PIECE;
    public static int COMPUTER_PIECE;
    public static int INVALID = 99;
    public static final String FILEPATH_PREFIX = "../";
    public static final String COMPUTER_OPT_FILE = "computer.txt";
    public static final String HUMAN_OPT_FILE = "human.txt";

    public enum MODE {
        INTERACTIVE,
        ONE_MOVE
    };

    public enum PLAYER_TYPE {
        HUMAN,
        COMPUTER
    };
    
    /*
	 * Method Name: makeComputerPlayOneMove
	 * Purpose: To handle the computer's move for one-move mode
	 */
    private static void makeComputerPlayOneMove(String outputFileName) throws CloneNotSupportedException {

        int playColumn = 99; 
       // boolean playMade = false; 

        System.out.print("\nMaxConnect-4 game:\n");

        System.out.print("Game state before move:\n");

        currentGame.printGameBoard();  // To print the current game board

        System.out.println("Score: Player-1 = " + currentGame.getScore(ONE) + ", Player-2 = " + currentGame.getScore(TWO)+ "\n ");  //To print the current scores

        if (currentGame.isGameFull()) {
            System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over.");
            return;
        }


        int current_player = currentGame.getCurrentTurn();

        playColumn = aiPlayer.findBestPlay(currentGame);

        if (playColumn == INVALID) {
            System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over.");
            return;
        }

        currentGame.playPiece(playColumn);

        System.out.println("move " + currentGame.getPieceCount() + ": Player " + current_player + ", column "+ (playColumn + 1));

        System.out.print("Game state after move:\n");

        currentGame.printGameBoard();

        System.out.println("Score: Player-1 = " + currentGame.getScore(ONE) + ", Player-2 = " + currentGame.getScore(TWO)+ "\n ");

        currentGame.printGameBoardToFile(outputFileName);

    }

    /*
	 * Method Name: makeComputerPlayInteractive
	 * Purpose: To handle the computer's move for interactive mode
	 */
    private static void makeComputerPlayInteractive() throws CloneNotSupportedException {

        printBoardAndScore();

        System.out.println("\n Computer's turn:\n");

        int playColumn = INVALID; 

        playColumn = aiPlayer.findBestPlay(currentGame);

        if (playColumn == INVALID) {
            System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over.");
            return;
        }

        currentGame.playPiece(playColumn);

        System.out.println("move: " + currentGame.getPieceCount() + " , Player: Computer , Column: " + (playColumn + 1));

        currentGame.printGameBoardToFile(COMPUTER_OPT_FILE);

        if (currentGame.isGameFull()) {
            printBoardAndScore();
            printFinalResult();
        } else {
            makeHumanPlay();
        }
    }


    /*
	 * Method Name: printFinalResult
	 * Purpose: To print the final result of the game
	 */
    private static void printFinalResult() {
        int human_score = currentGame.getScore(maxconnect4.HUMAN_PIECE);
        int comp_score = currentGame.getScore(maxconnect4.COMPUTER_PIECE);
        
        System.out.println("\n Final Result:");
        if(human_score > comp_score){
            System.out.println("\n Congratulations!! You won this game."); 
        } else if (human_score < comp_score) {
            System.out.println("\n You lost!! Good luck for next game.");
        } else {
            System.out.println("\n Game is tie!!");
        }
    }


    /*
	 * Method Name: makeHumanPlay
	 * Purpose: To handle the human's move for interactive mode
	 */
    private static void makeHumanPlay() throws CloneNotSupportedException {

        printBoardAndScore();

        System.out.println("\n Human's turn:\nKindly play your move here(1-7):");

        ipstream = new Scanner(System.in);

        int playColumn = INVALID;

        do {
            playColumn = ipstream.nextInt();
        } while (!isValidPlay(playColumn));

        currentGame.playPiece(playColumn - 1);

        System.out.println("move: " + currentGame.getPieceCount() + " , Player: Human , Column: " + playColumn);
        
        currentGame.printGameBoardToFile(HUMAN_OPT_FILE);

        if (currentGame.isGameFull()) {
            printBoardAndScore();
            printFinalResult();
        } else {
            makeComputerPlayInteractive();
        }
    }

    /*
	 * Method Name: isValidPlay
	 * Purpose: To check whether the value entered by the human is valid play or not
	 */
    private static boolean isValidPlay(int playColumn) {
        if (currentGame.isValidPlay(playColumn - 1)) {
            return true;
        }
        System.out.println("Opps!!...Invalid column , Kindly enter column value between 1 to 7.");
        return false;
    }

    /*
	 * Method Name: printBoardAndScore
	 * Purpose: To display the current board state and score of each player.
	 */
    public static void printBoardAndScore() {

        System.out.print("Game state :\n");

        currentGame.printGameBoard();

        System.out.println("Score: Player-1 = " + currentGame.getScore(ONE) + ", Player-2 = " + currentGame.getScore(TWO)+ "\n ");
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        // check for the correct number of arguments
        if (args.length != 4) {
            System.out.println("Four command-line arguments are needed:\n"
                + "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
                + " or:  java [program name] one-move [input_file] [output_file] [depth]\n");

            exit_function(0);
        }

        String gameMode = args[0].toString(); 
        String inputFilePath = args[1].toString(); 
        int depthLevel = Integer.parseInt(args[3]); 

        currentGame = new GameBoard(inputFilePath); // To initialize the game board

        aiPlayer = new AiPlayer(depthLevel, currentGame); //To create the Ai Player

        if (gameMode.equalsIgnoreCase("interactive")) { //Interactive mode
            currentGame.setGameMode(MODE.INTERACTIVE);
            if (args[2].toString().equalsIgnoreCase("computer-next") || args[2].toString().equalsIgnoreCase("C")) { // if computer is next, computer makes a move
                currentGame.setFirstTurn(PLAYER_TYPE.COMPUTER);
                makeComputerPlayInteractive();
            } else if (args[2].toString().equalsIgnoreCase("human-next") || args[2].toString().equalsIgnoreCase("H")){ // if human is next, human makes a move
                currentGame.setFirstTurn(PLAYER_TYPE.HUMAN);
                makeHumanPlay();
            } else {
                System.out.println("\n" + "value for 'next turn' doesn't recognized.  \n try again. \n");
                exit_function(0);
            }
            if (currentGame.isGameFull()) {
                System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over.");
                exit_function(0);
            }

        }else if (!gameMode.equalsIgnoreCase("one-move")) {
            System.out.println("\n" + gameMode + " is an unrecognized game mode \n try again. \n");
            exit_function(0);
        }else { //One move mode
            currentGame.setGameMode(MODE.ONE_MOVE);
            String outputFileName = args[2].toString(); 
            makeComputerPlayOneMove(outputFileName);
        }
    }

    /*
	 * Method Name: exitFunction
	 * Purpose: This method is used when to exit the program prematurly.
	 */
    private static void exit_function(int value) {
        System.out.println("exiting from MaxConnectFour.java!\n\n");
        System.exit(value);
    }
} 