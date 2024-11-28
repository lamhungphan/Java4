$(document).ready(function () {
    // Bắt sự kiện click vào từng mục trong dropdown
    $('#selectVideo .dropdown-item').on('click', function (e) {
        e.preventDefault(); // Ngăn mở link
        var videoHref = $(this).data('href'); // Lấy giá trị data-href

        // Gửi AJAX request để lấy thông tin user
        $.ajax({
            url: 'admin/favorite?href=' + videoHref,
            type: 'GET',
            contentType: 'application/json',
            success: function (data) {
                // Kiểm tra dữ liệu trả về
                console.log(data);

                // Làm mới bảng
                var table = $('#example').DataTable();
                table.clear(); // Xóa dữ liệu cũ

                // Thêm dữ liệu mới vào bảng
                table.rows.add(data);
                table.draw();
            },
            error: function (xhr, status, error) {
                console.error('Error fetching favorite info:', error);
            }
        });
    });

    // Khởi tạo DataTable cho bảng nếu chưa có
    $('#example').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true,
        "columns": [
            { "data": "username" },
            { "data": "email" }
        ]
    });
});