<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h1>Admin Dashboard</h1>

<h2>Users</h2>
<a href="<c:url value='/admin/user/add'/>">Add User</a>
<table border="1">
    <tr>
        <th>ID</th><th>Username</th><th>Email</th><th>Role</th><th>Status</th><th>Action</th>
    </tr>
    <c:forEach items="${listuser}" var="u">
        <tr>
            <td>${u.userId}</td>
            <td>${u.username}</td>
            <td>${u.email}</td>
            <td>${u.role}</td>
            <td>${u.status==1?"Active":"Inactive"}</td>
            <td>
                <a href="<c:url value='/admin/user/edit?id=${u.userId}'/>">Edit</a> |
                <a href="<c:url value='/admin/user/delete?id=${u.userId}'/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<hr/>

<h2>Categories</h2>
<a href="<c:url value='/admin/category/add'/>">Add Category</a>
<table border="1">
    <tr>
        <th>ID</th><th>Name</th><th>Image</th><th>Status</th><th>Action</th>
    </tr>
    <c:forEach items="${listcate}" var="c">
        <tr>
            <td>${c.categoryId}</td>
            <td>${c.categoryName}</td>
            <td><img src="${pageContext.request.contextPath}/image?fname=${c.images}" width="80"/></td>
            <td>${c.status==1?"Active":"Inactive"}</td>
            <td>
                <a href="<c:url value='/admin/category/edit?id=${c.categoryId}'/>">Edit</a> |
                <a href="<c:url value='/admin/category/delete?id=${c.categoryId}'/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<hr/>

<h2>Videos</h2>
<a href="<c:url value='/admin/video/add'/>">Add Video</a>
<table border="1">
    <tr>
        <th>ID</th><th>Title</th><th>Video</th><th>Views</th><th>Status</th><th>Action</th>
    </tr>
    <c:forEach items="${listvideo}" var="v">
        <tr>
            <td>${v.videoId}</td>
            <td>${v.title}</td>
            <td>
                <c:if test="${not empty v.videoFile}">
                    <video width="200" controls>
                        <source src="${pageContext.request.contextPath}/video-file?fname=${v.videoFile}" type="video/mp4"/>
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
