# Autobots - Sistema de Gerenciamento de Clientes

## 📋 Sobre o Projeto

Autobots é um microserviço REST desenvolvido em Spring Boot para gerenciamento completo de dados de clientes de uma oficina automotiva. O sistema permite cadastro, consulta, atualização e exclusão de clientes, incluindo seus documentos, endereços e telefones.

### 🛠️ Tecnologias Utilizadas

- **Java 17** - Linguagem de programação
- **Spring Boot 2.6.3** - Framework para desenvolvimento de aplicações Java
- **Spring Data JPA** - Persistência de dados com JPA/Hibernate
- **H2 Database** - Banco de dados em memória para desenvolvimento
- **Lombok 1.18.34** - Redução de código boilerplate
- **Springdoc OpenAPI 1.7.0** - Documentação automática da API (Swagger)
- **Maven** - Gerenciamento de dependências e build

### 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas:

```
├── controles/          # Controllers REST (endpoints da API)
├── entidades/          # Entidades JPA (modelo de dados)
├── repositorios/       # Repositories Spring Data JPA
└── modelo/             # Classes auxiliares (Select, Atualizador, Verificadores)
```

### 📦 Entidades Principais

- **Cliente**: Dados principais do cliente (nome, data de nascimento, data de cadastro)
- **Documento**: Documentos do cliente (CPF, RG, etc.)
- **Endereco**: Endereço residencial do cliente
- **Telefone**: Telefones de contato do cliente

## 🚀 Como Executar o Projeto

### Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

1. **Java JDK 17 ou superior**
   - Verifique a instalação executando no terminal:
     ```powershell
     java -version
     ```

2. **Maven** (Opcional - o projeto inclui Maven Wrapper)
   - O projeto possui o Maven Wrapper (`mvnw.cmd`), portanto não é necessário instalar o Maven separadamente

### Passos para Executar

#### 1️⃣ Clone ou Baixe o Projeto

```powershell
git clone <https://github.com/KwMajor/autoBotsATVi>
cd automanager
```

Ou navegue até a pasta do projeto se já tiver baixado:

```powershell
cd c:\Desktop\Autobots\automanager
```

#### 2️⃣ Compile o Projeto (Opcional, mas Recomendado)

Execute o comando abaixo para compilar e verificar se tudo está correto:

```powershell
.\mvnw.cmd clean compile
```

**Saída esperada:** `BUILD SUCCESS`

#### 3️⃣ Execute a Aplicação

**Opção A - Usando Maven Wrapper (Recomendado):**

```powershell
.\mvnw.cmd spring-boot:run
```

**Opção B - Se você tiver Maven instalado globalmente:**

```powershell
mvn spring-boot:run
```

**Opção C - Executando o JAR compilado:**

```powershell
# Primeiro, compile e empacote
.\mvnw.cmd clean package

# Depois execute o JAR
java -jar target\automanager-0.0.1-SNAPSHOT.jar
```

#### 4️⃣ Verifique se a Aplicação Está Rodando

Quando a aplicação iniciar com sucesso, você verá mensagens como:

```
Tomcat started on port(s): 8080 (http) with context path ''
Started AutomanagerApplication in X.XXX seconds
```

### 🌐 Acessando a Aplicação

#### Swagger UI (Documentação Interativa da API)

Acesse a interface do Swagger para testar os endpoints:

```
http://localhost:8080/swagger-ui.html
```

Ou use o caminho alternativo:

```
http://localhost:8080/swagger
```

#### H2 Console (Banco de Dados)

Para acessar o console do banco de dados H2:

```
http://localhost:8080/h2-console
```

**Configurações de conexão:**
- **JDBC URL:** `jdbc:h2:mem:autobots`
- **Username:** `sa`
- **Password:** *(deixe em branco)*

#### Endpoints da API

A API REST possui os seguintes recursos principais:

**Clientes:**
- `GET /cliente` - Lista todos os clientes
- `GET /cliente/{id}` - Busca cliente por ID
- `POST /cliente` - Cria novo cliente
- `PUT /cliente/{id}` - Atualiza cliente existente
- `DELETE /cliente/{id}` - Remove cliente

**Documentos:**
- `GET /documento` - Lista todos os documentos
- `GET /documento/{id}` - Busca documento por ID
- `POST /documento` - Cria novo documento
- `PUT /documento/{id}` - Atualiza documento existente
- `DELETE /documento/{id}` - Remove documento

**Endereços:**
- `GET /endereco` - Lista todos os endereços
- `GET /endereco/{id}` - Busca endereço por ID
- `POST /endereco` - Cria novo endereço
- `PUT /endereco/{id}` - Atualiza endereço existente
- `DELETE /endereco/{id}` - Remove endereço

