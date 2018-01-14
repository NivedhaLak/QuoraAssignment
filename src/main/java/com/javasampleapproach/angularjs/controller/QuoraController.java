package com.javasampleapproach.angularjs.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.angularjs.repository.QuestionRepository;
import com.javasampleapproach.angularjs.vo.AnswerVO;
import com.javasampleapproach.angularjs.vo.QuestionVO;
import com.javasampleapproach.angularjs.service.UserService;

@RestController
public class QuoraController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testWeb() {
		return "test web services";
	}

	@RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
	public String addQuestion(@RequestBody QuestionVO question) throws Exception {

		return userService.addQuestion(question);
	}

	@RequestMapping(value = "/addAnswer", method = RequestMethod.POST)
	public String addAnswer(@RequestBody AnswerVO vo) throws Exception {
		return userService.addAnswer(vo);
	}

	@RequestMapping(value = "/getAllQuestion", method = RequestMethod.GET)
	public List<QuestionVO> getAllQuestion() throws Exception {
		return userService.getAllQuestion();
	}
	@RequestMapping(value = "/getbyQuestionId/{id}", method = RequestMethod.GET)
	public QuestionVO getbyQuestionId(@PathVariable String id) throws Exception {
		return userService.findByQuestionId(id);
	}
	

	@RequestMapping(value = "/updateVote/{id}/{isup}", method = RequestMethod.GET)
	public AnswerVO updateVote(@PathVariable String id ,@PathVariable boolean isup) throws Exception {
		return userService.updateVote(id,isup);
	}
}