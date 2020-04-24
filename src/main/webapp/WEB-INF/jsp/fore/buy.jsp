<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN" xml:lang="zh-CN">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" >
    <meta charset="UTF-8" />
    <title>填写订单信息</title>
    <meta name="viewport" content="width=1226" />
    <link rel="stylesheet" href="../../../css/bootstrap.min.css">
    <script src="../../../js/jquery-3.2.1.js"></script>
    <script src="../../../js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../../../css/order/base.min.css" />
    <link rel="stylesheet" type="text/css" href="../../../css/order/checkout.min.css" />
    <link rel="stylesheet" href="../../../css/index/common.css">
    <link rel="stylesheet" href="../../../css/index/index.css">

    <link href="<%=request.getContextPath()%>/js/jq-ui/jquery-ui.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/js/jq-ui/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jq-ui/jquery-ui.js"></script>

</head>
<body>
<div class="header-top">
    <div class="container clear">
        <div class="fl">
            <ul class="clear">
                <li class="fl navbar-nav" style="padding: 0"><a href="javascript:">小米商城</a> <span>|</span></li>
                <li class="fl navbar-nav"><a href="javascript:">MIII </a><span>|</span></li>
                <li class="fl navbar-nav"><a href="javascript:">我聊 </a> <span>|</span></li>
                <li class="fl navbar-nav"><a href="javascript:">游戏 </a><span>|</span></li>
                <li class="fl navbar-nav"><a href="javascript:">多看阅读 </a><span>|</span></li>
                <li class="fl navbar-nav"><a href="javascript:">云服务 </a><span>|</span></li>
                <li class="fl navbar-nav"><a href="javascript:">金融 </a><span>|</span></li>
                <li class="fl navbar-nav"><a href="javascript:">小米网移动版 </a><span>|</span></li>
                <li class="fl navbar-nav"><a href="javascript:">问题反馈 </a><span>|</span></li>
                <li class="fl navbar-nav"><a href="javascript:">Select Region</a></li>
            </ul>
        </div>
        <div class="fr">
            <%
                if (session.getAttribute("user")==null){
            %>
            <a href="registerPage" class="login" >注册</a> <span>|</span>
            <a href="loginPage" class="register">登陆</a><span>|</span>
            <%
            }else{
            %>
            欢迎你：${user.name}
            <div class="fr">
                <a href="logout1" class="register">注销</a>
                <a href="personalCenter" class="tz">个人中心</a>
            </div>
            <%
                }
            %>
            <a href="shoppingCar" class="cart">
                <i class="iconfont cart-mini">&#xe621;</i>
                购物车（<span class="carnum">0</span>）
                <div class="cart-menu">
                    <span class="carNull"></span>
                    <ul>
                    </ul>
                </div>
            </a>
        </div>
    </div>
</div>
</div>
<div class="header-navbar container">
    <div class="nav fl">
        <ul class="clear nav-item">
            <li class="fl "><a class="link" href="javascript:"><span class="text">小米手机</span></a></li>
            <li class="fl"><a class="link" href="javascript:"><span class="text">红米</span></a></li>
            <li class="fl"><a class="link" href="javascript:"><span class="text">平板 · 笔记本</span></a></li>
            <li class="fl"><a class="link" href="javascript:"><span class="text">电视</span></a></li>
            <li class="fl"><a class="link" href="javascript:"><span class="text">盒子 · 影音</span></a></li>
            <li class="fl"><a class="link" href="javascript:"><span class="text">路由器</span></a></li>
            <li class="fl"><a class="link" href="javascript:"><span class="text">智能硬件</span></a></li>
            <li class="fl"><a class="link" href="Service"><span class="text">服务</span></a></li>
            <!--  <li class="fl"><a class="link" href="community"><span class="text">社区</span></a></li> -->
        </ul>
    </div>
    <div class="fr clear nav-rt">
        <form class="search-form">
            <input type="text" name="userName" id="userName" id="search" class="search-text">

            <a href="javascript:" class="search-btn">
                <span class="iconfont center">搜</span>
            </a>


        </form>
    </div>
