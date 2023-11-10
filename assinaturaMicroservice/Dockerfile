# Use uma imagem base do OpenJDK
FROM openjdk:17-jdk

# Argumento para o arquivo JAR
ARG JAR_FILE=target/*.jar

# Copie o JAR da sua aplicação para o diretório /app
COPY ${JAR_FILE} app.jar

# Defina o diretório de trabalho
WORKDIR /app

# Comando para executar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "app.jar"]
