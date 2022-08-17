function redirectEdit() {
    let element = document.getElementById("edit-number-input");
    let number = element.value === "" ? 0 : parseInt(element.value);
    number = Math.max(0, number);

    redirect(document.location + "/edit?numberOfCards=" + number);
}