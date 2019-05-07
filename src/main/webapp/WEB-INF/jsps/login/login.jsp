<%--
  Created by IntelliJ IDEA.
  User: zhangwenhao
  Date: 2019/5/7
  Time: 上午10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

This is login page!!!


<div id="main">
    <div class="newcontainer" id="course_intro">
        <form name="mainForm" action="<%= request.getContextPath()%>/login/dologin" method="post">
            <div>
                <span>用户账号:</span><input type="text" id="account" name="account">
            </div>
            <div>
                <span>账号密码:</span><input type="password" id="pwd" name="pwd">
            </div>

            <div>
                <input type="submit" id="btnPass" value="提交" />
            </div>
        </form>
    </div>
</div>

</body>
</html>
