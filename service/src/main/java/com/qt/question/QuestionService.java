package com.qt.question;

import com.qt.domain.question.Question;
import com.qt.domain.question.dto.QuestionInfo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    public QuestionService(QuestionRepository questionRepository, ModelMapper modelMapper) {
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
    }

    public Long save(QuestionInfo questionInfo) {
        Question question = questionInfo.toEntity();
        return questionRepository.save(question).getId();
    }

    @Transactional(readOnly = true)
    public QuestionInfo findById(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(NotFoundQuestionException::new);
        return modelMapper.map(question, QuestionInfo.class);
    }

// TODO: 질문이 과연 Update가 필요한건지? 일회성으로 끝나야 하는건지 의논해야함.
//    public Long updateQuestion(Long id, QuestionInfo questionInfo) {
//        Question question = questionRepository.findById(id).orElseThrow(NotFoundQuestionException::new);
//        return question.updateTo(questionInfo);
//    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
