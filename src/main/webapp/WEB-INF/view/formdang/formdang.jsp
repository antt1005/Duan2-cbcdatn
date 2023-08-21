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
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#"><font color="black">Danh Sách</font></a>
                    </div>
                </nav>
                <hr>
                <div style="margin-left: 460px"><font color="#696969"><h5>THÊM KIỂU DÁNG</h5></font></div>
                <form action="/formdang/add" method="post">
                    <p style="margin-left: 20px">Tên kiểu dáng :</p>
                    <input name="ten" class="form-control"
                           type="text" style="margin-left: 20px;width: 1000px">
                    <br>
                    <font color="red">${checkTen}</font>
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
                        Xác
                        Nhận
                    </button>

                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">STT</th>
                            <th scope="col">Tên kiểu dáng</th>
                            <th scope="col">Trạng thái</th>
                            <th scope="col">Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list.getContent()}" var="form" varStatus="i">
                            <tr>
                                <th scope="row">${i.index+page}</th>
                                <td>${form.ten}</td>
                                <td>${form.trangThai==1?"Kích Hoạt":"Ngừng Kích Hoạt"}</td>
                                <td>
                                    <button type="submit" class="btn btn-info"
                                            style="border-top-left-radius: 20px;
                                        border-bottom-left-radius: 20px;
                                        border-bottom-right-radius: 20px;
                                        border-top-right-radius: 20px;background: lawngreen" ;
                                            formmethod="post" formaction="/formdang/update?id=${form.id}"
                                            onclick="alert('Cập nhật thành công!') ">Update

                                    </button>
                                    <button type="button" class="btn btn-info"
                                            style="margin-left: 10px;border-top-left-radius: 20px;
                                        border-bottom-left-radius: 20px;
                                        border-bottom-right-radius: 20px;
                                        border-top-right-radius: 20px;background: red"
                                            onclick="if(confirm('Bạn có chắc chắn muốn xoá không?')){window.location.href = '/formdang/delete/${form.id}';alert('Xoá thành công!')}
                                                    else{alert('Dữ liệu không được xoá!')}">Remove

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
                            <li class="page-item ${pageNo<=1?"disabled":""}"><a class="page-link" href="/formdang/hienthi?page=${pageNo-1}">Previous</a></li>
                            <c:forEach begin="1" end="${list.getTotalPages()}" var="i">
                                <li class="page-item"><a class="page-link ${i == pageNo ? 'active ' : ''}" href="/formdang/hienthi?page=${i}">${i}</a></li>
                            </c:forEach>
                            <li class="page-item ${pageNo>=list.getTotalPages()?"disabled":""}"><a class="page-link" href="/formdang/hienthi?page=${pageNo+1}">Next</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>


    </div>
</div>

</body>
</html>