(ns sk.handlers.reportes.ventas.model
  (:require [sk.models.crud :refer [db Query]]))

(def get-ventas-sql
  (str
   "
    select
    productos.nombre as producto_id_formatted,
    movimientos.cantidad
    from movimientos
    join productos on productos.id = movimientos.producto_id
    where movimientos.tipo_movimiento = 'venta'
    and 
    month(movimientos.fecha_movimiento) = month(curdate())
    and
    year(movimientos.fecha_movimiento) = year(curdate())
    order by producto_id_formatted
    "))

(defn get-ventas
  []
  (Query db get-ventas-sql))

(comment
  (get-ventas))