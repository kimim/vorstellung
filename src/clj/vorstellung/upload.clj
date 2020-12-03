(ns vorstellung.upload)

(defn upload [request]
  (println (:body request))
  #_(io/copy (:tempfile file) (io/file (str "files/" (:filename file))))
  {:status 200
   :body {:name "file"
          :size 2}})
