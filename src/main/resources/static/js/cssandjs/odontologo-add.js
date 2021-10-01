function buildJsonFromOdontologoAdd(form){
    const object = {};
    const formData = new FormData(form);

    object.nombre = formData.get("nombre")
    object.apellido = formData.get("apellido")
    object.matricula = formData.get("matricula")
    return JSON.stringify(object);
}

function submitOdontologoAdd(e, form){
    e.preventDefault();
    const json = buildJsonFromOdontologoAdd(form)

    fetch("http://localhost:8080/odontologo/add", {
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

const odontologoAddForm = document.getElementById("odontologoAdd");
odontologoAddForm.addEventListener("submit", e => {
    submitOdontologoAdd(e, odontologoAddForm);
})