(ns sk.handlers.reportes.inexistencia.view
  (:require
   [sk.models.grid :refer [build-dashboard]]))

(defn inexistencia-view
  [title rows]
  (let [table-id "inexistencia_table"
        labels ["PRODUCTO" "CANTIDAD"]
        db-fields [:producto_id_formatted :cantidad]
        fields (apply array-map (interleave db-fields labels))]
    (build-dashboard title rows table-id fields)))
