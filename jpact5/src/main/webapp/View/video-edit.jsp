<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<form action="<c:url value='/video/update'/>" method="post" enctype="multipart/form-data">
    <input type="text" id="videoId" name="videoId" value="${vid.videoId}" hidden="hidden"><br>
    
    <label for="title">Title:</label><br>
    <input type="text" id="title" name="title" value="${vid.title}" required><br>

    <label for="description">Description:</label><br>
    <textarea id="description" name="description" required>${vid.description}</textarea><br>

    <label for="poster">Poster Image:</label><br>
    <input type="file" id="poster" name="poster"><br>

       <div>
    <label for="category">Category:</label>
    <select id="category" name="categoryId" required>
        <c:forEach items="${listcate}" var="cate">
            <option value="${cate.categoryId}">
                <c:out value="${cate.categoryname}"/>
            </option>
        </c:forEach>
    </select>
</div>

<div>
    <label>Video:</label><br>
    <video height="150" width="200" controls>
        <c:if test="${vid.poster.startsWith('https')}">
            <source src="${vid.poster}" type="video/mp4">
        </c:if>
        <c:if test="${!vid.poster.startsWith('https')}">
            <source src="${pageContext.request.contextPath}/video?fname=${vid.poster}" type="video/mp4">
        </c:if>
        Your browser does not support the video tag.
    </video>
</div>
    <br>

    <label for="views">Views:</label><br>
    <input type="number" id="views" name="views" value="${vid.views}" min="0"><br>

    <p>Status:</p>
    <input type="radio" id="active" name="status" value="1" <c:if test="${vid.active}">checked</c:if>>
    <label for="active">Active</label><br>
    <input type="radio" id="inactive" name="status" value="0" <c:if test="${!vid.active}">checked</c:if>>
    <label for="inactive">Inactive</label><br>

    <input type="submit" value="Update">
</form>