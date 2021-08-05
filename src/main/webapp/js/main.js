function newGame() {
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                location.reload();
            } else {
                alert(xhr.responseText);
            }
        }
    }
    xhr.open('POST', '/new', true);
    xhr.send(null);
}

function play(pit) {
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                location.reload();
            } else {
                alert(xhr.responseText);
            }
        }
    }
    xhr.open('POST', '/play/' + pit, true);
    xhr.send(null);
}

