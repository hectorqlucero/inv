(ns sk.handlers.reportes.niveles.view
  (:require
   [sk.models.grid :refer [build-dashboard]]))

(defn niveles-view
  [title rows]
  (let [table-id "niveles_table"
        labels ["producto"
                "cantidad"]
        db-fields [:producto_id_formatted
                   :cantidad]
        fields (apply array-map (interleave db-fields labels))]
    (build-dashboard title rows table-id fields)))
