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
            <%--            code giao diện formdang --%>
            <div class="container">
                <nav class="navbar navbar-light" style="height:30px">

                </nav>
                <hr>
                <div style="margin-left: 460px"><font color="#696969"><h5>THÊM KIỂU DÁNG</h5></font></div>
                <div>
                    <form>
                        <%--                        tìm kiếm --%>
                        <div class="input-group" style="margin-left: 20px">
                            <div class="form-outline">
                                <input type="search" name="searchKD" value="${param.searchKD}"
                                       placeholder="Nhập tên tìm kiếm ..."/>

                            </div>
                            <button type="submit" class="btn btn-primary">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-search" viewBox="0 0 16 16">
                                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                                </svg>
                            </button>
                        </div>
                    </form>
                </div>
                <form action="/sanpham/addkieudang" method="post">
                    <p style="margin-left: 20px">Tên kiểu dáng :</p>
                    <input name="ten" class="form-control"
                           type="text" style="margin-left: 20px;width: 700px">
                    <br>

                    <br>
                    <p style="margin-left: 20px">Trạng thái :</p>
                    <input type="radio" style="margin-left: 60px" name="trangthai" value="1" checked="checked"> Kích
                    Hoạt
                    <br>
                    <input type="radio" style="margin-left: 60px" name="trangthai" value="0"> Ngừng Kích Hoạt
                    <br>
                    <button type="submit" class="btn btn-info" style="margin-left: 900px;border-top-left-radius: 20px;
                            border-bottom-left-radius: 20px;
                            border-bottom-right-radius: 20px;
                            border-top-right-radius: 20px"
                            onclick="alert('Thêm thành công!') ">
                        Thêm mới
                    </button>

                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">STT</th>
                            <th scope="col">Tên kiểu dáng</th>
                            <th scope="col">Trạng thái</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listForm.getContent()}" var="form" varStatus="i">
                            <tr onclick="window.location.href='/sanpham/idform?idform=${form.id}';">
                                <th scope="row">${i.index+page}</th>
                                <td>${form.ten}</td>
                                <td>${form.trangThai==1?"Kích Hoạt":"Ngừng Kích Hoạt"}</td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>

                    <%--                phân trang --%>
                    <div class="container-fluid mt-5">
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center">
                                <li class="page-item ${pageNo<=1?"disabled":""}"><a class="page-link"
                                                                                    href="/sanpham/view-kieu-dang?searchKD=${param.searchKD}&page=${pageNo-1}">Previous</a>
                                </li>
                                <c:forEach begin="1" end="${listForm.getTotalPages()}" var="i">
                                    <li class="page-item"><a class="page-link ${i == pageNo ? 'active ' : ''}"
                                                             href="/sanpham/view-kieu-dang?searchKD=${param.searchKD}&page=${i}">${i}</a>
                                    </li>
                                </c:forEach>
                                <li class="page-item ${pageNo>=listForm.getTotalPages()?"disabled":""}"><a
                                        class="page-link"
                                        href="/sanpham/view-kieu-dang?searchKD=${param.searchKD}&page=${pageNo+1}">Next</a>
                                </li>
                            </ul>
                        </nav>
                    </div>
            </div>
        </div>


    </div>
</div>
</form>
</body>
</html>