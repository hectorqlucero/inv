(ns sk.handlers.admin.provedores.controller
  (:require
   [sk.handlers.admin.provedores.model :refer [get-provedores get-provedores-id]]
   [sk.handlers.admin.provedores.view :refer [provedores-add-view
                                              provedores-edit-view
                                              provedores-modal-script
                                              provedores-view]]
   [sk.layout :refer [application error-404]]
   [sk.models.crud :refer [build-form-delete build-form-save]]
   [sk.models.util :refer [get-session-id user-level]]))

(defn provedores [_]
  (let [title "Provedores"
        ok (get-session-id)
        js nil
        rows (get-provedores)
        content (provedores-view title rows)]
    (if
     (or
      (= (user-level) "A")
      (= (user-level) "S"))
      (application title ok js content)
      (application title ok nil "Only <strong>los administrators </strong> can access this option!!!"))))

(defn provedores-edit
  [id]
  (let [title "Modificar provedores"
        ok (get-session-id)
        js (provedores-modal-script)
        row (get-provedores-id  id)
        rows (get-provedores)
        content (provedores-edit-view title row rows)]
    (application title ok js content)))

(defn provedores-save
  [{params :params}]
  (let [table "provedores"
        result (build-form-save params table)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/provedores")
      (error-404 "No se pudo procesar el record!" "/admin/provedores"))))

(defn provedores-add
  [_]
  (let [title "Crear nuevo provedores"
        ok (get-session-id)
        js (provedores-modal-script)
        row nil
        rows (get-provedores)
        content (provedores-add-view title row rows)]
    (application title ok js content)))

(defn provedores-delete
  [id]
  (let [table "provedores"
        result (build-form-delete table id)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/provedores")
      (error-404 "No se pudo procesar el record!" "/admin/provedores"))))

