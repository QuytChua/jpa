<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="jakarta.tags.core" %>
    <form action="<c:url value='/category/update'/>" method="post" enctype="multipart/form-data">>
        <input type="text" id="categoryid" name="categoryid" value="${cate.categoryId}" hidden="hidden"><br>
    
    <label for="categoryname">Category name:</label><br>
    <input type="text" id="categoryname" name="categoryname" value="${cate.categoryname}"><br>

    <label for="images">Images:</label><br>
    <input type="file" id="images" name="images" value="${cate.images}" >
    
    <c:if test="${cate.images.substring(0,5) == 'https'}">
        <c:url value="${cate.images}" var="imgUrl"></c:url>
    </c:if>
    <c:if test="${cate.images.substring(0,5) != 'https'}">
        <c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
    </c:if>
    <br>
    <td><img height="150" width="200" src="${imgUrl}" /></td>

    <p>Status:</p>
    <input type="radio" id="ston" name="status" value="1" <c:if test="${cate.status == 1}">checked</c:if>>
    <label for="ston">Đang hoạt động</label><br>
    <input type="radio" id="stoff" name="status" value="0" <c:if test="${cate.status == 0}">checked</c:if>>
    <label for="stoff">Khóa</label><br>

    <input type="submit" value="Update">
</form>