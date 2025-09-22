<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<a href="<c:url value='/admin/user/add'/>">Add user</a>

<table border="1">
    <tr>
        <th>STT</th>
        <th>Username</th>
        <th>Email</th>
        <th>Role</th>
        <th>Status</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${listuser}" var="u" varStatus="st">
        <tr>
            <td>${st.index + 1}</td>
            <td>${u.username}</td>
            <td>${u.email}</td>
            <td>${u.role}</td>
            <td>${u.status == 1 ? "Active" : "Inactive"}</td>
            <td>
                <a href="<c:url value='/admin/user/edit?id=${u.userId}'/>">Edit</a> |
                <a href="<c:url value='/admin/user/delete?id=${u.userId}'/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</html>