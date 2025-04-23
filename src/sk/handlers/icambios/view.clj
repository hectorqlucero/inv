(ns sk.handlers.icambios.view
  (:require
   [sk.models.grid :refer [build-dashboard]]))

(defn icambios-view
  [title rows]
  (let [table-id "icambios_table"
        labels ["INVENTARIO ID" "PROVEEDOR" "PRODUCTO" "CANTIDAD_ANTERIOR" "CANTIDAD_NUEVA" "MOVIMIENTO" "FECHA"]
        db-fields [:inventario_id :provedor :producto :cantidad_anterior :cantidad_nueva :movimiento :fecha]
        fields (apply array-map (interleave db-fields labels))]
    (build-dashboard title rows table-id fields)))
