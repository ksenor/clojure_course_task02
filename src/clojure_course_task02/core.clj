(ns clojure-course-task02.core
  (:gen-class)
  (:import java.io.File))

;# получаем все файлы в указанном пути
(defn get-files-by-path-recur [path]
  (pmap #(.getName %) (file-seq (clojure.java.io/file path))))

;# смотрим по регулярке file-name вхождение искомых
(defn find-files [file-name path]
  (let [pat (re-pattern file-name)
        files (get-files-by-path-recur path)]
  (filter #(re-matches pat %) files)))

(defn usage []
  (println "Usage: $ run.sh file_name path"))

(defn -main [file-name path]
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path))))))