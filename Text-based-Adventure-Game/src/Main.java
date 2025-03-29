import java.util.Random;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        // Creating System objects
        Scanner input = new Scanner(System.in); // use input from this system in this scanner object. Not important and we are using it just for input
        Random random = new Random(); // random object for random number generator.

        // Game variables
        String[] enemies = { "Skeleton", "Zombie", "Spider", "Magician" };
        int maxEnemyHealth = 120;
        int enemyAttackDamage = 10;

        // Player variables
        int health = 100;
        int attackDamage = 5;
        int numOfHealthPortions = 3;
        int healthHealAmount = 20;
        int healthDropChance = 50; // percentage change to drop health

        boolean running = true; // condition for while loop

        System.out.println("Welcome to the Cave!");

        GAME: // labeled this while with GAME.
        while (true)
        {
            System.out.println("----------------------------------------------");

            int enemyHealth = random.nextInt(maxEnemyHealth);
            String enemy = enemies[random.nextInt(enemies.length)]; // int between 0 and max length
            System.out.println("\t# " + enemy + " just appeared #\n");

            while (enemyHealth > 0) // while enemy is alive
            {
                System.out.println("\tYour HP: " + health);
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink health drink");
                System.out.println("\t3. Run away!");

                String in = input.nextLine();
                if (in.equals("1"))
                {
                    int damageAttack = random.nextInt(attackDamage);
                    int damageTaken = random.nextInt(enemyAttackDamage);

                    enemyHealth -= damageAttack;
                    health -= damageTaken;

                    System.out.println("\t> You strike the " + enemy + " with damage -" + damageAttack);
                    System.out.println("\t> you receive -" + damageTaken);

                    if (health < 1)
                    {
                        System.out.println("\t> You have died! GAME OVER");
                        break;
                    }
                }
                else if (in.equals("2"))
                {
                    if (numOfHealthPortions > 0)
                    {
                        health += healthHealAmount;
                        numOfHealthPortions--; // take one from inventory
                        System.out.println("\t> You drink a health portion of " + healthHealAmount + " HP!"
                                            + "\n\t> Now you have " + health + " HP."
                                            + "\n\t> You have " + numOfHealthPortions + " health portions left");
                    }
                    else
                    {
                        System.out.println("\t> You have no health portions left! defeat enemy to try get one!");
                    }
                }
                else if (in.equals("3"))
                {
                    System.out.println("\t You ran away from the " + enemy + "!");
                    continue GAME; // go back to the specific while loop
                }
                else
                {
                    System.out.println("\tInvalid command!");
                }
            }

            if (health < 1)
            {
                System.out.println("You limp out of the dungeon, weak from battle.");
                break;
            }

            System.out.println("----------------------------------------------");
            System.out.println(" # " + enemy + " was defeated... #");
            System.out.println(" # You have " + health + "  HP left #");
            if (random.nextInt(100) < healthDropChance)
            {
                numOfHealthPortions++; //add one
                System.out.println(" # The " + enemy + " dropped a health portion! #");
                System.out.println("Y # You have " + numOfHealthPortions + " health portion(s). #");
            }
            System.out.println("----------------------------------------------");
            System.out.println("What would you like to do now?");
            System.out.println("1. Continue fighting");
            System.out.println("2. Exit dungeon");

            String in = input.nextLine();

            while (!in.equals("1") && !in.equals("2"))
            {
                System.out.println("Invalid command!");
                in = input.nextLine();
            }

            if (in.equals("1"))
            {
                System.out.println("You decided to continue!");
            }
            else
            {
                System.out.println("You exit the dungeon");
                break;
            }
        }

        System.out.println("################");
        System.out.println("# THANKS FOR PLAYING: #");
        System.out.println("################");

        input.close();
    }
}