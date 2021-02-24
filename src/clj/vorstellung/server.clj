(ns vorstellung.server
  (:require
   [clojure.tools.logging :as logging]
   [org.httpkit.server :as http-server]))

(defn start [{:keys [handler host port] :as opts}]
  (try
    (logging/info "starting HTTP server on port" port)
    (http-server/run-server
      handler
      (-> opts
          (assoc  :legacy-return-value? false)
          (dissoc :handler :init)))
    (catch Throwable t
      (logging/error t (str "server failed to start on" host
                            "port" port))
      (throw t))))

(defn stop
  ([http-server] (stop http-server 100))
  ([http-server timeout]
   (let [result @(future
                   (http-server/server-stop!
                    http-server {:timeout timeout}))]
     (logging/info "HTTP server stopped")
     result)))
