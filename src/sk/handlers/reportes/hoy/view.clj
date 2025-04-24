(ns sk.handlers.reportes.hoy.view
  (:require
   [sk.models.grid :refer [build-dashboard]]))

(defn hoy-view
  [title rows]
  (let [table-id "hoy_table"
        labels ["producto"
                "total"]
        db-fields [:producto_id_formatted
                   :cantidad]
        fields (apply array-map (interleave db-fields labels))]
    (build-dashboard title rows table-id fields)))