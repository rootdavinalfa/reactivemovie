-- liquibase formatted sql

-- changeset davin:1655048355554-1
CREATE TABLE "public"."movie"
(
    "id"         INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    "movie_name" VARCHAR(255),
    "synopsis"   TEXT,
    "publisher"  VARCHAR(255),
    "cover"      VARCHAR(600),
    "source"     VARCHAR(600)
);

-- changeset davin:2022-06-12-1
create table comments
(
    id              serial,
    parent          int,
    movie_id        int,
    comment_by      varchar(255),
    comment_content text
);

-- changeset davin:2022-06-12-1-value
INSERT INTO movie(movie_name, synopsis, publisher, cover, source)
VALUES ('Titanic',
        'Seventeen-year-old Rose hails from an aristocratic family and is set to be married. When she boards the Titanic, she meets Jack Dawson, an artist, and falls in love with him.',
        'N/A', null, null);

-- changeset davin:2022-06-12-2-value
INSERT INTO movie(movie_name, synopsis, publisher, cover, source)
VALUES ('Fantastic Four',
        'Fantastic Four (sometimes stylized as Fantastic 4) is a 2005 American superhero film based on the Marvel Comics team of the same name. It was directed by Tim Story, and released by 20th Century Fox. The film stars Ioan Gruffudd, Jessica Alba, Chris Evans, Michael Chiklis, Julian McMahon and Kerry Washington.',
        'N/A', null, null);

-- changeset davin:2022-06-12-3-value
INSERT INTO comments(parent, movie_id, comment_by, comment_content)
VALUES (null, 1, 'Admin', 'This is a comment');
INSERT INTO comments(parent, movie_id, comment_by, comment_content)
VALUES (null, 1, 'Sera', 'This is a comment');
INSERT INTO comments(parent, movie_id, comment_by, comment_content)
VALUES (null, 1, 'Singh', 'This is a comment');
INSERT INTO comments(parent, movie_id, comment_by, comment_content)
VALUES (null, 2, 'Admin', 'This is a comment');
INSERT INTO comments(parent, movie_id, comment_by, comment_content)
VALUES (null, 2, 'Kuch', 'This is a comment');

-- changeset davin:2022-06-13-1-idx
create unique index movie_id_uindex
    on movie (id);

create index movie_attr_uindex
    on movie (movie_name, publisher);

create unique index comments_id_uindex
    on comments (id);

create index comments_movie_id_index
    on comments (movie_id);

alter table comments
    add constraint comments_movie_id_fk
        foreign key (movie_id) references movie (id);
