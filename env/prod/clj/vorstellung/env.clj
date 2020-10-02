(ns vorstellung.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[vorstellung started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[vorstellung has shut down successfully]=-"))
   :middleware identity})
