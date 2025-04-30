(ns sk.handlers.admin.movimientos.model
  (:require
   [sk.models.crud :refer [db Query Save]]))

(def get-movimientos-sql
  (str
   "
SELECT movimientos.*,
    productos.nombre as producto_id_formatted,
    DATE_FORMAT(movimientos.fecha_movimiento, '%d-%m-%Y') as fecha_movimiento_formatted
FROM movimientos
    JOIN productos on productos.id = movimientos.producto_id
    ORDER BY producto_id_formatted
"))

(defn get-movimientos
  []
  (Query db get-movimientos-sql))

(def get-movimientos-id-sql
  (str
   "
SELECT movimientos.*,
    productos.nombre as producto_id_formatted,
    date_format(movimientos.fecha_movimiento,'%d-%m-%Y) as fecha_movimiento_formatted
FROM movimientos
    JOIN productos on productos.id = movimientos.producto_id
    ORDER BY producto_id_formatted
WHERE id = ?
"))

(defn get-inventario-por-producto
  [producto-id]
  (let [total (-> (Query db ["select cantidad from inventario where producto_id = ?" producto-id])
                  first
                  :cantidad)
        balance (or total 0)]
    balance))

(defn actualizar-inventario
  [producto-id tipo-movimiento cantidad]
  (let [inv-total (get-inventario-por-producto producto-id)
        ajuste (cond
                 (= tipo-movimiento "compra") (+ inv-total cantidad)
                 (= tipo-movimiento "venta") (- inv-total cantidad)
                 :else 0)
        row {:cantidad ajuste}
        result (Save db :inventario row ["producto_id = ?" producto-id])]
    (if (seq result) 1 0)))

(defn productos-options
  []
  (let [data (Query db "select id as value, nombre as label from productos order by nombre")
        options (map #(assoc % :label (str (:label %) " - " (get-inventario-por-producto (:value %)))) data)]
    options))

(defn get-movimientos-id
  [id]
  (first (Query db [get-movimientos-id-sql id])))

(defn tipo_movimiento-options
  []
  [{:value "venta" :label "Venta"}
   {:value "compra" :label "Compra"}])

(comment
  (tipo_movimiento-options)
  (productos-options)
  (get-inventario-por-producto 1))
