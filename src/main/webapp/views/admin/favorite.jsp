<div class="container">
    <h2 class="mt-3">Your favorite list</h2>
    <form action="${root}/filter" method="get">
        <div class="mt-3 input-group">
            <input class="form-control" type="text" name="userId" value="${currUserId}"
                   placeholder="Search for..."/>
            <button class="btn btn-primary" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
        </div>
    </form>
    <table class="table">
        <thead>
        <tr>
            <th>Title</th>
            <th>Link</th>
            <th>TotalLike</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="index" begin="0" end="4" step="1">
            <c:choose>
                <c:when test="${currPage!=null && index<currPage.size()}">
                    <tr>
                        <td>${currPage.get(index).user.fullname}</td>
                        <td>${currPage.get(index).video.title}</td>
                        <td>${currPage.get(index).likedDate}</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td><span style="visibility: hidden">none</span></td>
                        <td><span style="visibility: hidden">none</span></td>
                        <td><span style="visibility: hidden">none</span></td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        </tbody>
    </table>
    <ul class="pagination justify-content-center">
        <li class="page-item ${currPageNumber==1?'disabled':''}"><a class="page-link"
                                                                    href="${root}/page/${currPageNumber-1}">Previous</a>
        </li>
        <c:if test="${pages.keySet()!=null}">
            <c:forEach items="${pages.keySet()}" varStatus="vs">
                <li class="page-item ${vs.count==currPageNumber?'active':''}"><a class="page-link"
                                                                                 href="${root}/page/${vs.count}">${vs.count}</a>
                </li>
            </c:forEach>
        </c:if>
        <c:if test="${pages.keySet()==null}">
            <li class="page-item active"><a class="page-link"
                                            href="${root}/page/1">1</a>
            </li>
        </c:if>
        <li class="page-item ${currPageNumber==pages.size()||pages.size()==0?'disabled':''}"><a
                class="page-link"
                href="${root}/page/${currPageNumber+1}">Next</a>
        </li>
    </ul>
</div>
