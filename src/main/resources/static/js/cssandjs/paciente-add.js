function buildJsonFromPacienteAdd(form){
    const object = { domicilio: {} };
    const formData = new FormData(form);

    object.nombre = formData.get("nombre")
    object.apellido = formData.get("apellido")
    object.dni = formData.get("dni")
    object.domicilio.calle = formData.get("domicilioCalle")
    object.domicilio.numero = formData.get("domicilioNumero")
    object.domicilio.localidad = formData.get("domicilioLocalidad")
    object.domicilio.provincia = formData.get("domicilioProvincia")
    return JSON.stringify(object);
}

function submitPacienteAdd(e, form){
    e.preventDefault();
    const json = buildJsonFromPacienteAdd(form)

    fetch("http://localhost:8080/paciente/add", {
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
        } else {
            res.text().then(msg => alert(msg));
        }
    })
    .catch(error => alert(error))
}

const pacienteAddForm = document.getElementById("pacienteAdd");
pacienteAddForm.addEventListener("submit", e => {
    submitPacienteAdd(e, pacienteAddForm);

})