# inv
Systema de inventarios

## Requisitos antes de Instalar
1. Java sdk instalado
2. MySQL instalado y configurado con una contraseña
3. Leiningen instalado. https://leiningen.org
4. vscode instalado con la extension calva:clojure

## Como usar la libreria
1. Clonar este repositorio en su maquina
2. Crear una base de datos en su cliente favorito de mysql, llamar la base de datos "inv"
3. Usar el dump en sql para actualizar la base de datos.  Usar el cliente dbeaver.
4. Renombrar **resources/private/config_example** a **config.clj**
6. Configurar todas las **xxxxx** con la informacion correcta
7. Click en el icono de la barra de estado abajo **REPL** para iniciar un repl. Usar la opción **Start your project with a REPL and connect (a.k.a. jack-in)**
8. Abrir una terminal nueva y correr: 
   * `lein with-profile dev run`
      * Creara un usuario user@gmail.com password **user** en la tabla **users**
      * Creara un usuario admin@gmail.com password **admin** en la tabla **users**
      * Creara un usuario sistema@gmail.com password **sistema** en la tabla **users**
09. Correr la pagina:
    * `http:localhost:3000` en tu browser favorito
10. Entrar a la pagina usando el usuario **sistema@gmail.com** con la contraseña **sistema**.

## Video de demostración

[Video tutorial](https://youtu.be/Ifnugf-dDsg)

## Simple roladex - demostración un poco mas avanzada

[Video Simple Roladex](https://youtu.be/TBArVXx3gl4)