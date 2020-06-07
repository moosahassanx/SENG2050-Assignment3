function fileValidation(){

    var description = document.getElementById("input-title");
    var file = document.getElementById("file");
        
    if(description.value === ""){
        alert("Invalid description");
        description.focus();
        return false;
    }
    if(file.files.length === 0){
        alert("Must attach a file");
        file.focus();
        return false;
    }
    return true;
} 

function discussionsValidate(){

    var title = document.getElementById("title");
    var message = document.getElementById("message");

    if(title.value === ""){
        alert("Thread needs a title");
        title.focus();
        return false;
    }
    if(message.value === ""){
        alert("Message needs text pls");
        message.focus();
        return false;
    }
return true;
}

function loginValidation(){

    var name = document.getElementById("username");
    var pass = document.getElementById("password");

    if(name.value === ""){
        alert("Must enter a username");
        name.focus();
        return false;
    }
    if(pass.value === ""){
        alert("Must enter a password");
        pass.focus();
        return false;
    }
return true;
}

function registerValidation(){

    var usernameRegex = /^[a-zA-Z0-9]+$/; // Username a-z A-Z 0-9
    var nameRegex = /^[a-zA-Z\-]+$/; // Name a-z A-Z
    var passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/ // 6 - 20 Characters with 1 numeric digit, 1 uppercase and 1 lowercase
    var phoneRegex = /^\d{10}$/; // Phone 10 digits

    var username = document.getElementById("username");
    var password = document.getElementById("password");
    var fName = document.getElementById("FirstName");
    var lName = document.getElementById("LastName");
    var phoneNo = document.getElementById("PhoneNo");

    if(username.value === "" || !username.value.match(usernameRegex)){
        alert("Incorrect username input - Only a-z, A-Z and 0-9");
        username.focus();
        return false;
    }
    if(password.value === "" || !password.value.match(passwordRegex)){
        alert("Incorrect password input - 6 - 20 chars, 1 numeric digit, 1 uppercase and 1 lowercase");
        password.focus();
        return false;
    }
    if(fName.value === "" || !fName.value.match(nameRegex) || lName.value === "" || !lName.value.match(nameRegex)){
        alert("Incorrect name input - Only a-z & A-Z");
        fName.focus();
        return false;
    }
    if(phoneNo.value === "" || !phoneNo.value.match(phoneRegex)){
        alert("Incorrect Phone input - Only 0-9 and 10 digits");
        phoneNo.focus();
        return false;
    }
return true;
}


function appointmentValidate(){

    var date = document.getElementById("date");
    var varDate = new Date(date);
    var todaysDate = new Date();

    var time = document.getElementById("time");
    var message = document.getElementById("description");

    if(date.value === "" || varDate < todaysDate){
        alert("Date must be in the future, time travel is not a thing yet");
        return false;
    }
    if(time.value === ""){
        alert("Must input a time");
        time.focus();
        return false;
    }
    if(message.value === ""){
        alert("Must input a message");
        message.focus();
        return false;
    }
    return true;
}

function responseValidation(){

    var date = document.getElementById("date").value;
    var description = document.getElementById("description");

    var varDate = new Date(date);
    var todaysDate = new Date();

    if(varDate < todaysDate || date === "dd/mm/yyyy"){
        alert("Date must be in the future, time travel is not a thing yet")
        return false;
    }
    if(description.value === ""){
        alert("Must input a description");
        description.focus();
        return false;
    }
    return true;
}

function groupValidation(){
    var groupName = document.getElementById("groupName");
    var subject = document.getElementById("subject");

    if(groupName.value === ""){
        alert("Must enter a group name")
        groupName.focus();
        return false;
    }
    if(subject.value === ""){
        alert("Must enter a subject");
        subject.focus();
        return false;
    }
    return true;
}

function milestoneValidation(){

    var title = document.getElementById("mTitle");
    var description = document.getElementById("description");
    var date = document.getElementById("date");

    var varDate = new Date(date);
    var todaysDate = new Date();

    if(title.value === ""){
        alert("Must enter a title");
        title.focus();
        return false;
    }
    if(description.value === ""){
        alert("Must enter a description");
        description.focus();
        return false;
    }
    if(varDate < todaysDate){
        alert("Date must be in the future, time travel is not a thing yet");
        return false;
    }

    return true;
}
