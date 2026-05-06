document.getElementById("formCadastro").addEventListener("submit", function(e){
    e.preventDefault();

    const formData = new FormData(this);

    fetch("conexao.php", {
        method: "POST",
        body: formData
    })

    //obtém resposta dos echos do php com a função "res" e converte para texto, com isso, inicializa a variável 
    // "retorno_php", possibilitando as verificação de tipos de mensagem
    .then(res => res.text())
    .then(retorno_php => {
        const msg = document.getElementById("msgCadastro");

        if (retorno_php === "MATRICULA_EXISTE") {
            msg.innerText = "Essa matrícula já está cadastrada!";
            msg.className = "msgErro";
        } 
        else if (retorno_php === "CADASTRO_OK") {
            msg.innerText = "Cadastro realizado com sucesso!";
            msg.className = "msgSucesso";
            document.getElementById("formCadastro").reset();
        } 
        else {
            msg.innerText = "Erro ao Cadastrar.";
            msg.className = "msgErro";
        }

        msg.style.display = "block";
    });
});