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
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#"><font color="black">Danh Sách</font></a>
                    </div>
                </nav>
                <hr>
                <form>
                    <button type="submit" class="btn btn-info" style="margin-left: 900px;border-top-left-radius: 20px;
                            border-bottom-left-radius: 20px;
                            border-bottom-right-radius: 20px;
                            border-top-right-radius: 20px"
                            formaction="/sanpham/viewadd"
                            >
                      Thêm Mới Sản Phẩm
                    </button>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">STT</th>
                            <th scope="col">Tên sản phẩm</th>
                            <th scope="col">Trạng thái</th>
                            <th scope="col">Ngày tạo</th>
                            <th scope="col">Giá nhập</th>
                            <th scope="col">Giá bán</th>
                            <th scope="col">Kiểu dáng</th>
                            <th scope="col">Chất liệu</th>
                            <th scope="col">Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list.getContent()}" var="sp" varStatus="i">
                            <tr onclick="window.location.href='/chi-tiet-san-pham/hien-thi?idsp=${sp.id}';">
                                <th scope="row">${i.index+page}</th>
                                <td width="150px" height="30px">${sp.ten}</td>
                                <td>${sp.trangThai==1?"Kích Hoạt":"Ngừng Kích Hoạt"}</td>
                                <td>${sp.ngayTao}</td>
                                <td>${sp.giaNhap}</td>
                                <td>${sp.giaBan}</td>
                                <td>${sp.formDang.ten}</td>
                                <td>${sp.chatLieu.ten}</td>
                                <td>
                                    <button type="button" class="btn btn-info"
                                            style="border-top-left-radius: 20px;
                                        border-bottom-left-radius: 20px;
                                        border-bottom-right-radius: 20px;
                                        border-top-right-radius: 20px;background: #03AA28" ><a class="text-white" style="text-decoration: none" href="/sanpham/viewupdate?id=${sp.id}">Update</a>
                                    </button>


                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </form>

                <%--                phân trang --%>
                <div class="container-fluid mt-5">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <li class="page-item ${pageNo<=1?"disabled":""}"><a class="page-link" href="/sanpham/hienthi?page=${pageNo-1}">Previous</a></li>
                            <c:forEach begin="1" end="${list.getTotalPages()}" var="i">
                                <li class="page-item"><a class="page-link ${i == pageNo ? 'active ' : ''}" href="/sanpham/hienthi?page=${i}">${i}</a></li>
                            </c:forEach>
                            <li class="page-item ${pageNo>=list.getTotalPages()?"disabled":""}"><a class="page-link" href="/sanpham/hienthi?page=${pageNo+1}">Next</a></li>
                        </ul>
                    </nav>
                </div>

            </div>
        </div>
    </div>
</body>