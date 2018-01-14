package com.javasampleapproach.angularjs.service;

import com.javasampleapproach.angularjs.vo.QuestionVO;
import com.javasampleapproach.angularjs.vo.AnswerVO;
import java.util.List;

public interface UserService {
	public String addQuestion(QuestionVO vo) throws Exception;

	public String addAnswer(AnswerVO vo) throws Exception;

	public List<QuestionVO> getAllQuestion() throws Exception;
	
	public QuestionVO findByQuestionId(String id) throws Exception;

	public AnswerVO updateVote(String id,boolean isup) throws Exception;

}
