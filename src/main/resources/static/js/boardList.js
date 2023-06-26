$(document).ready(function () { // 페이지가 로딩되는 순간 바로 실행
    console.log("ready!");
    boardListAjax(1,"creat_date","DESC",2); // 1,"creat_date","DESC",2
});

function boardListAjax(page, sortColumn, sortType, idx) { // page, sortColumn, sortType, idx
    // console.log("page : " + page)
    // console.log("sortColumn : " + sortColumn)
    // console.log("sortType : " + sortType)
    // console.log("idx : " + idx)
    const innerHtml = $("#boardListAjax")
    const f = document.getElementById("form1");
    f.page.value = page;
    f.sortColumn.value = sortColumn;
    f.sortType.value = sortType;
    f.idx.value = idx;
    console.log("page : " + f.page.value)

    $.ajax({
        url: "/board/listAjax",
        type: 'GET',
        cache: false,
        dataType: "html",
        async: false,
        success: function (data) {
            // $('.nav-link').removeClass('active');
            // document.getElementById("leagueTab" + league_id).className += " active"
            $(innerHtml).html(data)
            if (sortType !== 'undefined') {
                if (sortType === 'DESC') {
                    $(`#sort`+idx).attr({src:"../../static/images/rank/sortDown.svg"})
                } else {
                    $(`#sort`+idx).attr({src:"../../static/images/rank/sortUp.svg"})
                }
            }
            setTimeout(function () {
            }, 1000)
        },
        error: function (e) {
            $(innerHtml).html("")
        }
    })
}

function sortUpDown(idx) {
    const imgTag = document.getElementById("sort" + idx);
    if ($('#sortType').val() === 'DESC' && imgTag.className === $('#sortColumn').val()) {
        boardListAjax('1', imgTag.className, 'ASC', idx);
    } else {
        boardListAjax('1', imgTag.className, 'DESC', idx);
    }
}

