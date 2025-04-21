import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizAppGUI {
    private static JFrame frame;
    private static int currentQuestion = 0;
    private static int score = 0;
    private static int timeLeft = 10; // seconds per question
    private static Timer timer;

    private static String[] questions = {
        "What is the capital of France?",
        "Which language runs in a web browser?",
        "What does OOP stand for?",
        "Which company developed Java?"
    };

    private static String[][] options = {
        {"Paris", "London", "Berlin", "Madrid"},
        {"Java", "C", "Python", "JavaScript"},
        {"Object-Oriented Programming", "Open Online Platform", "One Object Pointer", "Only On Processor"},
        {"Microsoft", "Apple", "Sun Microsystems", "Google"}
    };

    private static String[] answers = {
        "Paris",
        "JavaScript",
        "Object-Oriented Programming",
        "Sun Microsystems"
    };

    private static JRadioButton[] optionButtons;
    private static ButtonGroup optionGroup;
    private static JLabel questionLabel;
    private static JLabel timerLabel;
    private static JButton nextButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> showLoginScreen());
    }

    private static void showLoginScreen() {
        frame = new JFrame("Java Quiz App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new FlowLayout());

        JLabel userLabel = new JLabel("Enter username:");
        JTextField usernameField = new JTextField(20);
        JLabel passLabel = new JLabel("Enter password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        frame.add(userLabel);
        frame.add(usernameField);
        frame.add(passLabel);
        frame.add(passwordField);
        frame.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equalsIgnoreCase("Vashika") && password.equals("1234")) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Login successful! Start quiz?", "Start Quiz", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    showQuizScreen();
                } else {
                    frame.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    private static void showQuizScreen() {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setLayout(new BorderLayout());

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.add(questionLabel, BorderLayout.NORTH);

        // Timer label
        timerLabel = new JLabel("Time left: 10s");
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.add(timerLabel, BorderLayout.SOUTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        optionButtons = new JRadioButton[4];
        optionGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }

        frame.add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        frame.add(nextButton, BorderLayout.EAST);

        nextButton.addActionListener(e -> {
            if (timer != null) timer.stop(); // stop current timer

            if (checkAnswer()) {
                score++;
            }
            currentQuestion++;
            if (currentQuestion < questions.length) {
                loadQuestion(currentQuestion);
            } else {
                JOptionPane.showMessageDialog(frame, "Quiz Over! Your score: " + score + "/" + questions.length);
                frame.dispose();
            }
        });

        loadQuestion(currentQuestion);
        frame.revalidate();
        frame.repaint();
    }

    private static void loadQuestion(int index) {
        questionLabel.setText("Q" + (index + 1) + ": " + questions[index]);
        optionGroup.clearSelection();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[index][i]);
        }

        // Timer logic
        timeLeft = 10;
        timerLabel.setText("Time left: " + timeLeft + "s");

        if (timer != null) timer.stop();

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time left: " + timeLeft + "s");
                if (timeLeft <= 0) {
                    timer.stop();
                    currentQuestion++;
                    if (currentQuestion < questions.length) {
                        loadQuestion(currentQuestion);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Time's up! Your score: " + score + "/" + questions.length);
                        frame.dispose();
                    }
                }
            }
        });

        timer.start();
    }

    private static boolean checkAnswer() {
        for (int i = 0; i < 4; i++) {
            if (optionButtons[i].isSelected()) {
                return optionButtons[i].getText().equals(answers[currentQuestion]);
            }
        }
        return false;
    }
}
