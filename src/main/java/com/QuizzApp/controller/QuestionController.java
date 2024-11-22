package com.QuizzApp.controller;

import com.QuizzApp.model.Question;
import com.QuizzApp.service.QuestionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;
import java.util.List;

@RestController
@Tag(name = "Qustion APIs")
@RequestMapping("question")
public class QuestionController {

    @Autowired
     private  QuestionService questionService;

    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
      return  questionService.getQuestionByCategory(category);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addQuestion( @RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        return  questionService.deleteQuestionById(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable Integer id,@RequestBody Question question){
        question.setId(id);
        return  questionService.updateQuestion(question);
    }
}
