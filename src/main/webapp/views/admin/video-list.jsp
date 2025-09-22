<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<a href="<c:url value='/admin/video/add'/>">Add video</a>

<table border="1">
    <tr>
        <th>STT</th>
        <th>VideoId</th>
        <th>Title</th>
        <th>Views</th>
        <th>Active</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${listvideo}" var="v" varStatus="st">
        <tr>
            <td>${st.index + 1}</td>
            <td>${v.videoId}</td>
            <td>${v.title}</td>
            <td>${v.views}</td>
            <td>${v.active==1?"Yes":"No"}</td>
            <td>
                <a href="<c:url value='/admin/video/edit?id=${v.videoId}'/>">Edit</a> |
                <a href="<c:url value='/admin/video/delete?id=${v.videoId}'/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
