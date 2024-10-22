// Set up Variable
let staffId = document.getElementById("staffId");
let password = document.getElementById("password");

// Change language
// Set up language
let lang = {
    eng: {
        title: "Sign in",
        staffPh: "Staff id",
        passwordPh: "New password",
        signIn: "Sign in",
        forgot: "Forgot password"
    },
    thai: {
        title: "เข้าสู่ระบบ",
        staffPh: "ไอดีพนักงาน",
        passwordPh: "รหัสผ่าน",
        signIn: "เข้าสู่ระบบ",
        forgot: "ลืมรหัสผ่าน"
    }
}
let langContainer = document.getElementById("langContainer");
let title = document.getElementById("title");
let signInBtn = document.getElementById("signInButton");
let forgot = document.getElementById("forgot");
let en = document.getElementById("en");
let th = document.getElementById("th");
// Set default value
if (langContainer.getAttribute("data-value") == "en") {
    en.style.color = "#CF2D1E";
    th.style.color = "black";
}
else {
    th.style.color = "#CF2D1E";
    en.style.color = "black";
}
// English
en.onclick = function() {
    langContainer.setAttribute("data-value", "en");
    en.style.color = "#CF2D1E";
    th.style.color = "black";
    title.innerHTML = lang.eng.title;
    staffId.placeholder = lang.eng.staffPh;
    password.placeholder = lang.eng.passwordPh;
    signInBtn.innerHTML = lang.eng.signIn;
    forgot.innerHTML = lang.eng.forgot;
}
// Thai
th.onclick = function() {
    langContainer.setAttribute("data-value", "th");
    th.style.color = "#CF2D1E";
    en.style.color = "black";
    title.innerHTML = lang.thai.title;
    staffId.placeholder = lang.thai.staffPh;
    password.placeholder = lang.thai.passwordPh;
    signInBtn.innerHTML = lang.thai.signIn;
    forgot.innerHTML = lang.thai.forgot;
}


// Eye icon in password input type
let eyeIcon = document.getElementById("passIcon");
// Password field
eyeIcon.addEventListener("click", function(){
    if (password.getAttribute("type") == "password") {
        eyeIcon.setAttribute("src", "/img/visible-icon.svg");
        password.setAttribute("type", "text");
    } else {
        eyeIcon.setAttribute("src", "/img/hidden-icon.svg");
        password.setAttribute("type", "password");
    }
})


// Sign in Button
signInBtn.onclick = function() {
    let staff = staffId.value;
    let passwordValue = password.value;
    let errorMessage = document.getElementById("errorMessage");  
    // Error text function
    function errorAlert(enError, thError) {
        errorMessage.style.visibility = "visible";
        if (langContainer.getAttribute("data-value") == "en") {
            errorMessage.textContent = enError;
        } else {
            errorMessage.textContent = thError;
        }
        setTimeout(() => {
            errorMessage.style.visibility = "hidden";
        }, 3000)
    }
    // Check statement
    // All fields are not entered
    if (!staff || !passwordValue) {
        errorAlert("Please fill all fields", "กรุณากรอกข้อมูลให้ครบทุกช่อง");
        return;
    }
    // Password are not reach min length
    if (passwordValue.length < 6 || passwordValue.length > 12) {
        errorAlert("Password must be at 6 - 12 characters long", "รหัสผ่านต้องมีความยาว 6 - 12 ตัวอักษร");
        return;
    }
    // Go to Dashboard page
};