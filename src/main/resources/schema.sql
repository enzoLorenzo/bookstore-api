drop table if exists authors;
drop table if exists author;
create table author(
                       id long not null auto_increment primary key,
                       first_name varchar(20),
                       surname varchar(50) not null,
                       country varchar(50)
);
drop table if exists book;
drop table if exists books;
create table book(
                     id long not null auto_increment primary key,
                     isbn varchar(13) not null,
                     title varchar not null,
                     price double,
                     currency varchar(10),
                     release_date datetime,
                     author_id long,

                     foreign key (author_id) references author(id)
)