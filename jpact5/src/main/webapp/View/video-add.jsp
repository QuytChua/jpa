<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath}/video/insert" method="post" enctype="multipart/form-data">
    <label for="videoId">Video ID:</label><br>
    <input type="text" id="videoId" name="videoId" required><br>
    
    <label for="title">Title:</label><br>
    <input type="text" id="title" name="title" required><br>

    <label for="description">Description:</label><br>
    <textarea id="description" name="description" required></textarea><br>
    
    <label for="poster">Poster Image:</label><br>
    <input type="file" id="poster" name="poster" required><br>
    
    <label for="views">Views:</label><br>
    <input type="text" id="views" name="views" value="0" min="0"><br>
    
    
<div>
    <label for="category">Category:</label>
    <select id="category" name="categoryId" required>
        <c:forEach items="${listcate}" var="cate">
  <option value="${cate.categoryId}"><c:out value="${cate.categoryname}"/></option>
</c:forEach>
    </select>
</div>

    <label>Status:</label><br>
    <input type="radio" id="active" name="status" value="1" checked>
    <label for="active">Active</label><br>
    
    <input type="radio" id="inactive" name="status" value="0">
    <label for="inactive">Inactive</label><br>

    <input type="submit" value="Submit">
</form>