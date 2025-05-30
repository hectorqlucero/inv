(ns sk.layout
  (:require
   [clj-time.core :as t]
   [hiccup.page :refer [html5 include-css include-js]]
   [sk.migrations :refer [config]]
   [sk.models.util :refer [user-level user-name]]))

(defn build-reportes []
  (list
   nil
   (when (or
          (= (user-level) "A")
          (= (user-level) "S"))
     (list
      [:li [:a.dropdown-item {:href "/reportes/niveles"} "Niveles de Inventario"]]
      [:li [:a.dropdown-item {:href "/reportes/movimientos"} "Movimientos"]]
      [:li [:a.dropdown-item {:href "/reportes/reordenar"} "Re-Ordenar Inventario"]]
      [:li [:a.dropdown-item {:href "/reportes/valor"} "Valor del Inventario"]]
      [:li [:a.dropdown-item {:href "/reportes/inexistencia"} "Inexistencia"]]
      [:li [:a.dropdown-item {:href "/reportes/hoy"} "Ventas Hoy"]]
      [:li [:a.dropdown-item {:href "/reportes/ventas"} "Ventas del Mes"]]
      [:li [:a.dropdown-item {:href "/reportes/tipo"} "Movimientos por Tipo"]]
      [:li [:a.dropdown-item {:href "/reportes/maximo"} "Producto mas vendido en el Mes"]]
      (when (= (user-level) "S")
        (list
         nil))))))

(defn build-admin []
  (list
   nil
   (when (or
          (= (user-level) "A")
          (= (user-level) "S"))
     (list
      [:li [:a.dropdown-item {:href "/admin/provedores"} "Provedores"]]
      [:li [:a.dropdown-item {:href "/admin/productos"} "Productos"]]
      [:li [:a.dropdown-item {:href "/admin/inventario"} "Inventario"]]
      (when (= (user-level) "S")
        (list
         [:li [:a.dropdown-item {:href "/icambios"} "Cambios del Inventario"]]
         [:li [:a.dropdown-item {:href "/admin/users"} "Usuarios"]]))))))

(defn menus-private []
  (list
   [:nav.navbar.navbar-expand-lg.navbar-light.bg-light.fixed-top
    [:div.container-fluid
     [:a.navbar-brand {:href "#"}
      [:img {:src "/images/logo.png"
             :alt (:site-name config)
             :style "width:40px;height:40px;"}]]
     [:button.navbar-toggler {:type "button"
                              :data-bs-toggle "collapse"
                              :data-bs-target "#collapsibleNavbar"
                              :aria-controls "collapsibleNavbar"
                              :aria-expanded "false"
                              :aria-label "Toggle navigation"}
      [:span.navbar-toggler-icon]]
     [:div#collapsibleNavbar.collapse.navbar-collapse
      [:ul.navbar-nav.ms-auto
       [:li.nav-item [:a.nav-link.active {:href "/"
                                          :aria-current "page"} "Inicio"]]
       [:li.nav-item [:a.nav-link {:href "/admin/movimientos"} "Movimientos"]]
       (when
        (or
         (= (user-level) "U")
         (= (user-level) "A")
         (= (user-level) "S"))
         [:li.nav-item.dropdown
          [:a.nav-link.dropdown-toggle {:href "#"
                                        :id "navdrop0"
                                        :role "button"
                                        :data-bs-toggle "dropdown"
                                        :aria-expanded "false"} "Reportes"]
          [:ul.dropdown-menu {:aria-labelledby "navdrop0"}
           (build-reportes)]])
       (when
        (or
         (= (user-level) "U")
         (= (user-level) "A")
         (= (user-level) "S"))
         [:li.nav-item.dropdown
          [:a.nav-link.dropdown-toggle {:href "#"
                                        :id "navdrop0"
                                        :role "button"
                                        :data-bs-toggle "dropdown"
                                        :aria-expanded "false"} "Administrar"]
          [:ul.dropdown-menu {:aria-labelledby "navdrop0"}
           (build-admin)]])
       [:li.nav-item [:a.nav-link {:href "/home/logoff"} (str "Salir [" (user-name) "]")]]]]]]))