</div>
<form class="form-horizontal">
    <div class="form-group">
        <label for="inputPassword2" class="col-sm-2 control-label">收货地址</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="inputPassword2" placeholder="请填写详细收货地址">
        </div>
    </div>
    <div class="form-group">
        <label for="inputPassword3" class="col-sm-2 control-label">收货人姓名</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="inputPassword3" placeholder="请填写姓名">
        </div>
    </div>
    <div class="form-group">
        <label for="inputPassword4" class="col-sm-2 control-label">联系电话</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="inputPassword4" placeholder="请填写电话">
        </div>
    </div>
</form>
<!--<div class="form-group has-success">
    <label class="control-label" for="inputSuccess1">收获地址</label>
    <input type="text" class="form-control" id="inputSuccess1" aria-describedby="helpBlock2" height="50px">
    <span id="helpBlock2" class="help-block">请填写详细的收货地址</span>
</div>
<div class="form-group has-warning">
    <label class="control-label" for="inputWarning1">收货人姓名</label>
    <input type="text" class="form-control" id="inputWarning1"  height="50px">
</div>
<div class="form-group has-error">
    <label class="control-label" for="inputError1">联系电话</label>
    <input type="text" class="form-control" id="inputError1"  height="50px">
</div> -->
    <div class="fr">
        <a href="yu?moneys=${param.moneys}" class="btn btn-primary" id="J_checkoutToPay">去结算</a>
    </div>



<!--<div class="page-main">
    <div class="container">
        <div class="checkout-box">
            <div class="section section-address">
                <div class="address">
                    <div class="addressTip">输入收货地址</div>
                    <div>



                                <td class="firstColumn">详细地址<span class="redStar">*</span></td>

                                <td><textarea name="address" placeholder="建议您如实填写详细收货地址，例如接到名称，门牌好吗，楼层和房间号等信息" ></textarea></td>
                            </tr>
                            <tr>
                                <td>邮政编码</td>
                                <td><input  name="post" placeholder="如果您不清楚邮递区号，请填写000000" type="text" value="277500"></td>
                            </tr>
                            <tr>
                                <td>收货人姓名<span class="redStar">*</span></td>
                                <td><input  name="receiver"  placeholder="长度不超过25个字符" type="text" ></td>
                            </tr>
                            <tr>
                                <td>手机号码 <span class="redStar">*</span></td>
                                <td><input name="mobile"  placeholder="请输入11位手机号码" type="text"></td>
                            </tr>
                        </table>

                    </div>






                    <div class="section-bar clearfix">
                    <div class="fl">
                        <div class="seleced-address hide" id="J_confirmAddress">
                        </div>
                        <div class="big-pro-tip hide J_confirmBigProTip"></div>
                    </div>
                    <div class="fr">
                        <a href="yu?moneys=${param.moneys}" class="btn btn-primary" id="J_checkoutToPay">去结算</a>
                    </div>
                </div>
            </div>
        </div>
    </div>-->
<script>
    $(function () {
        $.ajax({
            url:'carNums',
            type:'post',
            success:function (a) {
                $(".carnum").html(a);
            }
        })
        $.ajax({
            url:'carIndexShow',
            type:'post',
            dataType: 'text',
            success:function (data) {
                //遍历List集合
                if(data=="[]"){
                    $(".carNull").html("购物车中还没有商品，赶紧选购吧！")
                }else{
                    var dataObj=eval("("+data+")");
                    for(var i=0;i<dataObj.length;i++){
                        //alert(dataObj[i].goodsID+" "+dataObj[i].goodsName);
                        $(".carNull").next().append("<li><a href='shoppingCar'><img src=\"img/goodsInfo/"+dataObj[i].productimg+"\" class=\"carImg\"> <span class=\"carName\">"+dataObj[i].goodsName+"</span> <span class=\"carPrice\">￥"+dataObj[i].price+"</span></a> </li>")
                    }
                }

                //alert($(".carNull").next().html());
                //遍历List集合
                /*var jsonObj=eval("("+data+")");
                $.each(jsonObj, function (i, item) {
                    alert(item.goodsID + ","  + item.goodsName);
                });*/
                //$(".cart-menu").html(data);
            }
        })

    })
</script>
    <jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
