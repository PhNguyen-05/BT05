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
<%@ page contentType="text/html; charset=UTF-8" %>
<form action="${pageContext.request.contextPath}/admin/video/update" method="post" enctype="multipart/form-data">
    <input type="hidden" name="videoid" value="${video.videoId}"/>
    Title: <input type="text" name="title" value="${video.title}"/><br/>
    Description: <textarea name="description">${video.description}</textarea><br/>
    Poster: <input type="text" name="poster" value="${video.poster}"/><br/>
    Views: <input type="number" name="views" value="${video.views}"/><br/>
    Active: 
    <input type="radio" name="active" value="1" ${video.active==1?"checked":""}/>Yes
    <input type="radio" name="active" value="0" ${video.active==0?"checked":""}/>No<br/>

    <p>Current Video:</p>
    <c:if test="${not empty video.videoFile}">
        <video width="300" controls>
            <source src="${pageContext.request.contextPath}/video-file?fname=${video.videoFile}" type="video/mp4"/>
        </video>
    </c:if>
    <br/>
    Upload New Video: <input type="file" name="videofile" accept="video/mp4"/><br/>

    <input type="submit" value="Update"/>
</form>
