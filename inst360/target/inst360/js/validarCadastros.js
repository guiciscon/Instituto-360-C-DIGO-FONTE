window.onload = function(){

    fetch("usuariosPendentes")

    .then(response => response.json())

    .then(usuarios => {
        const lista = document.getElementById("listaUsuarios");

        lista.innerHTML = "";

        usuarios.forEach(usuario => {
            lista.innerHTML += `
            
            <div class="usuarios">
                <div class="dados-usuarios">
                    <div class="label-usuarios">
                        <label>Nome:</label><p> ${usuario.nome}</p>
                    </div>
                    <div class="label-usuarios">
                        <label>Matrícula:</label><p> ${usuario.matricula}</p>
                    </div>
                    <div class="label-usuarios">
                        <label>Email:</label><p> ${usuario.email}</p>
                    </div>
                </div>
            <div class="btns-usuarios">
                <button class="btn-validar btns" data-id="${usuario.id}">Validar</button>
                <button class="btn-invalidar btns" data-id="${usuario.id}">Invalidar</button>
            </div>
            </div>
            `;
        });
    });

};

document.getElementById("btnInicio").addEventListener("click", function(){
    window.location.href = "home";    
})

function logout(){
    window.location.href = "logout";
}

document.addEventListener("click", function(e){

    if(e.target.classList.contains("btn-validar")){

        const id = e.target.dataset.id;

        console.log("VALIDAR:", id);

        fetch("validarUsuario", {

            method: "POST",

            headers: {
                "Content-Type":
                "application/x-www-form-urlencoded"
            },

            body: "id=" + id

        })

        .then(response => response.text())

        .then(msg => {

            alert(msg);

            location.reload();

        });

    }

    if(e.target.classList.contains("btn-invalidar")){

        const id = e.target.dataset.id;

        console.log("INVALIDAR:", id);

        fetch("invalidarUsuario", {

            method: "POST",

            headers: {
                "Content-Type":
                "application/x-www-form-urlencoded"
            },

            body: "id=" + id

        })

        .then(response => response.text())

        .then(msg => {

            alert(msg);

            location.reload();

        });

    }

});