<%@ page contentType="text/html; charset=UTF-8" %>
<form action="${pageContext.request.contextPath}/admin/video/insert" method="post" enctype="multipart/form-data">
    Video ID: <input type="text" name="videoid"/><br/>
    Title: <input type="text" name="title"/><br/>
    Description: <textarea name="description"></textarea><br/>
    Poster (image URL or file name): <input type="text" name="poster"/><br/>
    Views: <input type="number" name="views" value="0"/><br/>
    Active: 
    <input type="radio" name="active" value="1" checked/>Yes
    <input type="radio" name="active" value="0"/>No<br/>

    Upload Video: <input type="file" name="videofile" accept="video/mp4"/><br/>

    <input type="submit" value="Insert"/>
</form>
