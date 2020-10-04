(ns vorstellung.login.page
  (:require [reagent.core :as r]
            ["@material-ui/core" :as m]
            ["@material-ui/icons" :refer [LockOutlined]]))

(defn signin []
  [:> m/Container {:component "main" :maxWidth "xs"}
   [:div {:style {:display "flex" :flexDirection "column"
                  :alignItems "center" :marginTop 120}}
    [:> m/Avatar {:style {:backgroundColor "red" :margin 20}}
     [:> LockOutlined]]
    [:> m/Typography {:component "h1" :variant "h5"} "Sign in"]
    [:form
     [:> m/TextField
      {:variant "outlined" :margin "normal" :required true
       :fullWidth true :id "email" :label "Email Address"
       :name "email" :autoComplete "email" :autoFocus true}]
     [:> m/TextField
      {:variant "outlined" :margin "normal" :required true
       :fullWidth true :id "password" :label "Password"
       :name "password" :type "password"
       :autoComplete "current-password"}]
     [:> m/FormControlLabel
      {:control (r/as-element
                 [:> m/Checkbox {:value "remember"
                                 :color "primary"}])
       :label "Remember me"}]
     [:> m/Button
      {:type "submit" :fullWidth true :variant "contained"
       :color "primary"}
      "Sign in"]
     [:> m/Grid {:container true}
      [:> m/Grid {:item true :xs true}
       [:> m/Link {:href "#/" :variant "body2"}
        "Forgot password?"]]
      [:> m/Grid {:item true}
       [:> m/Link {:href "#/signup" :variant "body2"}
        "Don't have an account? Sign up"]]]]]])

(defn signup []
  [:> m/Container {:component "main" :maxWidth "xs"}
   [:div {:style {:display "flex" :flexDirection "column"
                  :alignItems "center" :marginTop 120}}
    [:> m/Avatar {:style {:backgroundColor "red" :margin 20}}
     [:> LockOutlined]]
    [:> m/Typography {:component "h1" :variant "h5"} "Sign up"]
    [:form {:noValidate true}
     [:> m/Grid {:container true :spacing 2}
      [:> m/Grid {:item true :xs 12 :sm 6}
       [:> m/TextField
        {:variant "outlined" :margin "normal" :required true
         :fullWidth true :id "first-name" :label "First Name"
         :name "first-name" :autoComplete "fname" :autoFocus true}]]
      [:> m/Grid {:item true :xs 12 :sm 6}
       [:> m/TextField
        {:variant "outlined" :margin "normal" :required true
         :fullWidth true :id "last-name" :label "Last Name"
         :name "last-name" :autoComplete "lname"}]]
      [:> m/Grid {:item true :xs 12}
       [:> m/TextField
        {:variant "outlined" :margin "normal" :required true
         :fullWidth true :id "email" :label "Email Address"
         :name "email" :autoComplete "email"}]]
      [:> m/Grid {:item true :xs 12}
       [:> m/TextField
        {:variant "outlined" :margin "normal" :required true
         :fullWidth true :id "password" :label "Password"
         :name "password" :autoComplete "current-password"}]]]
     [:> m/Grid {:item true :xs 12}
      [:> m/FormControlLabel
       {:control (r/as-element
                  [:> m/Checkbox {:value "allow-extra-emails"
                                  :color "primary"}])
        :label "I want to receive inspiration, marketing promotions and updates via email."}]]
     [:> m/Button {:type "submit" :fullWidth true :variant "contained"
                   :color "primary"}
      "Sign up"]
     [:> m/Grid {:container true}
      [:> m/Grid {:item true}
       [:> m/Link {:href "#/" :variant "body2"}
        "Already have an account? Sign in"]]]]]])
