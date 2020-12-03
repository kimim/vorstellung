(ns vorstellung.data-grid
  (:require
   [reagent.core :as r]
   ["@material-ui/core" :as m]
   ["@material-ui/data-grid" :as dg]))

(def rows  [{:id 1, :lastName "Snow", :firstName "Jon", :age 35}
            {:id 2, :lastName "Rain", :firstName "Hans", :age 45}])

(def cols [{:field :id :width 100}
           {:field :firstName :width 180}
           {:field :lastName :width 180}
           {:field :age :width 80}])

(defn people []
  [:div {:style {:height 400, :width "100%"}}
   [:> dg/DataGrid {:rows rows :columns cols :checkboxSelection true}
    ]])


(defn page []
  [:main {:style {:flexGrow 1 :padding "88px 24px 24px 24px"}}
   [:div
    [:> m/Grid {:container true :spacing 3}
     [:> m/Grid {:item true :xs 12 :sm 12 :lg 12}
      [:> m/Paper
       [people]]]]]])
