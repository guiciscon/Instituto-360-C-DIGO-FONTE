<?php

// Conexão com o banco
$host = "sql201.infinityfree.com";
$user = "if0_41812748";
$pass = "8rGTJRMih0w6";
$db = "if0_41812748_inst360";

$conn = new mysqli($host, $user, $pass, $db);

$conn->set_charset("utf8mb4");

// Verifica conexão
if ($conn->connect_error) {
    die("Erro na conexão: " . $conn->connect_error);
}

// Pegando os dados do formulário (precisam estar na ordem da tabela criada na DB)
$nome = $_POST['nome'];
$endereco = $_POST['endereco'];
$data_nasc = $_POST['data_nasc'];
$email = $_POST['email'];
$senha = $_POST['senha'];
$usuario_valido = $_POST['usuario_valido'];
$matricula = $_POST['matricula'];

// Criptografar senha 
$senha_hash = password_hash($senha, PASSWORD_DEFAULT);

// Verificar se matrícula já existe no banco
$sql_check = "SELECT id FROM usuarios WHERE matricula = ?";
$stmt_check = $conn->prepare($sql_check);
$stmt_check->bind_param("s", $matricula);
$stmt_check->execute();
$result = $stmt_check->get_result();

if ($result->num_rows > 0) {
    echo "MATRICULA_EXISTE";
    exit; // impede o insert
}

// Insere os dados no banco (precisam estar na ordem da tabela criada na DB)
$sql = "INSERT INTO usuarios (nome, endereco, data_nasc, email, senha, usuario_valido, matricula)
        VALUES (?, ?, ?, ?, ?, ?, ?)";

$stmt = $conn->prepare($sql);
$stmt->bind_param("sssssis", $nome, $endereco, $data_nasc, $email, $senha_hash, $usuario_valido, $matricula);

if ($stmt->execute()) {
    echo "CADASTRO_OK";
} else {
    echo "Erro: " . $stmt->error;
}

$stmt->close();
$conn->close();
?>