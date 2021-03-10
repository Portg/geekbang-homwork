<head>
    <jsp:directive.include file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
    <title>注册</title>
    <style type="text/css">
        h1{text-align:center;}
        h4{text-align:right;color:red;}
    </style>
</head>
<script type="text/javascript">
    $(document).ready(function(){
        $("#form1").submit(function() {
            var name=$("#name").val();//获取提交的值
            if(name.length==0){//进行判断，如果获取的值为0那么提示账号不能为空
                $("#nameError").html("账号不能为空");
                    return false;
             }
             //密码进行验证不能为空
            var password=$("#password").val();//获取提交的密码的值
            if(password.length==0){
                $("#passwordError").html("密码不能为空");
                    return false;
            }
        });
    });
</script>
</head>
<body>
    <form action="register" method="post" id="form1">
    <h1>用户注册页面</h1>
    <hr/>
    <table align="center">
        <tr>
            <td>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
            <td>
                <input type="text" name="name" id="name"/>
                <div id="nameError" style="display:inline;color:red;"></div>
            </td>
        </tr>
        <tr>
            <td>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
            <td>
                <input type="password" name="password" id="password">
                <div id="passwordError" style="display:inline;color:red;"></div>
            </td>
        </tr>
<%--        <tr>--%>
<%--            <td>确认密码：</td>--%>
<%--            <td>--%>
<%--                <input type="password" name="relpassword" id="relpassword">--%>
<%--                <div id="relpasswordError" style="display:inline;color:red;"></div>--%>
<%--            </td>--%>
<%--        </tr>--%>
        <tr>
            <td>电话号码：</td>
            <td><input type="text" name="phoneNumber" id="phoneNumber"></td>
        </tr>
        <tr>
            <td>电子邮件：</td>
            <td><input type="text" name="email" id="email"></td>
        </tr>
        <tr>
            <td colspan="1"></td>
            <td>
                <input type="submit" value="注册"/>
                <input type="reset" value="重置"/>
                <a href="login-form.jsp" target="_blank">登陆</a>
            </td>
        </tr>
    </table>
    </form>
</body>
