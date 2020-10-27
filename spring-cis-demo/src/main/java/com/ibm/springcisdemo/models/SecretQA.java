package com.ibm.springcisdemo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class SecretQA {

	@Id
	@Field(value = "UserId")
	public String userId;
	@Field(value = "Question")
	public String question;
	@Field(value = "Answer")
	public String answer;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
