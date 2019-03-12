import java.util.*;
import java.io.*;

public class USACO {
  public static int bronze(String filename) throws FileNotFoundException{
  	String printing = "";

    int R = 0;
    int C = 0;
    int E = 0;
    int N = 0;
    File f = new File(filename);
    Scanner s = new Scanner(f);
    //set the first 4 numbers
    if (s.hasNext()) R = Integer.parseInt(s.next());
    if (s.hasNext()) C = Integer.parseInt(s.next());
    if (s.hasNext()) E = Integer.parseInt(s.next());
    if (s.hasNext()) N = Integer.parseInt(s.next());
    printing += "PRESETS: \n======\n" + R + " " + C + " " + E + " " +N;

    //fill in the array
    int[][] squares = new int[R][C];
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (s.hasNext()) squares[i][j] = Integer.parseInt(s.next());
      }
    }
    printing += "\n\nBOARD:\n======\n";

    //print heights
    for (int i = 0; i < squares.length; i++) {
      for (int j = 0; j < squares[i].length; j++) {
        printing += squares[i][j] + " ";
      }
      printing += "\n";
    }
    printing += "\n\nINSTRUCTIONS:\n======\n";

    //start digging, one instruction at a time
    for (int n = 0; n < N; n++) {
      int R_s = 0;
      int C_s = 0;
      int D_s = 0;

      //set starting position and initial amount of digging PER instruction
      if (s.hasNext()) {
        R_s = Integer.parseInt(s.next());
      }
      if (s.hasNext()) {
        C_s = Integer.parseInt(s.next());
      }
      if (s.hasNext()) {
        D_s = Integer.parseInt(s.next());
      }

      printing += R_s+" "+C_s+" "+D_s;

      //find biggest number
      int max = squares[R_s][C_s];
      for (int r = -1; r < 2; r++) {
      	for (int c = -1; c < 2; c++) {
      		if (squares[R_s+r][C_s+c] > max) max = squares[R_s+r][C_s+c];
      	}
      }
      //find number other spaces should stop digging at
      int dig = max - D_s;
      //System.out.println(max);
      //System.out.println(dig);

      //change the 9 numbers selected
      for (int r = -1; r < 2; r++) {
      	for (int c = -1; c < 2; c++) {
      		//start with original value, end up as new number
      		int num = squares[R_s+r][C_s+c];
      		while (num > dig) { //make it smaller until it reaches the limit
      			num--;
      		}
      		squares[R_s+r][C_s+c] = num; //put it back in
      	}
      }
    }

    printing += "\n\nNEW BOARD:\n======\n";

    //find volume
    int aDepth = 0;
    int volume = 0;

    int[][] depths = new int[R][C];
    for (int i = 0; i < R; i++) {
    	for (int j = 0; j < C; j++) {
    		depths[i][j] = E - squares[i][j];
     	}
    }

    //print heights
    for (int i = 0; i < squares.length; i++) {
      for (int j = 0; j < squares[i].length; j++) {
        printing += squares[i][j] + " ";
      }
      printing += "\n";
    }

    //add depths (and print)
    printing += "\n\nDEPTHS:\n======\n";
    for (int i = 0; i < depths.length; i++) {
      for (int j = 0; j < depths[i].length; j++) {
        if (depths[i][j] > 0) {
        	aDepth += depths[i][j];
        	printing += depths[i][j] + " ";
        }
        else printing += "- ";
      }
      printing += "\n";
    }

    printing += "\n\nAGGREGATED DEPTH:\n======\n";
    printing += aDepth;

    volume = aDepth * 72 * 72; //volume = depth * 6 feet * 6 feet

    printing += "\n\nVOLUME:\n======\n";
    printing += volume + "\n";

    //SINGLE PRINT STATION TO SHOW ALL STEPS
    //System.out.println(printing);

    return volume;
    //return -1; //so it compiles
  }

  public static int silver(String filename) throws FileNotFoundException{
    String printing = "";

    int N = 0;
    int M = 0;
    int T = 0;
    File f = new File(filename);
    Scanner s = new Scanner(f);
    if (s.hasNext()) N = Integer.parseInt(s.next());
    if (s.hasNext()) M = Integer.parseInt(s.next());
    if (s.hasNext()) T = Integer.parseInt(s.next());
    printing += "PRESETS: \n======\n" + N + " " + M + " " + T;

    //filling new array with ints to represent the pasture and trees given in
    //the file
    int[][] pasture = new int[N][M];
    for (int i = 0; i < N; i++) {
      if (s.hasNext()) {
        String x = s.next();
        for (int j = 0; j < M; j++) {
          if (x.charAt(j) == '.') pasture[i][j] = 0;
          if (x.charAt(j) == '*') pasture[i][j] = -1;
        }
      }
    }

    //print pasture
    printing += "\n\nBOARD:\n======\n";
    for (int i = 0; i < pasture.length; i++) {
      for (int j = 0; j < pasture[i].length; j++) {
        printing += pasture[i][j] + " ";
      }
      printing += "\n";
    }

    int R1 = 0;
    int C1 = 0;
    int R2 = 0;
    int C2 = 0;
    if (s.hasNext()) R1 = Integer.parseInt(s.next());
    if (s.hasNext()) C1 = Integer.parseInt(s.next());
    if (s.hasNext()) R2 = Integer.parseInt(s.next());
    if (s.hasNext()) C2 = Integer.parseInt(s.next());
    printing += "\nPRESETS: \n======\n" + R1 + " " + C1 + " " + R2 + " " +C2;

    //new array with number of moves
    int[][]moves = new int[N][M];
    //start with time = 0, starting place gets one
    pasture[R1-1][C1-1] = 1;
    //repeat for as many steps are given
    for (int t = 0; t < T; t++) {
    	//for each space in the array fill in all the numbers around it that add up
    	for (int i = 0; i < pasture.length; i++) {
    		for (int j = 0; j < pasture[0].length; j++) {
    			//only works if there is no tree
    			if (pasture[i][j] >= 0) {
    				//for each of the 4 directions, if it is a valid space on the board then it will add the number from there to the original space
    				if (i - 1 >= 0 && pasture[i-1][j] > 0) {
    					moves[i][j] += pasture[i-1][j];
    				}
    				if (i + 1 < pasture.length && pasture[i+1][j] > 0) {
    					moves[i][j] += pasture[i+1][j];
    				}
    				if (j - 1 >= 0 && pasture[i][j-1] > 0) {
    					moves[i][j] += pasture[i][j-1];
    				}
    				if (j + 1 < pasture[0].length && pasture[i][j+1] > 0) {
    					moves[i][j] += pasture[i][j+1];
    				}
    			}
    			//if there's a tree
    			else {
    				moves[i][j] = -1;
    			}
    		}
    	}
    	//need to put the current moves for this "second" on the real board, reset moves board for the next second
    	pasture = moves;
    	moves = new int[N][M];
    }

    //print pasture
    printing += "\n\nBOARD AFTER:\n======\n";
    for (int i = 0; i < pasture.length; i++) {
      for (int j = 0; j < pasture[i].length; j++) {
        printing += pasture[i][j] + " ";
      }
      printing += "\n";
    }

    //System.out.println(printing+"\n");
    return pasture[R2-1][C2-1]; //so it compiles
  }

  public static void main(String[] args) {
    try {
      //System.out.println(bronze("makelake.1.in"));
      System.out.println(silver("ctravel.1.in"));
    }catch(FileNotFoundException e) {
      System.out.println("invalid filename");
    }
  }
}
