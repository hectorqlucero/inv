(ns sk.handlers.reportes.reordenar.model
  (:require [sk.models.crud :refer [db Query]]))

(def get-reordenar-sql
  (str
   "
    select
    inventario.*,
    provedores.nombre as provedor_id_formatted,
    productos.nombre as producto_id_formatted
    from inventario
    join provedores on provedores.id = inventario.provedor_id
    join productos on productos.id = inventario.producto_id
    where inventario.cantidad < 5
    order by producto_id_formatted
    "))

(defn get-reordenar
  []
  (Query db get-reordenar-sql))

(comment
  (get-reordenar))