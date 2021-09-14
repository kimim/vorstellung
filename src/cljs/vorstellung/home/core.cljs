(ns vorstellung.home.core
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
   [vorstellung.home.page :as page])
  (:import goog.History))

(defn home-page []
  [:section.section>div.container>div.content
   (when-let [docs @(rf/subscribe [:docs])]
     [:div {:dangerouslySetInnerHTML {:__html (md->html docs)}}])])

(def router
  (reitit/router
   [["/"        {:name        :home
                 :view        {:page #'home-page
                               :tool (fn [] [:div])}
                 :controllers [{:start (fn [_] (rf/dispatch [:page/init-home]))}]}]
    ["/about"   {:name        :about
                 :view        {:page #'home-page
                               :tool (fn [] [:div])}
                 :controllers [{:start (fn [_] (rf/dispatch [:page/init-home]))}]}]]))

;; -------------------------
;; Initialize app
(defn ^:dev/after-load mount-components []
  (rf/clear-subscription-cache!)
  (rdom/render [#'header/page] (.getElementById js/document "app")))

(defn init! []
  (route/start-router! router route/navigate!)
  (ajax/load-interceptors!)
  (mount-components))
