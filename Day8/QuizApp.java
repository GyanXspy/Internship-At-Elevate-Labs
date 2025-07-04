package Day8;

import java.util.*;
public class QuizApp {

    public static void main(String[] args) {
        List<Question> questions = Arrays.asList(
        new Question("What is the capital of France?", Arrays.asList("Berlin", "Paris", "Rome", "Madrid"), 2),
        new Question("Which planet is known as the Red Planet?", Arrays.asList("Earth", "Mars", "Jupiter", "Venus"), 2),
        new Question("What is 2 + 2?", Arrays.asList("3", "4", "5", "6"), 2));
        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}
class Question {
    String question;
    List<String> options;
    int correctOption;

    public Question(String question, List<String> options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }
}

class Quiz {
    private List<Question> questions;
    private int score;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.score = 0;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("Q" + (i + 1) + ": " + q.question);
            for (int j = 0; j < q.options.size(); j++) {
                System.out.println((j + 1) + ". " + q.options.get(j));
            }
            System.out.print("Your answer (1-" + q.options.size() + "): ");
            int answer = scanner.nextInt();
            if (answer == q.correctOption) {
                score++;
            }
        }
        System.out.println("Quiz finished! Your score: " + score + "/" + questions.size());
        scanner.close();
    }
}
