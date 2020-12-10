(ns vorstellung.material.upload
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   ["@material-ui/core" :as m]
   [react-dropzone-uploader :default Dropzone]))

(defn uploader []
  (let [message (r/atom "Please choose a file")]
    (fn []
      [:div
       [:> Dropzone
        {:getUploadParams #(clj->js {"url" "/upload"})
         :onChangeStatus #(reset! message %2)
         :maxFiles 1
         :multiple false
         :canCancel false
         :inputContent "Drop a file"}]
       [:> m/Typography @message]])))

(defn page []
  [:> m/Grid {:container true :spacing 3}
   [:> m/Grid {:item true :xs 6 :sm 6 :lg 6}
    [:> m/Paper
     [uploader]]]])
