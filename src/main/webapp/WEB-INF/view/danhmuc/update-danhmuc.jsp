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
            <%--            code giao diện danh mục--%>
            <div class="container">
                <nav class="navbar navbar-light" style="height:30px">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#"><font color="black">Dữ liệu</font></a>
                    </div>
                </nav>
                <hr>
                <div style="margin-left: 460px"><font color="#696969"><h5>THÊM DANH MỤC</h5></font></div>
                <%--@elvariable id="danhMuc" type=""--%>
                <form:form method="post" action="/danh-muc/update/${danhMuc.id}" modelAttribute="danhMuc">
                    Tên size :<form:input path="ten" class="form-control" type="text"/><br>
                    Trạng thái : <form:radiobutton path="trangThai" value="1" checked="checked"/> Kích Hoạt<br>
                    <form:radiobutton  style="margin-left: 83px" path="trangThai" value="0"/> Ngừng Kích Hoạt<br>
                    <form:button type="submit" class="btn btn-info text-white" style="margin-left: 900px;border-top-left-radius: 20px;
                                    border-bottom-left-radius: 20px;
                                    border-bottom-right-radius: 20px;
                                    border-top-right-radius: 20px">
                        Xác
                        Nhận
                    </form:button>
                </form:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>