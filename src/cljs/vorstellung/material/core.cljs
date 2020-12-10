(ns vorstellung.material.core
  (:require
   [day8.re-frame.http-fx]
   [reagent.dom :as rdom]
   [reagent.core :as r]
   [re-frame.core :as rf]
   [goog.events :as events]
   [goog.history.EventType :as HistoryEventType]
   [vorstellung.events]
   [reitit.core :as reitit]
   [reitit.frontend.easy :as rfe]
   [clojure.string :as string]
   [vorstellung.header :as header]
   [vorstellung.material.data-grid :as data-grid]
   [vorstellung.material.grid :as layout]
   [vorstellung.material.upload :as upload]
   [vorstellung.material.picker :as picker])
  (:import goog.History))

(defn navigate! [match _]
  (rf/dispatch [:common/navigate match]))

(defn start-router! []
  (rfe/start!
   (reitit/router
    [["/data-grid"  {:name      :data-grid
                     :view      data-grid/page}]
     ["/grid"       {:name      :layout
                     :view      layout/page}]
     ["/upload"     {:name      :upload
                     :view      upload/page}]
     ["/picker"     {:name      :picker
                     :view      picker/page}]])
   navigate!
   {}))

(defn init! []
  (start-router!)
  (rf/clear-subscription-cache!)
  (rdom/render [#'header/page] (.getElementById js/document "app")))
