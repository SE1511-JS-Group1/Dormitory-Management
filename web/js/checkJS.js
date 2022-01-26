/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global Room */

function CheckRegister() {
    return false;
}

function loadRoomInformation(name,floor, status, gender) {
    document.getElementById('chosenName').value = name;
    document.getElementById('chosenFloor').value = floor;
    document.getElementById('chosenStatus').value = status + ' Bed';
    document.getElementById('chosenGender').value = gender;
}
