Introducción a Ciencias de la Computación
=========================================

Práctica 9: Interfaces gráficas de usuario
------------------------------------------

### Fecha de entrega: martes 12 de noviembre, 2019

Deben agregar escuchas a las clases
[BaseDeDatos](https://aztlan.fciencias.unam.mx/gitlab/2020-1-icc/practica9/blob/master/src/main/java/mx/unam/ciencias/icc/BaseDeDatos.java)
y
[Estudiante](https://aztlan.fciencias.unam.mx/gitlab/2020-1-icc/practica9/blob/master/src/main/java/mx/unam/ciencias/icc/Estudiante.java).

También deben completar los métodos de las clases controladores en el paquete
[fx](https://aztlan.fciencias.unam.mx/gitlab/2020-1-icc/practica9/blob/master/src/main/java/mx/unam/ciencias/icc/fx/);
noten que las mismas no cuentan con pruebas unitarias.

Una vez que hayan terminado con sus clases, deben compilar al hacer:

```
$ mvn compile
```

También deben pasar todas las pruebas unitarias al hacer:

```
$ mvn test
```

Por último, se debe ejecutar correctamente el programa escrito en la clase
[Practica9](https://aztlan.fciencias.unam.mx/gitlab/2020-1-icc/practica9/blob/master/src/main/java/mx/unam/ciencias/icc/Practica9.java)
al hacer:

```
$ mvn install
...
$ java -jar target/practica9.jar
```

Los únicos archivos que deben modificar son:

* `Arreglos.java`,
* `BaseDeDatos.java`,
* `BaseDeDatosEstudiantes.java`,
* `CampoEstudiante.java`,
* `Estudiante.java`,
* `Lista.java`,
* `fx/ControladorFormaBusquedaEstudiantes.java`,
* `fx/ControladorFormaEstudiante.java`,
* `fx/ControladorForma.java`,
* `fx/ControladorInterfazEstudiantes.java` y
* `fx/EntradaVerificable.java`.

*No deben modificar de ninguna manera ninguno de los otros archivos de la
práctica*.

### Repositorio

Pueden clonar la práctica con el siguiente comando:

```
$ git clone https://canek@aztlan.fciencias.unam.mx/gitlab/2020-1-icc/practica9.git
```

### Documentación

La documentación generada por JavaDoc la pueden consultar aquí:

[Documentación generada por JavaDoc para la práctica
9](https://aztlan.fciencias.unam.mx/~canek/2020-1-icc/practica9/apidocs/index.html)
