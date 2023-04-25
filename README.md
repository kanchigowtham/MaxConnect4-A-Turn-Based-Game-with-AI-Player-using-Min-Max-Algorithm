# MaxConnect4-A-Turn-Based-Game-with-AI-Player-using-Min-Max-Algorithm
README

Task 2

Name: Kanchi Gowtham Kumar
UTA ID: 1002044003

The programming language used to complete the Task 2 is Java.

Code Structure:
AiPlayer.java
•	findBestPlay() : To make a move for the computer using the min and max value.
•	calculateMinUtility() : To calculate the min value.
•	calculateMaxUtility() : To calculate the max value.

GameBoard.java
•	getScore() : This function calculates the player’s score and returns it.
•	setPieceValue() : To set the piece value for both human & computer(1 or 2).
•	getCurrentTurn() : To get the current turn.
•	getGameBoard() :  To get the whole gameboard as a dual indexed array.
•	isGameFull() : To check whether the Gameboard is full or not.
•	printGameBoard() : To print the GameBoard.

maxconnect4.java
•	makeComputerPlayOneMove() : To handle the computer's move for one-move mode.
•	makeComputerPlayInteractive() : To handle the computer's move for interactive mode.
•	printFinalResult() : To print the final result of the game.
•	makeHumanPlay() : To handle the human's move for interactive mode.



Instructions to run the code:

Before running the code, we need to make sure that the system should be able to run java files. The following setup needs to be done before running the file.
IDE: 
•	Any IDE which can run java files can be used to run these files. We have used Visual Studio Code to create, edit and run the files.
•	To download and install VS Code follow this link: Download Visual Studio Code - Mac, Linux, Windows
•	Based on the specifications of your device select a version to download and install it.

Steps to be done before running the java file:
After installing, click on extensions from the side or click CTRL+SHIFT+X to open the extensions. Then search for ‘Extension Pack for Java’ (Published by Microsoft) and install it. Make sure you have installed the java JDK if not, download from Java Downloads | Oracle and install it.

Run the Java file:
•	Open the project folder from the VS code window and open the file maxconnect4.java
•	From the menu select Terminal and select New Terminal.
•	When the terminal opens type “javac maxconnect4.java” then click enter
•	Then type “java maxconnect4 interactive [input_file] [computer-next/human-next] [depth]” then click enter.
•	Then play the game till the board gets full by entering the column number.
•	Once the game gets over it will print the winner of the game.











