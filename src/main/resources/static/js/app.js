const rootURL = window.location.origin;
const pageURL = rootURL + "/api/announcement/page/";
const gamesSearchURL = rootURL + "/api/game/search";

const postsPerPage = 5;
let currentPageId;
let postsArray;
let searchInput;

function addDataToTable() {
    let tbody = document.querySelector("#posts-body");
    tbody.innerHTML = "";        // Clear previous data, to prepare for loading new data

    for (let i = 0; i < postsArray.length; ++i) {
        let th = document.createElement("th");
        th.scope = "row";
        th.textContent = (currentPageId * postsPerPage + i + 1).toString();
        let title_td = document.createElement("td");
        let td_a = document.createElement("a");
        td_a.textContent = postsArray[i].announcementTitle;
        td_a.href = window.location.origin + "/announcements/" + postsArray[i].announcementHash.toString();
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

function getPagePosts(pageId) {
    fetch(pageURL + pageId)
        .then(response => response.json())
        .then(data => {
            postsArray = data;
            addDataToTable();
            // console.log(data);
        })
        .catch(err => console.error('ERROR: ', err.message));
}

function onCreateBtnClick() {
    // searchInput = document.querySelector("#userDropdown .bs-searchbox input");
    searchInput = document.querySelector("#select-game");
    searchInput.addEventListener('keyup', getGameSearchResults);
}

function loadPrevPage() {
    if (currentPageId > 0) {
        currentPageId--;
        getPagePosts(currentPageId);
    }
}

function loadNextPage() {
    fetch(pageURL + (currentPageId + 1))
        .then(response => response.json())
        .then(data => {
            if (data.length) {
                postsArray = data;
                currentPageId++;
                console.log(postsArray);
                addDataToTable();
            }
        })
        .catch(err => console.error('ERROR: ', err.message));
}

function onLoad() {
    currentPageId = 0;
    getPagePosts(0);
}