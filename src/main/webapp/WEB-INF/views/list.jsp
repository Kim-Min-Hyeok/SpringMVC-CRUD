<%--
  Created by IntelliJ IDEA.
  User: minhyeokkim
  Date: 2022/12/02
  Time: 4:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>자유게시판</h1>
    <table id="list" width="90%">
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Writer</th>
            <th>Content</th>
            <th>Regdate</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${list}" var="u">
            <tr>
                <td>${u.getSeq()}</td>
                <td>${u.getTitle()}</td>
                <td>${u.getWriter()}</td>
                <td>${u.getContent()}</td>
                <td>${u.getRegdate()}</td>
                <td><a href="editform.jsp?id=${u.getSeq()}">Edit</a></td>
                <td><a href="javascript:delete_ok('${u.getSeq()}')">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <br/><a href="addpostform.jsp">Add New Post</a>
</body>
<script>
    function delete_Ok(id){
        var a = confirm("정말로 삭제하겠습니까?");
        if(a) location.href='deleteok/' + id;
    }
</script>
</html>
