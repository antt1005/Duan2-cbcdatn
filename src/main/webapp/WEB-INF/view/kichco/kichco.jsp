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

</head>
<body>
<%--navbar--%>
<jsp:include page="../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="../sidebar/sidebar.jsp"/>
        <div class="col py-3">
            <%--            code giao diện size --%>
            <div class="container">
                <nav class="navbar navbar-light" style="height:30px">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#"><font color="black">Danh Sách</font></a>
                    </div>
                </nav>
                <hr>
                <div style="margin-left: 460px"><font color="#696969"><h5>THÊM SIZE</h5></font></div>
                <%--@elvariable id="size" type=""--%>
                <form:form method="post" action="/size/add" modelAttribute="size">
                    Tên size :<form:input path="ten" class="form-control" type="text"/><br>
                    Trạng thái : <form:radiobutton path="trangThai" value="1" checked="checked"/> Kích Hoạt<br>
                    <form:radiobutton  style="margin-left: 83px" path="trangThai" value="0"/> Ngừng Kích Hoạt<br>
                    <form:button type="submit" class="btn btn-info text-white" style="margin-left: 900px;border-top-left-radius: 20px;
                                    border-bottom-left-radius: 20px;
                                    border-bottom-right-radius: 20px;
                                    border-top-right-radius: 20px;background: #29B5D4;text-decoration-color: #FFFFFF"
                                 onclick="alert('Thêm thành công!')
                                                ">
                        Xác
                        Nhận
                    </form:button>
                </form:form>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Tên size</th>
                        <th scope="col">Trạng thái</th>
                        <th scope="col">Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${lst}" var="size" varStatus="i">
                        <tr>
                            <th scope="row">${i.index+1}</th>
                            <td>${size.ten}</td>
                            <td>${size.trangThai==1?"Kích hoạt":"Ngừng kích hoạt"}</td>
                            <td>
                                <button type="button" class="btn btn-info"
                                        style="border-top-left-radius: 20px;
                                        border-bottom-left-radius: 20px;
                                        border-bottom-right-radius: 20px;
                                        border-top-right-radius: 20px;background: #03AA28" ><a class="text-white" style="text-decoration: none" href="/size/view-update/${size.id}">Update</a>

                                </button>
                                <button type="button" class="btn btn-info text-white"
                                        style="margin-left: 10px;border-top-left-radius: 20px;
                                        border-bottom-left-radius: 20px;
                                        border-bottom-right-radius: 20px;
                                        border-top-right-radius: 20px;background: #E43535"
                                        onclick="if(confirm('Bạn có chắc chắn muốn xoá không?')){window.location.href = '/size/delete/${size.id}';}
                                                else{alert('Dữ liệu không được xoá!')}">Remove

                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <%--                phân trang --%>
                <div class="container-fluid mt-5">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <li class="page-item ${currentPage<=0?"disabled":""}"><a class="page-link" href="/size/hien-thi/${currentPage-1}">Previous</a></li>
                            <c:forEach begin="1" end="${totalsPage}" var="i">
                                <li class="page-item"><a class="page-link" href="/size/hien-thi/${i-1}">${i}</a></li>
                            </c:forEach>
                            <li class="page-item ${currentPage>=totalsPage-1?"disabled":""}"><a class="page-link" href="/size/hien-thi/${currentPage+1}">Next</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>