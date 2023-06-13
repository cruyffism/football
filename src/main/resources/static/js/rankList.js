$(document).ready(function () {
    console.log("ready!");
    rankListAjax(1);
});

function rankListAjax(type) {
    console.log("league_id : ", type);
    const innerHtml = $("#rankList")
    if (type === 1) {
        $.ajax({
            url: "/rank/team/1",
            type: 'GET',
            cache: false,
            dataType: "html",
            async: false,
            success: function (data) {
                // document.getElementById("rankTab1").classList.remove("active");
                document.getElementById("rankLeagueListTab2").classList.remove("active");
                document.getElementById("rankTab1").className += " active"
                $(innerHtml).html(data)
                setTimeout(function () {
                }, 1000)
            },
            error: function (e) {
                $(innerHtml).html("")
            }
        })

    } else {
        $.ajax({
            url: "/rank/player",
            type: 'GET',
            cache: false,
            dataType: "html",
            async: false,
            success: function (data) {
                document.getElementById("rankTab1").classList.remove("active");
                document.getElementById("rankTab2").classList.remove("active");
                document.getElementById("rankTab2").className += " active"
                $(innerHtml).html(data)
                setTimeout(function () {
                }, 1000)
            },
            error: function (e) {
                $(innerHtml).html("")
            }
        })
    }
}

function rankLeagueListAjax(league_id) {
    console.log("league_id : ", league_id);
    const innerHtml = $("#rankList")
    $.ajax({
        url: "/rank/team/" + league_id,
        type: 'GET',
        cache: false,
        dataType: "html",
        async: false,
        success: function (data) {
            document.getElementById("leagueTab1").classList.remove("active");
            document.getElementById("leagueTab2").classList.remove("active");
            document.getElementById("leagueTab3").classList.remove("active");
            document.getElementById("leagueTab4").classList.remove("active");
            document.getElementById("leagueTab5").classList.remove("active");
            document.getElementById("leagueTab" + league_id).className += " active"
            $(innerHtml).html(data)
            setTimeout(function () {
            }, 1000)
        },
        error: function (e) {
            $(innerHtml).html("")
        }
    })
}