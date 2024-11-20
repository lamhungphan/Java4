<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/head.jsp" %>
    <title>${video.title}</title>
</head>
<body>
<%@include file="/common/header.jsp" %>
<div class="container-fluid p-0 m-0">
    <!-- Video Section -->
    <div class="row p-4">
        <div class="col-md-8">
            <div class="single-video-left">
                <div class="single-video">
                    <iframe width="100%" height="315" id="tm-video"
                            src="https://www.youtube.com/embed/${video.href}">
                    </iframe>
                </div>
                <div class="single-video-title box mb-3">
                    <h2><a href="#">${video.title}</a></h2>
                    <p class="mb-0"><i class="fas fa-eye"></i> 0 view</p>
                    <c:if test="${not empty sessionScope.currentUser}">
                        <button class="btn-success" id="likeBtn">
                            <a href="<c:url value='/video?action=like&id=${video.href}'/>">
                                <c:choose>
                                    <c:when test="${flagLikedBtn == true}">Unlike</c:when>
                                    <c:otherwise>Like</c:otherwise>
                                </c:choose>
                            </a>
                        </button>
                        <button class="btn-primary" share="shareBtn">Share</button>
                    </c:if>
                </div>
                <div class="single-video-info-content box mb-3">
                    <h6>Description</h6>
                    <p>${video.description}</p>
                </div>
            </div>
        </div>
        <!-- Related Videos -->
        <div class="col-md-4">
            <div class="single-video-right">
                <div class="row">
                    <div class="col-md-12">
                        <div class="main-title">
                            <div class="btn-group float-right right-action">
                                <a href="#" class="right-action-link text-gray" data-toggle="dropdown"
                                   aria-haspopup="true" aria-expanded="false">
                                    Sort by <i class="fa fa-caret-down" aria-hidden="true"></i>
                                </a>
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a class="dropdown-item" href="#"><i class="fas fa-fw fa-star"></i> &nbsp; Top
                                        Rated</a>
                                    <a class="dropdown-item" href="#"><i class="fas fa-fw fa-signal"></i> &nbsp;
                                        Viewed</a>
                                    <a class="dropdown-item" href="#"><i class="fas fa-fw fa-times-circle"></i>
                                        &nbsp; Close</a>
                                </div>
                            </div>
                            <h6>Up Next</h6>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="video-card video-card-list">
                            <div class="video-card-image">
                                <a class="play-icon" href="#"><i class="fas fa-play-circle"></i></a>
                                <a href="#"><img class="img-fluid" src="img/v1.png" alt=""></a>
                                <div class="time">3:50</div>
                            </div>
                            <div class="video-card-body">
                                <div class="btn-group float-right right-action">
                                    <a href="#" class="right-action-link text-gray" data-toggle="dropdown"
                                       aria-haspopup="true" aria-expanded="false">
                                        <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right">
                                        <a class="dropdown-item" href="#"><i class="fas fa-fw fa-star"></i> &nbsp; Top
                                            Rated</a>
                                        <a class="dropdown-item" href="#"><i class="fas fa-fw fa-signal"></i> &nbsp;
                                            Viewed</a>
                                        <a class="dropdown-item" href="#"><i class="fas fa-fw fa-times-circle"></i>
                                            &nbsp; Close</a>
                                    </div>
                                </div>
                                <div class="video-title">
                                    <a href="#">Here are many variati of passages of Lorem</a>
                                </div>
                                <div class="video-page text-success">
                                    Education <a title="" data-placement="top" data-toggle="tooltip" href="#"
                                                 data-original-title="Verified"><i
                                        class="fas fa-check-circle text-success"></i></a>
                                </div>
                                <div class="video-view">
                                    1.8M views &nbsp;<i class="fas fa-calendar-alt"></i> 11 Months ago
                                </div>
                            </div>
                        </div>

                        <div class="video-card video-card-list">
                            <div class="video-card-image">
                                <a class="play-icon" href="#"><i class="fas fa-play-circle"></i></a>
                                <a href="#"><img class="img-fluid" src="img/v2.png" alt=""></a>
                            </div>
                            <div class="video-card-body">
                                <div class="btn-group float-right right-action">
                                    <a href="#" class="right-action-link text-gray" data-toggle="dropdown"
                                       aria-haspopup="true" aria-expanded="false">
                                        <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right">
                                        <a class="dropdown-item" href="#"><i class="fas fa-fw fa-star"></i> &nbsp; Top
                                            Rated</a>
                                        <a class="dropdown-item" href="#"><i class="fas fa-fw fa-signal"></i> &nbsp;
                                            Viewed</a>
                                        <a class="dropdown-item" href="#"><i class="fas fa-fw fa-times-circle"></i>
                                            &nbsp; Close</a>
                                    </div>
                                </div>
                                <div class="video-title">
                                    <a href="#">Video title is here</a>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!-- Comments Section -->
        <div class="mt-4 col-8">
            <h5>Comments</h5>
            <form class="mb-3">
                <div class="mb-3">
                    <textarea class="form-control" placeholder="Add a comment..." rows="3"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>

            <!-- List of Comments -->
            <div class="list-group">
                <div class="list-group-item">
                    <strong>User 1</strong>
                    <p>This is a comment.</p>
                </div>
                <div class="list-group-item">
                    <strong>User 2</strong>
                    <p>Another comment here.</p>
                </div>
            </div>
        </div>
        <input id="videoIdHdn" type="hidden" value="${video.href}">
    </div>
</div>

<%@include file="/common/footer.jsp" %>
<script>
    $('#likeBtn').click(function () {
        var videoId = $('#videoIdHdn').val();
        $.ajax({
            url: 'video?action=like&id=' + videoId
        }).then(function (data) {
            var text = $('#likeBtn').text();
            if (text.indexOf('Like') !== -1) {
                $('#likeBtn').text('Unlike');
            } else {
                $('#likeBtn').text('Like');
            }
        }).fail(function (error) {
            alert('please try again, fail case');
        });
    });

</script>
</body>
</html>