<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="container-fluid">
        <div class="row mt-3">
            <h3 class="mt-3">Favorite Info</h3>
            <div class="card-body mb-3 mt-3">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown"
                            aria-expanded="false">
                        List video
                    </button>
                    <ul class="dropdown-menu" id="selectVideo">
                        <c:forEach items="${videos}" var="item">
                            <li><a class="dropdown-item" href="#" data-href="${item.href}">${item.title}</a></li>
                        </c:forEach>
                    </ul>
                </div>
                <table id="example3" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>Username</th>
                        <th>Email</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/templates/admin/script.js"/>"></script>