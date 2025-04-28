(ns sk.handlers.reportes.movimientos.model
  (:require [sk.models.crud :refer [Query db]]
            [clojure.string :as st]))

(def get-movimientos-sql
  (str
   "
    select
    movimientos.*,
    date_format(movimientos.fecha_movimiento,'%d-%m-%Y') as fecha_movimiento_formatted,
    productos.nombre as producto_id_formatted
    from movimientos
    join productos on productos.id = movimientos.producto_id
    order by producto_id_formatted
    "))

(defn get-movimientos
  []
  (Query db get-movimientos-sql))
