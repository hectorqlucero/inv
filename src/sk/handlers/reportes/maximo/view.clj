(ns sk.handlers.reportes.maximo.view
  (:require [sk.models.grid :refer [build-dashboard]]))

(defn maximo-view
  [title rows]
  (let [table-id "maximo_table"
        labels ["producto"
                "total"]
        db-fields [:producto_id_formatted
                   :total]
        fields (apply array-map (interleave db-fields labels))]
    (build-dashboard title rows table-id fields)))
