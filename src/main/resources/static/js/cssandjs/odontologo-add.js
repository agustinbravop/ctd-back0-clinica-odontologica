function buildJsonFromOdontologoAdd(form){
    const json = { domicilio: {} };
    const formData = new FormData(form);

    json.nombre = formData.get("nombre")
    json.apellido = formData.get("apellido")
    json.matricula = formData.get("matricula")
    return json;
}

function submitOdontologoAdd(e, form){
    e.preventDefault();
    const json = buildJsonFromOdontologoAdd(form)

    fetch("http://localhost:8080/odontologo/add", {
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

const odontologoAddForm = document.getElementById("odontologoAdd");
odontologoAddForm.addEventListener("submit", e => {
    submitOdontologoAdd(e, odontologoAddForm);
})