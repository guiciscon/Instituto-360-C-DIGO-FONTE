console.log("js funcionando...");

const form = document.getElementById("formLogin");
const mensagem = document.getElementById("mensagem");

form.addEventListener("submit", function(e) {

    e.preventDefault();

    const formData = new URLSearchParams(new FormData(form));

    fetch("login", {
    method: "POST",
    headers: {
        "Content-Type": "application/x-www-form-urlencoded"
    },
    body: formData
    })
    .then(response => {

        return response.text().then(text => {
            return {
                status: response.status,
                mensagem: text
            };
        });

    })
    .then(data => {

        mensagem.innerText = data.mensagem;

        if (data.status === 200) {
            mensagem.style.display = "block";
            mensagem.style.color = "green";
            mensagem.innerText = "Login realizado! Redirecionando...";

            setTimeout(() => {
                window.location.href = "home";
            }, 1000);
        } else {
            mensagem.style.display = "block";
            mensagem.style.color = "red";
        }

    })
    .catch(error => {

        mensagem.innerText = "Erro de conexão com o servidor!";
        mensagem.style.color = "red";

    });

});

function logout(){
    window.location.href = "logout";
}