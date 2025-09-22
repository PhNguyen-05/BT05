<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h2>Add Category</h2>

<form action="${pageContext.request.contextPath}/admin/category/insert" 
      method="post" enctype="multipart/form-data">

    <label>Category name:</label><br/>
    <input type="text" name="categoryname"/><br/>

    <label>Upload Image:</label><br/>
    <img id="preview" src="" width="200" height="150"/><br/>
    <input type="file" name="images" accept="image/*" onchange="previewFile(this)"/><br/>

    <p>Status:</p>
    <input type="radio" name="status" value="1" checked/>Active
    <input type="radio" name="status" value="0"/>Inactive<br/>

    <input type="submit" value="Insert"/>
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
