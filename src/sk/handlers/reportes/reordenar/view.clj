(ns sk.handlers.reportes.reordenar.view
  (:require
   [sk.models.grid :refer [build-dashboard]]))

(defn reordenar-view
  [title rows]
  (let [table-id "reordenar_table"
        labels ["provedor"
                "producto"
                "cantidad"]
        db-fields [:provedor_id_formatted
                   :producto_id_formatted
                   :cantidad]
        fields (apply array-map (interleave db-fields labels))]
    (build-dashboard title rows table-id fields)))