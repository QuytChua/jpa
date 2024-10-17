<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<a href="${pageContext.request.contextPath}/video/add">Add Video</a>


<form action="${pageContext.request.contextPath}/video/search" method="get">
    <input type="text" name="name" placeholder="Search">
    <button type="submit">Tìm kiếm</button>
</form>


<table style="border: 1px solid black; width: 100%;">
    <tr>
        <th>STT</th>
        <th>Poster</th>
        <th>Title</th>
        <th>Views</th>
        <th>Status</th>
        <th>Category ID</th>
        <th>Video ID</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${listVideos}" var="video" varStatus="STT">
        <tr class="odd gradeX">
            <td>${STT.index + 1}</td>
            <td>
                <video height="150" width="200" controls>
                    <c:if test="${video.poster.substring(0, 5) == 'https'}">
                        <source src="${video.poster}" type="video/mp4">
                    </c:if>
                    <c:if test="${video.poster.substring(0, 5) != 'https'}">
                        <source src="${pageContext.request.contextPath}/video?fname=${video.poster}" type="video/mp4">
                    </c:if>
                    Your browser does not support the video tag.
                </video>
            </td>
            <td>${video.title}</td>
            <td>${video.views}</td>
            <td>${video.active == 1 ? 'Active' : 'Inactive'}</td>
            <td>${video.category.categoryId}</td> <!-- Hiển thị categoryId -->
            <td>${video.videoId}</td> <!-- Hiển thị videoId -->
            <td>
                <a href="<c:url value='/video/edit?id=${video.videoId}'/>">Edit</a>
                | 
                <a href="<c:url value='/video/delete?id=${video.videoId}'/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>