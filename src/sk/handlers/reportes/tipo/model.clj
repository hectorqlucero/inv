(ns sk.handlers.reportes.tipo.model
  (:require
   [sk.models.crud :refer [db Query]]))

(def get-tipo-sql
  (str
   "
SELECT 
    movimientos.tipo_movimiento as tipo_movimiento,
    movimientos.fecha_movimiento,
    DATE_FORMAT(movimientos.fecha_movimiento, '%d/%m/%Y') as fecha_movimiento_formatted,
    productos.nombre as producto_id_formatted,
    movimientos.cantidad as cantidad
FROM movimientos
    JOIN productos on productos.id = movimientos.producto_id
ORDER by movimientos.tipo_movimiento DESC,movimientos.fecha_movimiento
"))

(defn get-tipo
  []
  (Query db [get-tipo-sql]))

(comment
  (get-tipo))

