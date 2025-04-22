(ns sk.handlers.admin.productos.controller
  (:require
   [sk.handlers.admin.productos.model :refer [get-productos get-productos-id]]
   [sk.handlers.admin.productos.view :refer [productos-add-view
                                             productos-edit-view
                                             productos-modal-script
                                             productos-view]]
   [sk.layout :refer [application error-404]]
   [sk.models.crud :refer [build-form-delete build-form-save]]
   [sk.models.util :refer [get-session-id user-level]]))

(defn productos [_]
  (let [title "Productos"
        ok (get-session-id)
        js nil
        rows (get-productos)
        content (productos-view title rows)]
    (if
     (or
      (= (user-level) "A")
      (= (user-level) "S"))
      (application title ok js content)
      (application title ok nil "Only <strong>los administrators </strong> can access this option!!!"))))

(defn productos-edit
  [id]
  (let [title "Modificar productos"
        ok (get-session-id)
        js (productos-modal-script)
        row (get-productos-id  id)
        rows (get-productos)
        content (productos-edit-view title row rows)]
    (application title ok js content)))

(defn productos-save
  [{params :params}]
  (let [table "productos"
        result (build-form-save params table)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/productos")
      (error-404 "No se pudo procesar el record!" "/admin/productos"))))

(defn productos-add
  [_]
  (let [title "Crear nuevo productos"
        ok (get-session-id)
        js (productos-modal-script)
        row nil
        rows (get-productos)
        content (productos-add-view title row rows)]
    (application title ok js content)))

(defn productos-delete
  [id]
  (let [table "productos"
        result (build-form-delete table id)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/productos")
      (error-404 "No se pudo procesar el record!" "/admin/productos"))))

