(ns sk.routes.proutes
  (:require
   [sk.handlers.admin.inventario.controller :as inventario-controller]
   [sk.handlers.admin.productos.controller :as productos-controller]
   [sk.handlers.admin.provedores.controller :as provedores-controller]
   [compojure.core :refer [defroutes GET POST]]
   [sk.handlers.admin.users.controller :as users-controller]
   [sk.handlers.users.controller :as users-dashboard]))

(defroutes proutes
  (GET "/admin/inventario" params [] (inventario-controller/inventario params))
  (GET "/admin/inventario/edit/:id" [id] (inventario-controller/inventario-edit id))
  (POST "/admin/inventario/save" params [] (inventario-controller/inventario-save params))
  (GET "/admin/inventario/add" params [] (inventario-controller/inventario-add params))
  (GET "/admin/inventario/delete/:id" [id] (inventario-controller/inventario-delete id))
  (GET "/admin/productos" params [] (productos-controller/productos params))
  (GET "/admin/productos/edit/:id" [id] (productos-controller/productos-edit id))
  (POST "/admin/productos/save" params [] (productos-controller/productos-save params))
  (GET "/admin/productos/add" params [] (productos-controller/productos-add params))
  (GET "/admin/productos/delete/:id" [id] (productos-controller/productos-delete id))
  (GET "/admin/provedores" params [] (provedores-controller/provedores params))
  (GET "/admin/provedores/edit/:id" [id] (provedores-controller/provedores-edit id))
  (POST "/admin/provedores/save" params [] (provedores-controller/provedores-save params))
  (GET "/admin/provedores/add" params [] (provedores-controller/provedores-add params))
  (GET "/admin/provedores/delete/:id" [id] (provedores-controller/provedores-delete id))
  (GET "/admin/users" params [] (users-controller/users params))
  (GET "/admin/users/edit/:id" [id] (users-controller/users-edit id))
  (POST "/admin/users/save" params [] (users-controller/users-save params))
  (GET "/admin/users/add" params [] (users-controller/users-add params))
  (GET "/admin/users/delete/:id" [id] (users-controller/users-delete id))
  (GET "/users" params [] (users-dashboard/users params)))