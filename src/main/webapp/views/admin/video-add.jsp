<%@ page contentType="text/html; charset=UTF-8" %>
<form action="${pageContext.request.contextPath}/admin/video/insert" method="post">
    Video ID: <input type="text" name="videoid"/><br/>
    Title: <input type="text" name="title"/><br/>
    Description: <textarea name="description"></textarea><br/>
    Poster: <input type="text" name="poster"/><br/>
    Views: <input type="number" name="views" value="0"/><br/>
    Active: 
    <input type="radio" name="active" value="1" checked/> Yes
    <input type="radio" name="active" value="0"/> No <br/>
    <input type="submit" value="Insert"/>
</form>
