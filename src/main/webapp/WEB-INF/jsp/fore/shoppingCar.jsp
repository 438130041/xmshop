<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>成功加入购物车</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../../css/index/common.css">
    <link rel="stylesheet" href="../../../css/index/index.css">
    <link rel="stylesheet" href="../../../css/shoppingCar/cart.css">
</head>
<body>
<%--top--%>
<%@ include file="Top1.jsp"%>
<div class="page-main">

    <div class="container">
        <div class="cart-loading loading hide" id="J_cartLoading">
            <div class="loader"></div>
        </div>
        <div id="J_cartBox" class="">
            <div class="cart-goods-list">
                <div class="list-head clearfix">
                    <div class="col col-check">
                        <%--<i class="iconfont icon-checkbox icon-checkbox-selected" id="J_selectAll">√</i>取消全选--%>
                        <i class="iconfont icon-checkbox" id="J_selectAll">√</i>全选
                    </div>
                    <div class="col col-img">&nbsp;</div>
                    <div class="col col-name">商品名称</div>
                    <div class="col col-price">单价</div>
                    <div class="col col-num">数量</div>
                    <div class="col col-total">小计</div>
                    <div class="col col-action">操作</div>
                </div>
                <div class="list-body" id="J_cartListBody">
                    <c:set var="a" value="0"></c:set>
                    <c:forEach items="${goods}" var="g">
                        <%--商品--%>
                        <div class="item-box i_${a+1}">
                            <div class="item-table J_cartGoods">
                                <div class="item-row clearfix">
                                        <%--选择框--%>
                                    <div class="col col-check">
                                            <%--<i class="iconfont icon-checkbox icon-checkbox-selected" id="J_select">√</i>--%>
                                        <i class="iconfont icon-checkbox iyou" id="J_select">√</i>
                                    </div>
                                        <%--图片--%>
                                    <div class="col col-img">
                                        <a href="#" target="_blank"><img
                                                src="img/goodsInfo/${g.goodsImg}"
                                                width="80" height="80"> </a>
                                    </div>
                                        <%--商品名称--%>
                                    <div class="col col-name">
                                        <h3 class="name"><a href="" target="${g.id}">${g.goodsName} </a></h3>
                                    </div>
                                        <%--价格--%>
                                    <div class="col col-price"><i id="money_${a+1}"> ${g.marketPrice}</i>元</div>
                                        <%--数量--%>
                                    <div class="col col-num">
                                        <div class="change-goods-num clearfix J_changeGoodsNum">
                                            <a href="javascript:void(0)" class="J_minus">
                                                <i class="iconfont">&nbsp;-</i></a>
                                            <input tyep="text" name="2164100003_0_buy" value="1" data-num="1" readonly="true" class="goods-num J_goodsNum">
                                            <a href="javascript:void(0)" class="J_plus"><i class="iconfont">&nbsp;+</i></a>
                                        </div>
                                    </div>
                                        <%--小计--%>
                                    <div class="col col-total"><i id="moneyx_${a+1}"> 0</i>元 <p class="pre-info"></p></div>
                                        <%--删除--%>
                                    <div class="col col-action">
                                        <a id="2164100003_0_buy" data-msg="确定删除吗？" href="javascript:void(0);" title="删除"
                                           class="del J_delGoods">
                                            <i class="iconfont">×</i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:set var="a" value="${a+1}"></c:set>
                    </c:forEach>
                    <div class="cart-bar clearfix" id="J_cartBar">
                        <div class="section-left">
                            <a href="#" class="back-shopping J_goShoping">继续购物</a>
                            <span class="cart-total">共 <i id="J_cartTotalNum" class="num">0</i> 件商品，已选择 <i id="J_selTotalNum" class="choose">0</i> 件</span>
                            <span class="cart-coudan hide" id="J_coudanTip">
                            ，还需 <i id="J_postFreeBalance">150</i> 元即可免邮费
                                <a href="javascript:void(0);" id="J_showCoudan">立即凑单</a>
                        </span>
                        </div>
                        <span class="activity-money hide">
                        活动优惠：减 <i id="J_cartActivityMoney">0</i> 元
                        </span>
                        <span class="total-price">
                        <i class="it">合计（不含运费）：</i><em id="J_cartTotalPrice"><i id="moneys">0</i></em>元
                        </span>
                        <a href="javascript:void(0)" class="btn-disabled wei" id="J_goCheckout">去结算</a>

                        <div class="no-select-tip" id="J_noSelectTip">
                            请勾选需要结算的商品
                            <i class="arrow arrow-a"></i>
                            <i class="arrow arrow-b"></i>
                        </div>
                    </div>
                </div>
            </div>
            <div class="cart-recommend" id="J_miHistoryBox"></div>
        </div>
    </div>
</div>
</body>
<%--footer--%>
<%@ include file="footer.jsp"%>

<script src="../../../js/shoppingCar/shoppingCar.js"></script>

<script>

    $(function () {
        $("#J_goCheckout").click(function () {
            location.href = "forebuy?moneys="+Number($("#moneys").html());
        })

    })
</script>
</html>
