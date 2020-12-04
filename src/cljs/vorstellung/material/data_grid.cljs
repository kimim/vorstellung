(ns vorstellung.material.data-grid
  (:require
   [reagent.core :as r]
   ["@material-ui/core" :as m]
   ["@material-ui/data-grid" :as dg]
   [vorstellung.utils :as utils]))

(def rows  (for [i (range 1 101)]
             {:id i, :lastName "Snow", :firstName "Jon", :age (rand-int 35)}))

(def cols [{:field :id :width 100}
           {:field :firstName :width 180}
           {:field :lastName :width 180}
           {:field :age :width 80}])

(defn export-rows [rows]
  (let [header-str (->> (first rows)
                    (keys)
                    (map name)
                    (interpose ",")
                    (reduce str))
        rows-str (->> rows
                      (map vals)
                      (map (fn [x] (interpose "," x)))
                      (map (fn [x] (reduce str x)))
                      (interpose "\n")
                      (reduce str))]
    (utils/download-data
     (str header-str "\n" rows-str)
     "exported-data.csv" "application/vnd.ms-excel")))

(defn people []
  [:div {:style {:height "90vh", :width "100%"}}
   [:> m/Paper
    [:> m/Toolbar
     [:> m/Button {:style {:margin "5px 5px 5px 10px"}
                   :type "button" :variant "contained" :color "primary"}
      "Load"]
     [:> m/Button {:style {:margin "5px 5px 5px 10px"}
                   :type "button" :variant "contained" :color "primary"}
      "Send"]
     [:> m/Button {:style {:margin "5px 5px 5px 10px"}
                   :type "button" :variant "contained" :color "primary"
                   :on-click #(export-rows rows)}
      "Export"]]
    [:> m/TableContainer {:style {:height "80vh", :width "auto"}}
     [:> dg/DataGrid {:rows rows :columns cols :checkboxSelection true :autoPageSize true
                      :exportAllData true}]]]])

(defn page []
  [:main {:style {:flexGrow 1 :padding "88px 24px 24px 24px"}}
   [:div
    [:> m/Grid {:container true :spacing 3}
     [:> m/Grid {:item true :xs 12 :sm 12 :lg 12}
      [people]]]]])
