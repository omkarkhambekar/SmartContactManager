console.log("Script Loaded");

let currentTheme = getTheme();
console.log(currentTheme);

document.addEventListener("DOMContentLoaded",() => {
    changeTheme();
});

// TODO:
function changeTheme() {
    //set to web page
    changePageTheme(currentTheme);

    //set the listener to change theme button
    const changeThemeButton = document.querySelector('#theme_change_button');

    // changeThemeButton.addEventListener("click",(event) => {
    //     console.log("change theme button clicked");
    //     const oldTheme = currentTheme;
    //     if(currentTheme === "dark") {
    //         //theme change to light
    //         currentTheme = "light";
    //     }
    //     else {
    //         currentTheme = "dark";
    //     }
    //
    //     changePageTheme(currentTheme,oldTheme);
    // });
    if (changeThemeButton) {
        changeThemeButton.addEventListener("click", () => {
            console.log("change theme button clicked");
            const oldTheme = currentTheme;
            currentTheme = (currentTheme === "dark") ? "light" : "dark";
            changePageTheme(currentTheme, oldTheme);
        });
    }

}

// set theme to localstorage
function setTheme(theme) {
    localStorage.setItem("theme",theme);
}


//get theme from localstorage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "dark";
}

//change current page theme
function changePageTheme(theme,oldTheme) {
    setTheme(currentTheme);
    // document.querySelector('html').classList.remove(oldTheme);
    // //set the current theme
    // document.querySelector('html').classList.add(theme);
    //
    // //change the text of button
    // document.querySelector('#theme_change_button').querySelector("span").textContent= theme == "light" ? "Light" : "Dark";
    const htmlElement = document.querySelector('html');
    if (oldTheme) {
        htmlElement.classList.remove(oldTheme);
    }
    htmlElement.classList.add(theme);

    const buttonTextElement = document.querySelector('#theme_change_button span');
    if (buttonTextElement) {
        buttonTextElement.textContent = theme === "light" ? "Light" : "Dark";
    }
}