(defn menus-public []
  (list
   [:nav.navbar.navbar-expand-lg.navbar-light.bg-light.fixed-top
    [:div.container-fluid
     [:a.navbar-brand {:href "#"}
      [:img {:src "/images/logo.png"
             :alt (:site-name config)
             :style "width:40px;height:40px;"}]]
     [:button.navbar-toggler {:type "button"
                              :data-bs-toggle "collapse"
                              :data-bs-target "#collapsibleNavbar"
                              :aria-controls "collapsibleNavbar"
                              :aria-expanded "false"
                              :aria-label "Toggle navigation"}
      [:span.navbar-toggler-icon]]
     [:div#collapsibleNavbar.navbar-collapse
      [:ul.navbar-nav.ms-auto
       [:li.nav-item [:a.nav-link.active {:aria-current "page"
                                          :href "/"} "Inicio"]]
       [:li.nav-item [:a.nav-link {:href "/home/login"
                                   :aria-current "page"} "Entrar al sitio"]]]]]]))

(defn menus-none []
  (list
   [:nav.navbar.navbar-expand-lg.navbar-light.bg-light.fixed-top
    [:div.container-fluid
     [:a.navbar-brand {:href "#"}
      [:img {:src "/images/logo.png"
             :alt (:site-name config)
             :style "width:40px;height:40px;"}]]
     [:button.navbar-toggler {:type "button"
                              :data-bs-toggle "collapse"
                              :data-bs-target "#collapsibleNavbar"
                              :aria-controls "collapsibleNavbar"
                              :aria-expanded "false"
                              :aria-label "Toggle navigation"}
      [:span.navbar-toggler-icon]]
     [:div#collapsibleNavbar.collapse.navbar-collapse]]]))

(defn app-css []
  (list
   (include-css "/bootstrap5/css/bootstrap.min.css")
   (include-css "/bootstrap-icons/font/bootstrap-icons.css")
   (include-css "/bootstrap-table-master/dist/bootstrap-table.min.css")
   (include-css "/css/extra.css")))

(defn app-js []
  (list
   (include-js "/js/jquery.min.js")
   (include-js "/bootstrap5/js/bootstrap.bundle.min.js")
   (include-js "/bootstrap-table-master/dist/extensions/export/tableExport.min.js")
   (include-js "/bootstrap-table-master/dist/extensions/export/jspdf.umd.min.js")
   (include-js "/bootstrap-table-master/dist/extensions/export/bootstrap-table.min.js")
   (include-js "/bootstrap-table-master/dist/extensions/export/bootstrap-table-export.min.js")
   (include-js "/bootstrap-table-master/dist/extensions/export/bootstrap-table-print.min.js")
   (include-js "/bootstrap-table-master/dist/locale/bootstrap-table-es-MX.min.js")
   (include-js "/js/extra.js")))

(defn application [title ok js & content]
  (html5 {:ng-app (:site-name config) :lang "en"}
         [:head
          [:title (if title
                    title
                    (:site-name config))]
          [:meta {:charset "UTF-8"}]
          [:meta {:name "viewport"
                  :content "width=device-width, initial-scale=1"}]
          (app-css)
          [:link {:rel "shortcut icon"
                  :type "image/x-icon"
                  :href "data:image/x-icon;,"}]]
         [:body
          [:div.container.flex-nowrap.overflow-auto.margin-top {:style "margin-top:75px;margin-bottom:25px;"}
           (cond
             (= ok -1) (menus-none)
             (= ok 0) (menus-public)
             (> ok 0) (menus-private))
           [:div {:style "padding-left:14px;"} content]]
          (app-js)
          js
          [:footer.bg-light.text-center.fixed-bottom
           [:span  "Copyright &copy;" (t/year (t/now)) " " (:company-name config) " - All Rights Reserved"]]]))

(defn error-404 [content return-url]
  (html5 {:ng-app (:site-name config) :lang "es"}
         [:head
          [:title "Mesaje"]
          [:meta {:charset "UTF-8"}]
          [:meta {:name "viewport"
                  :content "width=device-width, initial-scale=1"}]
          (app-css)
          [:link {:rel "shortcut iconcompojure"
                  :type "image/x-icon"
                  :href "data:image/x-icon;,"}]]
         [:body
          [:div.container.flex-nowrap.overflow-auto.margin-top {:style "margin-top:75px;margin-bottom:25px;"}
           (menus-none)
           [:div {:style "padding-left:14px;"}
            [:div
             [:p [:h3 [:b "Mensaje: "]] [:h3 content]]
             [:p [:h3 [:a {:href return-url} "Clic aqui para " [:strong "Continuar"]]]]]]]

          (app-js)
          nil
          [:footer.bg-light.text-center.fixed-bottom
           [:span  "Copyright &copy;" (t/year (t/now)) " " (:company-name config) " - All Rights Reserved"]]]))
