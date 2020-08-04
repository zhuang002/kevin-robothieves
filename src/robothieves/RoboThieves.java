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
        int row = sc.nextInt();
        int column = sc.nextInt();
        sc.nextLine();
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
                    result[i][j] = -1;
                }
            }
        }
    }

    private static void preprocessCamera() {
        for (int[] position : cameras) {
            for (int i = position[1] - 1; i >= 0; i--) {
                if (map[position[0]][i] == '.') {
                    result[position[0]][i] = -2;
                } else if (map[position[0]][i] == 'W') {
                    break;
                }
            }
            for (int i = position[1] + 1; i < map[0].length; i++) {
                if (map[position[0]][i] == '.') {
                    result[position[0]][i] = -2;
                } else if (map[position[0]][i] == 'W') {
                    break;
                }
            }
            for (int i = position[0] - 1; i >= 0; i--) {
                if (map[i][position[1]] == '.') {
                    result[i][position[1]] = -2;
                } else if (map[i][position[1]] == 'W') {
                    break;
                }
            }
            for (int i = position[0] + 1; i < map.length; i++) {
                if (map[i][position[1]] == '.') {
                    result[i][position[1]] = -2;
                } else if (map[i][position[1]] == 'W') {
                    break;
                }
            }
        }
    }

    private static void process() {
        int step = 1;
        ArrayList<int[]> currentPositions = new ArrayList();
        ArrayList<int[]> nextPositions = new ArrayList();
        currentPositions.add(start);
        while (!currentPositions.isEmpty()) {
            for (int[] position : currentPositions) {
                int[] pos = move(position, 0, -1); //move left
                if (pos != null) {
                    result[pos[0]][pos[1]] = step;
                    nextPositions.add(pos);
                }
                pos = move(position, 0, 1); //move right
                if (pos != null) {
                    result[pos[0]][pos[1]] = step;
                    nextPositions.add(pos);
                }
                pos = move(position, -1, 0); //move up
                if (pos != null) {
                    result[pos[0]][pos[1]] = step;
                    nextPositions.add(pos);
                }

                pos = move(position, 1, 0); //move down;
                if (pos != null) {
                    result[pos[0]][pos[1]] = step;
                    nextPositions.add(pos);
                }
            }
            step++;
            currentPositions = nextPositions;
            nextPositions = new ArrayList();
        }
    }

    private static void printResult() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[1].length; j++) {
                if (map[i][j] == '.') {
                    System.out.println(result[i][j]);
                }
            }
        }
    }

    private static int[] move(int[] position, int i, int j) {
        if (position[0] + i >= map.length) {
            return null;
        }
        if (position[1] + j >= map[1].length) {
            return null;
        }
        if (result[position[0] + i][position[1] + j] >= 0) {
            return null;
        }
        if (result[position[0] + i][position[1] + j] == -2) {
            return null;
        }
        if (map[position[0] + i][position[1] + j] == 'C' || map[position[0] + i][position[1] + j] == 'W') {
            return null;
        } else if (map[position[0] + i][position[1] + j] == '.') {
            int[] current = new int[2];
            current[0] = position[0] + i;
            current[1] = position[1] + j;
            return current;
        } else if (map[position[0] + i][position[1] + j] == 'U') {
            return move(position, i - 1, j);
        } else if (map[position[0] + i][position[1] + j] == 'W') {
            return move(position, i + 1, j);
        } else if (map[position[0] + i][position[1] + j] == 'L') {
            return move(position, i, j - 1);
        } else if (map[position[0] + i][position[1] + j] == 'R') {
            return move(position, i, j + 1);
        }
        return null;
    }
}
