$(document).ready(function () {
    console.log("ready!");
    rankTeamAjax(1);
});

function rankTeamAjax(league_id) {
    console.log("league_id : ", league_id);
    const innerHtml = $("#rankList")

    $.ajax({
        url: "/rank/team/" + league_id,
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
}

function rankPlayerAjax(page, sortColumn, sortType, idx) {
    const innerHtml = $("#rankList")
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
                    console.log("내림2")
                    $(`#sort`+idx).attr({src:"../../static/images/rank/sortDown.svg"})
                    $('#type').val('D');
                    $('#typeCol').val('${PageMaker.criteria.sortColumn}');
                } else {
                    console.log("오름2")
                    $(`#sort`+idx).attr({src:"../../static/images/rank/sortUp.svg"})
                    $('#type').val('A');
                    $('#typeCol').val('${PageMaker.criteria.sortColumn}');
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

function leagueTabAjax(league_id) {
    rankTeamAjax(league_id);
    document.getElementById("leagueTab1").classList.remove("active");
    document.getElementById("leagueTab2").classList.remove("active");
    document.getElementById("leagueTab3").classList.remove("active");
    document.getElementById("leagueTab4").classList.remove("active");
    document.getElementById("leagueTab5").classList.remove("active");
    document.getElementById("leagueTab" + league_id).className += " active";
}

function sortUpDown(idx) {
    const imgTag = document.getElementById("sort" + idx);
    if ($('#type').val() === 'D' && imgTag.className === $('#typeCol').val()) {
        rankPlayerAjax('1', imgTag.className, 'ASC', idx);
    } else {
        rankPlayerAjax('1', imgTag.className, 'DESC', idx);
    }
}