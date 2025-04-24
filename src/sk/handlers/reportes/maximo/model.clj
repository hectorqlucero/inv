(ns sk.handlers.reportes.maximo.model
  (:require [sk.models.crud :refer [db Query]]))

(def get-maximo-sql
  (str
   "
    select
    productos.nombre as producto_id_formatted,
    sum(movimientos.cantidad) as total
    from movimientos
    join productos on productos.id = movimientos.producto_id
    where movimientos.tipo_movimiento = 'venta'
    and 
    month(movimientos.fecha_movimiento) = month(curdate())
    and
    year(movimientos.fecha_movimiento) = year(curdate())
    group by producto_id_formatted
    order by total DESC
    limit 1
    "))

(defn get-maximo
  []
  (Query db get-maximo-sql))

(comment
  (get-maximo))