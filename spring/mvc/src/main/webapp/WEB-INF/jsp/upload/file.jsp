<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<c:forEach items="${files}" var="file" varStatus="vs">
    ${file.fileName}----${file.fileSize}----${file.fileType}----${file.path}
        <a href="<%=request.getContextPath()%>/file/get/${vs.index+1}">下载</a>
    <br/>  
</c:forEach>  
<br/>
<form action="<%=request.getContextPath()%>/file/add" method="POST" enctype="multipart/form-data">
    参数: <input type="text" name="name"/><br/>
    文件1: <input type="file" name="myfiles"/><br/>
    文件2: <input type="file" name="myfiles"/><br/>
    文件3: <input type="file" name="myfiles"/><br/>
    <input type="submit" value="上传"/>
</form>
