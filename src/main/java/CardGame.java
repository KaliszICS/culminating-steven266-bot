import java.util.ArrayList;
import java.util.Scanner;

public class CardGame
{
 static Scanner input = new Scanner(System.in);
    static int playerWins = 0;
    static int systemWins = 0;

    public static void main(String[] args)
    {
        String start;

        do
        {
            System.out.print("Enter Y to start the game: ");
            start = input.nextLine().toUpperCase();

            if (!start.equals("Y"))
            {
                System.out.println("Invalid input. Please enter Y.");
            }

        } while (!start.equals("Y"));

        boolean systemFirst = false;
        boolean playAgain = true;

        while (playAgain)
        {
            systemFirst = playRound(systemFirst);

            displayLeaderboard();

            String answer;

            do
            {
                System.out.print("\nPlay another round? (Y/N): ");
                answer = input.nextLine().toUpperCase();

                if (!answer.equals("Y") && !answer.equals("N"))
                {
                    System.out.println("Invalid input.");
                }

            } while (!answer.equals("Y") && !answer.equals("N"));

            if (answer.equals("N"))
            {
                playAgain = false;
            }
        }

        System.out.println("\nThank you for playing!");
    }

    public static boolean playRound(boolean systemFirst)
    {
        ArrayList<Integer> playerCards = new ArrayList<Integer>();
        ArrayList<Integer> systemCards = new ArrayList<Integer>();

        playerCards.add(drawCard());
        systemCards.add(drawCard());

        System.out.println("\n====================");
        System.out.println("NEW ROUND");
        System.out.println("====================");

        System.out.println("Player starting card: "
                + playerCards.get(0));

        System.out.println("System starting card: "
                + systemCards.get(0));

        boolean playerBust = false;
        boolean systemBust = false;
        if (systemFirst)
        {
            System.out.println("\nSystem goes first this round.");


            systemBust = systemTurn(systemCards);


            if (!systemBust)
            {
                playerBust = playerTurn(playerCards);
            }
        }
        else
        {
 		System.out.println("\nPlayer goes first this round.");


            playerBust = playerTurn(playerCards);


            if (!playerBust)
            {
                systemBust = systemTurn(systemCards);
            }
        }


        int playerTotal = calculateTotal(playerCards);
        int systemTotal = calculateTotal(systemCards);


        System.out.println("\n----- Final Results -----");
        System.out.println("Player Cards: " + playerCards);
        System.out.println("Player Total: " + playerTotal);


        System.out.println("System Cards: " + systemCards);
        System.out.println("System Total: " + systemTotal);


        if (playerBust)
        {
            System.out.println("\nPlayer busts! System wins.");
            systemWins++;
            return false;
        }
        else if (systemBust)
        {
            System.out.println("\nSystem busts! Player wins.");
            playerWins++;
            return false;
             }
        else if (playerTotal > systemTotal)
        {
            System.out.println("\nPlayer wins!");
            playerWins++;
            return false;
        }
        else if (systemTotal > playerTotal)
        {
            System.out.println("\nSystem wins!");
            systemWins++;
            return false;
        }
        else
        {
            System.out.println("\nDraw!");
            System.out.println(
                "Next round will start with the system.");
            return true;
        }
    }
 public static boolean playerTurn(ArrayList<Integer> cards)
    {
        int choice;


        while (true)
        {
            System.out.println("\nYour Cards: " + cards);
            System.out.println("Current Total: "
                    + calculateTotal(cards));


            do
            {
                System.out.print(
                    "Enter 1 to draw a card or 2 to stop: ");


                while (!input.hasNextInt())
                {
                    System.out.println("Invalid input.");
                    input.next();
                }


                choice = input.nextInt();


                if (choice != 1 && choice != 2)
                {
                    System.out.println(
                        "Please enter 1 or 2.");
                }


            } while (choice != 1 && choice != 2);


            input.nextLine();


            if (choice == 2)
            {
                break;
            }


            int card = drawCard();
            cards.add(card);


            System.out.println("You drew: " + card);


            if (calculateTotal(cards) > 21)
            {
                return true;
            }
        }


        return false;
    }
     public static boolean systemTurn(ArrayList<Integer> cards)
    {
        System.out.println("\nSystem's Turn");


        while (calculateTotal(cards) < 17)
        {
            int card = drawCard();
            cards.add(card);


            System.out.println("System drew a card: " + card);


            System.out.println("System Total: "+ calculateTotal(cards));
            
            if (calculateTotal(cards) > 21)
            {
                return true;
            }
        }

        System.out.println("System stops drawing at " + calculateTotal(cards));
        return false;
    }

        public static int drawCard()
    {
        return (int)(Math.random() * 15) + 1;
    }
        public static int calculateTotal(
            ArrayList<Integer> cards)
    {
        int total = 0;

        for (int card : cards)
        {
            total += card;
        }
        return total;
    }

        public static void displayLeaderboard()
    {
        System.out.println("\n====================");
        System.out.println("LEADERBOARD");
        System.out.println("====================");
        System.out.println("Player Wins: " + playerWins);
        System.out.println("System Wins: " + systemWins);
    }
}
