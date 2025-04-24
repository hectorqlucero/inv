(ns sk.handlers.reportes.valor.model
  (:require [sk.models.crud :refer [db Query]]))

(def get-valor-sql
  (str
   "
    select
    inventario.*,
    provedores.nombre as provedor_id_formatted,
    productos.nombre as producto_id_formatted,
    format(productos.precio,2) as precio,
    format((inventario.cantidad * productos.precio),2) as total
    from inventario
    join provedores on provedores.id = inventario.provedor_id
    join productos on productos.id = inventario.producto_id
    order by producto_id_formatted
    "))

(defn get-valor
  []
  (Query db get-valor-sql))

(comment
  (get-valor))