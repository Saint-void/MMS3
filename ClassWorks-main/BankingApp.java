import java.util.Scanner;

public class BankingApp{
    public static void main(String[] args) {
        double balance = 100000.00;
        //String name = "Peter";
        String name;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name to start banking:...");
        name = scanner.nextLine();

        System.out.println(name + "--- Welcome to NIIT Digital Bank ---");
        System.out.println(name + " Current Balance: $" + balance);

        System.out.print("Enter amount to deposit: ");
        double deposit = scanner.nextDouble();

	balance += deposit;

	System.out.println("Deposit successfully");
        System.out.println(name + "\n New Balance: $" + balance);
	System.out.println("-----------------------------------");

	scanner.close();
    }
}