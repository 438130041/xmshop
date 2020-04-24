

<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@include file="header.jsp"%>
<%@include file="Top1.jsp"%>

<script>
    $(function(){

        <c:if test="${!empty msg}">
        $("span.errorMessage").html("${msg}");
        $("div.registerErrorMessageDiv").css("visibility","visible");
        </c:if>
        $(".registerForm").submit(function(){
            if(0==$("#name").val().length){
                $("span.errorMessage").html("请输入用户名");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if(0==$("#password").val().length){
                $("span.errorMessage").html("请输入密码");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if(0==$("#repeatpassword").val().length){
                $("span.errorMessage").html("请输入重复密码");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if($("#password").val() !=$("#repeatpassword").val()){
                $("span.errorMessage").html("重复密码不一致");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if(0==$("#uname").val().length){
                $("span.errorMessage").html("请输入真实姓名");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if(0==$("#maii").val().length){
                $("span.errorMessage").html("请输入邮箱");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if(0==$("#phone").val().length){
                $("span.errorMessage").html("请输入手机号码");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if(0==$("#adds").val().length){
                $("span.errorMessage").html("请输入地址");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            return true;
        });
    })
    //邮箱
  /*  function cheakMails() {
        var mails = document.getElementById("maii").value;
        var expr = /^([0-9]|[a-z])+@([0-9]|[a-z])+(\.[c][o][m])$/i;
        if (!expr.test(mails)) {
            document.getElementById("mailId").innerHTML = "输入的邮箱格式有误";
            return false;
        } else {
            document.getElementById("mailId").innerHTML = "";
            return true;
        }
    }
    //手机号
    function checkPhone() {
        // 利用正则表达式对输入数据匹配
        var id = document.getElementById("phone").value;
//匹配到一个非数字字符，则返回false
        var expr = /\D/i;
        if (expr.test(id)) {
            document.getElementById("phoneId").innerHTML = "不能输入非数字字符";
            return false;
        }
        else {
            document.getElementById("phoneId").innerHTML = "";
            return true;
        }
    }*/
</script>
<form method="post" action="register" class="registerForm">
    <div class="registerDiv">
        <div class="registerErrorMessageDiv">
            <div class="alert alert-danger" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                <span class="errorMessage"></span>
            </div>
        </div>


        <table class="registerTable" align="center">
            <tr>
                <td  class="registerTip registerTableLeftTD">设置会员名</td>
                <td></td>
            </tr>
            <tr>
                <td class="registerTableLeftTD">登陆名</td>
                <td  class="registerTableRightTD"><input id="name" name="name" placeholder="会员名一旦设置成功，无法修改" > </td>
            </tr>
            <tr>
                <td  class="registerTip registerTableLeftTD">设置登陆密码</td>
                <td  class="registerTableRightTD">登陆时验证，保护账号信息</td>
            </tr>
            <tr>
                <td class="registerTableLeftTD">登陆密码</td>
                <td class="registerTableRightTD"><input id="password" name="password" type="password"  placeholder="设置你的登陆密码" > </td>
            </tr>
            <tr>
                <td class="registerTableLeftTD">密码确认</td>
                <td class="registerTableRightTD"><input id="repeatpassword" type="password"   placeholder="请再次输入你的密码" > </td>
            </tr>
            <tr>
                <td class="registerTableLeftTD">真实姓名</td>
                <td class="registerTableRightTD"><input  type="text"   placeholder="请输入您的真实姓名" id="uname"
                                                 name="user_realname"       onblur="checkUname()" class="inputWidth"/>
                    <div id="unameId" class="red"></div> </td>
            </tr>
            <tr>
                <td class="registerTableLeftTD">邮箱</td>
                <td class="registerTableRightTD"><input type="text"   placeholder="请输入邮箱" id="maii"
                                                name="email"        class="inputWidth"     onblur="cheakMails()"/>
                    <div id="mailId" class="red"></div>
                </td>
            </tr>
            <tr>
                <td class="registerTableLeftTD">手机号码</td>
                <td class="registerTableRightTD"><input id="phone" type="text"   placeholder="请输入手机号码"
                                           name="phone"             onblur="checkPhone()" class="inputWidth" maxlength="11"/>
                    <div id="phoneId" class="red"></div> </td>
            </tr>
            <tr>
                <td class="registerTableLeftTD">性别</td>
                <td><input type="radio" value="男" name="sex" checked/>男
                    <input
                            type="radio" value="女" name="sex"/>女
                </td>
            </tr>
            <tr>
                <td class="registerTableLeftTD">地址</td>
                <td class="registerTableRightTD"><input  type="text"   placeholder="请输入地址"
                                       name="address"                 id="adds"  onblur="checkAdds()" class="inputWidth" />
                    <div id="addsId" class="red"></div> </td>
            </tr>

            <tr>
                <td colspan="2" class="registerButtonTD">
                    <a href="registerSuccess.jsp"><button>注 册</button></a>
                </td>
            </tr>
        </table>
    </div>
</form>