(ns vorstellung.upload
  (:require
   [clojure.java.io :as io]))

(defn upload [{{{:keys [filename tempfile size]} "file"} :multipart-params}]
  (try
    (println filename)
    (let [dir (io/file "files/")]
      (when-not (.exists dir) (.mkdir dir)))
    (io/copy tempfile (io/file (str "files/" filename)))
    {:status 200
     :body {:name filename
            :size size}}
    (catch Exception e {:status 500
                        :body (str (.getMessage e))})))
