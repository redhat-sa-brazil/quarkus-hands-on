## Pré-requisitos
Para completar este guia, você precisa de:

- Aproximadamente 25 minutos
- Uma IDE para Desenvolvimento
- JDK 11+ instalado com JAVA_HOME configurado apropriadamente
- Apache Maven 3.6.2+


#### Verifcando a versão da JVM:

```sh
➜  quickstart-session1 java -version
openjdk version "11.0.9" 2020-10-20
OpenJDK Runtime Environment GraalVM CE 20.3.0 (build 11.0.9+10-jvmci-20.3-b06)
OpenJDK 64-Bit Server VM GraalVM CE 20.3.0 (build 11.0.9+10-jvmci-20.3-b06, mixed mode, sharing)
```

#### Verifcando a versão do Maven:

```sh
➜  quickstart-session1 mvn -version
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /usr/local/Cellar/maven/3.6.3_1/libexec
Java version: 15.0.1, vendor: N/A, runtime: /usr/local/Cellar/openjdk/15.0.1/libexec/openjdk.jdk/Contents/Home
Default locale: pt_BR, platform encoding: UTF-8
OS name: "mac os x", version: "10.15.7", arch: "x86_64", family: "mac"
```

#### Verifcando a versão do Docker:

```sh
➜  quickstart-session1 docker version
Client: Docker Engine - Community
 Cloud integration: 1.0.12
 Version:           20.10.5
 API version:       1.41
 Go version:        go1.13.15
 Git commit:        55c4c88
 Built:             Tue Mar  2 20:13:00 2021
 OS/Arch:           darwin/amd64
 Context:           default
 Experimental:      true
```

## Criando a estrutura
Nosso primeiro passo e criar uma estrutura de projeto para ser utillizado, para isso vamos fazer uso dos arquétipos utilizando o maven. 
Criando o projeto Maven:

```sh
mvn io.quarkus:quarkus-maven-plugin:1.13.4.Final:create \
    -DprojectGroupId=com.redhat \
    -DprojectArtifactId=quickstart-session1 \
    -DclassName="com.redhat.openapi.swaggerui.PessoaResource" \
    -Dpath="/pessoa" \
    -Dextensions="resteasy,resteasy-jackson"
    cd quickstart-session1
```
Após a estrutura do projeto criada, vamos inicializar em nodo de desenvolvedor e validar se iniciou corretamente.
Iniciando a app:

```sh
➜  quickstart-session1 ./mvnw compile quarkus:dev
[INFO] Scanning for projects...
[INFO]
[INFO] -------------------< com.redhat:quickstart-session1 >-------------------
[INFO] Building quickstart-session1 1.0.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- quarkus-maven-plugin:1.13.4.Final:generate-code (default) @ quickstart-session1 ---
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ quickstart-session1 ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 2 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ quickstart-session1 ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- quarkus-maven-plugin:1.13.4.Final:dev (default-cli) @ quickstart-session1 ---
Listening for transport dt_socket at address: 5005
__  ____  __  _____   ___  __ ____  ______
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/
2021-05-21 10:07:00,652 INFO  [io.quarkus] (Quarkus Main Thread) quickstart-session1 1.0.0-SNAPSHOT on JVM (powered by Quarkus 1.13.4.Final) started in 1.790s. Listening on: http://localhost:8080
2021-05-21 10:07:00,658 INFO  [io.quarkus] (Quarkus Main Thread) Profile dev activated. Live Coding activated.
2021-05-21 10:07:00,659 INFO  [io.quarkus] (Quarkus Main Thread) Installed features: [cdi, resteasy, resteasy-jackson]
```

Após a aplicação inicializada vamos fazer um teste de acesso ao endpoint /pessoa
Realizando teste de acesso:

```sh
➜  quickstart-session1 curl http://localhost:8080/pessoa
Olá Quarkus!%
```

#### Criando os serviços de Pessoa

Criaremos um bean chamado Pessoa.
Criando artefatos java:

```sh
package com.redhat.openapi.swaggerui;

public class Pessoa {

    public String nome;
    public string idade;
    public string sexo;
    public string cpf;
    public string rg;
    public string mae;
    public string pai;


}
```
