<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="Top1.jsp"%>
<%@ include file="header.jsp"%>
<div class="container theme-showcase" role="main">
    <div class="row row-self page-container">
        <div class="col-md-4 col-md-offset-4">
            <div class="page-header">
                <br>
                <h2>会员信息修改页面</h2>
            </div>
            <form name="" id="" action="updateUser" method="post">

                <div class="form-group ">
                    <label for="tel">id</label>

                    <input name="id" type="hidden" class="form-control" onblur="ischeckNum()" id="id"  value="${user.id}">
                    <p>${user.id}</p>
                </div>

                <div class="form-group ">
                    <label for="username">用户名</label>
                    <input name="name" type="text" class="form-control" onblur="checkName()" id="username" value="${user.name}">
                    <p id="userInfo"></p>
                </div>

                <div class="form-group ">
                    <label for="password">新密码</label>
                    <input name="password" type="password" class="form-control"  id="password" value="${user.password}">
                    <p></p>
                </div>

                <div class="form-group ">
                    <label for="user_realname">真实姓名</label>
                    <input name="user_realname" type="text" class="form-control" onblur="checkName1()" id="user_realname" value="${user.user_realname}">
                    <p id="userInfo1"></p>
                </div>


                <div class="form-group ">
                    <label for="email">邮箱</label>
                    <input name="email" type="text" class="form-control" onblur="cheakMails()" id="email"  value="${user.email}">
                    <p id="emailInfo"></p>
                </div>


                <div class="form-group ">
                    <label for="tel">联系电话</label>
                    <input name="phone" type="text" class="form-control" onblur="ischeckNum()" id="tel"  value="${user.phone}">
                    <p id="phoneInfo"></p>
                </div>

                <div class="form-group ">
                    <label for="sex">性别</label>
                    <input name="sex" type="text" class="form-control" id="sex" onblur="checksex()" value="${user.sex}">
                    <p id="sexInfo"></p>
                </div>

                <div class="form-group ">
                    <label for="address">地址</label>
                    <input name="address" type="text" class="form-control" id="address" onblur="checkAddr()" value="${user.address}">
                    <p id="addrInfo"></p>
                </div>

                <hr>
                <div class="form-group  text-center">
                    <button type="submit" name="submit" class="btn btn-success">点我提交</button>
                </div>

            </form>
        </div>
    </div>
</div>




<%--<script src="${pageContext.request.contextPath}/js/jquery.js"></script>--%>

<%--<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>--%>


<script>

    function ischeckNum()
    {
        var num = document.getElementById("tel").value;
        if( num )
        {
            if( /^1[34578]\d{9}$/.test(num) )
            {
                document.getElementById("phoneInfo").innerHTML="手机号有效";
                return false;
            }
            else
            {
                document.getElementById("phoneInfo").innerHTML="请输入有效手机号";
                document.getElementById('tel').value="";
                document.getElementById('tel').focus();
                return false;
            }
        }
        else
        {
            document.getElementById("phoneInfo").innerHTML="请输入有效手机号";
            document.getElementById('tel').focus();
        }
    }



    function checkPassword(){

        var password=document.getElementById("password").value;
        var repassword=document.getElementById("repassword").value;

        if(password==""){
            document.getElementById("repasswordInfo").innerHTML="密码不能为空!";
            document.getElementById("password").focus();
            return false;
        }
        if(password==repassword){
            document.getElementById("repasswordInfo").innerHTML="密码一致";

        }else{
            document.getElementById("repasswordInfo").innerHTML="密码不一致";
        }


    }

    function checkName(){

        var username=document.getElementById("username").value;

        if(username==""){
            document.getElementById("userInfo").innerHTML="用戶名不能为空!";
            document.getElementById("username").focus();
            return false;
        }
    }
    function checkName1(){

        var user_realname=document.getElementById("user_realname").value;

        if(user_realname==""){
            document.getElementById("userInfo1").innerHTML="姓名不能为空!";
            document.getElementById("user_realname").focus();
            return false;
        }
    }
    function checkAddr() {

        var address = document.getElementById("address").value;

        if (address == "") {
            document.getElementById("addrInfo").innerHTML = "地址不能为空!";
            document.getElementById("address").focus();
            return false;
        }

        function checksex() {

            var sex = document.getElementById("sex").value;

            if (sex == "") {
                document.getElementById("sexInfo").innerHTML = "性别不能为空!";
                document.getElementById("sex").focus();
                return false;
            }


            //邮箱
            function cheakMails() {
                var email = document.getElementById("email").value;
                var expr = /^([0-9]|[a-z])+@([0-9]|[a-z])+(\.[c][o][m])$/i;
                if (!expr.test(email)) {
                    document.getElementById("emailInfo").innerHTML = "输入的邮箱格式有误";
                    document.getElementById("email").focus();
                    return false;
                }


            }
        }
    }

    <%@ include file="footer.jsp"%>

</script>