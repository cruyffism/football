$(document).ready(function () { // 페이지가 로딩되는 순간 바로 실행
    console.log("ready!");
    memberListAjax(1,"username","DESC",1); // 1,"creat_date","DESC",2
});

function memberListAjax(page, sortColumn, sortType, idx) { // page, sortColumn, sortType, idx
    const innerHtml = $("#memberList")
    const f = document.getElementById("form1");
    f.page.value = page;
    f.sortColumn.value = sortColumn;
    f.sortType.value = sortType;
    f.idx.value = idx;
    f.noticeSearchText.value = $('#searchText').val();
    // id가 searchtext인 애의 값(list.html의 39라인)을 list.html의 31라인의 value="" 에다가 값을 넣어주는 것.

    const select_value = document.getElementById('searchType');
    //teamList.html 에서 id가 searchType인 애의 값을 가져와서 변수 select_value에 넣는다.
    f.noticeSearchType.value = select_value.options[select_value.selectedIndex].value;
    // select_value 변수에서 option이 선택된 값을 list.htmldml 32라인의 value=""에 넣는거!

    $.ajax({
        url: "/admin/memberListAjax",
        type: 'GET',
        cache: false,
        data: $('#form1').serialize(),
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
        memberListAjax('1', imgTag.className, 'ASC', idx);
    } else {
        memberListAjax('1', imgTag.className, 'DESC', idx);
    }
}

