import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
// write burnt in file, ORDER DOESNT MATTER even when checking who won
public class NBA_Draft_Simulator {
    private static ArrayList<String> allTickets;
    private static ArrayList<String> empty;
    private static ArrayList<ArrayList<String>> teamsTickets;
    private static String[] worstTeams;
    private static String[] AworstTeams;
    private static ArrayList<String> lotteryLosers;
    private static double secondsToSleep;
    private static double secondsToSleep2;
    private static boolean goFast;
    private static String isFast;
    public static void main(String args[]) throws IOException {
        boolean goAgain = false;
        do{
            worstTeams = new String[]{"Houston Rockets", "Orlando Magic", "Detroit Pistons", "Oklahoma City Thunder", "Indiana Pacers", "Portland Trail Blazers", "Sacramento Kings", "Los Angeles Lakers", "San Antonio Spurs", "Washington Wizards", "New York Knicks", "New Orleans Pelicans", "LA Clippers", "Charlotte Hornets"};
            Scanner file = new Scanner(new File("input.txt"));
            int lines = 0;
            while (file.hasNextLine()) {
                worstTeams[lines] = file.nextLine();
                lines++;
            }
            allTickets = new ArrayList<>();
            empty = new ArrayList<>();
            teamsTickets = new ArrayList<ArrayList<String>>();
            AworstTeams = Arrays.copyOf(worstTeams, worstTeams.length);
            lotteryLosers = new ArrayList<>();
            secondsToSleep = 1;
            secondsToSleep2 = 500;
            goFast = false;
            isFast = "";
            printAllWinners();
            Scanner keyboard = new Scanner(System.in);
            System.out.println("\n\nTo see each teams tickets look at the teamsTickets.txt file");
            System.out.print("To run a new simulation type \"sim\" :: ");
            if (keyboard.nextLine().equals("sim")) {
                goAgain = true;
            }
            else{
                System.out.println("Thank you for trying the NBA 2022 Draft Simulator!");
            }
        } while (goAgain);

    }
    public static String[] swap(String a, String b) {
        return new String[]{b,a};
    }
    public static void assignTickets() {
        for(int i = 1; i<=14;i++){
            for(int j = 1; j<=14;j++){
                for(int k = 1; k<=14;k++) {
                    for (int l = 1; l <= 14; l++) {
                        if ((i > j && i > k && i > l && j > k && j > l && k > l)){
                            allTickets.add(i + " " + j + " " + k + " " + l);
                        }
                    }
                }
            }
        }
// change order of each number here
        Random rand = new Random();
        for (int i = 0; i<allTickets.size();i++){
            String newTicket = "";
            String[] ticketInArray = new String[]{};
            ticketInArray = allTickets.get(i).split(" ");
            for (int j = 0; j < ticketInArray.length; j++) {
                int index = rand.nextInt(ticketInArray.length - j);
                String tmp = ticketInArray[ticketInArray.length - 1 - j];
                ticketInArray[ticketInArray.length - 1 - j] = ticketInArray[index];
                ticketInArray[index] = tmp;
            }
            for(int k = 0; k < ticketInArray.length; k++){
                newTicket += ticketInArray[k] + " ";
            }
            allTickets.set(i, newTicket.strip());

        }
        for(int j = 0; j<=13;j++) {
            teamsTickets.add(new ArrayList<>());
        }
        //System.out.println(allTickets);
        while (allTickets.size() > 1){
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
        Scanner keyboard = new Scanner(System.in);
        while (!winnerFound) {
            String winner = (int) (Math.random() * 13 + 1) + " " + (int) (Math.random() * 13 + 1) + " " + (int) (Math.random() * 13 + 1) + " " + (int) (Math.random() * 13 + 1);
            for (int i = 0; i < teamsTickets.size(); i++) {
                for (int j = 0; j < teamsTickets.get(i).size(); j++) {
                    if (teamsTickets.get(i).get(j).equals(winner)) {
                        indexOfWinner = i;
                        winnerFound = true;
                        int indexOfFirstSpace = winner.indexOf(" ");
                        int indexOfSecondSpace = winner.substring(indexOfFirstSpace+1).indexOf(" ") + indexOfFirstSpace+1;
                        int indexOfThirdSpace = winner.substring(indexOfSecondSpace+1).indexOf(" ") + indexOfSecondSpace+1;
                        int indexOfFourthSpace = winner.substring(indexOfThirdSpace+1).indexOf(" ") + indexOfThirdSpace+1;
                        if (!goFast) isFast = keyboard.nextLine();
                        if (!goFast && isFast.equals("fast")) goFast = true;
                        System.out.println("The first number is " + winner.substring(0,indexOfFirstSpace) + "!");
                        System.out.println("These teams could still win! - " + findPossibleWinners(winner.substring(0,indexOfFirstSpace),1));
                        if (!goFast) isFast = keyboard.nextLine();
                        if (!goFast && isFast.equals("fast")) goFast = true;
                        System.out.println("The second number is " + winner.substring(indexOfFirstSpace+1,indexOfSecondSpace) + "!");
                        System.out.println("These teams could still win! - " + findPossibleWinners(winner.substring(0,indexOfSecondSpace),2));
                        if (!goFast) isFast = keyboard.nextLine();
                        if (!goFast && isFast.equals("fast")) goFast = true;
                        System.out.println("The third number is " + winner.substring(indexOfSecondSpace+1,indexOfThirdSpace) + "!");
                        System.out.println("These teams could still win! - " + findPossibleWinners(winner.substring(0,indexOfThirdSpace),3));
                        if (!goFast) isFast = keyboard.nextLine();
                        if (!goFast && isFast.equals("fast")) goFast = true;
                        System.out.println("The fourth number is " + winner.substring(indexOfFourthSpace+1) + "!");
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
            if(!worstTeams[i].equals(" ")){
                lotteryLosers.add(worstTeams[i]);
            }
        }
    }
    public static void printAllWinners() throws FileNotFoundException {
        assignTickets();
        writeTeamsTicketsFile();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to the lottery in the NBA 2022 Draft" + "\n");
        System.out.println("Type \"fast\" to speed up the simulation");
        if (!goFast) isFast = keyboard.nextLine();
        if (!goFast && isFast.equals("fast")) goFast = true;
        System.out.println("The burnt ticket was " + allTickets.get(0));
        System.out.println("Pick number 1 goes to the " + findLotteryWinners());
        System.out.println("Pick number 2 goes to the " + findLotteryWinners());
        System.out.println("Pick number 3 goes to the " + findLotteryWinners());
        System.out.println("Pick number 4 goes to the " + findLotteryWinners());
        findOtherWinners();
        if (!goFast) isFast = keyboard.nextLine();
        if (!goFast && isFast.equals("fast")) goFast = true;
        System.out.println("Pick number 5 goes to the " + lotteryLosers.get(0));
        if (!goFast) isFast = keyboard.nextLine();
        if (!goFast && isFast.equals("fast")) goFast = true;
        System.out.println("Pick number 6 goes to the " + lotteryLosers.get(1));
        if (!goFast) isFast = keyboard.nextLine();
        if (!goFast && isFast.equals("fast")) goFast = true;
        System.out.println("Pick number 7 goes to the " + lotteryLosers.get(2));
        if (!goFast) isFast = keyboard.nextLine();
        if (!goFast && isFast.equals("fast")) goFast = true;
        System.out.println("Pick number 8 goes to the " + lotteryLosers.get(3));
        if (!goFast) isFast = keyboard.nextLine();
        if (!goFast && isFast.equals("fast")) goFast = true;
        System.out.println("Pick number 9 goes to the " + lotteryLosers.get(4));
        if (!goFast) isFast = keyboard.nextLine();
        if (!goFast && isFast.equals("fast")) goFast = true;
        System.out.println("Pick number 10 goes to the " + lotteryLosers.get(5));
        if (!goFast) isFast = keyboard.nextLine();
        if (!goFast && isFast.equals("fast")) goFast = true;
        System.out.println("Pick number 11 goes to the " + lotteryLosers.get(6));
        if (!goFast) isFast = keyboard.nextLine();
        if (!goFast && isFast.equals("fast")) goFast = true;
        System.out.println("Pick number 12 goes to the " + lotteryLosers.get(7));
        if (!goFast) isFast = keyboard.nextLine();
        if (!goFast && isFast.equals("fast")) goFast = true;
        System.out.println("Pick number 13 goes to the " + lotteryLosers.get(8));
        if (!goFast) isFast = keyboard.nextLine();
        if (!goFast && isFast.equals("fast")) goFast = true;
        System.out.println("Pick number 14 goes to the " + lotteryLosers.get(9));
        if (!goFast) isFast = keyboard.nextLine();
        if (!goFast && isFast.equals("fast")) goFast = true;
    }
    public static ArrayList<String> findPossibleWinners(String ball, int whichTime){
        ArrayList<String> possibleWinners = new ArrayList<>();
        for (int i = 0; i < teamsTickets.size(); i++) {
            for (int j = 0; j < teamsTickets.get(i).size(); j++) {
                String str = teamsTickets.get(i).get(j); // 9 10 4 2
                int spacesFound = 0;
                String oneBall = "";
                String twoBalls = "";
                String threeBalls = "";
                for (int k = str.length()-1; k>0;k--){
                    if (str.substring(k-1,k).equals(" ")){
                        if (spacesFound == 0){
                            threeBalls = str.substring(0,k-1);
                        }
                        if (spacesFound == 1){
                            twoBalls = str.substring(0,k-1);
                        }
                        if (spacesFound == 2){
                            oneBall = str.substring(0,k-1);
                        }
                        spacesFound++;
                    }
                }
                if (whichTime == 1 && oneBall.equals(ball)){
                    possibleWinners.add(worstTeams[i]);
                }
                if (whichTime == 2 && twoBalls.equals(ball)){
                    possibleWinners.add(worstTeams[i]);
                }
                if (whichTime == 3 && threeBalls.equals(ball)){
                    possibleWinners.add(worstTeams[i]);
                }
            }
        }
        Set<String> set = new HashSet<String>();
        for (String team : possibleWinners) {
            set.add(team);
        }
        for (int j = possibleWinners.size()-1; j >= 0; j--) {
            possibleWinners.remove(j);
        }
        possibleWinners.addAll(set);
        return possibleWinners;
    }
    public static void writeTeamsTicketsFile() throws FileNotFoundException{
        String filePath = "C:\\Users\\lahiv\\Github repos\\NBA Draft Simulator\\teamsTickets.txt";
        // Content that will be written in the file
        PrintWriter writer = new PrintWriter(filePath);
        writer.print("");
        writer.close();

        List<String> lines = Arrays.asList(worstTeams[0] + " - " + teamsTickets.get(0),worstTeams[1] + " - "+teamsTickets.get(1),worstTeams[2] + " - "+teamsTickets.get(2), worstTeams[3] + " - "+teamsTickets.get(3), worstTeams[4] + " - "+teamsTickets.get(4), worstTeams[5] + " - "+teamsTickets.get(5), worstTeams[6] + " - "+teamsTickets.get(6), worstTeams[7] + " - "+teamsTickets.get(7), worstTeams[8] + " - "+teamsTickets.get(8), worstTeams[9] + " - "+teamsTickets.get(9), worstTeams[10] + " - "+teamsTickets.get(10), worstTeams[11] + " - "+teamsTickets.get(11), worstTeams[12] + " - "+teamsTickets.get(12), worstTeams[13] + " - "+teamsTickets.get(13));
        PrintWriter printWriter = null;
        try {
            Writer fileWriter = new FileWriter(filePath, true);
            printWriter = new PrintWriter(fileWriter);

            for (String line : lines) {
                printWriter.write(line);
                printWriter.write(System.getProperty("line.separator"));//The two above lines can be replaced with : printWriter.println(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            // Closing the file
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}
