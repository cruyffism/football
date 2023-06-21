$( document ).ready(function() {
    console.log( "ready!" );
    const team_id = $("#teamId").val();
    teamInfoAjax("FW",team_id);
});

function teamInfoAjax(position,team_id) {
    const innerHtml = $("#playerList")
    $.ajax({
        url: "/team/infoAjax/" + team_id + "?position=" + position,
        type: 'GET',
        cache: false,
        dataType: "html",
        async: false,
        success: function (data) {
            $('.nav-link').removeClass('active');
            document.getElementById("positionTab_" + position).className += " active"
            $(innerHtml).html(data)
            setTimeout(function () {
            }, 1000)
        },
        error: function (e) {
            $(innerHtml).html("")
        }
    })
}
