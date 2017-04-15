package com.roden.spring.mvc.controller;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@Controller
@RequestMapping(value="/poi")
public class PoiController{	
	@RequestMapping(value = "/excel/export", method = RequestMethod.GET)
	@ResponseBody
	public void export(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="keywords",required=false, defaultValue="")String keywords) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();//声明一个工作薄		
		HSSFSheet sheet = workbook.createSheet("订单列表");// 生成一个表格
		String[] headers ={"姓名","手机号码"};
		HSSFRow row = sheet.createRow(0);//产生表格标题行
		for (int i = 0; i < headers.length; i++){
			HSSFCell cell = row.createCell(i);			
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}	
		
			row = sheet.createRow(1);
			int k=0;
			row.createCell(k++).setCellValue("雷锋");			
			row.createCell(k++).setCellValue("12345678910");
		
		String fileName ="订单列表.xls";	
		final String attachmentHeader = "Attachment; Filename*=utf-8''"+ URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");	
		response.reset();
		response.setHeader("Content-Disposition", attachmentHeader);
		response.setContentType("application/msexcel");
		//response.setContentType("application/x-msdownload");	
		//response.setContentType("application/vnd.ms-excel");    
		OutputStream out=response.getOutputStream();
		workbook.write(out);
		out.flush();
		out.close();
		response.flushBuffer();
		//window.location.assign("")加载
	}
}

