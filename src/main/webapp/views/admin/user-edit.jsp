<%@ page contentType="text/html; charset=UTF-8" %>
<form action="${pageContext.request.contextPath}/admin/user/update" method="post">
    <input type="hidden" name="userid" value="${user.userId}"/>
    Username: <input type="text" name="username" value="${user.username}"/><br/>
    Password: <input type="password" name="password" value="${user.password}"/><br/>
    Email: <input type="email" name="email" value="${user.email}"/><br/>
    Role: <input type="text" name="role" value="${user.role}"/><br/>
    Status: 
    <input type="radio" name="status" value="1" ${user.status==1?"checked":""}/> Active
    <input type="radio" name="status" value="0" ${user.status==0?"checked":""}/> Inactive <br/>
    <input type="submit" value="Update"/>
</form>
