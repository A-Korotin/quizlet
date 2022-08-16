const ENTER_KEY_CODE = 13;

function search(keyEvent) {
    if (keyEvent.keyCode !== ENTER_KEY_CODE) {
        return;
    }

    let searchText = document.getElementById("search-bar").value.trim();

    if (searchText.length === 0) {
        redirect("/sets");
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