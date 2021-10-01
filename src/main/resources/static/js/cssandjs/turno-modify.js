function buildJsonFromTurnoModify(form){
    const object = {
            paciente: {},
            odontologo: {}
    };
    const formData = new FormData(form);

    // modifyId es el name de una tag select
    object.id = form.modifyId.value
    object.fecha = formData.get("fecha")
    object.paciente.id = form.modifyPaciente.value
    object.odontologo.id = form.modifyOdontologo.value

    fetch(`http://localhost:8080/paciente/${object.paciente.id}`)
        .then(res => res.json())
        .then(data => object.paciente = data)
        .catch(error => alert(error))


    fetch(`http://localhost:8080/odontologo/${object.odontologo.id}`)
        .then(res => res.json())
        .then(data => object.odontologo = data)
        .catch(error => alert(error))

    return JSON.stringify(object);
}

function submitTurnoModify(e, form){
    e.preventDefault();
    const json = buildJsonFromTurnoModify(form)

    fetch("http://localhost:8080/turno/modify", {
        method: "PUT",
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
        return res.json();
    })
    .catch(error => alert(error))
}

const turnoModifyForm = document.getElementById("turnoModify");
turnoModifyForm.addEventListener("submit", e => {
    submitTurnoModify(e, turnoModifyForm);
})