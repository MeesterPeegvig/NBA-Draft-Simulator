import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class NBA_Draft_Simulator {
    private static ArrayList<Integer> allTickets;
    private static ArrayList<Integer> empty;
    private static ArrayList<ArrayList<Integer>> teamsTickets;
    private static String[] worstTeams;
    private static String[] AworstTeams;
    private static ArrayList<String> lotteryLosers;

    public static void main(String args[]) throws IOException {
        worstTeams = new String[]{"Houston Rockets", "Orlando Magic", "Detroit Pistons", "Oklahoma City Thunder", "Indiana Pacers", "Portland Trail Blazers", "Sacramento Kings", "Los Angeles Lakers", "San Antonio Spurs", "Washington Wizards", "New York Knicks", "New Orleans Pelicans", "LA Clippers", "Charlotte Hornets"};
        Scanner file = new Scanner(new File("input.txt"));
        int lines = 0;
        while(file.hasNextLine()) {
            worstTeams[lines] = file.nextLine();
            lines++;
        }
        allTickets = new ArrayList<Integer>();
        empty = new ArrayList();
        teamsTickets = new ArrayList<ArrayList<Integer>>();
        AworstTeams = Arrays.copyOf(worstTeams,worstTeams.length);
        lotteryLosers = new ArrayList<String>();
        printAllWinners();
    }
    public static void assignTickets() {
        for(int i = 1; i<=1000;i++){
            allTickets.add(i);
        }

        for(int j = 0; j<=13;j++) {
            teamsTickets.add(new ArrayList<Integer>());
        }
        while (allTickets.size() > 0){
            int whichTicketIndex = (int) (Math.random()*allTickets.size());
            int whichTeamIndex =  (int) (Math.random()*teamsTickets.size());
            if ( !((whichTeamIndex == 0 && teamsTickets.get(whichTeamIndex).size() > 139) || (whichTeamIndex == 1 && teamsTickets.get(whichTeamIndex).size() > 139) || (whichTeamIndex == 2 && teamsTickets.get(whichTeamIndex).size() > 139) || (whichTeamIndex == 3 && teamsTickets.get(whichTeamIndex).size() > 124) || (whichTeamIndex == 4 && teamsTickets.get(whichTeamIndex).size() > 104)||(whichTeamIndex == 5 && teamsTickets.get(whichTeamIndex).size() > 89)|| (whichTeamIndex == 6 && teamsTickets.get(whichTeamIndex).size() > 74)||(whichTeamIndex == 7 && teamsTickets.get(whichTeamIndex).size() > 59)||(whichTeamIndex == 8 && teamsTickets.get(whichTeamIndex).size() > 44)||(whichTeamIndex == 9 && teamsTickets.get(whichTeamIndex).size() > 29)||(whichTeamIndex == 10 && teamsTickets.get(whichTeamIndex).size() > 19)||(whichTeamIndex == 11 && teamsTickets.get(whichTeamIndex).size() > 14)||(whichTeamIndex == 12 && teamsTickets.get(whichTeamIndex).size() > 9)||(whichTeamIndex == 13 && teamsTickets.get(whichTeamIndex).size() > 4))) {
                teamsTickets.get(whichTeamIndex).add(allTickets.get(whichTicketIndex));
                allTickets.remove(whichTicketIndex);
            }
        }
    }
    public static String findLotteryWinners(){


        int indexOfWinner = -1;
        boolean winnerFound = false;
        while (!winnerFound) {
            int winner = (int) (Math.random() * 1000 + 1);
            for (int i = 0; i < teamsTickets.size(); i++) {
                for (int j = 0; j < teamsTickets.get(i).size(); j++) {
                    if (teamsTickets.get(i).get(j) == winner) {
                        indexOfWinner = i;
                        winnerFound = true;
                        break;
                    }
                }
            }
        }
        switch(indexOfWinner){
            case 0: teamsTickets.set(0,empty); worstTeams[0] = " "; return AworstTeams[0];
            case 1: teamsTickets.set(1,empty); worstTeams[1] = " "; return AworstTeams[1];
            case 2: teamsTickets.set(2,empty); worstTeams[2] = " "; return AworstTeams[2];
            case 3: teamsTickets.set(3,empty); worstTeams[3] = " "; return AworstTeams[3];
            case 4: teamsTickets.set(4,empty); worstTeams[4] = " "; return AworstTeams[4];
            case 5: teamsTickets.set(5,empty); worstTeams[5] = " "; return AworstTeams[5];
            case 6: teamsTickets.set(6,empty); worstTeams[6] = " "; return AworstTeams[6];
            case 7: teamsTickets.set(7,empty); worstTeams[7] = " "; return AworstTeams[7];
            case 8: teamsTickets.set(8,empty); worstTeams[8] = " "; return AworstTeams[8];
            case 9: teamsTickets.set(9,empty); worstTeams[9] = " "; return AworstTeams[9];
            case 10: teamsTickets.set(10,empty); worstTeams[10] = " "; return AworstTeams[10];
            case 11: teamsTickets.set(11,empty); worstTeams[11] = " "; return AworstTeams[11];
            case 12: teamsTickets.set(12,empty); worstTeams[12] = " "; return AworstTeams[12];
            case 13: teamsTickets.set(13,empty); worstTeams[13] = " "; return AworstTeams[13];
            default: return indexOfWinner + "";
        }
    }
    public static void findOtherWinners(){
        for(int i=0;i< worstTeams.length;i++){
            if(worstTeams[i] != " " ){
                lotteryLosers.add(worstTeams[i]);
            }
        }
    }
    public static void printAllWinners(){
        assignTickets();
        System.out.println("Welcome to the lottery in the NBA 2022 Draft" + "\n");
        System.out.println("Pick number 1 goes to the " + findLotteryWinners());
        System.out.println("Pick number 2 goes to the " + findLotteryWinners());
        System.out.println("Pick number 3 goes to the " + findLotteryWinners());
        System.out.println("Pick number 4 goes to the " + findLotteryWinners());
        findOtherWinners();
        System.out.println("Pick number 5 goes to the " + lotteryLosers.get(0));
        System.out.println("Pick number 6 goes to the " + lotteryLosers.get(1));
        System.out.println("Pick number 7 goes to the " + lotteryLosers.get(2));
        System.out.println("Pick number 8 goes to the " + lotteryLosers.get(3));
        System.out.println("Pick number 9 goes to the " + lotteryLosers.get(4));
        System.out.println("Pick number 10 goes to the " + lotteryLosers.get(5));
        System.out.println("Pick number 11 goes to the " + lotteryLosers.get(6));
        System.out.println("Pick number 12 goes to the " + lotteryLosers.get(7));
        System.out.println("Pick number 13 goes to the " + lotteryLosers.get(8));
        System.out.println("Pick number 14 goes to the " + lotteryLosers.get(9));
        System.out.println( "\n" + "This concludes the lottery portion of the NBA 2022 draft");
    }
}
