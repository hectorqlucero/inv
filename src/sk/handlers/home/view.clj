(ns sk.handlers.home.view
  (:require
   [sk.models.form :refer [login-form password-form]]))

(defn home-view
  []
  (list
   [:div.container.mt-5
    [:div.text-center
     [:h1.text-info "Ruiz Inventory Solutions"]
     [:p.text-muted "Ofreciendo una gestión de inventario confiable y eficiente desde 1998."]
     [:p "123 Warehouse Lane, Industrial City, CA 90210"]
     [:p "Telefono: (686) 123-4567 | Email: contacto@ruizinventario.com"]]]))

(defn main-view
  "This creates the login form and we are passing the title from the controller"
  [title]
  (let [href "/home/login"]
    (login-form title href)))

(defn change-password-view
  [title]
  (password-form title))
