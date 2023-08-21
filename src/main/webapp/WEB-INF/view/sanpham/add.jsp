<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>


    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
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
                    <div style="margin-left: 460px"><font color="#696969"><h5>THÊM SẢN PHẨM</h5></font></div>
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
                                <input type="radio" style="margin-left: 60px" name="trangthai"
                                       value="0" ${sp.trangThai==0?"checked":""}> Ngừng Kích
                                Hoạt
                                <br>
                            </div>
                        </div>
                        <div class="col-sm-9">
                            <div class="well" style="margin-left: 250px">
                                <p>Giá Nhập :</p>
                                <input name="gianhap" class="form-control"
                                       type="text" style="width: 500px" value="${param.gianhap}">
                                <p>Giá Bán :</p>
                                <input name="giaban" class="form-control"
                                       type="text" style="width: 500px">
                                <p>Kiểu Dáng :</p>
                                <select name="form" onchange="getTen(this.value)">
                                    <option value="null" >Kiểu Dáng</option>
                                    <c:forEach items="${listForm}" var="form">
                                        <option value="${form.id}" ${idform==form.id?"selected":""}>${form.ten}</option>
                                    </c:forEach>
                                </select>
                                <%--                                thêm nhanh kiểu dáng --%>
                                <button type="submit" class="btn btn-primary" title="Thêm mới kiểu dáng"
                                        formaction="/sanpham/view-kieu-dang">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-plus-circle" viewBox="0 0 16 16">
                                        <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                        <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                                    </svg>
                                </button>


                                <div id="ten" style="color: cadetblue"></div>
                                <p>Chất Liệu :</p>
                                <select name="chat" onchange="chatLieu(this.value)">
                                    <option>Chất Liệu</option>
                                    <c:forEach items="${listChat}" var="chat">
                                        <option value="${chat.id}" ${idchat==chat.id?"selected":""} >${chat.ten}</option>
                                    </c:forEach>
                                </select>
                                <%--                                thêm nhanh chất liệu --%>
                                <button type="submit" class="btn btn-primary" title="Thêm mới chất liệu"
                                        formaction="/sanpham/view-chat-lieu">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-plus-circle" viewBox="0 0 16 16">
                                        <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                        <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                                    </svg>
                                </button>
                                <div id="chatlieu" style="color: cadetblue"></div>
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
                            border-top-right-radius: 20px;width: 100px"
                                formmethod="post"
                                formaction="/sanpham/add"
                                onclick="alert('Thêm thành công!') ">
                            Xác
                            Nhận
                        </button>


                    </div>
                </form>
            </div>
        </div>
</body>