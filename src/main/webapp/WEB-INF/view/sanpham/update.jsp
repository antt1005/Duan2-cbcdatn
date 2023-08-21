<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>

</head>
<body>
<%--navbar--%>
<jsp:include page="../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="../sidebar/sidebar.jsp"/>
        <div class="col py-3">
            <%--            code giao diện sản phẩm --%>
            <div class="container">
                <nav class="navbar navbar-light" style="height:30px">
                    <div style="margin-left: 460px"><font color="#696969"><h5>CẬP NHẬT SẢN PHẨM</h5></font></div>
                </nav>
                <hr>

                <form>
                    <div class="row">
                        <div class="col-sm-3">
                            <div class="well">
                                <p style="margin-left: 10px">Tên sản phẩm :</p>
                                <input name="ten" class="form-control"
                                       type="text" style="margin-left: 10px;width: 500px" value="${sp.ten}">
                                <p style="margin-left: 10px">Mô Tả :</p>

                                <textarea name="moTa" class="form-control" placeholder="Mời bạn nhập mô tả"
                                          style="width: 500px;height: 120px">${sp.moTa}</textarea>

                                <p style="margin-left: 10px">Trạng thái :</p>
                                <input type="radio" style="margin-left: 60px" name="trangthai" value="1"
                                       checked="checked" ${sp.trangThai==1?"checked":""}>
                                Kích
                                Hoạt
                                <br>
                                <input type="radio" style="margin-left: 60px" name="trangthai" value="0" ${sp.trangThai==0?"checked":""}> Ngừng Kích
                                Hoạt
                                <br>
                            </div>
                        </div>
                        <div class="col-sm-9">
                            <div class="well" style="margin-left: 250px">
                                <p>Giá Nhập :</p>
                                <input name="gianhap" class="form-control"
                                       type="text" style="width: 500px" value="${sp.giaNhap}">
                                <p>Giá Bán :</p>
                                <input name="giaban" class="form-control"
                                       type="text" style="width: 500px" value="${sp.giaBan}">
                                <p>Kiểu Dáng :</p>
                                <select name="form" onchange="getTen(this.value)">
                                    <option>Kiểu Dáng</option>
                                    <c:forEach items="${listForm}" var="form">
                                        <option value="${form.id}" ${sp.formDang.id.equals(form.id)?"selected":""}>${form.ten}</option>
                                    </c:forEach>
                                </select>
                                <div id="ten" style="color: cadetblue"></div>
                                <p>Chất Liệu :</p>
                                <select name="chat" onchange="chatLieu(this.value)">
                                    <option>Chất Liệu</option>
                                    <c:forEach items="${listChat}" var="chat">
                                        <option value="${chat.id}" ${sp.chatLieu.id.equals(chat.id)?"selected":""}>${chat.ten}</option>
                                    </c:forEach>
                                </select>
                                <div id="chatlieu" style="color: cadetblue"></div>

                            </div>
                        </div>
                    </div>


                    <script>
                        function getTen(id) {
                            fetch('/sanpham/layten/' + id)
                                .then(response => response.json())
                                .then(data => {
                                    document.getElementById("ten").innerHTML = data.ten;
                                });
                        }

                        function chatLieu(id) {
                            fetch('/sanpham/chatlieu/' + id)
                                .then(response => response.json())
                                .then(data => {
                                    document.getElementById("chatlieu").innerHTML = data.ten;
                                });
                        }


                    </script>

                    <button type="submit" class="btn btn-info" style="margin-left: 900px;border-top-left-radius: 20px;
                            border-bottom-left-radius: 20px;
                            border-bottom-right-radius: 20px;
                            border-top-right-radius: 20px"
                            formmethod="post"
                            formaction="/sanpham/update?id=${sp.id}"
                            onclick="alert('Cập nhật thành công!') ">
                        Xác
                        Nhận
                    </button>


                </form>



            </div>
        </div>
    </div>
</body>