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
    $('#example2').DataTable({
        "paging": true, // Bật phân trang
        "lengthChange": false, // Tắt tính năng thay đổi số dòng hiển thị
        "searching": true, // Bật tính năng tìm kiếm
        "ordering": true, // Bật sắp xếp cột
        "info": true, // Hiển thị thông tin về số dòng
        "autoWidth": false, // Không tự động điều chỉnh chiều rộng cột
        "responsive": true, // Bật chế độ phản hồi
        "columns": [
            { "data": "title" },
            { "data": "link" },
            { "data": "totalLike" }
        ]
    });
});