<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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

    <c:forEach items="${listcate}" var="cate" varStatus="STT">
        <tr>
            <td>${STT.index + 1}</td>

            <td>
                <c:if test="${cate.images.substring(0, 5) == 'https'}">
                    <c:url value="${cate.images}" var="imgUrl" />
                </c:if>
                <c:if test="${cate.images.substring(0, 5) != 'https'}">
                    <c:url value="/image?fname=${cate.images}" var="imgUrl" />
                </c:if>
                <img src="${imgUrl}" width="100" height="100" />
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