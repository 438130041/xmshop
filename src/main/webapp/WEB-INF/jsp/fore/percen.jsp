<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!doctype html>
<html lang="zh-CN" xml:lang="zh-CN">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<meta charset="UTF-8" />
	<title>个人中心</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=1226" />
	<meta name="description" content="" />
	<meta name="keywords" content="小米商城" />
</head>
<body>
<%@ include file="Top1.jsp"%>
<%@ include file="header.jsp"%>
<div class="boughtDiv">
	<div class="orderType">
		<div class="selectedOrderType"><a orderStatus="all" href="#nowhere">个人资料</a></div>
		<div><a  orderStatus="waitPay" href="shoppingCar">我的购物车</a></div>
		<div><a  orderStatus="waitConfirm" href="percens">我的订单</a></div>
		<div class="orderTypeLastOne"><a class="noRightborder">&nbsp;</a></div>
	</div>

</div>
<div class="container theme-showcase" role="main">
	<div class="row row-self page-container">
		<div class="col-md-4 col-md-offset-4">
			<div class="page-header">
				<br>
				<h2>个人资料</h2>
			</div>

			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">用户名</h3>
				</div>
				<div class="panel-body">
					${user.name}
				</div>
			</div>


			<div class="panel panel-default">
				<div class="panel-heading">用户密码</div>
				<div class="panel-body">
					${user.password}
				</div>
			</div>



			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">真实姓名</h3>
				</div>
				<div class="panel-body">
					${user.user_realname}
				</div>
			</div>



			<div class="panel panel-default">
				<div class="panel-heading">邮箱</div>
				<div class="panel-body">
					${user.email}
				</div>
			</div>


			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">手机号码</h3>
				</div>
				<div class="panel-body">
					${user.phone}
				</div>
			</div>


			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">性别</h3>
				</div>
				<div class="panel-body">
					${user.sex}
				</div>
			</div>


			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">地址</h3>
				</div>
				<div class="panel-body">
					${user.address}
				</div>
			</div>


			<div class="form-group  text-center">
				<%--<button type="submit" name="submit" class="btn btn-success" href="updateMyinfo.jsp">点我修改个人信息</button>--%>
				<a class="btn btn-success" href="foreupdate">点我修改个人信息</a>

			</div>

		</div>

	</div>
</div>
	<%@ include file="footer.jsp"%>

</body>
</html>
