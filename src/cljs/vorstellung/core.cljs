(ns vorstellung.core
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
   [vorstellung.events]
   [vorstellung.ajax :as ajax]
   [vorstellung.header :as header]
   [vorstellung.icons :as icons]
   [vorstellung.grid :as grid]
   [vorstellung.bizcharts :as bizcharts]
   [vorstellung.data-grid :as data-grid])
  (:import goog.History))

(defn about-page []
  [:div {:style {:padding "88px 24px 24px 24px"}}
   [:img {:src "/img/warning_clojure.png"}]])

(defn home-page []
  [:section.section>div.container>div.content
   (when-let [docs @(rf/subscribe [:docs])]
     [:div {:dangerouslySetInnerHTML {:__html (md->html docs)}}])])

(defn page []
  (if-let [page @(rf/subscribe [:common/page])]
    [:div {:style {:display "flex"}}
     [header/navbar]
     [page]]))

(defn navigate! [match _]
  (rf/dispatch [:common/navigate match]))

(def router
  (reitit/router
    [["/" {:name        :home
           :view        icons/page
           :controllers [{:start (fn [_] (rf/dispatch [:page/init-home]))}]}]
     ["/icons" {:name :icons
                :view icons/page}]
     ["/grid" {:name :grid
               :view grid/page}]
     ["/bizcharts" {:name :bizcharts
                    :view bizcharts/page}]
     ["/data-grid" {:name :data-grid
                    :view data-grid/page}]
     ["/about" {:name :about
                :view #'about-page}]]))

(defn start-router! []
  (rfe/start!
    router
    navigate!
    {}))

;; -------------------------
;; Initialize app
(defn ^:dev/after-load mount-components []
  (rf/clear-subscription-cache!)
  (rdom/render [#'page] (.getElementById js/document "app")))

(defn init! []
  (start-router!)
  (ajax/load-interceptors!)
  (mount-components))
