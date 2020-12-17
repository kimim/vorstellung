(ns vorstellung.routes.home
  (:require
   [clojure.java.io :as io]
   [ring.util.response :refer [redirect]]
   [ring.util.http-response :as response]
   [buddy.auth :refer [authenticated?]]
   [vorstellung.db.core :as db]
   [vorstellung.layout :as layout]
   [vorstellung.middleware :as middleware]
   [vorstellung.auth.session :as auth]
   [vorstellung.upload :as upload]))

(defn home-page [request app]
  (if (authenticated? request)
    (layout/render request "home.html" {:script app})
    (auth/login request)))

(defn signup [request]
  (try
    (db/create-user! (:params request))
    (auth/signin request)
    (catch Exception e
      (print e)
      {:status 400
       :body {:message "Already created"}})))

(defn home-routes []
  [""
   {:middleware [#_middleware/wrap-csrf
                 auth/wrap-auth
                 middleware/wrap-formats]}
   ["/"         {:get #(home-page % "/js/app.js")}]
   ["/login" {:get auth/login
              :post auth/signin}]
   ["/logout" {:get auth/logout}]
   ["/signup" {:post signup}]
   ["/upload" {:post upload/upload}]
   ["/docs" {:get (fn [_]
                    (-> (response/ok (-> "README.md" #_io/resource slurp))
                        (response/header "Content-Type" "text/plain; charset=utf-8")))}]])
