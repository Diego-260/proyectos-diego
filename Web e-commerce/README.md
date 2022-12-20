# SOBRE EL PROYECTO
## Aplicación web destinada a funcionar para la venta de productos en línea, en este caso se realizo con ropa.
## La aplicación funciona de la siguiente manera: Se debe ingresar al catálogo por medio de la página principal,
## una vez ya en el catálogo se debe agregar los productos que se quiera al carrito. Ya cuando se acabe de agregar
## los productos se debe ir al carrito y ahí, además de encontrarse un resumen de los productos agregados, se encontrara
## un campo que se debe llenar con el correo a donde se desea que llegue la información para el pago del pedido.
## Finalmente, luego de seleccionar la opción de pagar llegara al correo la información para efectuar el pago por el 
## monto total del pedido por medio de PayPal.

# REQUERIMIENTOS
## * Tener el PHP instalado (La aplicación se desarrollo con la versión 7.4.33)
## * Servidor de aplicaciones web local (Se recomienda que sea XAMPP)

# COMO EJECUTAR
## 1. Se debe descargar el .zip del repositorio y descomprimirlo en una carpeta normal.
## 2. Luego se debe mover la carpeta dentro del directorio del servidor de aplicaciones web local para poder ejecutar el
##    código php. En este caso se movió a la ruta C:\xampp\htdocs, pero eso va a depender del servidor que se haya instalado.
## 3. Se debe activar el servidor local Apache y el MySQL en el panel de control del servidor de aplicaciones.
## 4. Luego se debe ir a la base de datos de php en el navegador de su preferencia. En este caso se ira utilizando la ruta
##    'http://localhost/phpmyadmin/'.
## 5. Se debe crear una nueva base de datos con el nombre 'login'. Luego de crear la base de datos se debe ejecutar el script 
##    sql que se encuentra en la ruta 'components\login\bd' para crear las tablas correspondientes dentro de la base de datos
##    'login'.
## NOTA: Si ya se tenía instalado el servidor de aplicaciones web local previamente y se ingreso con otro usuario diferente al 
##       que viene por defecto en el servidor MySQL, se debe cambiar los datos de usuario y contraseña a los que correspondan 
##       en el archivo 'conexión.php' que se encuentra en la misma ruta que el script sql.
## 6. Se debe acceder a la carpeta con el proyecto por medio del navegador. En este caso se hara yendo a la ruta
##    'http://localhost/Web%20e-commerce/'. 