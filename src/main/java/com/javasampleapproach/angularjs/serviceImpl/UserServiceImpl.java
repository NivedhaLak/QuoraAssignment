package com.javasampleapproach.angularjs.serviceImpl;

import com.javasampleapproach.angularjs.vo.AnswerVO;
import com.javasampleapproach.angularjs.vo.QuestionVO;
import com.javasampleapproach.angularjs.vo.UserVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javasampleapproach.angularjs.repository.QuestionRepository;
import com.javasampleapproach.angularjs.repository.UserRepository;
import com.javasampleapproach.angularjs.repository.AnswerRepository;
import com.javasampleapproach.angularjs.service.UserService;
import com.javasampleapproach.angularjs.dao.Question;
import com.javasampleapproach.angularjs.dao.User;
import com.javasampleapproach.angularjs.dao.Answer;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AnswerRepository answerRepository;

	@Override
	public String addQuestion(QuestionVO vo) throws Exception {
		Question question = new Question();
		question.setQust(vo.getQust());
		question.setTags(vo.getTags());
		question.setDescription(vo.getDescription());
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		question.setUpdatedAt(dt.format(Calendar.getInstance().getTime()));
		if (vo.getUser() != null && vo.getUser().getId() != null && !vo.getUser().getId().isEmpty()) {
			Long userId = new Long(vo.getUser().getId());
			User user = userRepository.findOne(userId);
			question.setUser(user);
		}
		questionRepository.save(question);
		return "Success";
	}

	@Override
	public String addAnswer(AnswerVO vo) throws Exception {
		Answer answer = new Answer();
		answer.setId(new Long(vo.getId()));
		answer.setAns(vo.getAns());
		answer.setVote(vo.getVote());
		// answer.setUpdatedAt(Calendar.getInstance().toString() );
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		answer.setUpdatedAt(dt.format(Calendar.getInstance().getTime()));
		if (vo.getQuesId() != null && !vo.getQuesId().isEmpty()) {
			Question question = questionRepository.findOne(new Long(vo.getQuesId()));
			answer.setQuestion(question);
		} else {
			throw new Exception("invalid question");
		}
		if (vo.getUser() != null && vo.getUser().getId() != null && !vo.getUser().getId().isEmpty()) {
			Long userId = new Long(vo.getUser().getId());
			User user = userRepository.findOne(userId);
			answer.setUser(user);
		}
		answerRepository.save(answer);
		return "success";
	}

	@Override
	public List<QuestionVO> getAllQuestion() throws Exception {
		return createQuestionVO(questionRepository.findAll());
	}

	@Override
	public AnswerVO updateVote(String id, boolean isup) throws Exception {
		Answer answer = answerRepository.findOne(new Long(id));
		if (answer == null)
			throw new Exception("invalid answer");
		answer.setVote(isup ? (answer.getVote() + 1) : (answer.getVote() - 1));
		answerRepository.save(answer);
		return createAnswerVO(answer);
	}

	private List<QuestionVO> createQuestionVO(List<Question> question) {
		List<QuestionVO> list = new ArrayList();
		for (Question dao : question) {
			list.add(createQuestionVO(dao));
		}
		return list;

	}

	private QuestionVO createQuestionVO(Question question) {
		QuestionVO vo = new QuestionVO();
		vo.setId(question.getId() + "");
		vo.setQust(question.getQust());
		vo.setTags(question.getTags());
		vo.setDescription(question.getDescription());
		vo.setUpdatedAt(question.getUpdatedAt());
		if (question.getAnswers() != null && !question.getAnswers().isEmpty())
			vo.setAnswer(createAnswerVO(question.getAnswers()));
		if (question.getUser() != null)
			vo.setUser(createUserVO(question.getUser()));
		return vo;
	}

	private List<AnswerVO> createAnswerVO(List<Answer> answer) {
		List<AnswerVO> list = new ArrayList();
		for (Answer dao : answer) {
			list.add(createAnswerVO(dao));
		}
		return list;
	}

	private AnswerVO createAnswerVO(Answer answer) {
		AnswerVO vo = new AnswerVO();
		vo.setId(answer.getId().toString());
		vo.setAns(answer.getAns());
		vo.setVote(answer.getVote());
		vo.setVote(answer.getVote());
		vo.setUpdatedAt(answer.getUpdatedAt());
		vo.setUser(createUserVO(answer.getUser()));
		return vo;
	}

	private UserVO createUserVO(User user) {
		UserVO vo = new UserVO();
		if (user != null) {
			vo.setId(user.getId() + "");
			vo.setFirstName(user.getFirstName());
			vo.setLastName(user.getLastName());
			vo.setEmail(user.getEmail());
		}
		return vo;
	}

	@Override
	public QuestionVO findByQuestionId(String id) throws Exception {
		System.out.println(Long.parseLong(id) + "------" + id);
		return createQuestionVO(questionRepository.findById(Long.parseLong(id)));
	}

}
