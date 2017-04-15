package com.roden.spring.mvc.controller;

import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

public class JsonView implements View {
	private String json;

	public JsonView(String json) {
		this.json = json;
	}
	@Override
	public String getContentType() {
		return "text/plain;charset=UTF-8";
	}
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType(this.getContentType());
		PrintWriter out = response.getWriter();
		out.write(this.json);
		out.close();
	}
}
