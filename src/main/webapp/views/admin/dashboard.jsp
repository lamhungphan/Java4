<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="container-fluid">
        <div class="row mt-3">
            <h3 class="mt-3">All Video</h3>
            <div class="card-body mb-3">
                <table id="example2" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Link</th>
                        <th>TotalLike</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${videos}" var="item">
                        <tr>
                            <td>${item.title}</td>
                            <td><a href="video?action=watch&id=${item.href}" target="_blank">${item.href}</a></td>
                            <td>${item.totalLike}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/templates/admin/script.js"/>"></script>