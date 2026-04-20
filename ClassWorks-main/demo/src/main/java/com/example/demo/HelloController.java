package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.*;
//import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController{
	
	@GetMapping("/")
	public String sayHello(){
		return "<h1>Welcome to NIIT Semester 3!</h1><p>Your Spring Boot Sever is officially LIVE.</p>";
}

   @GetMapping("/BankingApp")
	public String displayBankAppInfo(){
        double balance = 100000.00;
        String name, n1, n2, n3, n4, n5, n6, n7;

        n1 = ("<p>Enter your name to start banking:...</p>");
	name = "Peter";

        n2 = (name + "<h1>--- Welcome to NIIT Digital Bank ---</h1>");
        n3 = (name + " <p><h2>Current Balance: $</h2></p>" + balance);
        n4 = ("<p><h2>Enter amount to deposit: </p></h2>");
	double deposit = 1200;

	balance += deposit;

	n5 = ("<p><h2>Deposit Successfully!</p></h2>");
        n6 = (name + "<p><h2> New Balance: $</p></h2>" + balance);
	n7 = ("<p><h2>-----------------------------------</p></h2>");

	return n1 + n2 + n3 + n4 + n5 + n6 + n7;
	
}



   @GetMapping("/play")
    public String showGame(@RequestParam(name="guess", required=false) Integer guess) {
        String resultHtml = "";
        String bgColor = "#2c3e50"; // Dark blue-grey default

        // 1. Logic: Only run if the user has actually submitted a guess
        if (guess != null) {
            int secret = new Random().nextInt(10) + 1;
            if (guess == secret) {
                bgColor = "#27ae60"; // Green for Win
                resultHtml = "<h2 style='color:white;'>🎉 Correct! The number was                " + secret + "</h2>";
            } else {
                bgColor = "#e74c3c"; // Red for Loss
                resultHtml = "<h2 style='color:white;'>❌ Wrong! The secret was "              + secret + ". Try again!</h2>";
            }
        }

        // 2. The UI with the <form> input
        return "<html><body style='font-family:Segoe UI, sans-serif; background:"         + bgColor + "; display:flex; justify-content:center; align-items:center;        height:100vh; margin:0;'>" +
               "<div style='background:white; padding:40px; border-radius:20px;         text-align:center; box-shadow:0 15px 35px rgba(0,0,0,0.2);'>" +
               "<h1 style='color:#333;'>Number Guessing Game</h1>" +
               "<p style='color:#666;'>Enter a number between 1 and 10:</p>" +
               
               // --- THE FORM START ---
               "<form action='/play' method='get'>" +
               "<input type='number' name='guess' min='1' max='10' required " +
               "style='padding:15px; font-size:20px; width:80px; border-                radius:10px; border:2px solid #ddd; outline:none; text-                align:center;'>" +
               "<br><br>" +
               "<button type='submit' style='background:#3498db; color:white;                 border:none; padding:15px 30px; font-size:18px; border-                radius:10px; cursor:pointer;'>Submit Guess</button>" +
               "</form>" +
               // --- THE FORM END ---
               
               "<div style='margin-top:20px;'>" + resultHtml + "</div>" +
               "</div></body></html>";
    }


  @GetMapping("/api/status")
  public Map<String, Object> getStatus(){
   Map<String, Object> task = new HashMap<>();
   task.put("status", "Success");
   task.put("Instructor","Wisdom Amaju");
   task.put("message","The map task is working perfectly");
   return task;
  }
}