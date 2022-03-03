/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global Room */
function CheckRegister() {
    var txt = document.getElementById('message_username').innerHTML.trim().length
            + document.getElementById('message_password').innerHTML.trim().length
            + document.getElementById('message_confirmpassword').innerHTML.trim().length
            + document.getElementById('message_email').innerHTML.trim().length
            + document.getElementById('message_phone').innerHTML.trim().length;
    document.getElementById('message_username').style.display = 'block';
    document.getElementById('message_password').style.display = 'block';
    document.getElementById('message_confirmpassword').style.display = 'block';
    document.getElementById('message_email').style.display = 'block';
    document.getElementById('message_phone').style.display = 'block';
    return txt === 0;
}
function CheckConfirmPassword(param) {
    document.getElementById('message_username').style.display = 'none';
    document.getElementById('message_password').style.display = 'none';
    document.getElementById('message_confirmpassword').style.display = 'none';
    document.getElementById('message_email').style.display = 'none';
    document.getElementById('message_phone').style.display = 'none';
    var confirmpass = param.value;
    param.value = confirmpass.trim();
    var password = document.getElementById('password').value;
    if (password !== confirmpass) {
        document.getElementById('message_confirmpassword').innerHTML = 'Mật khẩu không khớp';
    } else {
        document.getElementById('message_confirmpassword').innerHTML = '';
    }
}
function CheckPassword(param) {
    document.getElementById('message_username').style.display = 'none';
    document.getElementById('message_password').style.display = 'none';
    document.getElementById('message_confirmpassword').style.display = 'none';
    document.getElementById('message_email').style.display = 'none';
    document.getElementById('message_phone').style.display = 'none';
    var password = param.value;
    param.value = password.trim();
    var lengthPass = password.length;
    if (lengthPass < 6 || lengthPass > 32) {
        document.getElementById('message_password').innerHTML = 'Mật khẩu phải chứa 6-32 ký tự không bao gồm khoảng trắng';
    } else {
        document.getElementById('message_password').innerHTML = '';
    }
}
function CheckAccount(type, param) {
    document.getElementById('message_username').style.display = 'none';
    document.getElementById('message_password').style.display = 'none';
    document.getElementById('message_confirmpassword').style.display = 'none';
    document.getElementById('message_email').style.display = 'none';
    document.getElementById('message_phone').style.display = 'none';
    var txt = param.value.trim();
    param.value = txt;
    $.ajax({
        url: "/Dormitory/checkaccount",
        type: "get", //send it through get method
        data: {
            type: type,
            txt: txt
        },
        success: function (data) {
            var row = document.getElementById(type);
            row.innerHTML = data;
        },
        error: function (xhr) {
            //Do Something to handle error
        }
    });
}

function loadRoomInformation(name, floor, status, gender) {
    document.getElementById('chosenName').value = name;
    document.getElementById('chosenFloor').value = floor;
    document.getElementById('chosenStatus').value = status + ' Bed';
    document.getElementById('chosenGender').value = gender;
}
function loadUserInformation(fullname, gender, dob, email, phone) {
    document.getElementById('chosenFullName').value = fullname;
    document.getElementById('chosenUGender').value = gender;
    document.getElementById('chosenDOB').value = dob;
    document.getElementById('chosenEmail').value = email;
    document.getElementById('chosenPhone').value = phone;
}
