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
                <table id="favoriteTable" class="table table-bordered table-striped">
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
<%--<script src="<c:url value="/templates/admin/script.js"/>"></script>--%>
<script>

    document.querySelector(".dropdown-menu").addEventListener("click", function(e) {
        const target = e.target;
        if (target.classList.contains("dropdown-item")) {
            const videoHref = target.getAttribute("data-href");
            fetchFavoriteUsers(videoHref);  // Gọi hàm fetch để lấy dữ liệu
        }
    });


    function fetchFavoriteUsers(videoHref) {
        const encodedHref = encodeURIComponent(videoHref);
        fetch(`/api/favorite?href=${encodedHref}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                const tbody = document.querySelector("#favoriteTable tbody");
                tbody.innerHTML = "";  // Clear current table rows
                if (data.length === 0) {
                    const row = tbody.insertRow();
                    const cell = row.insertCell();
                    cell.colSpan = 2;
                    cell.textContent = "No users liked this video.";
                } else {
                    data.forEach(user => {
                        const row = tbody.insertRow();
                        const usernameCell = row.insertCell();
                        usernameCell.textContent = user.username;
                        const emailCell = row.insertCell();
                        emailCell.textContent = user.email;
                    });
                }
            })
            .catch(error => {
                console.error('Error fetching favorite users:', error);
            });
    }
</script>