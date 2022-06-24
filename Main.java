import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void letsPlayMonopoly() {
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter the number of players: ");
            String option = scan.nextLine();
            String[] numbers = {"2", "3", "4", "5", "6"};
            boolean checkTheOption = false;
            for (String num : numbers) {
                if (option.equalsIgnoreCase(num)) {
                    checkTheOption = true;
                }
            }
            if (checkTheOption) {
                String[] nicknames = letsPickNicknames(Integer.parseInt(option));
                String[] finalNicknames = letsThrowDicesBeforeTheActualGame(nicknames);
                for (int i = 0; i < finalNicknames.length; i++) {
                    System.out.println(finalNicknames[i]);
                }
                break;
            } else {
                System.out.println("Your input isn't correct!");
            }
        }
    }

    public static String[] letsPickNicknames(int countOfPlayers) {
        Scanner scan = new Scanner(System.in);
        String[] nicknames = new String[countOfPlayers];
        for (int i = 0; i < countOfPlayers; i++) {
            System.out.print("Enter a nickname: ");
            nicknames[i] = scan.nextLine();
        }
        return nicknames;
    }

    public static void throwTheDices(String n) {
        Scanner scan = new Scanner(System.in);
        System.out.print(n + ", throw the dices by typing \"t\": ");
        while (true) {
            String typed = scan.nextLine();
            if (typed.equalsIgnoreCase("t")) {
                break;
            }
        }
    }

    public static String[] letsThrowDicesBeforeTheActualGame(String[] n) {
        Scanner scan = new Scanner(System.in);
        String[] finalNicknames = new String[n.length];
        int[][] sortedScore = new int[2][n.length];//[score][index of nickname]
        int[][] safeIndexesWhichOwnersHadEqualScores = new int[n.length][2];
        int s1 = 0;
        int s2 = 0;
        for (int i = 0; i < n.length; i++) {
            throwTheDices(n[i]);
            int score = rollingTheDices();
            System.out.println(n[i] + " got score " + score);
            sortedScore[0][i] = score;
            sortedScore[1][i] = i;
        }//8,7,5,5,  0,1,2,3
        for (int i = 0; i < sortedScore[0].length; i++) {
            for (int j = 0; j < sortedScore[0].length; j++) {
                if ((i != j) && (sortedScore[0][i] == sortedScore[0][j])) {
                    int br2 = 0;
                    //checkIfWe
                    boolean letsSeeIfWeHaveNotAlreadyCheckedTheseIndexesAndTheirScores = true;
                    for (int k = 0; k < safeIndexesWhichOwnersHadEqualScores.length; k++) {
                        int s3 = safeIndexesWhichOwnersHadEqualScores[k][0];
                        int s4 = safeIndexesWhichOwnersHadEqualScores[k][1];
                        if ((sortedScore[1][i] == s3 && sortedScore[1][j] == s4) || (sortedScore[1][i] == s4 && sortedScore[1][j] == s3)) {
                            letsSeeIfWeHaveNotAlreadyCheckedTheseIndexesAndTheirScores = false;

                        }
                    }

                    while (br2 == 0 && letsSeeIfWeHaveNotAlreadyCheckedTheseIndexesAndTheirScores) {
                        int[] newThrowingScores = {0, 0};
                        throwTheDices(n[i]);
                        newThrowingScores[0] = rollingTheDices();
                        System.out.println(n[i] + " got score " + newThrowingScores[0]);
                        throwTheDices(n[j]);
                        newThrowingScores[1] = rollingTheDices();
                        System.out.println(n[j] + " got score " + newThrowingScores[1]);
                        if (newThrowingScores[0] < newThrowingScores[1]) {
                            sortedScore[1][i] = j;
                            sortedScore[1][j] = i;
                            safeIndexesWhichOwnersHadEqualScores[s1][s2] = i;
                            s2++;
                            safeIndexesWhichOwnersHadEqualScores[s1][s2] = j;
                            s1++;
                            br2 = 1;
                        } else if (newThrowingScores[0] > newThrowingScores[1]) {
                            br2 = 1;
                            safeIndexesWhichOwnersHadEqualScores[s1][s2] = i;
                            s2++;
                            safeIndexesWhichOwnersHadEqualScores[s1][s2] = j;
                            s1++;
                        }
                    }
                    s2=0;
                }
            }
        }
        ArrayList<Integer> inDescendingOrder = new ArrayList<Integer>(n.length);
        for (int i = 0; i < sortedScore[0].length; i++) {
            inDescendingOrder.add(sortedScore[0][i]);
        }
        Collections.sort(inDescendingOrder);
        Collections.reverse(inDescendingOrder);
        int[] a = new int[n.length];
        int b = 0;
        for (int num : inDescendingOrder) {
            a[b] = num;
            b++;
        }
        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n.length; j++) {
                if (a[i] == sortedScore[0][j]) {
                        finalNicknames[i] = n[sortedScore[1][j]];
                        sortedScore[0][j]=0;
                        j=n.length;
                }
            }
        }
        return finalNicknames;
    }

    public static int rollingTheDices()
    {
        int sum=0;
        sum+=Math.floor(Math.random()*(6-1+1)+1);
        sum+=Math.floor(Math.random()*(6-1+1)+1);
        return sum;
    }
    public static int [][] startingWith1500Dollars()
    {
        int [][]theArrayWithValues={{500, 100, 50, 20, 10, 5,1},{2,2,2,6,5,5,5}};
        //2 x $500's, 2 x $100's, 2 x $50's, 6 x $20's, 5 x $10's, 5 x $5's, and 5 x $1's.
        int sum=0;
        for (int i = 0; i < theArrayWithValues[0].length; i++) {
            sum+=theArrayWithValues[0][i]*theArrayWithValues[1][i];
            }
        return theArrayWithValues;
    }
    public static void main(String[] args) {
        letsPlayMonopoly();
        int a=rollingTheDices();
        System.out.println(a);
        int [][] n=startingWith1500Dollars();
    }
}