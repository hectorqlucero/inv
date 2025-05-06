(ns sk.handlers.reportes.tipo.view
  (:require
   [sk.models.grid :refer [build-dashboard]]))

(defn tipo-view
  [title rows]
  (let [table-id "tipo_table"
        labels ["TIPO" "FECHA" "PRODUCTO" "CANTIDAD"]
        db-fields [:tipo_movimiento :fecha_movimiento_formatted :producto_id_formatted :cantidad]
        fields (apply array-map (interleave db-fields labels))]
    (build-dashboard title rows table-id fields)))
