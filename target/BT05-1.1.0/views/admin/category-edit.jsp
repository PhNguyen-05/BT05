<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<form action="<c:url value='/admin/category/update'/>" method="post" enctype="multipart/form-data">
    <input type="text" id="categoryid" name="categoryid" hidden="hidden" value="${cate.categoryId}" />
    
    <label for="fname">Category name:</label><br>
    <input type="text" id="categoryname" name="categoryname" value="${cate.categoryName}" /><br>
    
    <label for="lname">Images:</label><br>
    <c:if test="${cate.images.substring(0, 5) == 'https'}">
        <c:url value="${cate.images}" var="imgUrl"/>
    </c:if>
    <c:if test="${cate.images.substring(0, 5) != 'https'}">
        <c:url value='/image?fname=${cate.images}' var="imgUrl"/>
    </c:if>
    
    <img id="imagess" height="150" width="200" src="${imgUrl}" />
    <input type="file" onchange="chooseFile(this)" id="images" name="images" /><br>
    
    <p>Status:</p>
    <input type="radio" id="ston" name="status" value="1" ${cate.status==1?'checked':''} />
    <label for="ston">Đang hoạt động</label><br>
    
    <input type="radio" id="stoff" name="status" value="0" ${cate.status!=1?'checked':''} />
    <label for="stoff">Khóa</label><br>
    
    <input type="submit" value="Update" />
</form>

<script>
function chooseFile(fileInput) {
    if (fileInput.files && fileInput.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            document.getElementById('imagess').src = e.target.result;
        }
        reader.readAsDataURL(fileInput.files[0]);
    }
}
</script>