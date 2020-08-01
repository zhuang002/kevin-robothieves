/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robothieves;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author zhuan
 */
public class RoboThieves {

    /**
     * @param args the command line arguments
     */
    static char[][] map = null;
    static int[] start = new int[2];
    static int[][] result = null;
    static ArrayList<int[]> cameras = new ArrayList();

    public static void main(String[] args) {
        // TODO code application logic here
        getInput();
        preprocessCamera();
        process();
        printResult();
    }

    private static void getInput() {
        Scanner sc = new Scanner(System.in);
        int column = sc.nextInt();
        int row = sc.nextInt();
        map = new char[row][column];
        result = new int[row][column];
        for (int i = 0; i < row; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < column; j++) {
                char c = s.charAt(j);
                map[i][j] = c;
                if (map[i][j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                    result[i][j] = 0;
                } else if (c == 'C') {
                    result[i][j] = -2;
                    int[] position = new int[2];
                    position[0] = i;
                    position[1] = j;
                    cameras.add(position);
                } else if (c == 'W') {
                    result[i][j] = -2;
                } else if (c == '.') {
                    result[i][j] = -1;
                } else {
                    result[i][j] = -2;
                }
            }
        }
    }

    private static void preprocessCamera() {
        for (int[] position : cameras) {
            for (int i = position[1] - 1; i >= 0; i--) {
                if (map[position[0]][i] == '.') {
                    result[position[0]][i] = -1;
                } else if (map[position[0]][i] == 'W') {
                    break;
                }
            }
            for (int i = position[1] + 1; i < map[0].length; i++) {
                if (map[position[0]][i] == '.') {
                    result[position[0]][i] = -1;
                } else if (map[position[0]][i] == 'W') {
                    break;
                }
            }
            for (int i = position[0] - 1; i >= 0; i--) {
                if (map[i][position[1]] == '.') {
                    result[i][position[1]] = -1;
                } else if (map[i][position[1]] == 'W') {
                    break;
                }
            }
            for (int i = position[0] + 1; i < map.length; i++) {
                if (map[i][position[1]] == '.') {
                    result[i][position[1]] = -1;
                } else if (map[i][position[1]] == 'W') {
                    break;
                }
            }
        }
    }

    private static void process() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void printResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
