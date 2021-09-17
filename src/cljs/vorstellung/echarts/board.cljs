(ns vorstellung.echarts.board
  (:require
   [reagent.core :as r]
   ["@material-ui/core" :as m]
   [re-echarts.core :refer [ECharts]]
   [vorstellung.common.utils :as utils]))

(defn distribution []
  [:> ECharts
      {:style {:width "100%" :height "100%"}
       :theme "dark"
       :option
       {:title {:text "Energy Consumption"}
        :dataset {:dimention [:Week :Value]
                  :source [{:Week "Coal" :Value 820} {:Week "Petro" :Value 932} {:Week "Gas" :Value 901}
                           {:Week "New Energy" :Value 934}]}
        :xAxis {:type "category"}
        :yAxis {:type "value"}
        :series [{:type "line"
                  :smooth true}]}}])

(defn capacity []
  [:> ECharts
      {:style {:width "100%" :height "100%"}
       :theme "dark"
       :option
       {:title {:text "Energy Generation"}
        :dataset {:dimention [:Week :Value]
                  :source [{:Week "Mon" :Value 820} {:Week "Tue" :Value 932} {:Week "Wed" :Value 901}
                           {:Week "Thu" :Value 934} {:Week "Fri" :Value 1220} {:Week "Sat" :Value 820}
                           {:Week "Sun" :Value 990}]}
        :xAxis {:type "category"}
        :yAxis {:type "value"}
        :series [{:type "line"
                  :smooth true}]}}])

(defn pie []
  [:> ECharts
   {:style {:width "100%" :height "100%"}
    :theme "dark"
    :option
    {:title {:text "Energy Generation"}
     :series [{:name "面积分布"
               :type :pie
               :radius [50 250]
               :center ["50%" "50%"]
               :roseType :area
               :item-style {:border-radius 8}
               :data [{:value 40 :name "rose 1"}
                      {:value 38 :name "rose 2"}
                      {:value 32 :name "rose 3"}
                      {:value 28 :name "rose 4"}
                      {:value 26 :name "rose 5"}
                      {:value 22 :name "rose 6"}
                      {:value 18 :name "rose 7"}]}]}}])



(defn bar []
  [:> ECharts
   {:style {:width "100%" :height "100%"}
    :theme "dark"
    :option
    {:title {:text "世界人口"
             :subtext "数据来自网络"}
     :tooltip {:trigger :axis
               :axis-pointer {:type :shadow}}
     :legend {:data ["2011年" "2012年"]}
     :grid {:left "3%"
            :right "4%"
            :bottom "3%"
            :contain-label true}
     :x-axis {:type :value
              ::boundary-gap [0 0.01]}
     :y-axis {:type :category
              :data ["巴西", "印尼", "美国", "印度", "中国", "世界人口(万)"]}
     :series [{:name "2011"
               :type :bar
               :data [18203, 23489, 29034, 104970, 131744, 630230]}
              {:name "2012"
               :type :bar
               :data [19325, 23438, 31000, 121594, 134141, 681807]}]}}])

(defn machines []
  [:> ECharts
   {:style {:width "100%" :height "100%"}
    :theme "dark"
    :option
    {:title {:text "火电机组状态"
             :subtext "数据来自网络"}
     :tooltip {:trigger :axis
               :axis-pointer {:type :shadow}}
     :legend {:data ["10万" "30万" "60万"]}
     :grid {:left "3%"
            :right "4%"
            :bottom "3%"
            :contain-label true}
     :x-axis {:type :value
              ::boundary-gap [0 0.01]}
     :y-axis {:type :category
              :data ["运行", "停用", "维修"]}
     :series [{:name ""
               :type :bar
               :data [170 150 50]}
              {:name ""
               :type :bar
               :data [250 200 80]}
              {:name ""
               :type :bar
               :data [375 160 90]}]}}])

(defn generations []
  [:> ECharts
      {:style {:width "100%" :height "100%"}
       :theme "dark"
       :option
       {:title {:text "发电量"}
        :dataset {:dimention [:Factory :Value]
                  :source [{:Factory "电厂1#" :Value 820}
                           {:Factory "电厂2#" :Value 700}
                           {:Factory "电厂3#" :Value 650}
                           {:Factory "电厂4#" :Value 601}
                           {:Factory "电厂5#" :Value 801}
                           {:Factory "电厂6#" :Value 301}
                           {:Factory "电厂76#" :Value 301}
                           {:Factory "电厂8#" :Value 301}
                           {:Factory "电厂9#" :Value 301}
                           {:Factory "电厂10#" :Value 301}]}
        :xAxis {:type "category"}
        :yAxis {:type "value"}
        :series [{:type "bar"
                  :smooth true}]}}])

(defn page []
  [:div {:style {:background-color "#333" :width "100%" :height "100%" :margin 0 :padding 0}}
   [:> m/Grid {:container true :spacing 0 :style {:height "100vh" :width "100vw"}}
    [:> m/Grid {:container true :xs 4 :spacing 1}
     [:> m/Grid {:item true :xs 12 :spacing 0 :style {:height "50vh" :width "100vw"}}
      [machines]]
     [:> m/Grid {:item true :xs 12 :spacing 0 :style {:height "50vh" :width "100vw"}}
      [capacity]]]
    [:> m/Grid {:container true :xs 4 :spacing 1}
     [:> m/Grid {:item true :xs 12 :spacing 0 :style {:height "25vh" :width "100vw"}}
      [capacity]]
     [:> m/Grid {:item true :xs 12 :spacing 0 :style {:height "50vh" :width "100vw"}}
      [capacity]]
     [:> m/Grid {:item true :xs 12 :spacing 0 :style {:height "25vh" :width "100vw"}}
      [generations]]]
    [:> m/Grid {:container true :xs 4 :spacing 1}
     [:> m/Grid {:item true :xs 12 :spacing 0 :style {:height "50vh" :width "100vw"}}
      [pie]]
     [:> m/Grid {:item true :xs 12 :spacing 0 :style {:height "50vh" :width "100vw"}}
      [bar]]]]])

(defn tool []
  [:div])
