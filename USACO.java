import java.util.*;
import java.io.*;

public class USACO {
  public static int bronze(String filename) throws FileNotFoundException{
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
    System.out.println(R+" "+C+" "+E+" "+N);
    //fill in the array
    int[][] squares = new int[R][C];
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (s.hasNext()) squares[i][j] = Integer.parseInt(s.next());
      }
    }
    //print array
    for (int i = 0; i < squares.length; i++) {
      for (int j = 0; j < squares[i].length; j++) {
        System.out.print(squares[i][j] + " ");
      }
      System.out.print("\n");
    }
    //start digging, one instruction at a time
    for (int n = 0; n < N; n++) {
      int R_s = 0;
      int C_s = 0;
      int D_s = 0;
      if (s.hasNext()) {
        R_s = Integer.parseInt(s.next());
      }
      if (s.hasNext()) {
        C_s = Integer.parseInt(s.next());
      }
      if (s.hasNext()) {
        D_s = Integer.parseInt(s.next());
      }
      System.out.println(R_s+" "+C_s+" "+D_s);
    }

    return -1; //so it compiles
  }

  public static int silver(String filename) {
    return -1; //so it compiles
  }

  public static void main(String[] args) {
    try {
      bronze("makelake.1.in");
    }catch(FileNotFoundException e) {
      System.out.println("invalid filename");
    }
  }
}
