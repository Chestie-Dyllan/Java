package org.example;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
public class FootballLeague {
    static HashMap<String, Integer> scoreBoard = new HashMap<>();
    public static void main(String[] args) {


        BufferedReader in = getReader();
        String works = checkMatches(in, scoreBoard);
        while (works != null) {
                works = checkMatches(in, scoreBoard);
        }

        System.out.println("\nLeague Results.\n");

       // System.out.println("\n" + scoreBoard.toString());

        scoreBoard.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .forEach(e -> System.out.println(e + " " + scoreBoard.get(e)));
    }

    private static BufferedReader getReader() //→19
    {
        BufferedReader in = null;
        try {
            File file = new File("Soccer.txt");
            in = new BufferedReader(
                    new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println(
                    "The file doesn't exist.");
            System.exit(0);
        }
        return in;
    }

    private static String checkMatches(BufferedReader in, HashMap<String, Integer> scoreBoard) {
        String line;
        String team1;
        String team2;
        String[] match , team1score1 , team2score2;
        int score1 , score2;



        try {
            line = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (line == null || line.equals(""))
            return null;
        else {
            match = line.split(",");
            team1score1 = match[0].split(" ");
            team2score2  = match[1].trim().split(" ");
            team1 = team1score1[0];
            score1 = Integer.parseInt(team1score1[1]);
            team2 = team2score2[0];
            score2 = Integer.parseInt(team2score2[1]);


            if (score1 > score2) {
                System.out.println(team1 + " beats " + team2 + " " + score1 + ":" + score2);
                if (scoreBoard.containsKey(team1)) {
                    scoreBoard.put(team1, scoreBoard.get(team1) + 3);
                } else {
                    scoreBoard.put(team1, 3);
                }

                if (!scoreBoard.containsKey(team2)) {
                    scoreBoard.put(team2, 0);
                }
            } else if (score1 < score2) {
                System.out.println(team2 + " beats " + team1 + " " + score2 + ":" + score1);
                if (scoreBoard.containsKey(team2)) {
                    scoreBoard.put(team2, scoreBoard.get(team2) + 3);
                } else {
                    scoreBoard.put(team2, 3);
                }

                if (!scoreBoard.containsKey(team1)) {
                    scoreBoard.put(team1, 0);
                }
            } else {
                System.out.println("It's a draw between " + team1 + " and " + team2 + " " + score1 + ":" + score2);
                if (scoreBoard.containsKey(team1)) {
                    scoreBoard.put(team1, scoreBoard.get(team1) + 1);
                } else {
                    scoreBoard.put(team1, 1);
                }

                if (scoreBoard.containsKey(team2)) {
                    scoreBoard.put(team2, scoreBoard.get(team2) + 1);
                } else {
                    scoreBoard.put(team2, 1);
                }
            }
            return "Successful ReadLine";
        }
    }

}
