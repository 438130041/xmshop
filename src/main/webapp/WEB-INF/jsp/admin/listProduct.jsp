<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/19
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="admin_header.jsp"%>
<title>商品管理</title>
<div class="admin-biaogelist">
    <div class="listbiaoti am-cf">
        <ul class="am-icon-users">商品管理</ul>

        <dl class="am-icon-home" style="float: right;">
            当前位置：<a href="main"> 首页 </a>>
            <a href="admin_listproduct">商品列表</a>
        </dl>

        <dl>
            <a href="javascript:;" onclick="addProducts()"><button type="button" class="am-btn am-btn-danger am-round am-btn-xs am-icon-plus">
                添加商品</button></a>
        </dl>
    </div>

    <div class="soso am-form am-g ">
        <form action="" method="post">
            <p class="ycfg">
                <input name="search_input" type="text"
                       class="am-form-field am-input-sm" placeholder="圆角表单域" />
            </p>
            <p>
                <button type="submit" class="am-btn am-btn-xs am-btn-default am-xiao">
                    <i class="am-icon-search"></i>
                </button>
            </p>
        </form>
    </div>


    <form class="am-form am-g">
        <table width="100%"class="am-table am-table-bordered am-table-radius am-table-striped">
            <thead>
            <tr class="am-success">
                <th class="table-check"><input type="checkbox" />
                </th>
                <th>ID</th>
                <th>商品名称</th>
                <th>商品描述</th>
                <th width="53px">商品图片</th>
                <th width="80px">商品价格</th>
                <th width="80px">库存数量</th>
                <th width="150px">添加时间</th>
                <th width="60px">状态</th>
                <th width="50px">操作</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${productList}" var="p">
                <tr>
                    <td><input type="checkbox" />
                    </td>
                    <td>${p.id}</td>
                    <td>${p.product_name}</td>
                    <td>${p.subTitle}</td>
                    <td><img src="<%= request.getContextPath()%>/img/goodsInfo/${p.productimg}" width="100"></td>
                    <td>${p.price}</td>
                    <td>${p.stock}</td>
                    <td><fmt:formatDate value="${p.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <c:if test="${p.stock gt 50}">
                            <c:if test="${p.status==1}">
                                <span class="label label-success">已发布</span>
                            </c:if>
                            <c:if test="${p.status==0}">
                                <span class="label label-warning">已下架</span>
                            </c:if>
                        </c:if>
                        <c:if test="${p.stock le 50}">
                            <span class="label label-danger">库存不足</span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${p.status==0}">
                            <a href="javascript:;" onclick="product_start(this,${p.id},${p.status})" title="上架">
                                <span class="glyphicon glyphicon-circle-arrow-up" aria-hidden="true" ></span>
                            </a>
                        </c:if>
                        <c:if test="${p.status==1}">
                            <a href="javascript:;" onclick="product_stop(this,${p.id},${p.status})" title="下架">
                                <span class="glyphicon glyphicon-circle-arrow-down" aria-hidden="true" ></span>
                            </a>
                        </c:if>
                        <a href="javascript:" onclick="editProducts(${p.id})" title="编辑">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true" ></span>
                        </a>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
        <%--分页--%>
        <div class="pageDiv">
            <%@include file="adminPage.jsp"%>
        </div>
    </form>



    <div class="foods">
        <p>版权所有@2019 XM <a href="#" target="_blank">XM</a></p>
    </div>

</div>

<script>

    /*产品-下架*/
    function product_stop(obj,id,status){
        layer.confirm('确认要下架ID为\''+id+'\'的商品吗？',{icon:0},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'PUT',
                async:false,
                url: '/admin_updateStatus/'+id+'/'+status,
                dataType: 'text',
                success: function(data){
                    layer.close(index);
                    location.reload();
                    layer.msg('已下架!',{icon: 5,time:1000});
                },
                error:function(XMLHttpRequest){
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            });
        });
    }
    /*产品-发布*/
    function product_start(obj,id,status){
        layer.confirm('确认要发布ID为\''+id+'\'的商品吗？',{icon:3},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'PUT',
                async:false,
                url: '/admin_updateStatus/'+id+'/'+status,
                dataType: 'text',
                success: function(data){
                    layer.close(index);
                    location.reload();
                    layer.msg('已发布!',{icon: 6,time:1000});
                },
                error:function(XMLHttpRequest){
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            });
        });
    }

    function addProducts() {
        //弹出即全屏
        var index = layer.open({
            type: 2,
            title:'添加商品',
            content: 'getCategory',
            area: ['320px', '195px'],
            maxmin: true
        });
        layer.full(index);
    }

    function editProducts(id) {
        //弹出即全屏
        var index = layer.open({
            type: 2,
            title:'添加商品',
            content: 'editProduct?id='+id,
            area: ['320px', '195px'],
            maxmin: true
        });
        layer.full(index);
    }



</script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<!--[if (gte IE 9)|!(IE)]><!-->
<script src="${pageContext.request.contextPath}/admin/assets/js/amazeui.min.js"></script>
<!--<![endif]-->

</html>