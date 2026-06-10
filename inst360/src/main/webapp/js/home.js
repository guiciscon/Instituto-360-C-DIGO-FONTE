console.log("js funcionando...");

window.onload = function () {

    fetch("sessao")

        .then(response => response.text())
        .then(perfil => {
            if (perfil === "ADMIN") {
                document.getElementById("painelAdmin").innerHTML = 
                    `
                    <button class="btns" id="btnValidarCadastros">
                    Validar Cadastros
                    </button>
                    `
                    ;
            }
        })
        .catch(error => {
            console.error(error);
        });

    document.addEventListener("click", function(e){

        if(e.target.id === "btnValidarCadastros"){
            window.location.href = "validarCadastros.html";
        }

    });

};

function logout(){
    window.location.href = "logout";
}