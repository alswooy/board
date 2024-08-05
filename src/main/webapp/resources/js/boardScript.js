
$(document).ready(function () {
    $('#registerBtn').click(function (event) {
        event.preventDefault();
        const title = $('#title').val();
        const content = $('#content').val();
        let form = $("#form");
        if (title && content) {
            form.attr("action", "/write");
            form.attr("method", "post");
            form.submit();
        }else{
            alert("제목과 내용을 입력해주세요.");
        }
    });
});