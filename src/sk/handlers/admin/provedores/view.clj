(ns sk.handlers.admin.provedores.view
  (:require
   [sk.models.form :refer [build-field build-hidden-field build-modal-buttons
                           form]]
   [sk.models.grid :refer [build-grid build-modal modal-script]]))

(defn provedores-view
  [title rows]
  (let [labels ["NOMBRE" "EMAIL" "TELEFONO"]
        db-fields [:nombre :email :telefono]
        fields (apply array-map (interleave db-fields labels))
        table-id "provedores_table"
        args {:new true :edit true :delete true}
        href "/admin/provedores"]
    (build-grid title rows table-id fields href args)))

(defn build-provedores-fields
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
   (build-field {:label "EMAIL"
                 :type "text"
                 :id "email"
                 :name "email"
                 :placeholder "email aqui..."
                 :required false
                 :value (:email row)})
   (build-field {:label "TELEFONO"
                 :type "text"
                 :id "telefono"
                 :name "telefono"
                 :placeholder "telefono aqui..."
                 :required false
                 :value (:telefono row)})))

(defn build-provedores-form
  [title row]
  (let [fields (build-provedores-fields row)
        href "/admin/provedores/save"
        buttons (build-modal-buttons)]
    (form href fields buttons)))

(defn build-provedores-modal
  [title row]
  (build-modal title row (build-provedores-form title row)))

(defn provedores-edit-view
  [title row rows]
  (list
   (provedores-view "provedores Manteniento" rows)
   (build-provedores-modal title row)))

(defn provedores-add-view
  [title row rows]
  (list
   (provedores-view "provedores Mantenimiento" rows)
   (build-provedores-modal title row)))

(defn provedores-modal-script
  []
  (modal-script))
