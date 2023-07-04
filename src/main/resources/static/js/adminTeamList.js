$(document).ready(function () {
    console.log("ready!");
    leagueTabAjax(1);
});

function leagueTabAjax(league_id) {
    console.log("league_id : ", league_id);
    const innerHtml = $("#teamList")
    $.ajax({
        url: "/admin/listAjax/" + league_id,
        type: 'GET',
        cache: false,
        dataType: "html",
        async: false,
        success: function (data) {
            $('.nav-link').removeClass('active');
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
