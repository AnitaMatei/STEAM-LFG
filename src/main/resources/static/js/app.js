const rootURL = "";//window.location.origin;
const pageURL = rootURL + "/api/announcement/page/";
const gamesSearchURL = rootURL + "/api/game/search";

let postsArray;
let searchInput;

function addDataToTable() {
    let tbody = document.querySelector("#posts-body");
    tbody.innerHTML = "";        // Clear previous data, to prepare for loading new data

    for (let i = 0; i < postsArray.length; ++i) {
        let th = document.createElement("th");
        th.scope = "row";
        th.textContent = (i + 1).toString();
        let title_td = document.createElement("td");
        //TODO Find a way to put a link to the announcement's templated view, from javascrip
        let td_a = document.createElement("a");
        td_a.textContent = postsArray[i].announcementTitle;
        td_a.setAttribute("th:href", "=@{/announcements/" + postsArray[i].announcementHash.toString() + "}");
        td_a.type = "submit";
        title_td.appendChild(td_a);
        let gameName_td = document.createElement("td");
        gameName_td.textContent = postsArray[i].gameByGameId.gameName;
        let userName_td = document.createElement("td");
        userName_td.innerHTML = '<a href="' + postsArray[i].userByUserId.steamProfile + '">' +
            postsArray[i].userByUserId.username + '</a>';
        let tr = document.createElement("tr");
        tr.append(th, gameName_td, title_td, userName_td);
        tbody.appendChild(tr);
    }
}

function setGamesSelectOptions(gamesJson) {
    let select = document.getElementById("matched-games");
    select.innerHTML = '';  //Clear old options
    for (let i = 0; i < gamesJson.length; i++) {
        let option = document.createElement("option");
        option.value = gamesJson[i].steamAppId;
        option.innerText = gamesJson[i].gameName;
        select.appendChild(option);
    }
}

function getGameSearchResults() {
    let gameName = searchInput.value;
    if (gameName !== '') {
        let url = new URL(gamesSearchURL);
        let params = {gameName: searchInput.value};
        url.search = new URLSearchParams(params).toString();
        fetch(url.toString())
            .then(response => response.json())
            .then(data => {
                setGamesSelectOptions(data);
                console.log(data);
            })
            .catch(err => console.log('ERROR: ', err.message));
    }
}

function getPagePosts(pageId) {
    fetch(pageURL + pageId)
        .then(response => response.json())
        .then(data => {
            postsArray = data;
            addDataToTable();
            // console.log(data);
        })
        .catch(err => console.log('ERROR: ', err.message));
}

function onCreateBtnClick() {
    // searchInput = document.querySelector("#userDropdown .bs-searchbox input");
    searchInput = document.querySelector("#select-game");
    searchInput.addEventListener('keyup', getGameSearchResults);
}

function onLoad() {
    getPagePosts(0);
}