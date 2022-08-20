const ENTER_KEY_CODE = 13;
const MAX_ASCII_CODE = 127

function containsNonASCII(str) {
    return [...str].some(char => char.charCodeAt(0) > MAX_ASCII_CODE);
}

function search(keyEvent) {
    if (keyEvent.keyCode !== ENTER_KEY_CODE) {
        return;
    }

    let searchBar = document.getElementById("search-bar");

    let searchText = searchBar.value.trim();

    if (searchText.length === 0) {
        redirect("/sets");
    }

    if (containsNonASCII(searchText)) {
        searchBar.value = "";
        return;
    }

    redirect("/sets?filter=" + searchText);
}

function setupHeaderSearch() {
    document.getElementById("search-bar").addEventListener('keyup', search);
}

function fillCurrentSearch() {
    let searchParams = new URLSearchParams(window.location.search);
    let filter = searchParams.get('filter');

    if (filter === null) {
        return;
    }

    document.getElementById("search-bar").value = filter;
}

function createRedirect() {
    let numberInput = document.getElementById("number-input");

    let number = numberInput.value === "" ? 0 : parseInt(numberInput.value);

    number = Math.max(0, number);

    redirect("/sets/create?numberOfCards=" + number);
}