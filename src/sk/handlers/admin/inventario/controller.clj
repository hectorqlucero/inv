(ns sk.handlers.admin.inventario.controller
  (:require
   [sk.handlers.admin.inventario.model :refer [get-inventario get-inventario-id]]
   [sk.handlers.admin.inventario.view :refer [inventario-add-view
                                              inventario-edit-view
                                              inventario-modal-script
                                              inventario-view]]
   [sk.layout :refer [application error-404]]
   [sk.models.crud :refer [build-form-delete build-form-save]]
   [sk.models.util :refer [get-session-id user-level]]))

(defn inventario [_]
  (let [title "Inventario"
        ok (get-session-id)
        js nil
        rows (get-inventario)
        content (inventario-view title rows)]
    (if
     (or
      (= (user-level) "A")
      (= (user-level) "S"))
      (application title ok js content)
      (application title ok nil "Only <strong>los administrators </strong> can access this option!!!"))))

(defn inventario-edit
  [id]
  (let [title "Modificar inventario"
        ok (get-session-id)
        js (inventario-modal-script)
        row (get-inventario-id  id)
        rows (get-inventario)
        content (inventario-edit-view title row rows)]
    (application title ok js content)))

(defn inventario-save
  [{params :params}]
  (let [table "inventario"
        result (build-form-save params table)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/inventario")
      (error-404 "No se pudo procesar el record!" "/admin/inventario"))))

(defn inventario-add
  [_]
  (let [title "Crear nuevo inventario"
        ok (get-session-id)
        js (inventario-modal-script)
        row nil
        rows (get-inventario)
        content (inventario-add-view title row rows)]
    (application title ok js content)))

(defn inventario-delete
  [id]
  (let [table "inventario"
        result (build-form-delete table id)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/inventario")
      (error-404 "No se pudo procesar el record!" "/admin/inventario"))))

