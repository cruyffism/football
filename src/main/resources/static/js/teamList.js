$(document).ready(function () {
    console.log("ready!");
    leagueTabAjax(1);
});

function leagueTabAjax(league_id) {
    console.log("league_id : ", league_id);
    const innerHtml = $("#teamList")
    $.ajax({
        url: "/team/listAjax/" + league_id,
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
