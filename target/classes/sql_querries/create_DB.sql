create schema library;


create table authors (
	authorId int auto_increment primary key,
    authorName varchar(50),
    authorSurname varchar(50)
);


create table books(
	bookId int auto_increment primary key,
    bookTitle varchar(50),
    bookDescription varchar(255),
    authorId int, 
    foreign key (authorId) references authors(authorId)
);



create table reviews(
	reviewId int auto_increment primary key,
    bookScore int,
    reviewComment varchar(255),
	bookId int, 
    foreign key (bookId) references books(bookId)
);


