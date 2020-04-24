<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/19
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="adminHeader.jsp"%>
<title>编辑用户</title>
<script>
    $(function() {
        $("#editForm").submit(function() {
            if (!checkInt("id", "id"))
                return false;
            if (!checkEmpty("user_name", "用户名"))
                return false;
			if (!checkEmpty("user_password", "密码"))
			return false;
            if (!checkEmpty("user_realname", "真实姓名"))
                return false;
            if (!checkEmpty("email", "邮箱"))
                return false;
            if (!checkNumber("phone", "手机号码"))
                return false;
            if (!checkEmpty("sex", "性别"))
                return false;
            if (!checkEmpty("address", "地址"))
                return false;
            return true;
        });
    });
</script>

<div class="workingArea">
    <div class="panel panel-warning editDiv">
        <div class="panel-heading">编辑用户</div>
        <div class="panel-body">
            <form method="post" id="editForm" action="admin_updateUser">
                <table class="editTable">
                    <tr>
                        <td>id</td>
                        <td><input id="id" name="id" value="${user.id}"
                                   type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>用户名</td>
                        <td><input id="user_name" name="name" value="${user.name}"
                                   type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>密码</td>
                        <td><input id="user_password" name="password" type="password"
                                   value="${user.password}"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>真实姓名</td>
                        <td><input id="user_realname" value="${user.user_realname}" name="user_realname" type="text"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>邮箱</td>
                        <td><input id="email"  value="${user.email}" name="email" type="text"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>手机号码</td>
                        <td><input id="phone"  value="${user.phone}" name="phone" type="text"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>性别</td>
                        <td><input id="sex"  value="${user.sex}" name="sex" type="text"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>地址</td>
                        <td><input id="address"  value="${user.address}" name="address" type="text"
                                   class="form-control"></td>
                    </tr>

                    <tr class="submitTR">
                            <button type="submit" class="btn btn-success">提 交</button></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
