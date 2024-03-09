package hw2;

import api.PlayerPosition;
import api.BallType;
import static api.PlayerPosition.*;
import static api.BallType.*;

/**
 * Class that models the game of three-cushion billiards.
 * 
 * @author anoopboyal
 */
public class ThreeCushion {
	
	/**
	 * The points until the game is over
	 */
	private int pointsTillWin;
	
	/**
	 * the player who wins the lag
	 */
	private PlayerPosition lagwinner;
	
	/**
	 * the inning
	 */
	private int inningCount;
	
	/**
	 * the score of player a
	 */
	private int playerAscore;
	
	/**
	 * the score of player b
	 */
	private int playerBscore;
	
	/**
	 * declares when the game is over
	 */
	private boolean gameOver;
	
	/**
	 * sets whether is the shot is a bankshot or not
	 */
	private boolean bankShot;
	
	/**
	 * shows if the shot is a breakshot or not
	 */
	private boolean breakShot;
	
	/**
	 * show if inning is started
	 */
	private boolean inningStarted;
	
	/**
	 * shows if the shot is started
	 */
	private boolean shotStarted = false;
	
	/**
	 * finds the player of the inning
	 */
	private PlayerPosition inningPlayer;
	
	/**
	 * same as the selfBreak variable in lagwinnerchooses
	 */
	private boolean selfbreak;
	
	/**
	 * the color of the ball
	 */
	private BallType ballColor;
	
	/**
	 * the number of times the ball hit the cushion
	 */
	private int cushionHit;
	
	/**
	 * the other person who is not the lag winner
	 */
	private PlayerPosition notlagwinner;

	
	/**
	 * if ball hit red it switches to true
	 */
	private boolean hitRed;
	
	/**
	 * if ball hit yellow it switches to true
	 */
	private boolean hitYellow;
	
	/**
	 * if ball hit white switches to true
	 */
	private boolean hitWhite;
	
	/**
	 * if someone has scored it is true
	 */
	private boolean scored;
	
	/**
	 * the color of the ball for the lagwinner
	 */
	private BallType lagwinnerball;
	
	/**
	 * the color of the ball for the person who did not win the lag
	 */
	private BallType notlagwinnerball;
	
	/**
	 * the score of the lagwinner
	 */
	private int lagwinnerscore;
	
	/**
	 * the score of the player who is not the lag winner
	 */
	private int notlagwinnerscore;

	/**
	 * how many times the ball hit cushoin in a row 
	 * Used for figuring out whether its a bankShot
	 */
	private int hitCushInRow;


	// TODO: EVERTHING ELSE GOES HERE
	// Note that this code will not compile until you have put in stubs for all
	// the required methods.

	// The method below is provided for you and you should not modify it.
	// The compile errors will go away after you have written stubs for the
	// rest of the API methods.

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player A*: X Player B: Y, Inning: Z</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which player is at the
	 * table this inning. The number after the player's name is their score. Z is
	 * the inning number. Other messages will appear at the end of the string.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString() {
		String fmt = "Player A%s: %d, Player B%s: %d, Inning: %d %s%s";
		String playerATurn = "";
		String playerBTurn = "";
		String inningStatus = "";
		String gameStatus = "";
		if (getInningPlayer() == PLAYER_A) {
			playerATurn = "*";
		} else if (getInningPlayer() == PLAYER_B) {
			playerBTurn = "*";
		}
		if (isInningStarted()) {
			inningStatus = "started";
		} else {
			inningStatus = "not started";
		}
		if (isGameOver()) {
			gameStatus = ", game result final";
		}
		return String.format(fmt, playerATurn, getPlayerAScore(), playerBTurn, getPlayerBScore(), getInning(),
				inningStatus, gameStatus);
	}
	
