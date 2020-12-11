(ns vorstellung.material.picker
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   ["@material-ui/core" :as m]
   ["@material-ui/pickers" :as p]
   ["@date-io/date-fns" :as DateFnsUtils]))


(defn native-picker []
  [:<>
   [:> m/Typography {:variant "h5" :color "primary"} "Let's pick a time"]
   [:div {:style {:padding "24px 24px 24px 24px"}}
    [:> m/Grid {:container true :spacing 1 :justify "space-around"}
     [:form {:style {:padding "5px 5px 5px 5px"}}
      [:> m/TextField
       {:id "date"
        :label "Holiday"
        :type "date"
        :default-value "2012-12-12"
        :InputLabelProps {:shrink true}}]]
     [:form {:style {:padding "5px 5px 5px 5px"}}
      [:> m/TextField
       {:id "datetime-local"
        :label "Appointment"
        :type "datetime-local"
        :default-value "2020-12-12T12:12"
        :InputLabelProps {:shrink true}}]]
     [:form {:style {:padding "5px 5px 5px 5px"}}
      [:> m/TextField
       {:id "time"
        :label "Alarm"
        :type "time"
        :default-value "07:00"
        ;; +5*n min is valid
        :inputProps {:step 300}
        ;; shrink the label to up
        :InputLabelProps {:shrink true}}]]]]])

(defn mui-picker []
  (r/with-let [selected (r/atom (js/Date. "2014-08-18T21:11:54"))]
    [:<>
     [:> m/Typography {:variant "h5" :color "primary"} "Let's pick a time"]
     [:div {:style {:padding "24px 24px 24px 24px"}}
      [:> p/MuiPickersUtilsProvider {:utils DateFnsUtils}
       [:> m/Grid {:container true :spacing 1 :justify "space-around"}
        [:> p/KeyboardDatePicker
         {:disableToolbar true
          :variant "inline"
          :format "MM/dd/yyyy"
          :margin "normal"
          :id "date-picker-inline"
          :label "Data picker inline"
          :value @selected
          :on-change #(reset! selected %)
          :KeyboardButtonProps {:aria-label "change date"}}]
        [:> p/KeyboardTimePicker
         {:disableToolbar true
          :variant "inline"
          :margin "normal"
          :id "time-picker"
          :label "Time picker"
          :disablePast true
          :value @selected
          :on-change #(reset! selected %)
          :KeyboardButtonProps {:aria-label "change time"}}]
        [:> p/KeyboardDateTimePicker
         {:variant "inline"
          :format "MM/dd/yyyy HH:mm"
          :margin "normal"
          :id "date-picker-dialog"
          :label "Date picker dialog"
          :value @selected
          :on-change #(reset! selected %)
          :KeyboardButtonProps {:aria-label "change date and time"}}]]]]]))

(defn page []
  [:> m/Container {:max-width "lg" :disableGutters true}
   [:> m/Grid {:container true :spacing 3}
    [:> m/Grid {:item true :xs 12 :sm 12 :lg 12}
     [:> m/Paper
      [native-picker]]]
    [:> m/Grid {:item true :xs 12 :sm 12 :lg 12}
     [:> m/Paper
      [mui-picker]]]]])
