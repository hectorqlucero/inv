(ns sk.handlers.reportes.niveles.model
  (:require [sk.models.crud :refer [Query db]]
            [clojure.string :as st]))

(def get-niveles-sql
  (str
   "
    select
    inventario.*,
    productos.nombre as producto_id_formatted
    from inventario
    join productos on productos.id = inventario.producto_id
    order by producto_id_formatted
    "))

(defn get-niveles
  []
  (Query db get-niveles-sql))
