function handleRemoveOdontologo(btn) {
    const id = btn.id;

    fetch(`http://localhost:8080/odontologo/remove/${id}`, {
        method: "DELETE",
    })
    .then(res => {
        if(res.status === 204){
            setTimeout(() => {
                location.reload();
            }, 1000)
        }
    })
    .catch(error => console.log(error))
}