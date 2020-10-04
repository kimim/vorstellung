(ns vorstellung.routes.home
  (:require
   [vorstellung.layout :as layout]
   [clojure.java.io :as io]
   [vorstellung.middleware :as middleware]
   [ring.util.response]
   [ring.util.http-response :as response]))

(defn home-page [request]
  (layout/render request "home.html" {:script "js/app.js"}))

(defn login-page [request]
  (layout/render request "home.html" {:script "/js/login.js"}))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/login" {:get login-page}]
   ["/docs" {:get (fn [_]
                    (-> (response/ok (-> "docs/docs.md" io/resource slurp))
                        (response/header "Content-Type" "text/plain; charset=utf-8")))}]])
