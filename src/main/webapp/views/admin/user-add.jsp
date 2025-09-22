<%@ page contentType="text/html; charset=UTF-8" %>
<form action="${pageContext.request.contextPath}/admin/user/insert" method="post">
    Username: <input type="text" name="username"/><br/>
    Password: <input type="password" name="password"/><br/>
    Email: <input type="email" name="email"/><br/>
    Role: <input type="text" name="role" value="user"/><br/>
    Status: 
    <input type="radio" name="status" value="1" checked/> Active
    <input type="radio" name="status" value="0"/> Inactive <br/>
    <input type="submit" value="Insert"/>
</form>
