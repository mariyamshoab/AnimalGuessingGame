import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

public class AnimalGuessingGame {

    private JFrame frame;
    private JLabel titleLabel, questionLabel;
    private JButton yesButton, noButton, startButton;
    private JTextArea outputArea;
    private JPanel buttonPanel, topPanel;

    private String[] questions = {
            "Is it a mammal?",
            "Does it live on land?",
            "Is it domestic?",
            "Is it carnivorous?",
            "Is it large?",
            "Can it fly?",
            "Does it live in water?",
            "Does it bark?"
    };

    private Map<String, String> animals;
    private java.util.List<String> possibleAnimals;
    private StringBuilder userAnswers;
    private int currentQuestionIndex;

    public AnimalGuessingGame() {
        animals = new LinkedHashMap<>();
        animals.put("Dog", "YYYYNNNY");
        animals.put("Cat", "YYYYNNNN");
        animals.put("Cow", "YYYNYNNN");
        animals.put("Lion", "YYNYNNNN");
        animals.put("Eagle", "NNNYNYNN");
        animals.put("Shark", "NNNYYNYN");

        userAnswers = new StringBuilder();
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("🎮 Animal Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(245, 245, 245));

        topPanel = new JPanel();
        topPanel.setBackground(new Color(70, 130, 180));
        topPanel.setLayout(new BorderLayout());

        titleLabel = new JLabel("🐾 Let's Play! Guess the Animal!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel, BorderLayout.NORTH);

        questionLabel = new JLabel("Click START to see the animal options!", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setForeground(Color.WHITE);
        topPanel.add(questionLabel, BorderLayout.SOUTH);

        frame.add(topPanel, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));

        yesButton = new JButton("Yes");
        noButton = new JButton("No");
        startButton = new JButton("START");

        yesButton.setEnabled(false);
        noButton.setEnabled(false);

        buttonPanel.add(startButton);
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 16));
        outputArea.setBackground(new Color(230, 230, 250));
        frame.add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        startButton.addActionListener(e -> startGame());
        yesButton.addActionListener(e -> processAnswer('Y'));
        noButton.addActionListener(e -> processAnswer('N'));

        frame.setVisible(true);
    }

    private void startGame() {
        possibleAnimals = new ArrayList<>(animals.keySet());
        userAnswers.setLength(0);
        currentQuestionIndex = 0;

        outputArea.setText("Think of an animal from the following list and answer the questions:\n" +
                String.join(", ", animals.keySet()) + "\n\n");

        questionLabel.setText(questions[currentQuestionIndex]);
        startButton.setEnabled(false);
        yesButton.setEnabled(true);
        noButton.setEnabled(true);
    }

    private void processAnswer(char answerChar) {
        userAnswers.append(answerChar);

        Iterator<String> iterator = possibleAnimals.iterator();
        while (iterator.hasNext()) {
            String animal = iterator.next();
            String pattern = animals.get(animal);
            if (pattern.charAt(currentQuestionIndex) != answerChar) {
                iterator.remove();
            }
        }

        currentQuestionIndex++;

        if (possibleAnimals.size() == 1) {
            showGuess(possibleAnimals.get(0));
        } else if (possibleAnimals.isEmpty()) {
            showGuess(null);
        } else if (currentQuestionIndex >= questions.length) {
            showGuess(possibleAnimals.get(0));
        } else {
            questionLabel.setText(questions[currentQuestionIndex]);
        }
    }

    private void showGuess(String animal) {
        yesButton.setEnabled(false);
        noButton.setEnabled(false);

        String message;
        if (animal == null) {
            message = "😅 I couldn't guess your animal. Are you sure it was in the list?";
        } else {
            message = "🎉 YOUR ANIMAL WAS: " + animal + "!";
        }

        JOptionPane.showMessageDialog(frame, message, "Animal Guessed!", JOptionPane.INFORMATION_MESSAGE);

        startButton.setEnabled(true);
        startButton.setText("Play Again");
        questionLabel.setText("Click START to play again!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AnimalGuessingGame::new);
    }
}
