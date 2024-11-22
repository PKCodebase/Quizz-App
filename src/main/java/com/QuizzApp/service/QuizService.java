package com.QuizzApp.service;

import com.QuizzApp.dao.QuestionDao;
import com.QuizzApp.dao.QuizDao;
import com.QuizzApp.model.Question;
import com.QuizzApp.model.QuestionWrapper;
import com.QuizzApp.model.Quiz;
import com.QuizzApp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuestionDao questionDao;


    public ResponseEntity<String> createQuiz( String category,int numQ,String title){
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        Optional<Quiz> quiz = quizDao.findById(id);
       List<Question> questionFromDB = quiz.get().getQuestions();
       List<QuestionWrapper> questionForUser = new ArrayList<>();
       for(Question q: questionFromDB){
           QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
           questionForUser.add(qw);
       }
       return  new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
        List<Question> questions = quiz.getQuestions();

        Map<Integer, String> questionAnswers = new HashMap<>();
        for (Question question : questions) {
            questionAnswers.put(question.getId(), question.getRightAnswer());
        }

        int correctAnswers = 0;
        for (Response response : responses) {
            String correctAnswer = questionAnswers.get(response.getId());
            if (correctAnswer != null && correctAnswer.equals(response.getResponse())) {
                correctAnswers++;
            }
        }

        return new ResponseEntity<>(correctAnswers, HttpStatus.OK);
    }
}


