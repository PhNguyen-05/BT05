<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h2>Video Management</h2>

<form action="${pageContext.request.contextPath}/admin/videos" method="get">
    <input type="text" name="keyword" placeholder="Search by title" 
           value="${keyword != null ? keyword : ''}"/>
    <input type="submit" value="Search"/>
</form>

<a href="<c:url value='/admin/video/add'/>">Add video</a>

<table border="1">
    <tr>
        <th>STT</th>
        <th>VideoId</th>
        <th>Title</th>
        <th>Video</th>
        <th>Views</th>
        <th>Active</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${listvideo}" var="v" varStatus="st">
        <tr>
            <td>${st.index + 1}</td>
            <td>${v.videoId}</td>
            <td>${v.title}</td>
            <td>
                <c:if test="${not empty v.videoFile}">
                    <video width="200" height="150" controls>
                        <source src="${pageContext.request.contextPath}/video-file?fname=${v.videoFile}" type="video/mp4">
                    </video>
                </c:if>
            </td>
            <td>${v.views}</td>
            <td>${v.active==1?"Yes":"No"}</td>
            <td>
                <a href="<c:url value='/admin/video/edit?id=${v.videoId}'/>">Edit</a> |
                <a href="<c:url value='/admin/video/delete?id=${v.videoId}'/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
