<%--
  Created by IntelliJ IDEA.
  User: wanhuo
  Date: 2017-5-14
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://" +request.getServerName()+":" +request.getServerPort()+path+"/" ;
%>
<base href="<%=basePath%>" >
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<h1>用户注册</h1>
<form action="<%=basePath%>register" method="post">
    用户昵称：<input type="text" name="name"><br>
    用户账号：<input type="text" name="account"><br>
    用户密码：<input type="password" name="password"><br>
   <br>
    <input type="submit" value="注册">
</form>
</body>
</html>