**Telefones:**
- `GET /telefone` - Lista todos os telefones
- `GET /telefone/{id}` - Busca telefone por ID
- `POST /telefone` - Cria novo telefone
- `PUT /telefone/{id}` - Atualiza telefone existente
- `DELETE /telefone/{id}` - Remove telefone

#### Actuator (Monitoramento)

Endpoints de monitoramento da aplicação:

```
http://localhost:8080/actuator/health
http://localhost:8080/actuator/info
```

### 🛑 Parar a Aplicação

Para parar a aplicação, pressione **Ctrl + C** no terminal onde ela está executando.

## 🧪 Testando a API

### Exemplo de Requisição POST - Criar Cliente

```json
POST http://localhost:8080/cliente
Content-Type: application/json

{
  "nome": "João Silva",
  "nomeSocial": "João",
  "dataNascimento": "1990-05-15T00:00:00",
  "dataCadastro": "2025-11-20T00:00:00",
  "endereco": {
    "rua": "Rua das Flores",
    "numero": "123",
    "bairro": "Centro",
    "cidade": "São Paulo",
    "estado": "SP",
    "codigoPostal": "01234-567"
  },
  "documentos": [
    {
      "tipo": "CPF",
      "numero": "123.456.789-00"
    }
  ],
  "telefones": [
    {
      "ddd": "11",
      "numero": "98765-4321"
    }
  ]
}
```

### Exemplo de Requisição GET - Listar Clientes

```
GET http://localhost:8080/cliente
```

### Exemplo de Requisição GET - Buscar Cliente por ID

```
GET http://localhost:8080/cliente/1
```

## ⚙️ Configurações

As configurações da aplicação estão no arquivo `src/main/resources/application.properties`:

```properties
# Configuração do H2
spring.datasource.url=jdbc:h2:mem:autobots
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Console H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Swagger/OpenAPI
springdoc.swagger-ui.path=/swagger
springdoc.api-docs.path=/v3/api-docs
```

## 📝 Troubleshooting

### Erro: "Port 8080 already in use"

Se a porta 8080 já estiver em uso, você pode:

1. Parar o processo que está usando a porta 8080
2. Ou alterar a porta no `application.properties`:
   ```properties
   server.port=8081
   ```

### Erro: "JAVA_HOME not set"

Configure a variável de ambiente JAVA_HOME:

```powershell
# No PowerShell (temporário)
$env:JAVA_HOME = "C:\Program Files\Java\jdk-17"

# Ou configure permanentemente nas variáveis de ambiente do sistema
```

### Erro ao executar mvnw.cmd

Se houver erro ao executar `mvnw.cmd`, tente:

```powershell
# Garanta que está no diretório correto
cd c:\Users\mates\OneDrive\Desktop\Autobots\automanager

# Use Push-Location para garantir o contexto correto
Push-Location "c:\Autobots\automanager"
.\mvnw.cmd spring-boot:run
```

## 📚 Estrutura do Banco de Dados

O Hibernate cria automaticamente as seguintes tabelas:

- `cliente` - Dados principais dos clientes
- `documento` - Documentos dos clientes
- `endereco` - Endereços dos clientes
- `telefone` - Telefones dos clientes
- `cliente_documentos` - Relacionamento Cliente-Documento (N:N)
- `cliente_telefones` - Relacionamento Cliente-Telefone (N:N)

## 👨‍💻 Desenvolvimento

### Executar em Modo de Desenvolvimento

A aplicação já vem configurada com Spring Boot DevTools, que permite:

- Reinicialização automática ao alterar arquivos
- LiveReload para recarregar páginas automaticamente

### Estrutura de Pastas

```
automanager/
├── src/
│   ├── main/
│   │   ├── java/com/autobots/automanager/
│   │   │   ├── AutomanagerApplication.java (Classe principal)
│   │   │   ├── controles/         (Controllers REST)
│   │   │   ├── entidades/         (Entidades JPA)
│   │   │   ├── repositorios/      (Repositories)
│   │   │   └── modelo/            (Classes auxiliares)
│   │   └── resources/
│   │       └── application.properties (Configurações)
│   └── test/                       (Testes unitários)
├── target/                         (Arquivos compilados)
├── mvnw, mvnw.cmd                  (Maven Wrapper)
├── pom.xml                         (Configuração Maven)
└── README.md                       (Este arquivo)
```

## 📄 Licença

Projeto desenvolvido para fins educacionais.

---

**Desenvolvido com ❤️ usando Spring Boot**
