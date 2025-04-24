(ns sk.handlers.reportes.movimientos.view
  (:require
   [sk.models.grid :refer [build-dashboard]]))

(defn movimientos-view
  [title rows]
  (let [table-id "movimientos_table"
        labels ["producto"
                "tipo"
                "cantidad"
                "fecha"]
        db-fields [:producto_id_formatted
                   :tipo_movimiento
                   :cantidad
                   :fecha_movimiento_formatted]
        fields (apply array-map (interleave db-fields labels))]
    (build-dashboard title rows table-id fields)))