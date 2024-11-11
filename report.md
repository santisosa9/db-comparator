**Tablas adicionales en `bdd2_ejercicio_1`:** 
   * facturas_inconsistentes

**Tablas adicionales en `prueba`:** 
   * facturita

**La columna `telefono` de la tabla en común `cliente` tiene tipos distintos:** 
   * (int, varchar)

**Columnas adicionales en `bdd2_ejercicio_1.factura`:** 
   * max_items

**Columnas adicionales en `prueba.factura`:** 
   * columnaAdicional

**Claves únicas adicionales en `prueba.factura`:** 
   * fecha

**Indices adicionales en `prueba.factura`:** 
   * fecha

**Columnas adicionales en `bdd2_ejercicio_1.itemfactura`:** 
   * cod_producto

**Columnas adicionales en `prueba.itemfactura`:** 
   * nro_cliente
   * descripcion

**El trigger `limit_items` de la tabla `itemfactura` tiene eventos de disparo distintos:** 
   * (INSERT, DELETE)

**El trigger `limit_items` de la tabla `itemfactura` tiene tiempos de disparo distintos:** 
   * (BEFORE, AFTER)

**Claves primarias adicionales en `bdd2_ejercicio_1.itemfactura`:** 
   * cod_producto

**Claves primarias adicionales en `prueba.itemfactura`:** 
   * nro_cliente
   * descripcion

**Indices adicionales en `prueba.itemfactura`:** 
   * fk_cod_producto
   * PRIMARY

**El indice `PRIMARY` de la tabla en común `itemfactura` esta asociado a columnas distintas:** 
   * (cod_producto, nro_cliente)

**El indice `PRIMARY` de la tabla en común `itemfactura` esta asociado a columnas distintas:** 
   * (nro_factura, descripcion)

**Claves primarias adicionales en `bdd2_ejercicio_1.producto`:** 
   * cod_producto

**Claves primarias adicionales en `prueba.producto`:** 
   * descripcion

**El indice `PRIMARY` de la tabla en común `producto` esta asociado a columnas distintas:** 
   * (cod_producto, descripcion)

**Procedimientos adicionales en `bdd2_ejercicio_1`:** 
   * ejemplo
   * verificar_facturas_inconsistentes

