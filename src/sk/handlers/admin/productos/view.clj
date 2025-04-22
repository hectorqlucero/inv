(ns sk.handlers.admin.productos.view
  (:require
   [sk.models.form :refer [build-field build-hidden-field build-modal-buttons
                           form]]
   [sk.models.grid :refer [build-grid build-modal modal-script]]))

(defn productos-view
  [title rows]
  (let [labels ["NOMBRE" "PRECIO" "CATEGORIA"]
        db-fields [:nombre :precio_formatted :categoria]
        fields (apply array-map (interleave db-fields labels))
        table-id "productos_table"
        args {:new true :edit true :delete true}
        href "/admin/productos"]
    (build-grid title rows table-id fields href args)))

(defn build-productos-fields
  [row]
  (list
   (build-hidden-field {:id "id"
                        :name "id"
                        :value (:id row)})
   (build-field {:label "NOMBRE"
                 :type "text"
                 :id "nombre"
                 :name "nombre"
                 :placeholder "nombre aqui..."
                 :required false
                 :value (:nombre row)})
   (build-field {:label "PRECIO"
                 :type "number"
                 :min "1"
                 :max "10000"
                 :step "any"
                 :id "precio"
                 :name "precio"
                 :placeholder "precio aqui..."
                 :required false
                 :value (:precio row)})
   (build-field {:label "CATEGORIA"
                 :type "text"
                 :id "categoria"
                 :name "categoria"
                 :placeholder "categoria aqui..."
                 :required false
                 :value (:categoria row)})))

(defn build-productos-form
  [title row]
  (let [fields (build-productos-fields row)
        href "/admin/productos/save"
        buttons (build-modal-buttons)]
    (form href fields buttons)))

(defn build-productos-modal
  [title row]
  (build-modal title row (build-productos-form title row)))

(defn productos-edit-view
  [title row rows]
  (list
   (productos-view "productos Manteniento" rows)
   (build-productos-modal title row)))

(defn productos-add-view
  [title row rows]
  (list
   (productos-view "productos Mantenimiento" rows)
   (build-productos-modal title row)))

(defn productos-modal-script
  []
  (modal-script))
