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
            <%--            code giao diện formdang --%>
            <div class="container">
                <nav class="navbar navbar-light" style="height:30px">

                </nav>
                <hr>
                <div style="margin-left: 460px"><font color="#696969"><h5>THÊM CHẤT LIỆU</h5></font></div>
                <div>
                    <form>
                        <%--                        tìm kiếm --%>
                        <div class="input-group">
                            <div class="form-outline">
                                <input type="search" name="searchCL" value="${param.searchCL}"
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
                <%--@elvariable id="chatLieu" type=""--%>
                <form:form method="post" action="/sanpham/addchatlieu" modelAttribute="chatLieu">
                    Tên chất liệu :<form:input path="ten" class="form-control" type="text" cssStyle="width: 700px"/><br>
                    Trạng thái : <form:radiobutton path="trangThai" value="1" checked="checked"/> Kích Hoạt<br>
                    <form:radiobutton style="margin-left: 83px" path="trangThai" value="0"/> Ngừng Kích Hoạt<br>
                    <form:button type="submit" class="btn btn-info text-white" style="margin-left: 900px;border-top-left-radius: 20px;
                                    border-bottom-left-radius: 20px;
                                    border-bottom-right-radius: 20px;
                                    border-top-right-radius: 20px;background: #29B5D4;text-decoration-color: #FFFFFF"
                                 onclick="if(confirm('Bạn có chắc chắn muốn thêm không?')){window.location.href = '/sanpham/addchatlieu'}
                                                else{alert('Dữ liệu không được thêm!')}">
                        Xác
                        Nhận
                    </form:button>
                </form:form>

                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Tên chất liệu</th>
                        <th scope="col">Trạng thái</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${chatLieus.getContent()}" var="chatLieu" varStatus="i">
                        <tr onclick="window.location.href='/sanpham/idchat?idchat=${chatLieu.id}';">
                            <th scope="row">${i.index+page}</th>
                            <td>${chatLieu.ten}</td>
                            <td>${chatLieu.trangThai==1?"Kích hoạt":"Ngừng kích hoạt"}</td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>

                <%--                phân trang --%>
                <div class="container-fluid mt-5">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <li class="page-item ${pageNo<=1?"disabled":""}"><a class="page-link"
                                                                                href="/sanpham/view-chat-lieu?searchCL=${param.searchCL}&page=${pageNo-1}">Previous</a>
                            </li>
                            <c:forEach begin="1" end="${chatLieus.getTotalPages()}" var="i">
                                <li class="page-item"><a class="page-link ${i == pageNo ? 'active ' : ''}"
                                                         href="/sanpham/view-chat-lieu?searchCL=${param.searchCL}&page=${i}">${i}</a>
                                </li>
                            </c:forEach>
                            <li class="page-item ${pageNo>=chatLieus.getTotalPages()?"disabled":""}"><a
                                    class="page-link"
                                    href="/sanpham/view-chat-lieu?searchCL=${param.searchCL}&page=${pageNo+1}">Next</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>


    </div>
</div>
</body>
</html>