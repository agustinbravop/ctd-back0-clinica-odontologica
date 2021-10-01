function buildJsonFromPacienteModify(form){
    const object = { domicilio: {} };
    const formData = new FormData(form);

    // modifyId es el name de una tag select
    object.id = form.modifyId.value
    object.nombre = formData.get("nombre")
    object.apellido = formData.get("apellido")
    object.dni = formData.get("dni")
    object.domicilio.calle = formData.get("domicilioCalle")
    object.domicilio.numero = formData.get("domicilioNumero")
    object.domicilio.localidad = formData.get("domicilioLocalidad")
    object.domicilio.provincia = formData.get("domicilioProvincia")

    return JSON.stringify(object);
}

function submitPacienteModify(e, form){
    e.preventDefault();
    const json = buildJsonFromPacienteModify(form)

    fetch("http://localhost:8080/paciente/modify", {
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
    .then(data => console.log(data))
    .catch(error => console.log(error))
}

const pacienteModifyForm = document.getElementById("pacienteModify");
pacienteModifyForm.addEventListener("submit", e => {
    submitPacienteModify(e, pacienteModifyForm);
})