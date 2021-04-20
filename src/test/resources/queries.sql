SELECT META(`products`).id AS __id, META(`products`).cas AS __cas, `products`.* FROM `products` LIMIT $1 OFFSET $2
SELECT META(`products`).id AS __id, META(`products`).cas AS __cas, `products`.* FROM `products` LIMIT $1 OFFSET $2
SELECT META(`products`).id AS __id, META(`products`).cas AS __cas, `products`.* FROM `products` where name = $1 LIMIT $2 OFFSET $3
SELECT META(`products`).id AS __id, META(`products`).cas AS __cas, `products`.* FROM `products` where size = $1 LIMIT $2 OFFSET $3