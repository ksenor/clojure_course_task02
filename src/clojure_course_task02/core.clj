(ns clojure-course-task02.core
  (:gen-class)
  (:import java.io.File))

; pre-release
;; pre-pre-release
;;; super-base-primitive-example

;;; (#(re-pattern %) "dsflkjhsfs")
(import '(java.io File))
;; (def files (.listFiles (File. ".")))
;;; (.toString (get files 3))
;; (pmap #(get files %) '(1 2))
; (pmap  #(.toString (get files %)) (take (count files) (iterate inc 0)))

;# получаем все файлы в указанном пути
(defn get-files-by-path [path]
  (pmap #(.getName %) (seq (.listFiles (File. path)))))

;# проверка ненулевых
(defn not-nil? [val]
  (if (nil? val) false
      true))

;# получаем ненулевой распрямленный вектор
(defn nnv [lis]
  (vec (filter not-nil? (flatten lis))))

(defn get-files-tree-recur [start-path]
  (loop [pre-res (nnv (get-files-by-path start-path))
        res []]
    (if (= pre-res res)
      res
      (recur 
       (flatten (conj res (flatten pre-res)))
       ;(pmap get-files-tree-recur res)
       (println res)))))

(defn find-files [file-name path]
  "TODO: Implement searching for a file using his name as a regexp."
  (let [
       files (.listFiles (File. path))
  ])
  (pmap #(.getName %) (seq (.listFiles (File. path)))))

(defn usage []
  (println "Usage: $ run.sh file_name path"))

(defn -main [file-name path]
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path))))))

(-main "a" ".")