	// Constructor
	/**
	 * Creates a new game of three-cushion billiards with a given lag winner and 
	 * the predetermined number of points required to win the game.
	 * @param lagWinner 
	 * @param pointsToWin
	 */
	public ThreeCushion(PlayerPosition lagWinner, int pointsToWin) {
		pointsTillWin = pointsToWin;
		lagwinner = lagWinner;
		inningCount = 1;
		gameOver = false;
		lagwinnerscore = 0;
		notlagwinnerscore = 0;

	// Finds who is and isn't the lag winner
	if (lagwinner == PLAYER_A) {
		notlagwinner = PLAYER_B;
		playerAscore = lagwinnerscore;
		playerBscore = notlagwinnerscore;			
		} 
	
	else { 
		notlagwinner = PLAYER_A;
		lagwinner = PLAYER_B;
		playerAscore = notlagwinnerscore;
		playerBscore = lagwinnerscore;
		}	
	}
	
	// Methods
	
	/**
	 * Indicates the given ball has impacted the given cushion.
	 */
	public void cueBallImpactCushion() {
		cushionHit += 1;
		inningStarted = true;
		hitCushInRow += 1;
		if (hitRed == true || hitYellow == true || hitWhite == true) {
			bankShot = false;
		}
	}
	
	
	/**
	 * Indicates the player's cue ball has struck the given ball.
	 * @param ball
	 */
	public void cueBallStrike(BallType ball) {
		inningStarted = true;
		if (ball == RED) {
			hitRed = true;
		}
		else if (ball == YELLOW) {
			hitYellow = true;
		}
		else if (ball == WHITE) {
			hitWhite = true;
		if (hitCushInRow == 1 || hitCushInRow == 2) {
			hitCushInRow = 0;
		}
		}
	}
	

	/**
	 * Indicates the cue stick has struck the given ball.
	 * @param ball
	 */
	public void cueStickStrike(BallType ball) {
		
		
		hitCushInRow = 0;
		// For Player A
		bankShot = false;
		inningStarted = true;
		if (inningPlayer == lagwinner) {
			if (ball != lagwinnerball) {
				foul();
				shotStarted = false;
				inningStarted = false;
				
			}
			
		else if (ball == lagwinnerball) {
			shotStarted = true;
			
			}
		}
		
		// For Player B
		if (inningPlayer == notlagwinner) {
			if (ball != notlagwinnerball) {
				foul();
				shotStarted = false;
				inningStarted = false;
				
			}
			
		else if (ball == notlagwinnerball) {
			shotStarted = true;
			inningStarted = true;			
			}
		}
		
		if (gameOver == true) {
			inningStarted = false;
			shotStarted = false;
		}
		
	}
	
	/**
	 * Indicates that all balls have stopped motion.
	 */
	public void endShot() {
		if (lagwinnerball == WHITE && hitRed == true && hitYellow == true && cushionHit >= 3) {
			lagwinnerscore += 1;
			scored = true;
			if (hitCushInRow >= 3) {
				bankShot = true;
			}
		}
		else if (lagwinnerball == YELLOW && hitRed == true && hitWhite == true && cushionHit >= 3) {
			lagwinnerscore += 1;
			scored = true;
			if (hitCushInRow >= 3) {
				bankShot = true;
			}
		}
		
		if (notlagwinnerball == WHITE && hitRed == true && hitYellow == true && cushionHit >= 3) {
			notlagwinnerscore += 1;
			scored = true;
			if (hitCushInRow >= 3) {
				bankShot = true;
			}
		}
		
		else if (notlagwinnerball == YELLOW && hitRed == true && hitWhite == true && cushionHit >= 3) {
			notlagwinnerscore += 1;
			scored = true;
			if (hitCushInRow >= 3) {
				bankShot = true;
			}
		}
		
		if (scored == false) {
			inningCount += 1;
			if (inningPlayer == lagwinner) {
				ballColor = notlagwinnerball;
			}
			else {
				ballColor = lagwinnerball;
			}
		}
	
		if (lagwinnerscore >= pointsTillWin || notlagwinnerscore >= pointsTillWin) {
			gameOver = true;
		}
		
		if (inningStarted == false) {
			inningCount -= 1;
		}
		
		inningStarted = false;
		cushionHit = 0;
		shotStarted = false;
		hitYellow = false;
		hitWhite = false;
		hitRed = false;
		
		if (scored == true) {
			inningStarted = true;
		}
		
		scored = false;
		
		
		
		
		
		
		
		}
	
	
	/**
	 * 	A foul immediately ends the player's inning,
	 *  even if the current shot has not yet ended.
	 */
	public void foul() {
		
		if (inningPlayer == lagwinner) {
			ballColor = notlagwinnerball;
			inningCount += 1;
			

		}
		else if (inningPlayer == notlagwinner) {
			ballColor = lagwinnerball;
			inningCount += 1;		
			
		
		}
		
		if (gameOver == true) {
			inningCount = 1;
		}
		
		inningStarted = false;
		
	}
	
	
	/**
	 * Gets the cue ball of the current player.
	 * @return the ball color
	 */
	public BallType getCueBall() {
		return ballColor;
	}
	
	
	/**
	 * Gets the inning number
	 * @return the inning
	 */
	public int getInning() {
		return inningCount;
	}
	
