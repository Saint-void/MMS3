package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GuessController {

    private int targetNumber;
    private Random random = new Random();

    // Constructor → runs once when app starts
    public GuessController() {
        targetNumber = random.nextInt(10) + 1; // 1–10
        System.out.println("Target Number: " + targetNumber); // for testing
    }

    @GetMapping("/guess")
    public Map<String, Object> guessNumber(@RequestParam int number) {
        Map<String, Object> response = new HashMap<>();

        if (number == targetNumber) {
            response.put("result", "Correct 🎉");

            // generate new number after correct guess
            targetNumber = random.nextInt(10) + 1;

        } else if (number < targetNumber) {
            response.put("result", "Too low ⬇️");
        } else {
            response.put("result", "Too high ⬆️");
        }

        response.put("yourGuess", number);

        return response;
    }
}