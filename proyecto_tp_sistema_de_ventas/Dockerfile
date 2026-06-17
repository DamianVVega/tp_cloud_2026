FROM tomcat:9.0-jdk21-openjdk

COPY dist/proyecto_tp_sistema_de_ventas.war /usr/local/tomcat/webapps/proyecto_tp_sistema_de_ventas.war

RUN apt-get update && apt-get install -y --no-install-recommends \
    fontconfig \
    && rm -rf /var/lib/apt/lists/*

COPY fonts/* /usr/share/fonts/truetype/
RUN fc-cache -f -v

EXPOSE 8080

CMD ["catalina.sh", "run"]