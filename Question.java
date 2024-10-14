import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean isCorrectAnswer(int userAnswerIndex) {
        return userAnswerIndex == correctAnswerIndex;
    }
}

class Quiz {
    private List<Question> questions;
    private int score;
    private int currentQuestionIndex;
    private boolean answered;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.score = 0;
        this.currentQuestionIndex = 0;
        this.answered = false;
    }

    public void start() {
        for (currentQuestionIndex = 0; currentQuestionIndex < questions.size(); currentQuestionIndex++) {
            askQuestion();
            if (answered) {
                continue;
            }
            System.out.println("Time's up! Moving to the next question.");
        }
        displayResult();
    }

    private void askQuestion() {
        Question question = questions.get(currentQuestionIndex);
        System.out.println("\n" + question.getQuestionText());
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        // Start timer for 10 seconds
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!answered) {
                    System.out.println("\nTime's up! You didn't answer this question.");
                    answered = true;
                }
            }
        }, 10000); // 10 seconds

        Scanner scanner = new Scanner(System.in);
        int userAnswerIndex = -1;
        while (!answered) {
            if (scanner.hasNextInt()) {
                userAnswerIndex = scanner.nextInt() - 1; // Convert to 0-based index
                if (userAnswerIndex >= 0 && userAnswerIndex < options.length) {
                    answered = true;
                    timer.cancel(); // Cancel timer if answered
                } else {
                    System.out.println("Invalid choice. Please select a valid option.");
                }
            }
        }

        if (question.isCorrectAnswer(userAnswerIndex)) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer was: " + options[question.correctAnswerIndex]);
        }
        answered = false; // Reset for next question
    }

    private void displayResult() {
        System.out.println("\nQuiz Completed!");
        System.out.println("Your final score is: " + score + "/" + questions.size());
    }
}

public class Main {
    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2));
        questions.add(new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1));
        questions.add(new Question("What is the largest planet in our solar system?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 2));
        questions.add(new Question("What is the boiling point of water?", new String[]{"100°C", "90°C", "80°C", "70°C"}, 0));
        questions.add(new Question("Which element has the chemical symbol 'O'?", new String[]{"Oxygen", "Gold", "Silver", "Hydrogen"}, 0));

        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}

