package com.javasampleapproach.angularjs.vo;

public class AnswerVO {
	String id;
	String ans;
	int vote;
	QuestionVO question;
	UserVO user;
	String updatedAt;
	String quesId;
	String username;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public QuestionVO getQuestion() {
		return question;
	}

	public void setQuestion(QuestionVO question) {
		this.question = question;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}
	

	public String getQuesId() {
		return quesId;
	}

	public void setQuesId(String quesId) {
		this.quesId = quesId;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
