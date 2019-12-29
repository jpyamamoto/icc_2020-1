Introducción a Ciencias de la Computación
=========================================

Práctica 6: Genéricos
---------------------

### Fecha de entrega: martes 8 de octubre, 2019

Deben hacer sus clases
[Lista](https://aztlan.fciencias.unam.mx/gitlab/2020-1-icc/practica6/blob/master/src/main/java/mx/unam/ciencias/icc/Lista.java) y
[BaseDeDatos](https://aztlan.fciencias.unam.mx/gitlab/2020-1-icc/practica6/blob/master/src/main/java/mx/unam/ciencias/icc/BaseDeDatos.java) genéricas. Consecuentemente, deben hacer que sus clases
[BaseDeDatosEstudiantes](https://aztlan.fciencias.unam.mx/gitlab/2020-1-icc/practica6/blob/master/src/main/java/mx/unam/ciencias/icc/BaseDeDatosEstudiantes.java) y
[Estudiante](https://aztlan.fciencias.unam.mx/gitlab/2020-1-icc/practica6/blob/master/src/main/java/mx/unam/ciencias/icc/Estudiante.java) definan el tipo genérico correspondiente; la segunda porque la interfaz
[Registro](https://aztlan.fciencias.unam.mx/gitlab/2020-1-icc/practica6/blob/master/src/main/java/mx/unam/ciencias/icc/Registro.java) también se volvió genérica.

Una vez que hayan terminado con sus clases, deben compilar al hacer:

```
$ mvn compile
```

También deben pasar todas las pruebas unitarias al hacer:

```
$ mvn test
```

Por último, se debe ejecutar correctamente el programa escrito en la clase
[Practica6](https://aztlan.fciencias.unam.mx/gitlab/2020-1-icc/practica6/blob/master/src/main/java/mx/unam/ciencias/icc/Practica6.java)
al hacer:

```
$ mvn install
...
$ java -jar target/practica6.jar -g archivo.txt # para guardar en archivo.txt, o
$ java -jar target/practica6.jar -c archivo.txt # para cargar de archivo.txt
```

Los únicos archivos que deben modificar son:

* `BaseDeDatos.java`,
* `BaseDeDatosEstudiantes.java`,
* `CampoEstudiante.java`,
* `Estudiante.java` y
* `Lista.java`.

*No deben modificar de ninguna manera ninguno de los otros archivos de la
práctica*.

### Repositorio

Pueden clonar la práctica con el siguiente comando:

```
$ git clone https://canek@aztlan.fciencias.unam.mx/gitlab/2020-1-icc/practica6.git
```

### Documentación

La documentación generada por JavaDoc la pueden consultar aquí:

[Documentación generada por JavaDoc para la práctica
6](https://aztlan.fciencias.unam.mx/~canek/2020-1-icc/practica6/apidocs/index.html)
