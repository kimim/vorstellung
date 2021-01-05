
-- :name create-user! :! :n
-- :doc creates a new user record
INSERT INTO users
(email, first_name, last_name, password)
VALUES (:email, :first_name, :last_name, :password)

-- :name update-user! :! :n
-- :doc updates an existing user record
UPDATE users
SET first_name = :first_name, last_name = :last_name
WHERE id = :email

-- :name get-user :? :1
-- :doc retrieves a user record given the id
SELECT * FROM users
WHERE email = :email

-- :name delete-user! :! :n
-- :doc deletes a user record given the id
DELETE FROM users
WHERE email = :email

-- :name create-dog! :insert :1
-- :doc creates a new dog record
INSERT INTO dogs
(name, color)
VALUES (:name, :color)

-- :name update-dog! :! :n
-- :doc updates an existing dog record
UPDATE dogs
SET name = :name, color = :color
WHERE id = :id

-- :name get-dog :? :1
-- :doc retrieves a dog record given the id
SELECT * FROM dogs
WHERE id  = :id

-- :name delete-dog! :! :n
-- :doc deletes a dog record given the id
DELETE FROM dogs
WHERE id = :id

-- :name all-dogs :? :*
-- :doc get all dogs
SELECT * FROM dogs
