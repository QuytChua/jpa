<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<a href="${pageContext.request.contextPath}/category/add">Add
	Category</a>
<table style="border: 1px solid black; width: 100%;">
	<tr>
		<th>STT</th>
		<th>Images</th>
		<th>Category Name</th>
		<th>Status
		<th>Action</th>
	</tr>
	<c:forEach items="${listcategory}" var="cate" varStatus="STT">
		<tr class="odd gradeX">
			<td>${STT.index+1 }</td>
			<c:if test="${cate.images.substring(0,5)== 'https' }">
				<c:url value="${cate.images}" var="imgUrl"></c:url>

			</c:if>
			<c:if test="${cate.images.substring(0,5)!='https' }">
				<c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>

			</c:if>

			<td><img height="150" width="200" src="${imgUrl}" /></td>
			<td>${cate.categoryname}</td>
			<td><c:if test="${cate.status == 1}">
        	Hoạt động
    </c:if> <c:if test="${cate.status != 1}">
       		Khóa
    </c:if></td>

			<td>Image URL: ${imgUrl}</td>
			<td><a
				href="<c:url value='/category/edit?id=${cate.categoryId}'/>">Sửa</a>
				| <a
				href="<c:url value='/category/delete?id=${cate.categoryId}'/>">Xóa</a>
			</td>
		</tr>
	</c:forEach>
</table>