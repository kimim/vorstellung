(ns vorstellung.auth.session
  (:require
   [ring.util.response :refer [redirect]]
   [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
   [buddy.auth.backends.session :refer [session-backend]]
   [vorstellung.layout :as layout]))

(def authdata
  {:user "pass"})

(defn login [request]
  (layout/render request "home.html" {:script "/js/login.js"}))

(defn logout [request]
  (let [next-url (get-in request [:query-params :next] "/")]
    (-> (redirect next-url)
        (dissoc :identity)
        (update :session dissoc :identity))))

(defn authenticate
  [request]
  (let [username (get-in request [:params :email])
        password (get-in request [:params :password])
        session (:session request)
        found-password (authdata (keyword username))]
    (println password found-password)
    (if (and found-password (= found-password password))
      (assoc session :identity (keyword username))
      session)))

(defn signin [request]
  (let [updated-session (authenticate request)
        ;; probababily set next url in params
        next-url (get-in request [:query-params :next] "/")]
    (-> (redirect next-url)
        (assoc :session updated-session))))

(defn wrap-auth [handler]
  (let [backend (session-backend)]
    (-> handler
        (wrap-authentication backend)
        (wrap-authorization backend))))
