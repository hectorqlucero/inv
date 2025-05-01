(ns sk.handlers.reportes.inexistencia.model
  (:require
   [sk.models.crud :refer [db Query]]))

(def get-inexistencia-sql
  (str
   "
SELECT
    productos.nombre as producto_id_formatted,
    inventario.cantidad as cantidad
FROM inventario
JOIN productos on productos.id = inventario.producto_id
WHERE cantidad = 0
"))

(defn get-inexistencia
  []
  (Query db get-inexistencia-sql))

(comment
  (get-inexistencia))