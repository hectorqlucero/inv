(ns sk.handlers.admin.movimientos.view
  (:require
   [sk.handlers.admin.movimientos.model :refer [productos-options
                                                tipo_movimiento-options]]
   [sk.models.form :refer [build-field build-hidden-field build-modal-buttons
                           build-select form]]
   [sk.models.grid :refer [build-grid build-modal modal-script]]))

(defn movimientos-view
  [title rows]
  (let [labels ["PRODUCTO" "TIPO MOVIMIENTO" "FECHA MOVIMIENTO" "CANTIDAD"]
        db-fields [:producto_id_formatted :tipo_movimiento :fecha_movimiento_formatted :cantidad]
        fields (apply array-map (interleave db-fields labels))
        table-id "movimientos_table"
        args {:new true :edit false :delete false}
        href "/admin/movimientos"]
    (build-grid title rows table-id fields href args)))

(defn build-movimientos-fields
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
   (build-select {:label "TIPO MOVIMIENTO"
                  :id "tipo_movimiento"
                  :name "tipo_movimiento"
                  :required true
                  :value (:tipo_movimiento row)
                  :options (tipo_movimiento-options)})
   (build-field {:label "FECHA_MOVIMIENTO"
                 :type "date"
                 :id "fecha_movimiento"
                 :name "fecha_movimiento"
                 :required true
                 :value (:fecha_movimiento row)})
   (build-field {:label "CANTIDAD"
                 :type "number"
                 :min "1"
                 :max "10000"
                 :step "1"
                 :id "cantidad"
                 :name "cantidad"
                 :placeholder "cantidad aqui..."
                 :required true
                 :value (:cantidad row)})))

(defn build-movimientos-form
  [title row]
  (let [fields (build-movimientos-fields row)
        href "/admin/movimientos/save"
        buttons (build-modal-buttons)]
    (form href fields buttons)))

(defn build-movimientos-modal
  [title row]
  (build-modal title row (build-movimientos-form title row)))

(defn movimientos-edit-view
  [title row rows]
  (list
   (movimientos-view "movimientos Manteniento" rows)
   (build-movimientos-modal title row)))

(defn movimientos-add-view
  [title row rows]
  (list
   (movimientos-view "movimientos Mantenimiento" rows)
   (build-movimientos-modal title row)))

(defn movimientos-modal-script
  []
  (modal-script))
