"use strict"

import {Toast} from "../Toast.js";

import {success, error} from "../Icons.js";

const form = document.getElementById("socio-register-form");
const registerButton = document.getElementById("register-button");
const HTMLbody = document.querySelector('body');

const container = document.createElement('div');
container.classList.add('toast-cont')

HTMLbody.prepend(container);

const URL = "http://localhost:8080"

const register = async e => {
    e.preventDefault();
    const socioData = new FormData(form);

    const formJSON = Object.fromEntries(socioData.entries())
    
    // const body = {
    //     "persona": {...formJSON}
    // }

    // console.log('body', body);
    

    const response = await fetch(`${URL}/nomina/asociar`, {
        method: "POST",
        body: JSON.stringify(formJSON),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
          }
    });

    const respJson = await response.json();
    console.log("Respuesta", respJson);
    const triggerToast = (type, icon, msgTitle, msgBody) => {
        const toastContainer = document.querySelector('.toast-cont');
        const toast = new Toast(toastContainer, type, icon, msgTitle, msgBody)
        toast.showUp();
    }

    if(response.ok){
        triggerToast("success", success, "Socio agregado", `El socio ${formJSON.nombre} fue registrado con éxito.`, );
    } else {
       
        triggerToast("error", error, "Socio no agregado", `El socio no pudo ser inscripto <br> Error: ${respJson.message}`, );
    }

    form.reset();
};


registerButton.addEventListener('click', register);

