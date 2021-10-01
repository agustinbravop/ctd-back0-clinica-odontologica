function handleRemovePaciente(btn) {
    const id = btn.id;

    fetch(`http://localhost:8080/paciente/remove/${id}`, {
        method: "DELETE",
    })
    .then(res => {
        if(res.status === 204){
            setTimeout(() => {
                location.reload();
            }, 1000)
        } else {
            res.text().then(msg => alert(msg));
        }
    })
    .catch(error => alert(error))
}