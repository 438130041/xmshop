<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="adminHeader.jsp"%>
<title>编辑商品</title>
<script>
    $(function() {
        $("#editForm").submit(function() {
            if (!checkEmpty("name", "商品名称"))
                return false;
		   if (!checkEmpty("subTitle", "商品描述"))
				return false;
            if (!checkNumber("originalPrice", "价格"))
            if (!checkInt("stock", "库存"))
                return false;
            return true;
        });
    });
</script>
    <div class="panel panel-warning editDiv">
        <div class="panel-heading">编辑商品</div>
        <div class="panel-body">
            <form method="post" id="editForm" action="updateProduct">
                <table class="editTable">
                    <tr>
                        <td>商品名称</td>
                        <td><input id="name" name="name" value="${product.product_name}"
                                   type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>商品描述</td>
                        <td><input id="subTitle" name="subTitle" type="text"
                                   value="${product.subTitle}"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>价格</td>
                        <td><input id="originalPrice" value="${product.price}" name="originalPrice" type="text"
                                   class="form-control"></td>
                    </tr>

                    <tr>
                        <td>库存</td>
                        <td><input id="stock"  value="${product.stock}" name="stock" type="text"
                                   class="form-control"></td>
                    </tr>

                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="id" value="${product.id}">
                            <input type="hidden" name="cid" value="${product.cid}">
                            <button type="submit" class="btn btn-success">提 交</button></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>