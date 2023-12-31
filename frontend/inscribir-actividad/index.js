"use strict"

import {Toast} from "../Toast.js";

import {success, error} from "../Icons.js";

const form = document.getElementById("socio-register-form");
const registerButton = document.getElementById("register-button");
const HTMLbody = document.querySelector('body');


const container = document.createElement('div');
container.classList.add('toast-cont')

HTMLbody.prepend(container);

const activitiesSelect = document.getElementById("actividad");
const quotaSpans = document.getElementsByClassName("quota")

let activitiesList = [];
let selectedActivity = {};
let quota = 0;

const URL = "http://localhost:8080"

const register = async e => {
    e.preventDefault();
    const socioData = new FormData(form);

    const formJSON = Object.fromEntries(socioData.entries())
    
    delete formJSON.actividad;
    const body = {
        "actividad": {"nombre": socioData.get("actividad")},
        "socio": {
            "persona": {...formJSON}
        }
    }

    console.log('formJSON', formJSON);
    
    const response = await fetch(`${URL}/inscribir/`, {
        method: "POST",
        body: JSON.stringify(body),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
          }
    });
    const respJson = await response.json();

    const triggerToast = (type, icon, msgTitle, msgBody) => {
        const toastContainer = document.querySelector('.toast-cont');
        const toast = new Toast(toastContainer, type, icon, msgTitle, msgBody)
        toast.showUp();
    }

    if(response.ok){
        triggerToast("success", success, "Socio agregado", `El socio ${body.socio.persona.nombre} fue registrado con éxito.`, );
    } else {
       
        console.log('respJson', respJson);
        
        triggerToast("error", error, "Socio no agregado", `El socio no pudo ser inscripto <br> Error: ${respJson.message}`, );
    }

    form.reset();
    hideSpans();
    
};


registerButton.addEventListener('click', register);



const loadActivities = async () => {
    const response = await fetch(`${URL}/actividades/list`, {
        method: "GET",
        headers: {
            "Content-type": "application/json; charset=UTF-8"
          }
    });

    if (response.ok) {

        
        activitiesList = await response.json();

        const option = document.createElement("option");
        option.value = "";
        option.text = "";
        activitiesSelect.appendChild(option)

        activitiesList.forEach(activity => {
            const option = document.createElement("option");
            const { nombre } = activity;
            option.value = nombre;
            option.text = nombre;
                       
            activitiesSelect.appendChild(option);
        })
    }
}

activitiesSelect.addEventListener('change', (e) => {
    console.log(e);
    const { value } = e.target;

    selectedActivity = activitiesList.filter(act => {
        console.log(act)
        return act.nombre === value;
    })[0];

    console.log('actividad', selectedActivity);
    
    quota = selectedActivity.cupo - selectedActivity.inscriptos.length
    
    showSpans(quota);

    
});

const showSpans = (quota) => {
    const numberSpan = quotaSpans.namedItem("quota");
    numberSpan.innerHTML = quota;

    for (const span of quotaSpans) {
        span.style.display = "block";
        
    }
}

const hideSpans = () => {
    for (const span of quotaSpans) {
        span.style.display = "none";
    }
}



loadActivities();

