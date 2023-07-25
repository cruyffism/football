$(document).ready(function () {
    rankTeamAjax(1,"game_match","DESC",2)
});

function rankTeamAjax(league_id , sortColumn, sortType, idx) {
    const innerHtml = $("#rankList")
    const f = document.getElementById("form");
    f.sortColumn.value = sortColumn;
    f.sortType.value = sortType;
    f.idx.value = idx;

    $.ajax({
        url: "/rank/team/" + league_id,
        type: 'GET',
        cache: false,
        data: $('#form').serialize(),
        dataType: "html",
        async: false,
        success: function (data) {
            document.getElementById("rankTab2").classList.remove("active");
            document.getElementById("rankTab1").className += " active"
            $(innerHtml).html(data)
            if (sortType !== 'undefined') {
                if (sortType === 'DESC') {
                    $(`#sort`+idx).attr({src:"../../static/images/rank/sortDown.png"})
                } else {
                    $(`#sort`+idx).attr({src:"../../static/images/rank/sortUp.png"})
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

function rankPlayerAjax(page, sortColumn, sortType, idx) {
    const innerHtml = $("#rankAllList")
    const f = document.getElementById("form1");
    f.page.value = page;
    f.sortColumn.value = sortColumn;
    f.sortType.value = sortType;
    f.idx.value = idx;

    $.ajax({
        url: "/rank/player",
        type: 'GET',
        cache: false,
        dataType: "html",
        data: $('#form1').serialize(),
        async: false,
        success: function (data) {
            document.getElementById("rankTab1").classList.remove("active");
            document.getElementById("rankTab2").className += " active"
            $(innerHtml).html(data)
            if (sortType !== 'undefined') {
                if (sortType === 'DESC') {
                    $(`#sort`+idx).attr({src:"../../static/images/rank/sortDown.png"})
                } else {
                    $(`#sort`+idx).attr({src:"../../static/images/rank/sortUp.png"})
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

function podiumAjax() {
    const innerHtml = $("#rankList")
    $.ajax({
        url: "/rank/player/podium",
        type: 'GET',
        cache: false,
        dataType: "html",
        async: false,
        success: function (data) {
            document.getElementById("rankTab2").classList.remove("active");
            document.getElementById("rankTab1").className += " active"
            $(innerHtml).html(data)
            setTimeout(function () {
            }, 1000)
        },
        error: function (e) {
            $(innerHtml).html("")
        }
    })
    rankPlayerAjax(1,'rating','DESC',6);
}

function leagueTabAjax(league_id) {
    rankTeamAjax(league_id,"game_match","DESC",2)
    $('.nav-link').not(this).removeClass('active');
    // document.getElementById("rankTab2").classList.remove("active");
    document.getElementById("rankTab1").className += " active"
    document.getElementById("leagueTab" + league_id).className += " active";
}

function sortUpDown(idx) {
    const imgTag = document.getElementById("sort" + idx);
    if ($('#sortType').val() === 'DESC' && imgTag.className === $('#sortColumn').val()) {
        rankPlayerAjax('1', imgTag.className, 'ASC', idx);
    } else {
        rankPlayerAjax('1', imgTag.className, 'DESC', idx);
    }
}

function teamSortUpDown(idx) {
    const imgTag = document.getElementById("sort" + idx);
    if ($('#sortType').val() === 'DESC' && imgTag.className === $('#sortColumn').val()) {
        rankTeamAjax(1, imgTag.className, 'ASC', idx);
    } else {
        rankTeamAjax(1, imgTag.className, 'DESC', idx);
    }
}