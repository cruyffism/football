$( document ).ready(function() {
    console.log( "ready!" );
    teamInfoAjax("FW",1);
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
            document.getElementById("positionTab_FW").classList.remove("active");
            document.getElementById("positionTab_MF").classList.remove("active");
            document.getElementById("positionTab_DF").classList.remove("active");
            document.getElementById("positionTab_GK").classList.remove("active");
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
