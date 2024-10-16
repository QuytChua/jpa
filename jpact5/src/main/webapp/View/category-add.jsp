<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="${pageContext.request.contextPath}/category/insert" method="post" enctype="multipart/form-data">
  <label for="categoryname">Category name:</label><br>
  <input type="text" id="categoryname" name="categoryname" required><br>
  
  <label for="images">Images:</label><br>
  <input type="file" id="images" name="images"  required><br>
  
<input type="radio" id="ston" name="status" value="1">
<label for="css">Hoạt động</label>
<br>
<input type="radio" id="stoff" name="status" value="0">
<label for="javascript">Khóa</label>
  <br>
  <input type="submit" value="Submit">
</form>