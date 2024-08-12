package xyz.devmeko.ConwayLifeGame;

import java.util.Arrays;
import java.util.Random;

public class Main {

    static Integer[][] array = new Integer[32][32];

    public static void main(String[] args) {
        new Thread(() -> {
            for (int c = 0; c < 200; c++) {
                for (int i = 0; i < 1024; i++) {
                    array[Math.floorDiv(i, 32)][i % 32] = (new Random().nextFloat() > 0.9 ? 1 : 0);
                }

                for (int k = 0; k < 150; k++) {
                    StringBuilder output = new StringBuilder();

                    output.append(String.format("\033[2J"));
                    output.append('\r');
                    Integer[][] curStateArray = array.clone();
                    Integer[][] nextArray = new Integer[32][32];

                    output.append("Run " + (c + 1) +  ", Generation " + (k + 1) + "th\n");
                    for (int i = 0; i < 32; i++) {
                        for (int j = 0; j < 32; j++) {
                            if (array[i][j] == 1) output.append("⬛");
                            else if (array[i][j] == 0) output.append("⬜");
                        }
                        output.append('\n');
                    }

                    System.out.print(output);

                    for (int i = 0; i < 1024; i++) {
                        Integer curAlive = 0;

                        if (Math.floorDiv(i, 32) == 0 && i % 32 == 0) {
                            curAlive += curStateArray[Math.floorDiv(i, 32)][i % 32 + 1] +
                                    curStateArray[Math.floorDiv(i, 32) + 1][i % 32 + 1] +
                                    curStateArray[Math.floorDiv(i, 32) + 1][i % 32];
                        } else if (Math.floorDiv(i, 32) == 0 && i % 32 == 31) {
                            curAlive += curStateArray[Math.floorDiv(i, 32) + 1][i % 32] +
                                    curStateArray[Math.floorDiv(i, 32) + 1][i % 32 - 1] +
                                    curStateArray[Math.floorDiv(i, 32)][i % 32 - 1];
                        } else if (Math.floorDiv(i, 32) == 31 && i % 32 == 0) {
                            curAlive += curStateArray[Math.floorDiv(i, 32) - 1][i % 32] +
                                    curStateArray[Math.floorDiv(i, 32) - 1][i % 32 + 1] +
                                    curStateArray[Math.floorDiv(i, 32)][i % 32 + 1];
                        } else if (Math.floorDiv(i, 32) == 31 && i % 32 == 31) {
                            curAlive += curStateArray[Math.floorDiv(i, 32) - 1][i % 32 - 1] +
                                    curStateArray[Math.floorDiv(i, 32) - 1][i % 32] +
                                    curStateArray[Math.floorDiv(i, 32)][i % 32 - 1];
                        } else if (Math.floorDiv(i, 32) == 0 && i % 32 != 0 && i % 32 != 31) {
                            curAlive += curStateArray[Math.floorDiv(i, 32)][i % 32 + 1] +
                                    curStateArray[Math.floorDiv(i, 32) + 1][i % 32 + 1] +
                                    curStateArray[Math.floorDiv(i, 32) + 1][i % 32] +
                                    curStateArray[Math.floorDiv(i, 32) + 1][i % 32 - 1] +
                                    curStateArray[Math.floorDiv(i, 32)][i % 32 - 1];
                        } else if (Math.floorDiv(i, 32) == 31 && i % 32 != 0 && i % 32 != 31) {
                            curAlive += curStateArray[Math.floorDiv(i, 32) - 1][i % 32 - 1] +
                                    curStateArray[Math.floorDiv(i, 32) - 1][i % 32] +
                                    curStateArray[Math.floorDiv(i, 32) - 1][i % 32 + 1] +
                                    curStateArray[Math.floorDiv(i, 32)][i % 32 + 1] +
                                    curStateArray[Math.floorDiv(i, 32)][i % 32 - 1];
                        } else if (Math.floorDiv(i, 32) != 0 && Math.floorDiv(i, 32) != 31 && i % 32 == 0) {
                            curAlive += curStateArray[Math.floorDiv(i, 32) - 1][i % 32] +
                                    curStateArray[Math.floorDiv(i, 32) - 1][i % 32 + 1] +
                                    curStateArray[Math.floorDiv(i, 32)][i % 32 + 1] +
                                    curStateArray[Math.floorDiv(i, 32) + 1][i % 32 + 1] +
                                    curStateArray[Math.floorDiv(i, 32) + 1][i % 32];
                        } else if (Math.floorDiv(i, 32) != 0 && Math.floorDiv(i, 32) != 31 && i % 32 == 31) {
                            curAlive += curStateArray[Math.floorDiv(i, 32) - 1][i % 32 - 1] +
                                    curStateArray[Math.floorDiv(i, 32) - 1][i % 32] +
                                    curStateArray[Math.floorDiv(i, 32) + 1][i % 32] +
                                    curStateArray[Math.floorDiv(i, 32) + 1][i % 32 - 1] +
                                    curStateArray[Math.floorDiv(i, 32)][i % 32 - 1];
                        } else {
                            curAlive += curStateArray[Math.floorDiv(i, 32) - 1][i % 32 - 1] +
                                    curStateArray[Math.floorDiv(i, 32) - 1][i % 32] +
                                    curStateArray[Math.floorDiv(i, 32) - 1][i % 32 + 1] +
                                    curStateArray[Math.floorDiv(i, 32)][i % 32 + 1] +
                                    curStateArray[Math.floorDiv(i, 32) + 1][i % 32 + 1] +
                                    curStateArray[Math.floorDiv(i, 32) + 1][i % 32] +
                                    curStateArray[Math.floorDiv(i, 32) + 1][i % 32 - 1] +
                                    curStateArray[Math.floorDiv(i, 32)][i % 32 - 1];
                        }

                        if (((curAlive == 2 || curAlive == 3) && array[Math.floorDiv(i, 32)][i % 32] == 1) || (curAlive == 3 && array[Math.floorDiv(i, 32)][i % 32] == 0))
                            nextArray[Math.floorDiv(i, 32)][i % 32] = 1;
                        else nextArray[Math.floorDiv(i, 32)][i % 32] = 0;
                    }

                    array = nextArray.clone();

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}
