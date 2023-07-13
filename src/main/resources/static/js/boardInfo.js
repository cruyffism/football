function editForm(boardCommentsId, boardId, nickname, content, createDate, updateDate) {
    if (updateDate == null) {
        updateDate = ""
    }
    console.log("realDate : " + updateDate)
    let commentsview = '<div id="comment' + boardCommentsId + '">';
    commentsview += '<input type="hidden" name="boardId" id="boardId" value="' + boardId + '">';
    commentsview += '<input type="hidden" name="boardCommentsId" id="boardCommentsId" value="' + boardCommentsId + '">';
    commentsview += '<div class="mb-3 row">';
    commentsview += '<div class="col-sm-2">';
    commentsview += '<p type="text">' + nickname + '</p>';
    commentsview += '</div>';
    commentsview += '<div class="col-sm-6">';
    commentsview += '<textarea type="text" id="content" name="content">' + content + '</textarea>';
    commentsview += '</div>';
    commentsview += '<div class="col-sm-4">';
    commentsview += '<button class="btn btn-primary" type="submit" style="margin-right: 10px">수정</button>';
    commentsview += '<button class="btn btn-primary" type="button" ' +
        'onclick="getCommentList(' + boardCommentsId + ',' + boardId + ', \'' + nickname + '\' , \'' + content + '\', \'' + createDate + '\',\'' + updateDate + '\')">취소</button>';
    commentsview += '</div>';
    commentsview += '</div>';
    commentsview += '</div>';

    $('#comment' + boardCommentsId).replaceWith(commentsview)
    $('#comment' + boardCommentsId + '#content').focus()
}

function getCommentList(boardCommentsId, boardId, nickname, content, createDate, updateDate) {
    let realDate = ""
    if (updateDate !== "") {
        realDate = updateDate
    } else {
        realDate = createDate
    }

    let commentsview = '<div class="mb-3 row" id="comment' + boardCommentsId + '">';
    commentsview += '<div class="col-sm-2">'
    commentsview += '<p>' + nickname + '</p>'
    commentsview += '</div>'
    commentsview += '<div class="col-sm-6">'
    commentsview += '<p>' + content + '</p>'
    commentsview += '</div>'
    commentsview += '<div class="col-sm-2">'
    commentsview += '<p>' + realDate + '</p>'
    commentsview += '</div>'
    commentsview += '<div class="col-sm-2">'
    commentsview += '<img src="/static/images/board/edit.png"'
    commentsview += 'onclick="editForm(' + boardCommentsId + ',' + boardId + ', \'' + nickname + '\' , \'' + content + '\', \'' + createDate + '\',\'' + updateDate + '\')">'
    commentsview += '<img src="/static/images/board/delete.png"'
    commentsview += 'onclick="location.href=\'/board/commentDelete/' + boardCommentsId + '?boardId=' + boardId + '\'">'
    commentsview += '</div>';

    $('#comment' + boardCommentsId).replaceWith(commentsview)

}