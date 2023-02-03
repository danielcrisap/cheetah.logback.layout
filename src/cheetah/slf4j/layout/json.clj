(ns cheetah.slf4j.layout.json
  (:require [clojure.data.json :as json])
  (:import [clojure.lang PersistentArrayMap]))

(defrecord LogEvent [logger-name level message exception mdc timestamp args])

(defn layout [event]
  (let [log-event (LogEvent.
                   (:logger-name event)
                   (:level event)
                   (:message event)
                   (:exception event)
                   (:mdc event)
                   (:timestamp event)
                   (:args event))]
    (json/write-str log-event)))

(defn log-event [logger-name level message exception mdc timestamp args]
  (layout (PersistentArrayMap/create
           {:logger-name logger-name
            :level level
            :message message
            :exception exception
            :mdc mdc
            :timestamp timestamp
            :args args})))
