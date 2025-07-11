# 🛠️ GestAgro - Sistema de Manutenção de Equipamentos Agrícolas

Este é um sistema Java desktop simples para gerenciamento de manutenções de equipamentos agrícolas. Ele permite cadastrar usuários, mecânicos, equipamentos, manutenções e gerar vínculos entre eles.

---

## ⚙️ Requisitos

Antes de executar o programa, certifique-se de que os seguintes softwares estejam instalados na sua máquina:

- [✅ Java JDK 17+](https://adoptium.net/)
- [✅ MySQL Server 8+](https://dev.mysql.com/downloads/mysql/)
- [✅ MySQL Workbench ou algum cliente de banco](https://dev.mysql.com/downloads/workbench/)
- [✅ Maven (se quiser compilar)](https://maven.apache.org/)

---

## 🐬 Configuração do Banco de Dados

1. **Abra o MySQL Workbench ou terminal e execute o script abaixo para criar o banco e as tabelas:**

```sql
CREATE SCHEMA gestagro;
USE gestagro;

CREATE TABLE usuario (
    codigo INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    funcao VARCHAR(80) NOT NULL
);

CREATE TABLE equipamento (
    codigo INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(50) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL, 
    manutencao_feitas VARCHAR(300),
    manutencao_fazer VARCHAR(300)
);

CREATE TABLE manutencao (
    codigo INT PRIMARY KEY AUTO_INCREMENT,
    pecas_manutencao TEXT,
    data_manutencao DATE,
    executada BOOLEAN
);

CREATE TABLE mecanico (
    codigo INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80)
);

CREATE TABLE relacao (
    codigo INT PRIMARY KEY AUTO_INCREMENT,

    codigo_manutencao INT,
    CONSTRAINT fk_relacao_manutencao FOREIGN KEY (codigo_manutencao) REFERENCES manutencao(codigo),

    codigo_mecanico INT,
    CONSTRAINT fk_relacao_mecanico FOREIGN KEY (codigo_mecanico) REFERENCES mecanico(codigo),

    codigo_equipamento INT,
    CONSTRAINT fk_relacao_equipamento FOREIGN KEY (codigo_equipamento) REFERENCES equipamento(codigo)
);
```

2. **Usuário e senha padrão (altere no código se necessário):**

```
private static final String URL = "jdbc:mysql://localhost:3306/gestagro?useSSL=false&serverTimezone=UTC";
private static final String USUARIO = "root";
private static final String SENHA = "root";
```

## 🚀 Executando o Projeto

Para rodar o projeto, clone este repositório e abra-o em sua IDE Java. Certifique-se de que o Maven baixou todas as dependências (especialmente o mysql-connector-j).

Em seguida, execute a classe Main.java (ou MenuView.java) como uma aplicação Java.
