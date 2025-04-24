(ns sk.handlers.reportes.valor.view
  (:require
   [sk.models.grid :refer [build-dashboard]]))

(defn valor-view
  [title rows]
  (let [table-id "valor_table"
        labels ["provedor"
                "producto"
                "cantidad"
                "precio"
                "total"]
        db-fields [:provedor_id_formatted
                   :producto_id_formatted
                   :cantidad
                   :precio
                   :total]
        fields (apply array-map (interleave db-fields labels))]
    (build-dashboard title rows table-id fields)))