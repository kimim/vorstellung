(ns vorstellung.auth.core
  (:require
   [day8.re-frame.http-fx]
   [reagent.dom :as rdom]
   [reagent.core :as r]
   [re-frame.core :as rf]
   [goog.events :as events]
   [goog.history.EventType :as HistoryEventType]
   [markdown.core :refer [md->html]]
   [reitit.core :as reitit]
   [reitit.frontend.easy :as rfe]
   [clojure.string :as string]
   [vorstellung.common.route :as route]
   [vorstellung.events]
   [vorstellung.common.ajax :as ajax]
   [vorstellung.header :as header]
   [vorstellung.auth.page :as page])
  (:import goog.History))

(def router
  (reitit/router
   [["/"        {:name      :signin
                 :view      page/signin}]
     ["/signup" {:name      :signup
                 :view      page/signup}]]))

;; -------------------------
;; Initialize app
(defn ^:dev/after-load mount-components []
  (rf/clear-subscription-cache!)
  (rdom/render [#'header/page-no-header] (.getElementById js/document "app")))

(defn init! []
  (route/start-router! router route/navigate!)
  (ajax/load-interceptors!)
  (mount-components))
