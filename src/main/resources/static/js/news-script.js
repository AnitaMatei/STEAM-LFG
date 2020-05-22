const rootURL = window.location.origin;
const gamesSearchURL = rootURL + "/api/game/search";

let searchInput;

function setGamesSelectOptions(gamesJson) {
    let options = [];
    gamesJson.forEach(function (gameObject) {
        let option = document.createElement("option");
        option.value = gameObject.steamAppId;
        option.innerText = gameObject.gameName;
        options.push(option);
    });

    let select_game = $('#select-game').html(options);
    select_game.selectpicker('refresh');
}

function onSearchInput() {
    let gameName = searchInput.value;
    console.log(gamesSearchURL);
    if (gameName !== '') {
        let url = new URL(gamesSearchURL);
        let params = {gameName: gameName};
        url.search = new URLSearchParams(params).toString();
        fetch(url.toString())
            .then(response => response.json())
            .then(data => {
                setGamesSelectOptions(data);
                // console.log(data);
            })
            .catch(err => console.error('ERROR: ', err.message));
    }
}

function onLoad() {
    setTimeout(function () {
        searchInput = document.querySelector('#search-div .bs-searchbox input');
        searchInput.addEventListener('input', onSearchInput);
    }, 100);
}