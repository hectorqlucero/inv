(ns sk.handlers.admin.inventario.view
  (:require
   [sk.handlers.admin.inventario.model :refer [productos-options provedores-options]]
   [sk.models.form :refer [build-field build-select build-hidden-field build-modal-buttons
                           form]]
   [sk.models.grid :refer [build-grid build-modal modal-script]]))

(defn inventario-view
  [title rows]
  (let [labels ["PRODUCTO" "CANTIDAD" "PROVEDOR" "ULTIMA ACTUALIZACION"]
        db-fields [:producto_id_formatted :cantidad :provedor_id_formatted :ultima_actualizacion]
        fields (apply array-map (interleave db-fields labels))
        table-id "inventario_table"
        args {:new true :edit true :delete true}
        href "/admin/inventario"]
    (build-grid title rows table-id fields href args)))

(defn build-inventario-fields
  [row]
  (list
   (build-hidden-field {:id "id"
                        :name "id"
                        :value (:id row)})
   (build-select {:label "PRODUCTO"
                  :id "producto_id"
                  :name "producto_id"
                  :required true
                  :value (:producto_id row)
                  :options (productos-options)})
   (build-field {:label "CANTIDAD"
                 :type "number"
                 :min "0"
                 :max "10000"
                 :step "1"
                 :id "cantidad"
                 :name "cantidad"
                 :placeholder "cantidad aqui..."
                 :required true
                 :value (:cantidad row)})
   (build-select {:label "PROVEDOR"
                  :type "text"
                  :id "provedor_id"
                  :name "provedor_id"
                  :required true
                  :value (:provedor_id row)
                  :options (provedores-options)})))

(defn build-inventario-form
  [title row]
  (let [fields (build-inventario-fields row)
        href "/admin/inventario/save"
        buttons (build-modal-buttons)]
    (form href fields buttons)))

(defn build-inventario-modal
  [title row]
  (build-modal title row (build-inventario-form title row)))

(defn inventario-edit-view
  [title row rows]
  (list
   (inventario-view "inventario Manteniento" rows)
   (build-inventario-modal title row)))

(defn inventario-add-view
  [title row rows]
  (list
   (inventario-view "inventario Mantenimiento" rows)
   (build-inventario-modal title row)))

(defn inventario-modal-script
  []
  (modal-script))
