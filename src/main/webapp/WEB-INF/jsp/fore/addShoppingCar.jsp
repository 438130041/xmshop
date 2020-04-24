<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <title>成功加入购物车</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../../css/index/common.css">
    <link rel="stylesheet" href="../../../css/index/index.css">
    <link rel="stylesheet" href="../../../css/addShoppingCar/addSuccess.css">
</head>
<body>
<%--top--%>
<%@ include file="Top1.jsp"%>

<div class="page-main">
    <div class="container">
        <div class="buy-succ-box clearfix">
            <div class="goods-content" id="J_goodsBox">
                <div class="goods-img"><img src="img/goodsInfo/${goods.productimg}" width="110"
                                            height="110"></div>
                <div class="goods-info"><span class="name">${goods.product_name}</span> <span class="price">${goods.price}元</span>
                    <h3>已成功加入购物车！</h3></div>
            </div>

            <div class="actions">
                <a href="index" class="btn btn-line-gray" data-stat-id="5f1d291a747b70a9">继续购物</a>
                <a href="shoppingCar" class="btn btn-primary" data-stat-id="0e0460e3953e2713">去购物车结算</a>
            </div>
        </div>
    </div>
</div>
<%--footer--%>
<script src="js/index/jquery-1.11.3.js"></script>
<script src="js/index/index.js"></script>
</body>
</html>
