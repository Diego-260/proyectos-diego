# SOBRE EL PROYECTO
Aplicación de escritorio utilizada para la organización de horarios académicos. Esta aplicación
fue desarrollada completamente en Java y utiliza una base de datos local MySQL. La aplicación consta
de una ventana de login donde primero habra que crear un usuario para ahora si poder ingresar.
Ya después de haber iniciado sesión habra tres ventanas: Una para editar los datos del perfil, otra
para agregar las clases al horario y otra que es un chat que funciona con otros usuario que estén 
conectados a la misma red.

# REQUERIMIENTOS
* Tener el JDK instalado (En este caso se utilizo la versión 1.8.0)
* Tener instalado MySQLWorkbench

# COMO EJECUTAR
1. Se debe crear una base de datos en el MySQLWorkbench con el nombre 'PROYECTO_ARQUITECTURA'. Luego se
   debe modificar los datos de usuario y contraseña según correspondan en el archivo DBConexión.java que
   se encuentra en el directorio 'codigo_cliente/src/com/mysql/conexion'
2. Una vez creada la base de datos se debe ejecutar el script .sql que se encuentra en la ruta 
   'codigo_cliente/src/com/mysql/conexion' para crear las tablas correspondientes.
3. Luego se debe ir a la carpeta 'Aplicación' y se debe ejecutar los dos archivos ejecutables .jar
   llamados 'Servidor' y 'Cliente' respectivamente.