	/**
	 * Gets the current player.
	 * @return the player
	 */
	public PlayerPosition getInningPlayer() {
		
		if ((selfbreak == true) && (inningCount == 1)){
			inningPlayer = lagwinner;
		}		
			
		else if (selfbreak == false && inningCount == 1) {
			inningPlayer = notlagwinner;
		}

		else if (inningCount % 2 == 0) {
			inningPlayer = notlagwinner;
		}
		
		else {
			inningPlayer = lagwinner;

		}

		return inningPlayer;

	}
	
	
	/**
	 * Gets the number of points scored by Player A.
	 * @return returns player A points
	 */
	public int	getPlayerAScore() {		
		// Finds who is and isn't the lag winner
		if (lagwinner == PLAYER_A) {
			notlagwinner = PLAYER_B;
			playerAscore = lagwinnerscore;
			playerBscore = notlagwinnerscore;			
			} 
			
		return playerAscore;
	}
	
	
	/**
	 * Gets the number of points scored by Player B.
	 * @return points scored by player B
	 */
	public int	getPlayerBScore() {
		// Finds who is and isn't the lag winner
		if (lagwinner == PLAYER_A) {
			notlagwinner = PLAYER_B;
			playerAscore = lagwinnerscore;
			playerBscore = notlagwinnerscore;			
			} 
		
		else { 
			notlagwinner = PLAYER_A;
			lagwinner = PLAYER_B;
			playerAscore = notlagwinnerscore;
			playerBscore = lagwinnerscore;
			}
		return playerBscore;
	}
	
	
	/**
	 * Returns true if and only if the most recently completed shot was a bank shot.
	 * @return is a bankshot
	 */
	public boolean	isBankShot() {
		return bankShot;
	}
	
	
	/**
	 * Returns true if and only if this is the break shot (i.e., the first shot of the game).
	 * @return is a break shot
	 */
	public boolean	isBreakShot() {
		if (inningCount != 1) {
			breakShot = false;
		}
		return breakShot;
	}
	
	
	
	/**
	 * 	Returns true if the game is over 
	 * (i.e., one of the players has reached the designated number of points to win).
	 * @return
	 */
	public boolean isGameOver() {
		return gameOver;	
	}
	
	
	
	/**
	 * Returns true if the shooting player has taken their first shot of the inning.
	 * @return
	 */
	public boolean	isInningStarted() {
		return inningStarted;
	}	
	
	
	
	/**
	 * Returns true if a shot has been taken (see cueStickStrike()), but not ended (see endShot()).
	 * @return
	 */
	public boolean	isShotStarted() {
		return shotStarted;
	}
	
	
	/**
	 *  Sets whether the player that won the lag chooses to break (take first shot),
	 *  or chooses the other player to break.
	 * @param selfBreak
	 * @param cueBall
	 */
	public void lagWinnerChooses(boolean selfBreak, BallType cueBall) {
		
		selfbreak = selfBreak;
		lagwinnerball = cueBall;
		
	
		// Finds the color of the ball and what player is set too
		if (lagwinnerball == YELLOW) {
			notlagwinnerball = WHITE;

		}
		else if (lagwinnerball == WHITE) {
			notlagwinnerball = YELLOW;

		}
		
	// for setting the inning player 
		if (selfbreak == true) {
			inningPlayer = lagwinner;
			ballColor = lagwinnerball;
			breakShot = true;
			}
	
	
		else if (selfbreak == false) {
			inningPlayer = notlagwinner;
			ballColor = notlagwinnerball;
			breakShot = true;
	}
	}
}
