(ns cal-test.core
  (:require [reagent.core :as reagent :refer [atom]]
            ["moment" :as moment]))

(defn check-false-on-month [date]
  "conditionally checks if the month has two digits and returns sub if so - used so dates can be written 04 or 4 for month and still resolve to true"
  (if (= (count (str date)) 1)
    date
    (str (subs (str date) 1))))

(defn get-current-month-days [currentMonth]
  (.daysInMonth (moment currentMonth "MM") "YYYY-MM"))

(defn get-day-display [offsetAmount numberOfDays currentCount]
  "Determines what to display for a particular table cell"
  (if (<= currentCount offsetAmount)
    ""
    (if (> currentCount (+ offsetAmount numberOfDays))
    ""
    (- currentCount offsetAmount))))

(defn generate-table-row [offsetAmount numberOfDays i currentMonth currentYear date-values]
  "Generates the table HTML"
  (loop [x 1
         row [:tr]]
        (if (= x 8)
          row
          (do
              (if (or ( some #{(str currentMonth "/" (- (+ i x) offsetAmount) "/" currentYear)} date-values)
                  ( some #{(str (check-false-on-month currentMonth) "/" (- (+ i x) offsetAmount) "/" currentYear)} date-values))
              (recur (inc x) (conj row [:td.active (get-day-display offsetAmount numberOfDays (+ i x))]))
              (recur (inc x) (conj row [:td (get-day-display offsetAmount numberOfDays (+ i x))]))))
          )))

(defn generate-table-html [numberOfDays currentMonth currentYear date-values]
  (let [offsetAmount (.day (.startOf (moment (str currentMonth "/" currentYear) "MM/YYYY") "month"))
        loopTotal (+ offsetAmount numberOfDays)]
    (loop [i 0
          html [:tbody]]
          (if (>= i loopTotal)
            html ; Our end condition and output
          (recur (+ i 7) (conj html (generate-table-row offsetAmount numberOfDays i currentMonth currentYear date-values)))))))

(defn increment-year [currentYear]
  (inc (js/parseInt currentYear)))

(defn deincrement-year [currentYear]
  (- (js/parseInt currentYear) 1))

(defn increment-month [current currentYear monthDays]
  (if (= current 12)
    (do
      (reset! monthDays (get-current-month-days 1))
      (swap! currentYear (fn [current] (increment-year current)))
      1)
    (do
      (reset! monthDays (get-current-month-days (inc (js/parseInt current))))
      (inc (js/parseInt current)))))

(defn deincrement-month [current currentYear monthDays]
  (if (= current 1)
    (do
      (reset! monthDays (get-current-month-days 12))
      (swap! currentYear (fn [current] (deincrement-year current)))
      12)
    (do
      (reset! monthDays (get-current-month-days (- (js/parseInt current) 1)))
      (- (js/parseInt current) 1))))

(defn render [dates]
  (let [currentMonth (atom (.format (moment) "MM"))
        currentYear (atom (.format (moment) "YYYY"))
        monthDays (atom (get-current-month-days @currentMonth))]
    (fn []
    [:div.Calendar
      [:div.Calendar-Header
        [:p.Calendar-arrow {:on-click #(swap! currentMonth (fn [current currentYear] (deincrement-month current currentYear monthDays)) currentYear monthDays)} "<-"]
        [:p.Calendar-Title (str (.format (moment @currentMonth "MM") "MMMM") " " @currentYear)]
        [:p.Calendar-arrow {:on-click #(swap! currentMonth (fn [current currentYear] (increment-month current currentYear monthDays)) currentYear monthDays)} "->"]]
      [:table.Calendar-wrapper
        [:tr
          [:th "Sun"]
          [:th "Mon"]
          [:th "Tue"]
          [:th "Wed"]
          [:th "Thur"]
          [:th "Fri"]
          [:th "Sat"]]
          (generate-table-html @monthDays @currentMonth @currentYear @dates)]])))
