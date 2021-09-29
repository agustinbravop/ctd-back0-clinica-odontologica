function buildJsonFromPacienteAdd(form){
    const json = { domicilio: {} };
    const formData = new FormData(form);

    json.nombre = formData.get("nombre")
    json.apellido = formData.get("apellido")
    json.dni = formData.get("dni")
    json.domicilio.calle = formData.get("domicilioCalle")
    json.domicilio.numero = formData.get("domicilioNumero")
    json.domicilio.localidad = formData.get("domicilioLocalidad")
    json.domicilio.provincia = formData.get("domicilioProvincia")
    return json;
}

function submitPacienteAdd(e, form){
    e.preventDefault();
    const json = buildJsonFromPacienteAdd(form)

    fetch("http://localhost:8080/paciente/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        "body": JSON.stringify(json)
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

const pacienteAddForm = document.getElementById("pacienteAdd");
pacienteAddForm.addEventListener("submit", e => {
    submitPacienteAdd(e, pacienteAddForm);

})