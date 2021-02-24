(ns vorstellung.core
  (:require
   [clojure.tools.logging :as log]
   [clojure.tools.cli :refer [parse-opts]]
   [mount.core :as mount]
   [luminus-migrations.core :as migrations]
   [cider.nrepl :refer [cider-nrepl-handler]]
   [vorstellung.server :as http]
   [vorstellung.handler :as handler]
   [vorstellung.nrepl :as nrepl]
   [vorstellung.config :refer [env]])
  (:gen-class))

;; log uncaught exceptions in threads
(Thread/setDefaultUncaughtExceptionHandler
  (reify Thread$UncaughtExceptionHandler
    (uncaughtException [_ thread ex]
      (log/error {:what :uncaught-exception
                  :exception ex
                  :where (str "Uncaught exception on" (.getName thread))}))))

(def cli-options
  [["-p" "--port PORT" "Port number"
    :parse-fn #(Integer/parseInt %)]])

(mount/defstate ^{:on-reload :noop} http-server
  :start
  (http/start
    (-> env
        (assoc  :handler (handler/app))
        (update :port #(or (-> env :options :port) %))))
  :stop
  (http/stop http-server))

(mount/defstate ^{:on-reload :noop} repl-server
  :start
  (when (env :nrepl-port)
    (nrepl/start {:bind (env :nrepl-bind)
                  :handler cider-nrepl-handler
                  :port (env :nrepl-port)}))
  :stop
  (when repl-server
    (nrepl/stop repl-server)))


(defn stop-app []
  (doseq [component (:stopped (mount/stop))]
    (log/info component "stopped"))
  (shutdown-agents))

(defn start-app [args]
  (doseq [component (-> args
                        (parse-opts cli-options)
                        mount/start-with-args
                        :started)]
    (log/info component "started"))
  (.addShutdownHook (Runtime/getRuntime) (Thread. stop-app)))

(defn -main [& args]
  (mount/start #'vorstellung.config/env)
  (cond
    (nil? (:database-url env))
    (do
      (log/error "Database config not found, :database-url env variable must be set before running")
      (System/exit 1))
    (some #{"init"} args)
    (do
      (migrations/init (select-keys env [:database-url :init-script]))
      (System/exit 0))
    (migrations/migration? args)
    (do
      (migrations/migrate args (select-keys env [:database-url]))
      (System/exit 0))
    (some #{"create-migrate"} args)
    (do
      (migrations/create (get (vec args) 1) (select-keys env [:database-url])))
    :else
    (start-app args)))
