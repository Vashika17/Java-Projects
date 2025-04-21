import java.util.*;
import java.io.*;

public class QuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User("VASHIKA", "1234"); 

        System.out.println("=== Welcome to the Java Quiz App ===");

        // Login
        System.out.print("Enter username: ");
        String uname = scanner.nextLine();
        System.out.print("Enter password: ");
        String pass = scanner.nextLine();

        if (!user.login(uname, pass)) {
            System.out.println("❌ Invalid credentials. Exiting...");
            scanner.close();  
            return;           
        }

        System.out.println("✅ Login successful!\n");

        // Prepare questions
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the size of int in Java?",
                new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on OS"}, 'B'));
        questions.add(new Question("Which keyword is used to inherit a class?",
                new String[]{"this", "import", "extends", "implements"}, 'C'));
        questions.add(new Question("What does JVM stand for?",
                new String[]{"Java Virtual Method", "Java Variable Machine", "Java Virtual Machine", "Java Verified Machine"}, 'C'));
        questions.add(new Question("Which method is the entry point in Java?",
                new String[]{"start()", "main()", "run()", "init()"}, 'B'));
        questions.add(new Question("Which is not an OOP principle?",
                new String[]{"Inheritance", "Encapsulation", "Compilation", "Polymorphism"}, 'C'));

        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("\nQuestion " + (i + 1));
            q.display();

            System.out.print("Your answer (A/B/C/D): ");
            char ans = scanner.next().charAt(0);
            if (q.isCorrect(ans)) {
                score++;
                System.out.println("✅ Correct!");
            } else {
                System.out.println("❌ Wrong! Correct answer: " + q.correctAnswer);
            }
        }

        System.out.println("\n🎉 Quiz completed!");
        System.out.println("You scored: " + score + "/" + questions.size());

        // Save result to file
        try (FileWriter fw = new FileWriter("results.txt", true)) {
            fw.write("User: " + uname + " | Score: " + score + "/" + questions.size() + " | Time: " + new Date() + "\n");
        } catch (IOException e) {
            System.out.println("⚠️ Error writing to results file.");
        }

        scanner.close();
    }
}

