<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/head.jsp" %>
    <title>Admin page</title>
</head>
<body>
<%@include file="/common/header.jsp" %>
<div class="container mt-5">
    <h3 class="text-center">Add/Edit Video</h3>
    <div class="card mt-4">
        <div class="card-header bg-primary text-white" id="message">Add video</div>
        <div class="card-body">
            <form enctype="application/x-www-form-urlencoded">
                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" id="title" value="${video != null ? video.title : '' }" class="form-control"
                           placeholder="Enter video title">
                </div>
                <div class="form-group">
                    <label for="href">Href</label>
                    <input type="text" id="href" value="${video != null ? video.href : '' }" class="form-control" placeholder="Enter video link">
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea id="description" value="${video != null ? video.description : '' }" class="form-control"
                              rows="3"
                              placeholder="Enter video description"></textarea>
                </div>
                <div class="form-group">
                    <label for="poster">Poster</label>
                    <input type="text" id="poster" value="${video != null ? video.poster : '' }" class="form-control"
                           placeholder="Enter poster URL">
                    <input type="hidden" id="isEdit" value="${isEdit}" class="form-control">
                </div>
                <div class="form-group">
                    <label for="preview">Preview</label>
                    <div class="border p-3" id="preview"
                         style="height: 150px; display: flex; align-items: center; justify-content: center;">
                        <img src="${video != null ? video.poster : '' }" alt="Preview Image" id="imgPreview"
                             style="max-height: 100%; max-width: 100%;">
                    </div>
                </div>
                <div class="text-center">
                    <button type="button" id="resetBtn" class="btn btn-info">Reset</button>
                    <button type="button" id="submitBtn" class="btn btn-success">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    document.getElementById('poster').addEventListener('input', function () {
        const img = document.querySelector('#preview img');
        const url = this.value.trim();
        if (url) {
            img.src = url;
            img.style.display = 'block';
        } else {
            img.style.display = 'none';
        }
    });

    var titleOrigin, hrefOrigin, descriptionOrigin, posterOrigin;
    $(document).ready(function () {
        titleOrigin = $('#title').val();
        hrefOrigin = $('#href').val();
        descriptionOrigin = $('#description').val();
        posterOrigin = $('#poster').val();
    });

    $('#resetBtn').click(function () {
        $('#title').val(titleOrigin);
        $('#href').val(hrefOrigin);
        $('#description').val(descriptionOrigin);
        $('#poster').val(posterOrigin);

        if (posterOrigin.length > 0) {
            $('#imgPreview').attr('src', posterOrigin);
        }
    });

    $('#submitBtn').click(function () {
        $('#message').text('');

        var url;
        var isEdit = $('#isEdit').val();
        if (isEdit == 'true') {
            url: '/Java4_Youtube_war/admin/video?action=edit&href=' + hrefOrigin;
        } else {
            url: '/Java4_Youtube_war/admin/video?action=add';
        }

        var formData = {
            'hrefOrigin': hrefOrigin,
            'title': $('#title').val(),
            'newHref': $('#href').val(),
            'description': $('#description').val(),
            'poster': $('#poster').val(),
        }

        $.ajax({
            url: url,
            type: 'POST',
            data: formData
        }).then(function (data) {
            window.location.href = "http://localhost:8080/Java4_Youtube_war/admin/video?action=view"
        }).fail(function (error) {
            $('#message').text("Create fail, please try again!");
        });
    });

    $('#poster').change(function () {
        var newSrc = $('#poster').val();
        $('#imgPreview').attr('src', newSrc);
    });
</script>
<%@include file="/common/footer.jsp" %>
</body>
</html>
