<%@ page contentType="text/html; charset=UTF-8" %>
<form action="${pageContext.request.contextPath}/admin/video/update" method="post">
    <input type="hidden" name="videoid" value="${video.videoId}"/>
    Title: <input type="text" name="title" value="${video.title}"/><br/>
    Description: <textarea name="description">${video.description}</textarea><br/>
    Poster: <input type="text" name="poster" value="${video.poster}"/><br/>
    Views: <input type="number" name="views" value="${video.views}"/><br/>
    Active: 
    <input type="radio" name="active" value="1" ${video.active==1?"checked":""}/> Yes
    <input type="radio" name="active" value="0" ${video.active==0?"checked":""}/> No <br/>
    <input type="submit" value="Update"/>
</form>
