import java.io.*;
import java.util.*;
// separate logic and prints,
public class NBA_Draft_Simulator {
    private static ArrayList<String> allTickets;
    private static ArrayList<String> empty;
    private static ArrayList<ArrayList<String>> teamsTickets;
    private static String[] worstTeams;
    private static String[] AworstTeams;
    private static ArrayList<String> lotteryLosers;
    private static ArrayList<String> winners;
    private static int[] amountTickets;
    private static double secondsToSleep;
    private static double secondsToSleep2;
    private static boolean goFast;
    private static String isFast;

    public static void main(String args[]) throws IOException {
        boolean goAgain = false;
        do{
            worstTeams = new String[]{"Houston Rockets", "Orlando Magic", "Detroit Pistons", "Oklahoma City Thunder", "Indiana Pacers", "Portland Trail Blazers", "Sacramento Kings", "Los Angeles Lakers", "San Antonio Spurs", "Washington Wizards", "New York Knicks", "New Orleans Pelicans", "LA Clippers", "Charlotte Hornets"};
            amountTickets = new int[]{140,140,140,115,115,90,75,45,45,45,18,17,10,5};
            Scanner file = new Scanner(new File("input.txt"));
            int lines = 0;
            while (file.hasNextLine()) {
                worstTeams[lines] = file.nextLine();
                amountTickets[lines] = Integer.parseInt(file.nextLine());
                lines++;
            }
            allTickets = new ArrayList<>();
            empty = new ArrayList<>();
            teamsTickets = new ArrayList<ArrayList<String>>();
            AworstTeams = Arrays.copyOf(worstTeams, worstTeams.length);
            lotteryLosers = new ArrayList<>();
            winners = new ArrayList<>();
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
            if ( !((whichTeamIndex == 0 && teamsTickets.get(whichTeamIndex).size() > amountTickets[0]-1) || (whichTeamIndex == 1 && teamsTickets.get(whichTeamIndex).size() > amountTickets[1]-1) || (whichTeamIndex == 2 && teamsTickets.get(whichTeamIndex).size() > amountTickets[2]-1) || (whichTeamIndex == 3 && teamsTickets.get(whichTeamIndex).size() > amountTickets[3]-1) || (whichTeamIndex == 4 && teamsTickets.get(whichTeamIndex).size() > amountTickets[4]-1)||(whichTeamIndex == 5 && teamsTickets.get(whichTeamIndex).size() > amountTickets[5]-1)|| (whichTeamIndex == 6 && teamsTickets.get(whichTeamIndex).size() > amountTickets[6]-1)||(whichTeamIndex == 7 && teamsTickets.get(whichTeamIndex).size() > amountTickets[7]-1)||(whichTeamIndex == 8 && teamsTickets.get(whichTeamIndex).size() > amountTickets[8]-1)||(whichTeamIndex == 9 && teamsTickets.get(whichTeamIndex).size() > amountTickets[9]-1)||(whichTeamIndex == 10 && teamsTickets.get(whichTeamIndex).size() > amountTickets[10]-1)||(whichTeamIndex == 11 && teamsTickets.get(whichTeamIndex).size() > amountTickets[11]-1)||(whichTeamIndex == 12 && teamsTickets.get(whichTeamIndex).size() > amountTickets[12]-1)||(whichTeamIndex == 13 && teamsTickets.get(whichTeamIndex).size() > amountTickets[13]-1))) {
                teamsTickets.get(whichTeamIndex).add(allTickets.get(whichTicketIndex));
                allTickets.remove(whichTicketIndex);
            }
        }
    }
    public static int[] quick_sort(int intArray[], int low, int high) {
        if (low < high) {
            //partition the array around pi=>partitioning index and return pi
            int pi = partition(intArray, low, high);

            // sort each partition recursively
            quick_sort(intArray, low, pi-1);
            quick_sort(intArray, pi+1, high);
        }
        return intArray;
    }
    public static int partition(int intArray[], int low, int high) {
        int pi = intArray[high];
        int i = (low-1); // smaller element index
        for (int j=low; j<high; j++) {
            // check if current element is less than or equal to pi
            if (intArray[j] <= pi) {
                i++;
                // swap intArray[i] and intArray[j]
                int temp = intArray[i];
                intArray[i] = intArray[j];
                intArray[j] = temp;
            }
        }

        // swap intArray[i+1] and intArray[high] (or pi)
        int temp = intArray[i+1];
        intArray[i+1] = intArray[high];
        intArray[high] = temp;

        return i+1;
    }
    public static String findLotteryWinners(){
        int indexOfWinner = -1;
        boolean winnerFound = false;
        Scanner keyboard = new Scanner(System.in);
        while (!winnerFound) {
            String winner = (int) (Math.random() * 13 + 1) + " " + (int) (Math.random() * 13 + 1) + " " + (int) (Math.random() * 13 + 1) + " " + (int) (Math.random() * 13 + 1);
            for (int i = 0; i < teamsTickets.size(); i++) {
                for (int j = 0; j < teamsTickets.get(i).size(); j++) {
                    String[] winnerArray = new String[4];
                    int[] intWinnerArray = new int[4];
                    winnerArray = winner.split(" ");
                    for(int k = 0; k<winnerArray.length;k++){
                        intWinnerArray[k] = Integer.parseInt(winnerArray[k]);
                    }
                    String[] teamsTicketsArray = new String[4];
                    int[] intTeamsTicketsArray = new int[4];
                    teamsTicketsArray = teamsTickets.get(i).get(j).split(" ");
                    for(int l = 0; l<teamsTicketsArray.length;l++){
                        intTeamsTicketsArray[l] = Integer.parseInt(teamsTicketsArray[l]);
                    }
                    int[] sortedTeamsTicket = quick_sort(intTeamsTicketsArray,0,intTeamsTicketsArray.length-1);
                    int[] sortedWinner = quick_sort(intWinnerArray, 0, intWinnerArray.length-1);
                    if (Arrays.equals(sortedTeamsTicket,sortedWinner)) {
                        indexOfWinner = i;
                        winnerFound = true;
                        int indexOfFirstSpace = winner.indexOf(" ");
                        int indexOfSecondSpace = winner.substring(indexOfFirstSpace+1).indexOf(" ") + indexOfFirstSpace+1;
                        int indexOfThirdSpace = winner.substring(indexOfSecondSpace+1).indexOf(" ") + indexOfSecondSpace+1;
                        int indexOfFourthSpace = winner.substring(indexOfThirdSpace+1).indexOf(" ") + indexOfThirdSpace+1;
                        if (!goFast) isFast = keyboard.nextLine();
                        if (!goFast && isFast.equals("fast")) goFast = true;
                        System.out.println("The first number is " + winner.substring(0,indexOfFirstSpace));
                        System.out.println("These " + findPossibleWinners(Arrays.copyOfRange(sortedWinner,0,1),1).size() + " teams could still win! - " + findPossibleWinners(Arrays.copyOfRange(sortedWinner,0,1),1));
                        if (!goFast) isFast = keyboard.nextLine();
                        if (!goFast && isFast.equals("fast")) goFast = true;
                        System.out.println("The second number is " + winner.substring(indexOfFirstSpace+1,indexOfSecondSpace));
                        System.out.println("These " + findPossibleWinners(Arrays.copyOfRange(sortedWinner,0,2),2).size() + " teams could still win! - " + findPossibleWinners(Arrays.copyOfRange(sortedWinner,0,2),2));
                        if (!goFast) isFast = keyboard.nextLine();
                        if (!goFast && isFast.equals("fast")) goFast = true;
                        System.out.println("The third number is " + winner.substring(indexOfSecondSpace+1,indexOfThirdSpace));
                        System.out.println("These " + findPossibleWinners(Arrays.copyOfRange(sortedWinner,0,3),3).size() + " teams could still win! - " + findPossibleWinners(Arrays.copyOfRange(sortedWinner,0,3),3));
                        if (!goFast) isFast = keyboard.nextLine();
                        if (!goFast && isFast.equals("fast")) goFast = true;
                        System.out.println("The fourth number is " + winner.substring(indexOfFourthSpace+1));
                        break;
                    }
                }
            }
        }
        for (int i = 0; i<winners.size();i++){
            if(winners.get(i).equals(worstTeams[indexOfWinner])) {
                System.out.println("The " + worstTeams[indexOfWinner] + " won but since they have already won, this pick will be redone.");
                return findLotteryWinners();
            }
        }
        winners.add(worstTeams[indexOfWinner]);
        return AworstTeams[indexOfWinner];
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
        userInput();
        System.out.println("The burnt ticket was " + allTickets.get(0));
        System.out.println("Pick number 1 goes to the " + findLotteryWinners());
        System.out.println("Pick number 2 goes to the " + findLotteryWinners());
        System.out.println("Pick number 3 goes to the " + findLotteryWinners());
        System.out.println("Pick number 4 goes to the " + findLotteryWinners());
        findOtherWinners();
        userInput();
        System.out.println("Pick number 5 goes to the " + lotteryLosers.get(0));
        userInput();
        System.out.println("Pick number 6 goes to the " + lotteryLosers.get(1));
        userInput();
        System.out.println("Pick number 7 goes to the " + lotteryLosers.get(2));
        userInput();
        System.out.println("Pick number 8 goes to the " + lotteryLosers.get(3));
        userInput();
        System.out.println("Pick number 9 goes to the " + lotteryLosers.get(4));
        userInput();
        System.out.println("Pick number 10 goes to the " + lotteryLosers.get(5));
        userInput();
        System.out.println("Pick number 11 goes to the " + lotteryLosers.get(6));
        userInput();
        System.out.println("Pick number 12 goes to the " + lotteryLosers.get(7));
        userInput();
        System.out.println("Pick number 13 goes to the " + lotteryLosers.get(8));
        userInput();
        System.out.println("Pick number 14 goes to the " + lotteryLosers.get(9));
        userInput();
    }
    public static ArrayList<String> findPossibleWinners(int[] balls, int whichTime){
        ArrayList<String> possibleWinners = new ArrayList<>();
        for (int i = 0; i < teamsTickets.size(); i++) {
            for (int j = 0; j < teamsTickets.get(i).size(); j++) {
                String str = teamsTickets.get(i).get(j);
                int[] oneBallofTicketArray = new int[1];
                int[] twoBallofTicketArray = new int[2];
                int[] threeBallofTicketArray = new int[3];
                String[] sortedTicket = new String[4];
                int[] intSortedTicket = new int[4];
                sortedTicket = str.split(" ");
                for (int o = 0; o < sortedTicket.length; o++){
                    intSortedTicket[o] = Integer.parseInt(sortedTicket[o]);
                } // sooo {2,10,11} for {2,9,10,11} is possible winner, so make it like that smh
                intSortedTicket = quick_sort(intSortedTicket,0,intSortedTicket.length-1);
                oneBallofTicketArray = Arrays.copyOfRange(intSortedTicket,0,1);
                twoBallofTicketArray = Arrays.copyOfRange(intSortedTicket,0,2);
                threeBallofTicketArray = Arrays.copyOfRange(intSortedTicket,0,3);
                int twocount = 0;
                int threecount = 0;
                if (whichTime == 1){
                    for (int ball : balls){
                        for (int ticket : oneBallofTicketArray){
                            if (ball==ticket) possibleWinners.add(worstTeams[i]);
                        }
                    }
                }
                if (whichTime == 2){
                    for (int ball : balls){
                        for (int ticket : twoBallofTicketArray){
                            if (ball==ticket) twocount++;
                        }
                    }
                    if (twocount>=2) possibleWinners.add(worstTeams[i]);
                }
                if (whichTime == 3){
                    for (int ball : balls){
                        for (int ticket : threeBallofTicketArray){
                            if (ball==ticket) threecount++;

                        }
                    }
                    if (threecount>=3) possibleWinners.add(worstTeams[i]);
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
            printWriter.println("The burnt ticket is " + allTickets);

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
    public static void userInput(){
        Scanner keyboard = new Scanner(System.in);
        if (!goFast) isFast = keyboard.nextLine();
        if (!goFast && isFast.equals("fast")) goFast = true;
    }
}
