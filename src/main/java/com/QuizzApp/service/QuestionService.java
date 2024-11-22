package com.QuizzApp.service;


import com.QuizzApp.dao.QuestionDao;
import com.QuizzApp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {


    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionDao.findAll();
        if (questions.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }


    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        List<Question> questions = questionDao.findByCategory(category);
        if (questions.isEmpty()) {
            throw new RuntimeException("No questions found for category: " + category);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
    }


    public ResponseEntity<String> deleteQuestionById(Integer id) {
        Optional<Question> existingQuestion = questionDao.findById(id);
        if (existingQuestion.isEmpty()) {
            return new ResponseEntity<>("Question with ID " + id + " not found!", HttpStatus.NOT_FOUND);
        }
        questionDao.deleteById(id);
        return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
    }

    public ResponseEntity<String> updateQuestion(Question question) {
        Optional<Question> existingQuestion = questionDao.findById(question.getId());
        if (existingQuestion.isEmpty()) {
            return new ResponseEntity<>("Question with ID " + question.getId() + " not found!", HttpStatus.NOT_FOUND);
        }
        questionDao.save(question);
        return new ResponseEntity<>("Question updated successfully", HttpStatus.OK);
    }
}
