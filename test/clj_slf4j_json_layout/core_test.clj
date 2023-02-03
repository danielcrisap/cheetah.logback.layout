(ns cheetah.logback.layout.core-test
  (:require [cheetah.logback.layout.json :refer :all]
            [clojure.test :refer [deftest testing is]])
  (:import [ch.qos.logback.classic Level]
           [ch.qos.logback.classic.spi LoggingEvent]
           [org.slf4j LoggerFactory]))

(deftest test-json-layout-with-message-only
  (testing "json-layout function correctly transforms a log event with only a message into a JSON string."
    (let [log-event       {:timestamp 1
                           :level     :debug
                           :logger    "logger-name"
                           :message   "message"}
          expected-result "{\"timestamp\":1,\"level\":\"debug\",\"logger\":\"logger-name\",\"message\":\"message\"}"]
      (is (= (json-layout log-event) expected-result)))))

(defn generate-log-event ^LoggingEvent
  []
  (LoggingEvent. "the.logger"
                 (LoggerFactory/getLogger "the.Logger")
                 Level/ERROR
                 "Test Message"
                 nil
                 (into-array [:a :b :c :d])))

(deftest test-json-layout-includes-logger-level-message-and-timestamp
  (let [log-event   (generate-log-event)
        json-string (json-layout log-event)]
    (is (not (nil? json-string)))
    (is (re-find #"loggerName.*Test Logger" json-string))
    (is (re-find #"level.*ERROR" json-string))
    (is (re-find #"message.*Test Message" json-string))
    (is (re-find #"timestamp.*\d{13}" json-string))))
