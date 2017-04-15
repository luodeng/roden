package com.roden.spring.mvc.controller.upload;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * SpringMVC中的文件上传
 * @see 第一步：由于SpringMVC使用的是commons-fileupload实现，故将其组件引入项目中
 * @see       这里用到的是commons-fileupload-1.3.2.jar和commons-io-2.5.jar
 * @see 第二步：在####-servlet.xml中配置MultipartResolver处理器。可在此加入对上传文件的属性限制
 * @see 第三步：在Controller的方法中添加MultipartFile参数。该参数用于接收表单中file组件的内容
 * @see 第四步：编写前台表单。注意enctype="multipart/form-data"以及<input type="file" name="****"/>
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

	public static LinkedList<FileMeta> files = new LinkedList<FileMeta>();
	 @RequestMapping()
	 public String list(Model model){
		 model.addAttribute("files",files);
		 return "upload/file";
	 }
	@RequestMapping("html5")
	public String html5(Model model){
		model.addAttribute("files",files);
		return "upload/html5";
	}
	@RequestMapping(value="/add", method= RequestMethod.POST)
	public String add(@RequestParam MultipartFile[] myfiles, HttpServletRequest request,String name) throws IOException{
		//如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
		//如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
		//并且上传多个文件时，前台表单中的所有<input type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件
		for(MultipartFile myfile : myfiles){
			if(myfile.isEmpty()){
				System.out.println("文件未上传");
			}else{
				System.out.println("文件长度: " + myfile.getSize());
				System.out.println("文件类型: " + myfile.getContentType());
				System.out.println("文件名称: " + myfile.getName());
				System.out.println("文件原名: " + myfile.getOriginalFilename());
				System.out.println("========================================");
				//如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/");
				//这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
				FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, myfile.getOriginalFilename()));
				//myfile.transferTo(new File(realPath, myfile.getOriginalFilename()));

				System.out.println(myfile.getOriginalFilename() +" uploaded! ");
				FileMeta fileMeta = new FileMeta();
				fileMeta.setFileName(myfile.getOriginalFilename());
				fileMeta.setFileSize(myfile.getSize()/1024+" Kb");
				fileMeta.setFileType(myfile.getContentType());
				fileMeta.setPath(realPath+myfile.getOriginalFilename());
				files.add(fileMeta);
			}
		}
		return "redirect:/file";
	}
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	@ResponseBody
	public Object upload(MultipartHttpServletRequest mhsr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//---------------------------------------------------------使用文件参数解析-----------------------------------------------------------------
		//	    <input type="file" name="file" />    要对应上name=file取文件
		//		单文件
		//		@RequestParam("file") MultipartFile file  
		//		多文件
		//  	@RequestParam("file") MultipartFile[] files	
		//		多文件	CommonsMultipartFile继承于MultipartFile使用方法相同
		//		@RequestParam("file") CommonsMultipartFile[] files		
		
		//-----------------------------------------------------从普通request中解析文件----------------------------------------------------------------	
		/*
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(commonsMultipartResolver.isMultipart(request)){
        	//request解析或者转型为MultipartHttpServletRequest
        	MultipartHttpServletRequest multipartHttpServletRequest =  commonsMultipartResolver.resolveMultipart(request);         	
        	//MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		    // 根据 name 获取上传的文件...  
		    MultipartFile multipartFile = multipartHttpServletRequest.getFile("file"); 
		    //保存文件
		    multipartFile.transferTo(new File(""));		   
	    } 
        */
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/");
		 Iterator<String> itr =  mhsr.getFileNames();
		 while(itr.hasNext()){
			 MultipartFile mpf = mhsr.getFile(itr.next());
			 try {
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(realPath+mpf.getOriginalFilename()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			 System.out.println(mpf.getOriginalFilename() +" uploaded! ");
			 FileMeta fileMeta = new FileMeta();
			 fileMeta.setFileName(mpf.getOriginalFilename());
			 fileMeta.setFileSize(mpf.getSize()/1024+" Kb");
			 fileMeta.setFileType(mpf.getContentType());
			 fileMeta.setPath(realPath+mpf.getOriginalFilename());
			 files.add(fileMeta);
		 }
		return files;
 
	}
	@RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
	public void get(HttpServletResponse response,@PathVariable String value){
		 FileMeta getFile = files.get(Integer.parseInt(value)-1);
		 try {
			 	response.setContentType(getFile.getFileType());
			 	response.setHeader("Content-disposition", "attachment; filename=\""+getFile.getFileName()+"\"");
			 	String  realPath=getFile.getPath().replaceAll("\\\\","/");
			 	File file=new File(realPath);
		        FileCopyUtils.copy(new FileInputStream(file),response.getOutputStream());
		 }catch (IOException e) {
				e.printStackTrace();
		 }
	 }
	public class FileMeta {
		private String fileName;
		private String fileSize;
		private String fileType;
		private String path;
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getFileSize() {
			return fileSize;
		}
		public void setFileSize(String fileSize) {
			this.fileSize = fileSize;
		}
		public String getFileType() {
			return fileType;
		}
		public void setFileType(String fileType) {
			this.fileType = fileType;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
	}
}
