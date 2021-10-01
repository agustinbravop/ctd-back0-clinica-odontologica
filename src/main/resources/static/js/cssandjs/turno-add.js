function buildJsonFromTurnoAdd(form){
    const object = {
        paciente: {},
        odontologo: {}
    };
    const formData = new FormData(form);

    object.fecha = formData.get("fecha")
    object.paciente.id = form.paciente.value
        object.odontologo.id = form.odontologo.value
    return JSON.stringify(object);
}

function submitTurnoAdd(e, form){
    e.preventDefault();
    const json = buildJsonFromTurnoAdd(form)

    fetch("http://localhost:8080/turno/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        "body": json
    })
    .then(res => {
        if(res.ok){
            setTimeout(() => {
                location.reload();
            }, 1000)
        }
    })
    .catch(error => alert(error))
}

const turnoAddForm = document.getElementById("turnoAdd");
turnoAddForm.addEventListener("submit", e => {
    submitTurnoAdd(e, turnoAddForm);

})