<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판</title>
    <link rel="stylesheet" href="/css/boardCss.css">
</head>
<body>
    <div class="container">

        <h1>총 글의 수 : ${cnt}</h1>
        <form action = "" id="form" accept-charset="UTF-8">
            <h2>제목</h2>
            <input type="hidden" id="bno" name="bno" value="${boardDto.bno}">
            <input type="text" id="title" name= "title" value="${boardDto.title}" placeholder="제목을 입력해주세요.">
            <div class = "container2">
                <h2>내용</h2>
                <h3 id = "date" class = "date">작성날짜 : ${boardDto.reg_date}</h3>
            </div>
            <textarea id="content" name="content" rows="5"  placeholder="내용을 입력해주세요.">${boardDto.content}</textarea>

            <button id="registerBtn">등록</button>
            <button id="updateBtn" disabled>수정</button>
            <button id="deleteBtn" disabled>삭제</button>
        </form>
    </div>
    <div class = "posts-container">
        <table>
            <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>내용</th>
                <th>조회수</th>
            </tr>
            </thead>
            <tbody id="itemList">
                <c:forEach var="boardDto" items="${list}">
                    <tr>
                        <td>${boardDto.bno}</td>
                        <td>${boardDto.title}</td>
                        <td>${boardDto.content}</td>
                        <td>${boardDto.view_cnt}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<%--<script src="scripts/main.js"></script>--%>
<%--<script src="/js/script.js"></script>--%>
<script>
    $(document).ready(function () {
        let selectedRow = null;

        function clearInputs() { <!--입력이 되면 작동되는 -->
            $('#title').val('');
            $('#content').val('');
            selectedRow = null;
            $('#updateBtn').prop('disabled', true);
            $('#deleteBtn').prop('disabled', true);
        }

        $('#registerBtn').click(function (event) {
            event.preventDefault();
            const title = $('#title').val();
            const content = $('#content').val();
            const bnoField = $('#bno');
            bnoField.val('0');

            let form = $("#form");
            if (title && content) {
                form.attr("action", "/write");
                form.attr("method", "post");
                form.submit();
            }else{
                alert("제목과 내용을 입력해주세요.");
            }
        });

        $('#itemList').on('click', 'tr', function() {
            const bno = $(this).find('td:eq(0)').text();
            const title = $(this).find('td:eq(1)').text();
            const content = $(this).find('td:eq(2)').text();
            const date = $(this).find('td:eq(4)').text();
            $("#bno").val(bno);
            $('#title').val(title);
            $('#content').val(content);
            $('#date').text('작성날짜 : ' + date);

            selectedRow = $(this);
            $('#registerBtn').prop('disabled', true);
            $('#updateBtn').prop('disabled', false);
            $('#deleteBtn').prop('disabled', false);
        });

        $('#updateBtn').click(function() {
            if(selectedRow) {
                const bno = selectedRow.find('td:eq(0)').text($('#bno').val());
                const title = selectedRow.find('td:eq(1)').text($('#title').val());
                const content = selectedRow.find('td:eq(2)').text($('#content').val());
                // clearInputs();
                if (title && content) {
                    let form = $("#form");
                    form.attr("action", "/modify");
                    form.attr("method", "post");
                    form.submit();
                }else{
                    alert('수정할 제목과 내용을 입력해주세요!');
                }
            } else {
                alert('수정할 항목을 선택해주세요.');
            }
        });

        $('#deleteBtn').click(function() {
            let form = $("#form");
            form.attr("action", "/remove");
            form.attr("method", "post");
            form.submit();
            if(selectedRow) {
                clearInputs();
            } else {
                alert('삭제할 항목을 선택해주세요.');
            }
        });
    });
</script>
</body>
</html>