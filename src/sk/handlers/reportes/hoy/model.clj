(ns sk.handlers.reportes.hoy.model
  (:require [sk.models.crud :refer [Query db]]
            [clojure.string :as st]))

(def get-hoy-sql
  (str
   "
    select
    productos.nombre as producto_id_formatted,
    movimientos.cantidad
    from movimientos
    join productos on productos.id = movimientos.producto_id
    where movimientos.tipo_movimiento = 'venta'
    and date(movimientos.fecha_movimiento) = CURRENT_DATE
    "))

(defn get-hoy
  []
  (Query db get-hoy-sql))
