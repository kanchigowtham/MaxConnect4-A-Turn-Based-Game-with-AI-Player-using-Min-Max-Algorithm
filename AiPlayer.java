/*
 * CSE 5360-003 Artificial Intelligence - I
 * Kanchi Gowtham Kumar
 * 1002044003
 */

public class AiPlayer {

    public int depthLevel;
    public GameBoard gameBoardShallow;

    public AiPlayer(int depth, GameBoard currentGame) {
        this.depthLevel = depth;
        this.gameBoardShallow = currentGame;
    }

    /*
	 * Method Name: findBestPlay
	 * Purpose: To make a move for the computer using the min and max value
	 */
    public int findBestPlay(GameBoard currentGame) throws CloneNotSupportedException {

        int playChoice = maxconnect4.INVALID;
        if (currentGame.getCurrentTurn() == maxconnect4.ONE) {
            int maxVal = Integer.MAX_VALUE;
            for (int i = 0; i < GameBoard.NOF_COLS; i++) {
                if (currentGame.isValidPlay(i)) {
                    GameBoard nextMoveBoard = new GameBoard(currentGame.getGameBoard());
                    nextMoveBoard.playPiece(i);

                    int value = calculateMaxUtility(nextMoveBoard, depthLevel, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    if (maxVal > value) {
                        playChoice = i;
                        maxVal = value;
                    }
                }
            }
        } else {
            int minVal = Integer.MIN_VALUE;
            for (int i = 0; i < GameBoard.NOF_COLS; i++) {
                if (currentGame.isValidPlay(i)) {
                    GameBoard nextMoveBoard = new GameBoard(currentGame.getGameBoard());
                    nextMoveBoard.playPiece(i);

                    int value = calculateMinUtility(nextMoveBoard, depthLevel, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    if (minVal < value) {
                        playChoice = i;
                        minVal = value;
                    }
                }
            }
        }
        return playChoice;
    }

    /*
	 * Method Name: calculateMinUtility
	 * Purpose: To calculate the min value
	 */
    private int calculateMinUtility(GameBoard gameBoard, int depthLevel, int alpha, int beta) throws CloneNotSupportedException {

        if (!gameBoard.isGameFull() && depthLevel > 0) {
            int maxVal = Integer.MAX_VALUE;
            for (int i = 0; i < GameBoard.NOF_COLS; i++) {
                if (gameBoard.isValidPlay(i)) {
                    GameBoard board4NextMove = new GameBoard(gameBoard.getGameBoard());
                    board4NextMove.playPiece(i);
                    int value = calculateMaxUtility(board4NextMove, depthLevel - 1, alpha, beta);
                    if (maxVal > value) {
                        maxVal = value;
                    }
                    if (maxVal <= alpha) {
                        return maxVal;
                    }
                    if (beta > maxVal) {
                        beta = maxVal;
                    }
                }
            }
            return maxVal;
        } else {
            return gameBoard.getScore(maxconnect4.TWO) - gameBoard.getScore(maxconnect4.ONE);
        }
    }

    /*
	 * Method Name: calculateMinUtility
	 * Purpose: To calculate the max value
	 */
    private int calculateMaxUtility(GameBoard gameBoard, int depthLevel, int alpha, int beta) throws CloneNotSupportedException {

        if (!gameBoard.isGameFull() && depthLevel > 0) {
            int minVal = Integer.MIN_VALUE;
            for (int i = 0; i < GameBoard.NOF_COLS; i++) {
                if (gameBoard.isValidPlay(i)) {
                    GameBoard board4NextMove = new GameBoard(gameBoard.getGameBoard());
                    board4NextMove.playPiece(i);
                    int value = calculateMinUtility(board4NextMove, depthLevel - 1, alpha, beta);
                    if (minVal < value) {
                        minVal = value;
                    }
                    if (minVal >= beta) {
                        return minVal;
                    }
                    if (alpha < minVal) {
                        alpha = minVal;
                    }
                }
            }
            return minVal;
        } else {
            return gameBoard.getScore(maxconnect4.TWO) - gameBoard.getScore(maxconnect4.ONE);
        }
    }
}