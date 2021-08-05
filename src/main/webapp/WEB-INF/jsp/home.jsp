<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
    <script>
        window.onload = function () {
        };
    </script>
</head>
<body>
<div class="header">
    <center><h1>Mancala</h1></center>
    <center><h2>
        <c:choose>
            <c:when test="${state eq 'NEW'}">
                Welcome to Mancala.
            </c:when>
            <c:when test="${state eq 'PLAYER1'}">
                Player 1's turn to play.
            </c:when>
            <c:when test="${state eq 'PLAYER2'}">
                Player 2's turn to play.
            </c:when>
            <c:when test="${state eq 'FINISHED'}">
                Game over !
                <c:choose>
                    <c:when test="${fn:length(winner) eq 2}">
                        It's a draw !
                    </c:when>
                    <c:otherwise>
                        Winner is: ${winner[0]}
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
    </h2></center>
</div>
<br/>
<br/>

<c:if test="${not empty board}">
    <table class="mancala-table">
        <tr class="<c:choose><c:when test = "${state eq 'PLAYER1'}">clickable</c:when><c:otherwise>not-clickable</c:otherwise></c:choose>">
            <td class="mancala-pit-cell"<c:if test="${state eq 'PLAYER1'}"> onclick="play(12)"</c:if>>${board[12]}</td>
            <td class="mancala-pit-cell"<c:if test="${state eq 'PLAYER1'}"> onclick="play(11)"</c:if>>${board[11]}</td>
            <td class="mancala-pit-cell"<c:if test="${state eq 'PLAYER1'}"> onclick="play(10)"</c:if>>${board[10]}</td>
            <td class="mancala-pit-cell"<c:if test="${state eq 'PLAYER1'}"> onclick="play(9)"</c:if>>${board[9]}</td>
            <td class="mancala-pit-cell"<c:if test="${state eq 'PLAYER1'}"> onclick="play(8)"</c:if>>${board[8]}</td>
            <td class="mancala-pit-cell"<c:if test="${state eq 'PLAYER1'}"> onclick="play(7)"</c:if>>${board[7]}</td>
        </tr>
        <tr>
            <td class="mancala-cell">${board[13]}</td>
            <td class="mancala-empty-cell" style="text-align: center;vertical-align: top;font-size:12px">Player 1
                &#8593;
            </td>
            <td class="mancala-empty-cell"></td>
            <td class="mancala-empty-cell"></td>
            <td class="mancala-empty-cell" style="text-align: center;vertical-align: bottom;font-size:12px">Player 2
                &#8595;
            </td>
            <td class="mancala-cell">${board[6]}</td>
        </tr>
        <tr class="<c:choose><c:when test = "${state eq 'PLAYER2'}">clickable</c:when><c:otherwise>not-clickable</c:otherwise></c:choose>">
            <td class="mancala-pit-cell"<c:if test="${state eq 'PLAYER2'}"> onclick="play(0)"</c:if>>${board[0]}</td>
            <td class="mancala-pit-cell"<c:if test="${state eq 'PLAYER2'}"> onclick="play(1)"</c:if>>${board[1]}</td>
            <td class="mancala-pit-cell"<c:if test="${state eq 'PLAYER2'}"> onclick="play(2)"</c:if>>${board[2]}</td>
            <td class="mancala-pit-cell"<c:if test="${state eq 'PLAYER2'}"> onclick="play(3)"</c:if>>${board[3]}</td>
            <td class="mancala-pit-cell"<c:if test="${state eq 'PLAYER2'}"> onclick="play(4)"</c:if>>${board[4]}</td>
            <td class="mancala-pit-cell"<c:if test="${state eq 'PLAYER2'}"> onclick="play(5)"</c:if>>${board[5]}</td>
        </tr>
    </table>
</c:if>

<c:if test="${state eq 'NEW' || state eq 'FINISHED'}">
    <br/><br/>
    <center>
        <button onclick="newGame();" class="btn-newgame">New Game</button>
    </center>
</c:if>

</body>
</html>