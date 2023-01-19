use movies_db;
-- Mostrar el título y el nombre del género de todas las series.
SELECT s.title, g.name 
FROM series as s
JOIN genres as g ON g.id = s.genre_id;

-- Mostrar el título de los episodios, el nombre y apellido de los actores que trabajan en cada uno de ellos.
SELECT e.title, a.first_name, a.last_name
FROM episodes as e
JOIN actor_episode as ae ON e.id = ae.episode_id 
JOIN actors as a ON ae.actor_id = a.id;


-- Mostrar el título de todas las series y el total de temporadas que tiene cada una de ellas.
 SELECT s.title, COUNT(se.title) as numero_temporadas
 FROM series as s
 JOIN seasons se ON s.id = se.serie_id 
 group by s.title;
 
 -- Mostrar el nombre de todos los géneros y la cantidad total de películas por cada uno, siempre que sea mayor o igual a 3.
 SELECT g.name, COUNT(m.title)
 FROM movies as m
JOIN genres as g ON m.genre_id = g.id
group by g.name
HAVING COUNT(m.title) >3;
 
 -- Mostrar sólo el nombre y apellido de los actores que trabajan en todas las películas de la guerra de las galaxias y que estos no se repitan.
 SELECT DISTINCT a.first_name, a.last_name
 FROM movies as m
 JOIN actor_movie am ON am.movie_id = m.id
 JOIN actors a ON a.id = am.actor_id
 WHERE m.title LIKE "La Guerra de las galaxias%";

 