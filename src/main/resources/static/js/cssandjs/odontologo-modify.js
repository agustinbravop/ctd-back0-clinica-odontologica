function buildJsonFromOdontologoModify(form){
    const object = {};
    const formData = new FormData(form);

    // modifyId es el name de una tag select
    object.id = form.modifyId.value
    object.nombre = formData.get("nombre")
    object.apellido = formData.get("apellido")
    object.matricula = formData.get("matricula")

    return JSON.stringify(object);
}

function submitOdontologoModify(e, form){
    e.preventDefault();
    const json = buildJsonFromOdontologoModify(form)

    fetch("http://localhost:8080/odontologo/modify", {
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

const odontologoModifyForm = document.getElementById("odontologoModify");
odontologoModifyForm.addEventListener("submit", e => {
    submitOdontologoModify(e, odontologoModifyForm);
})