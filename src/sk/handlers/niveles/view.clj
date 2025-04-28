(ns sk.handlers.niveles.view
  (:require [sk.models.grid :refer [build-dashboard]]))

(defn niveles-view
  [title rows]
  (let [table-id "niveles_table"
        labels []
        db-fields []
        fields (apply array-map (interleave db-fields labels))]
    (build-dashboard title rows table-id fields)))
