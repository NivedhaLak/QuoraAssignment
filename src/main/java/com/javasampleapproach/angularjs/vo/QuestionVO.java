package com.javasampleapproach.angularjs.vo;

import java.util.List;

import javax.persistence.Column;

public class QuestionVO {
	String id;
	String qust;
	String tags;
	UserVO user;
	List<AnswerVO> answer;
	String description;
	String updatedAt;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQust() {
		return qust;
	}

	public void setQust(String qust) {
		this.qust = qust;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public List<AnswerVO> getAnswer() {
		return answer;
	}

	public void setAnswer(List<AnswerVO> answer) {
		this.answer = answer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

}
