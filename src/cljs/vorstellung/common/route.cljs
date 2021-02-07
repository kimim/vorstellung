(ns vorstellung.common.route
  (:require
   [day8.re-frame.http-fx]
   [reagent.dom :as rdom]
   [reagent.core :as r]
   [re-frame.core :as rf]
   [reitit.frontend.easy :as rfe])
  (:import goog.History))

(defn navigate! [match _]
  (rf/dispatch [:common/navigate match]))

(defn start-router! [router navigate!]
  (rfe/start!
    router
    navigate!
    {}))
