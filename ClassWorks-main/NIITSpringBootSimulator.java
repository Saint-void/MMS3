
import java.util.Scanner;

public class NIITSpringBootSimulator {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println(" SPRING BOOT BACKEND SIMULATOR (SEM 3) ");
        System.out.println("========================================");
        System.out.println("Goal: Build and Run your first REST API.\n");

        // The "Contract" of the lesson
        String[] steps = {
            "Download project from start.spring.io with 'Spring Web'",
            "Annotate your class to make it a Web Controller",
            "Define the URL path for your hello message",
            "Run the application and check the browser"
        };

	String[] keyAnswers = {
    		"generate",
    		"@RestController",
    		"@GetMapping",
    		"localhost:8080"
		};

	for (int i = 0; i < steps.length; i++) {
    		boolean correct = false;

		while (!correct) {
			System.out.println("\n[TASK " + (i + 1) + "]: " + steps[i]);
			System.out.println("Enter code/keyword: ");
			String input = scanner.nextLine();

			// Validation (ignores case for better student experience)
			if (input.trim().equalsIgnoreCase(keyAnswers[i])) {
			System.out.print("Compiling & Initializing");

				// The "..." Animation logic
				for (int dots =0; dots < 4; dots++) {
				Thread.sleep(600);
				System.out.print(".");
			}

			System.out.println("/n BEAN CREATED SUCCESSFULLY!");
			correct=true;
		} else {
			System.out.println("ERROR: Unexpected input.Check your spelling!");
			System.out.println("HINT: You are looking for " + keyAnswers[i]);
		}
    	}
}

		System.out.println("/n======================================");
		System.out.println("API IS LIVE! YOUR BACKEND IS READY!");
		System.out.println("JSON Sent to Frontend: {\"message\": \"Hello NIIT\"}");
		System.out.println("=========================================");
		
		scanner.close();
	}
}