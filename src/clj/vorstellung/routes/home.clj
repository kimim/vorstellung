(ns vorstellung.routes.home
  (:require
   [vorstellung.layout :as layout]
   [vorstellung.db.core :as db]
   [clojure.java.io :as io]
   [vorstellung.middleware :as middleware]
   [ring.util.response :refer [redirect]]
   [ring.util.http-response :as response]))

(defn home-page [request]
  (layout/render request "home.html" {:script "js/app.js"}))

(defn login-page [request]
  (layout/render request "home.html" {:script "/js/login.js"}))

(defn signin [request]
  (let [user (db/get-user (:params request))
        session (:session request)]
    (if (= (:password user) (get-in request [:params :password]))
      (let [updated-session (assoc session :identity (keyword (:email user)))]
        (-> (redirect "/")
            (assoc :session updated-session)
            (home-page)))
      (login-page request))))

(defn signup [request]
  (try
    (db/create-user! (:params request))
    (signin request)
    (catch Exception e
      (print e)
      {:status 400
       :body {:message "Already created"}})))

(defn home-routes []
  [""
   {:middleware [#_middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/login" {:get login-page
              :post signup}]
   ["/signin" {:post signin}]
   ["/signup" {:post signup}]
   ["/docs" {:get (fn [_]
                    (-> (response/ok (-> "docs/docs.md" io/resource slurp))
                        (response/header "Content-Type" "text/plain; charset=utf-8")))}]])
