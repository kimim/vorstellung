(ns vorstellung.login.core
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
    [vorstellung.login.page :as login-page])
  (:import goog.History))

(defn navigate! [match _]
  (rf/dispatch [:common/navigate match]))

(defn start-router! []
  (rfe/start!
   (reitit/router
    [["/"       {:name      :signin
                 :view      login-page/signin}]
     ["/signup" {:name      :signup
                 :view      login-page/signup}]])
   navigate!
   {}))

(defn page []
  (if-let [page @(rf/subscribe [:common/page])]
    [:div {:style {:display "flex"}}
     [page]]))

(defn init! []
  (start-router!)
  (rf/clear-subscription-cache!)
  (rdom/render [#'page] (.getElementById js/document "app")))
