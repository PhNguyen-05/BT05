<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h2>Edit Category</h2>

<form action="${pageContext.request.contextPath}/admin/category/update" 
      method="post" enctype="multipart/form-data">

    <input type="hidden" name="categoryid" value="${cate.categoryId}"/>

    <label>Category name:</label><br/>
    <input type="text" name="categoryname" value="${cate.categoryName}"/><br/>

    <label>Current Image:</label><br/>
    <c:if test="${not empty cate.images}">
        <img id="preview" src="${pageContext.request.contextPath}/image?fname=${cate.images}" 
             width="200" height="150"/>
    </c:if>
    <br/>
    <label>Upload New Image:</label><br/>
    <input type="file" name="images" accept="image/*" onchange="previewFile(this)"/><br/>

    <p>Status:</p>
    <input type="radio" name="status" value="1" ${cate.status==1?"checked":""}/>Active
    <input type="radio" name="status" value="0" ${cate.status==0?"checked":""}/>Inactive<br/>

    <input type="submit" value="Update"/>
</form>

<script>
function previewFile(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            document.getElementById('preview').src = e.target.result;
        }
        reader.readAsDataURL(input.files[0]);
    }
}
</script>
