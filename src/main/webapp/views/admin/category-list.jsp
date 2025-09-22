<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<a href="<c:url value='/admin/category/add'/>">Add category</a>

<table border="1">
    <tr>
        <th>STT</th>
        <th>Images</th>
        <th>Category Name</th>
        <th>Status</th>
        <th>Action</th>
    </tr>

    <c:if test="${empty listcate}">
        <tr>
            <td colspan="5">No categories found.</td>
        </tr>
    </c:if>

    <c:forEach items="${listcate}" var="cate" varStatus="st">
        <tr>
            <td>${st.index + 1}</td>

            <td>
                <c:choose>
                    <c:when test="${not empty cate.images and fn:startsWith(cate.images,'https')}">
                        <img src="${cate.images}" width="120" height="120" />
                    </c:when>
                    <c:when test="${not empty cate.images}">
                    
                        <img src="${pageContext.request.contextPath}/image?fname=${cate.images}" 
                             width="120" height="120"/>
                    </c:when>
                    <c:otherwise>
                        <span>No image</span>
                    </c:otherwise>
                </c:choose>
            </td>

            <td>${cate.categoryName}</td>
            <td>${cate.status}</td>

            <td>
                <a href="<c:url value='/admin/category/edit?id=${cate.categoryId}'/>">Edit</a> |
                <a href="<c:url value='/admin/category/delete?id=${cate.categoryId}'/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>