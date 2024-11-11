# DB Comparator

DB Comparator es una herramienta diseñada para comparar de manera estructural dos bases de datos relacionales, generando un reporte en formato Markdown que facilita la revisión de diferencias.

#### Configuración de Conexión
Antes de ejecutar la herramienta, añade la información de conexión de las bases de datos en el archivo de configuración `input.properties`, ubicado en el directorio `src/main/resources/config`.

Añadir datos para la conexión a las bases de datos en el archivo `input.properties` ubicado en `src/main/resources/config`.

#### Ejecución
Para ejecutar usando maven: `mvn clean compile exec:java -Dexec.mainClass="Main"`

#### Reporte de resultados
El resultado de la comparación se escribirá en un archivo `report.md`, permitiendo una visualización clara y estructurada de las diferencias. Este formato facilita la lectura y análisis del reporte.

#### Compatibilidad
Actualmente, DB Comparator es compatible exclusivamente con bases de datos MySQL. Sin embargo, la arquitectura de la herramienta permite su extensión para soportar otros motores de bases de datos con facilidad.

##### Extensión de Compatibilidad a Otros Motores
Para agregar soporte a otros motores de bases de datos, se deben implementar las siguientes interfaces:

`DatabaseConnection`: Esta interfaz define el contrato para establecer la conexión con el motor de base de datos.

`DatabaseBuilder`: Esta interfaz permite obtener, a través de consultas, toda la información necesaria para la comparación estructural, incluyendo tablas, procedimientos, triggers, índices, etc.

La implementación de estas interfaces facilita la integración de nuevos motores sin modificar la estructura principal de la herramienta.