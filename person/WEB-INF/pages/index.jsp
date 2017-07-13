<%--
  Created by IntelliJ IDEA.
  User: ahuo
  Date: 17-5-25
  Time: 上午11:02
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
    <title>阿火讲故事4</title>
  </head>
  <body>
  <img src="<%=basePath%>static/img/hk.jpg" width="100%">
  hello_hk+<%=basePath%>
  </body>
</html>
